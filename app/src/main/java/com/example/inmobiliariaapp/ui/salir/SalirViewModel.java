package com.example.inmobiliariaapp.ui.salir;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmobiliariaapp.MainActivity;

public class SalirViewModel extends AndroidViewModel {

    private Context context;

    public SalirViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void logout() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}
