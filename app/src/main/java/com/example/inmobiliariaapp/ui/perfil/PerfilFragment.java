package com.example.inmobiliariaapp.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliariaapp.databinding.FragmentPerfilBinding;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);

        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(PerfilViewModel.class);

        vm.getPropietario().observe(getViewLifecycleOwner(), propietario -> {
            if(propietario != null) {
                binding.etCodigo.setText(String.valueOf(propietario.getId()));
                binding.etDni.setText(String.valueOf((propietario.getDni())));
                binding.etNombre.setText(propietario.getNombre());
                binding.etApellido.setText(propietario.getApellido());
                binding.etEmail.setText(propietario.getEmail());
                binding.etPassword.setText(String.valueOf(propietario.getPassword()));
                binding.etTelefono.setText(propietario.getTelefono());
            }
        });

        vm.getEdicion().observe(getViewLifecycleOwner(), edicion -> {
            binding.etDni.setEnabled(edicion);
            binding.etNombre.setEnabled(edicion);
            binding.etApellido.setEnabled(edicion);
            binding.etEmail.setEnabled(edicion);
            binding.etPassword.setEnabled(edicion);
            binding.etTelefono.setEnabled(edicion);
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.btnSave.getText().equals("Editar")){
                    binding.btnSave.setText("Guardar");
                    vm.cambiarEstado();
                } else {
                    vm.editarPropietario(Long.parseLong(binding.etDni.getText().toString()), binding.etNombre.getText().toString(), binding.etApellido.getText().toString(), binding.etEmail.getText().toString(), binding.etPassword.getText().toString() , binding.etTelefono.getText().toString());
                    binding.btnSave.setText("Editar");
                    vm.cambiarEstado();
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}