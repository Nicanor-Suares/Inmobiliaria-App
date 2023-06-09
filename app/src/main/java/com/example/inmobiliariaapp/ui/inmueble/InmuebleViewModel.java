package com.example.inmobiliariaapp.ui.inmueble;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.MainActivity;
import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.request.ApiClient;
import com.example.inmobiliariaapp.request.ApiClientRetroFit;
import com.example.inmobiliariaapp.request.EndpointInmobiliaria;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;
    private Context context;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();

        inmuebles = new MutableLiveData<ArrayList<Inmueble>>();
        obtenerInmuebles();
    }

    public LiveData
    <ArrayList<Inmueble>> getInmuebles() {
        return inmuebles;
    }
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
        Call<List<Inmueble>> call = endpoint.obtenerInmuebles(token);

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
                Toast.makeText(context, "Error al obtener inmuebles", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
