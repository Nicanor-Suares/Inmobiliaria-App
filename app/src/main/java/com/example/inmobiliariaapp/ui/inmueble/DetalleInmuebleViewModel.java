package com.example.inmobiliariaapp.ui.inmueble;

import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.request.ApiClient;

public class DetalleInmuebleViewModel extends ViewModel {

    private ApiClient apiClient;
    private MutableLiveData<Inmueble> inmueble;

    public DetalleInmuebleViewModel() {
        apiClient = ApiClient.getApi();
        inmueble = new MutableLiveData<>();
    }

    public LiveData<Inmueble> getInmueble() {
        return inmueble;
    }

    public void obtenerInmuebles(Bundle bundle) {
        if(bundle == null) {
            inmueble.setValue(null);
            return;
        }
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        if(inmueble != null){
            this.inmueble.setValue(inmueble);
        }
    }
}
