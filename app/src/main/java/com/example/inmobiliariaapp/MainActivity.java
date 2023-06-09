package com.example.inmobiliariaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import android.Manifest;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inmobiliariaapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainActivityViewModel.class);

        vm.verificarSesion();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.login(binding.etEmail.getText().toString(), binding.etPassword.getText().toString());
            }
        });

        vm.getPermisoLlamada().observe(this, permisoLlamada -> {
            if (!permisoLlamada) {
                pedirPermisos();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        vm.registerListener();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vm.unregisterListener();
    }

    private void pedirPermisos() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MainActivityViewModel.CALL_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MainActivityViewModel.CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                vm.verificarPermisosDeLlamada();
            } else {
                Toast.makeText(this, "Permiso rechazado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}