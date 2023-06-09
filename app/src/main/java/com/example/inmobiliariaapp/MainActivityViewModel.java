package com.example.inmobiliariaapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariaapp.models.Propietario;
import com.example.inmobiliariaapp.models.Usuario;
import com.example.inmobiliariaapp.request.ApiClientRetroFit;
import com.example.inmobiliariaapp.request.EndpointInmobiliaria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Boolean> permisoLlamada;
    private LeeSensor leeSensor;
    protected static final int CALL_PERMISSION_REQUEST_CODE = 123;
    private static final String NRO_INMOBILIARIA = "2665241722";

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        permisoLlamada = new MutableLiveData<>();
        verificarPermisosDeLlamada();
        leeSensor = new LeeSensor(context);
        registerListener();
    }

    public LiveData<Boolean> getPermisoLlamada() {
        return permisoLlamada;
    }

    public void verificarPermisosDeLlamada() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            permisoLlamada.setValue(false);
            return;
        }
        permisoLlamada.setValue(true);
    }


    public void registerListener() {
        if (leeSensor == null) return;
        leeSensor.registerListener();
    }

    public void unregisterListener() {
        if (leeSensor == null) return;
        leeSensor.unregisterListener();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        unregisterListener();
    }

    private class LeeSensor implements SensorEventListener {

        private SensorManager sensorManager;
        private Sensor accelerometer;
        private float acceleration, currentAcceleration, lastAcceleration;

        private static final float SHAKE_THRESHOLD = 10.0f;

        public LeeSensor(Context context) {
            this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            acceleration = 0.0f;
            currentAcceleration = SensorManager.GRAVITY_EARTH;
            lastAcceleration = SensorManager.GRAVITY_EARTH;
        }

        public void registerListener() {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        public void unregisterListener() {
            sensorManager.unregisterListener(this);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            lastAcceleration = currentAcceleration;
            currentAcceleration = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = currentAcceleration - lastAcceleration;
            acceleration = acceleration * 0.9f + delta;

            if (acceleration > SHAKE_THRESHOLD) {
                MainActivityViewModel.this.llamar();
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }

    private void llamar() {
        if (Boolean.FALSE.equals(permisoLlamada.getValue())) {
            verificarPermisosDeLlamada();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + NRO_INMOBILIARIA));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void login(String email, String password) {

        Log.d("LOGIN", email + " " + password);

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(context, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
            return;
        }

       Usuario userPropietario = new Usuario(email, password);
        EndpointInmobiliaria endpoint = ApiClientRetroFit.getEndpoint();
        Call<String> call = endpoint.login(userPropietario);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("token", "Bearer " + response.body());
                        editor.commit();

                        Intent intent = new Intent(context, NavigationActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    } else{
                        if(response.code() == 400){
                            Toast.makeText(context, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Error en el login", Toast.LENGTH_SHORT).show();
                Log.e("RESPUESTA LOGIN", "Login request failed", t);
            }
        });
    }


    public void verificarSesion(){
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        if(token.isEmpty()){
            return;
        }

        EndpointInmobiliaria endpoint = ApiClientRetroFit.getEndpoint();
        Call<Propietario> call = endpoint.getPerfil(token);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(@NonNull Call<Propietario> call, @NonNull Response<Propietario> response) {
                if(response.isSuccessful() && response.body() != null){
                    Intent intent = new Intent(context, NavigationActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Propietario> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al verificar sesion", Toast.LENGTH_SHORT).show();
                Log.d("RESPUESTA VERIFICAR SESION", t.getMessage());
            }
        });

    }

}
