package com.example.inmobiliariaapp.ui.inquilino;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.inmobiliariaapp.MainActivity;
import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.models.Inquilino;
import com.example.inmobiliariaapp.request.ApiClientRetroFit;
import com.example.inmobiliariaapp.request.EndpointInmobiliaria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalleInquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquilino;
    private Context context;

    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        inquilino = new MutableLiveData<>();
    }

    public LiveData<Inquilino> getInquilino() {
        return inquilino;
    }

    public void obtenerInquilino(Bundle bundle) {
        if (bundle == null) {
            this.inquilino.setValue(null);
            return;
        }
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        if (inmueble == null){
            return;
        }

        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        String token = sp.getString("token", "");

        if(token.isEmpty()){
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            return;
        }

        EndpointInmobiliaria endpoint = ApiClientRetroFit.getEndpoint();
        Call<Inquilino> call = endpoint.obtenerInquilinoPorInmueble(token, inmueble.getIdInmueble());

        call.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        inquilino.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Toast.makeText(context, "Error al obtener inquilino", Toast.LENGTH_SHORT).show();
                Log.d("ERROR DETALLE INQUILINO", t.getMessage());
            }
        });

    }

}
