package com.example.inmobiliariaapp.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.media.tv.interactive.AppLinkInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariaapp.models.Propietario;
import com.example.inmobiliariaapp.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Propietario> propietario;
    private MutableLiveData<Boolean> edicion;
    ApiClient apiClient;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        apiClient = ApiClient.getApi();
        propietario = new MutableLiveData<>();
        obtenerPropietario();
    }

    public LiveData<Propietario> getPropietario() {
        return propietario;
    }

    public LiveData<Boolean> getEdicion() {
        if(edicion == null){
            edicion = new MutableLiveData<>();
            edicion.setValue(false);
        }
        return edicion;
    }

    public void cambiarEstado(){
        edicion.setValue(!edicion.getValue());
    }

    public void obtenerPropietario() {
        Propietario propietarioActual = apiClient.obtenerUsuarioActual();
        if(propietarioActual == null){
            Toast.makeText(context, "Error al obtener el propietario", Toast.LENGTH_SHORT).show();
            return;
        }
        propietario.setValue(propietarioActual);
    }

    public void editarPropietario(Long dni, String nombre, String apellido, String email, String password ,String telefono){
        Propietario propietarioEdit = new Propietario(propietario.getValue().getId(), dni, nombre, apellido, email, password, telefono, propietario.getValue().getAvatar());
        apiClient.actualizarPerfil(propietarioEdit);
        propietario.setValue(propietarioEdit);
    }

}