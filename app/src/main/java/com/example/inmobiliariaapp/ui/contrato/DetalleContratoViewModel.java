package com.example.inmobiliariaapp.ui.contrato;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.inmobiliariaapp.R;
import com.example.inmobiliariaapp.models.Contrato;
import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.request.ApiClient;

public class DetalleContratoViewModel extends ViewModel {


    private ApiClient apiClient;
    private MutableLiveData<Contrato> contrato;

    public DetalleContratoViewModel() {
        apiClient = ApiClient.getApi();
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

        Contrato contrato = apiClient.obtenerContratoVigente(inmueble);
        this.contrato.setValue(contrato);
    }

    public void verPagos(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("contrato", contrato.getValue());
        Navigation.findNavController(v).navigate(R.id.nav_pago, bundle);
    }


}
