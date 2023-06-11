package com.example.inmobiliariaapp.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariaapp.MainActivity;
import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.request.ApiClientRetroFit;
import com.example.inmobiliariaapp.request.EndpointInmobiliaria;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inmuebles = new MutableLiveData<>();
        obtenerInmuebles();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {return inmuebles;}

    private void obtenerInmuebles() {

        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        if(token.isEmpty()){
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            return;
        }

        EndpointInmobiliaria endpoint = ApiClientRetroFit.getEndpoint();
        Call<List<Inmueble>> call = endpoint.obtenerInmueblesAlquilados(token);

        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        inmuebles.setValue((ArrayList<Inmueble>) response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Error al obtener el inquilino", Toast.LENGTH_SHORT).show();
                Log.d("ERROR INQUILINO", t.getMessage());
            }
        });

    }

}
