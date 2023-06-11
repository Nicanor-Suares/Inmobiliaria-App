package com.example.inmobiliariaapp.ui.contrato;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.inmobiliariaapp.MainActivity;
import com.example.inmobiliariaapp.R;
import com.example.inmobiliariaapp.models.Contrato;
import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.request.ApiClient;
import com.example.inmobiliariaapp.request.ApiClientRetroFit;
import com.example.inmobiliariaapp.request.EndpointInmobiliaria;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> contrato;
    private Context context;

    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        contrato = new MutableLiveData<>();
    }

    public LiveData<Contrato> getContrato() { return contrato; }

    public void obtenerContrato(Bundle bundle) {
        if (bundle == null) {
            this.contrato.setValue(null);
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
        Call<Contrato> call = endpoint.obtenerContratoPorInmueble(token, inmueble.getIdInmueble());

        call.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if (response.isSuccessful()) {
                    if(response.body() == null){
                        contrato.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Toast.makeText(context, "Error al obtener contrato", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void verPagos(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("contrato", contrato.getValue());
        Navigation.findNavController(v).navigate(R.id.nav_pago, bundle);
    }

}
