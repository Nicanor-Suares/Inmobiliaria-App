package com.example.inmobiliariaapp.ui.contrato;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends ViewModel {


    private ApiClient apiClient;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;

    public ContratoViewModel() {
        apiClient = ApiClient.getApi();
        inmuebles = new MutableLiveData<>();
        obtenerInmuebles();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() { return inmuebles; }

    private void obtenerInmuebles() {
        ArrayList<Inmueble> propiedades = apiClient.obtenerPropiedadesAlquiladas();
        inmuebles.setValue(propiedades);
    }
}
