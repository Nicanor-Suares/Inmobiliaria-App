package com.example.inmobiliariaapp.ui.inquilino;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.models.Inquilino;
import com.example.inmobiliariaapp.request.ApiClient;

public class DetalleInquilinoViewModel extends ViewModel {
    private ApiClient apiClient;
    private MutableLiveData<Inquilino> inquilino;

    public DetalleInquilinoViewModel() {
        apiClient = ApiClient.getApi();
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
        Inquilino inquilino = apiClient.obtenerInquilino(inmueble);
        this.inquilino.setValue(inquilino);
    }

}
