package com.example.inmobiliariaapp.ui.inquilino;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.request.ApiClient;

import java.util.ArrayList;

public class InquilinoViewModel extends ViewModel {

    private ApiClient apiClient;
    private MutableLiveData<ArrayList<Inmueble>> inmuebles;

    public InquilinoViewModel() {
        apiClient = ApiClient.getApi();
        inmuebles = new MutableLiveData<>();
        obtenerInmuebles();
    }

    public LiveData<ArrayList<Inmueble>> getInmuebles() {return inmuebles;}

    private void obtenerInmuebles() {inmuebles.setValue(apiClient.obtenerPropiedadesAlquiladas());}

}
