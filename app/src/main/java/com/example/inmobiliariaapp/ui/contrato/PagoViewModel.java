package com.example.inmobiliariaapp.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariaapp.MainActivity;
import com.example.inmobiliariaapp.models.Contrato;
import com.example.inmobiliariaapp.models.Pago;
import com.example.inmobiliariaapp.request.ApiClient;
import com.example.inmobiliariaapp.request.ApiClientRetroFit;
import com.example.inmobiliariaapp.request.EndpointInmobiliaria;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Pago>> pagos;
    private Context context;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        pagos = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Pago>> getPagos() { return pagos; }

    public void obtenerPagos(Bundle bundle) {
        if (bundle == null) {
            return;
        }

        Contrato contrato = (Contrato) bundle.getSerializable("contrato");
        if (contrato == null){
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
        Call<List<Pago>> call = endpoint.obtenerPagosPorContrato(token, contrato.getIdContrato());

        call.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        pagos.setValue((ArrayList<Pago>) response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context, "Error al obtener pagos", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
