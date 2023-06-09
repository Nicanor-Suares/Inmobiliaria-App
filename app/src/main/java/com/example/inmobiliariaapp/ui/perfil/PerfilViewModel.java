package com.example.inmobiliariaapp.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.tv.interactive.AppLinkInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.MainActivity;
import com.example.inmobiliariaapp.models.Propietario;
import com.example.inmobiliariaapp.request.ApiClientRetroFit;
import com.example.inmobiliariaapp.request.EndpointInmobiliaria;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Propietario> propietario;
    private MutableLiveData<Boolean> edicion;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        propietario = new MutableLiveData<>();
        obtenerPropietario();
    }

    public LiveData<Propietario> getPropietario() {
        return propietario;
    }

    public LiveData<Boolean> getEdicion() {
        if(edicion == null){
            edicion = new MutableLiveData<>();
            edicion.setValue(false);
        }
        return edicion;
    }

    public void cambiarEstado(){
        edicion.setValue(!edicion.getValue());
    }

    public void obtenerPropietario() {
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        if(token.isEmpty()) {
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            return;
        }

        EndpointInmobiliaria endpoint = ApiClientRetroFit.getEndpoint();
        Call<Propietario> call = endpoint.getPerfil(token);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(@NonNull Call<Propietario> call, @NonNull Response<Propietario> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        propietario.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Error al obtener el usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public void editarPropietario(Long dni, String nombre, String apellido, String email, String password ,String telefono){

        if (dni == null || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        Propietario propietarioEdit = new Propietario(Objects.requireNonNull(propietario.getValue().getId()), dni, nombre, apellido, email, password, telefono, propietario.getValue().getAvatar());

        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);

        String token = sp.getString("token", "");

        if(token.isEmpty()){
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            return;
        }

        EndpointInmobiliaria endpoint = ApiClientRetroFit.getEndpoint();
        Call<Propietario> call = endpoint.editarPerfil(token, propietarioEdit);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(@NonNull Call<Propietario> call, @NonNull Response<Propietario> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        propietario.setValue(propietarioEdit);
                        Toast.makeText(context, "Propietario editado correctamente", Toast.LENGTH_SHORT).show();
                        cambiarEstado();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Propietario> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener usuario", Toast.LENGTH_SHORT).show();
            }
        });


        propietario.setValue(propietarioEdit);
    }

}