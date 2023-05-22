package com.example.inmobiliariaapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmobiliariaapp.models.Propietario;
import com.example.inmobiliariaapp.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void login(String email, String password) {

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(context, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
            return;
        }
       Propietario propietario = ApiClient.getApi().login(email, password);

        if(propietario == null){
            Toast.makeText(context, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(context, NavigationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
