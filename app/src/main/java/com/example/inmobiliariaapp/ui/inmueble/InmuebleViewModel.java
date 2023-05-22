package com.example.inmobiliariaapp.ui.inmueble;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.request.ApiClient;

import java.util.ArrayList;

public class InmuebleViewModel extends ViewModel {

    private ApiClient apiClient;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;

    public InmuebleViewModel() {
        apiClient = ApiClient.getApi();
        inmuebles = new MutableLiveData<ArrayList<Inmueble>>();
        obtenerInmuebles();
    }

    public LiveData
    <ArrayList<Inmueble>> getInmuebles() {
        return inmuebles;
    }
    private void obtenerInmuebles() {
        inmuebles.setValue(apiClient.obtnerPropiedades());
    }
}
