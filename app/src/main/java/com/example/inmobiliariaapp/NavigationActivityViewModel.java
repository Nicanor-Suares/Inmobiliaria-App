package com.example.inmobiliariaapp;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariaapp.models.Propietario;
import com.example.inmobiliariaapp.request.ApiClient;

public class NavigationActivityViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient api;
    MutableLiveData<Propietario> propietarioMutableLiveData = new MutableLiveData<>();

    public NavigationActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        api = ApiClient.getApi();

        obtenerPropietario();
    }

    public LiveData<Propietario> getPropietario() {
        return propietarioMutableLiveData;
    }

    public void obtenerPropietario() {
        Propietario propietario = api.obtenerUsuarioActual();
        if(propietario == null){
            Toast.makeText(context, "Error al obtener propietario", Toast.LENGTH_SHORT).show();
            return;
        }
        propietarioMutableLiveData.setValue(propietario);
    }
}
