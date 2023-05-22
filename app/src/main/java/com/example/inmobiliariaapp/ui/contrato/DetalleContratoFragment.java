package com.example.inmobiliariaapp.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliariaapp.databinding.FragmentDetalleContratoBinding;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel vm;
    private FragmentDetalleContratoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(DetalleContratoViewModel.class);

        vm.obtenerContrato(getArguments());

        vm.getContrato().observe(getViewLifecycleOwner(), contrato -> {
            binding.tvCodigoContrato.setText(String.valueOf(contrato.getIdContrato()));
            binding.tvFechaInicio.setText(contrato.getFechaInicio());
            binding.tvFechaFin.setText(contrato.getFechaFin());
            binding.tvInquilinoContrato.setText(contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());
            binding.tvInmuebleContrato.setText(contrato.getInmueble().getDireccion());
            binding.tvMontoContrato.setText(String.valueOf(contrato.getMontoAlquiler()));
        });

        binding.btnPagos.setOnClickListener(v -> {
            vm.verPagos(v);
        });

        return root;
    }


}
