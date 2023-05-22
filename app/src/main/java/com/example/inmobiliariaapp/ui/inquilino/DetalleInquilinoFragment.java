package com.example.inmobiliariaapp.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliariaapp.databinding.FragmentDetalleInquilinoBinding;

public class DetalleInquilinoFragment extends Fragment {

    private DetalleInquilinoViewModel vm;
    private FragmentDetalleInquilinoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDetalleInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(DetalleInquilinoViewModel.class);

        vm.obtenerInquilino(getArguments());

        vm.getInquilino().observe(getViewLifecycleOwner(), inquilino -> {
            binding.tvCodigoInquilino.setText(String.valueOf(inquilino.getIdInquilino()));
            binding.tvNombreInquilino.setText(inquilino.getNombre());
            binding.tvApellidoInquilino.setText(inquilino.getApellido());
            binding.tvDniInquilino.setText(String.valueOf(inquilino.getDNI()));
            binding.tvEmailInquilino.setText(inquilino.getEmail());
            binding.tvTelefonoInquilino.setText(String.valueOf(inquilino.getTelefono()));
            binding.tvGaranteInquilino.setText(inquilino.getNombreGarante());
            binding.tvTelefonoGarante.setText(String.valueOf(inquilino.getTelefonoGarante()));
        });

        return root;

    }
}
