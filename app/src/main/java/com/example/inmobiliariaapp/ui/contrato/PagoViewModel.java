package com.example.inmobiliariaapp.ui.contrato;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.models.Contrato;
import com.example.inmobiliariaapp.models.Pago;
import com.example.inmobiliariaapp.request.ApiClient;

import java.util.ArrayList;

public class PagoViewModel extends ViewModel {

    private ApiClient apiClient;
    private MutableLiveData<ArrayList<Pago>> pagos;

    public PagoViewModel() {
        apiClient = ApiClient.getApi();
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

        ArrayList<Pago> pagos = apiClient.obtenerPagos(contrato);
        this.pagos.setValue(pagos);
    }


}
