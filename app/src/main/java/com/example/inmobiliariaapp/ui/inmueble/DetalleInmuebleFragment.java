package com.example.inmobiliariaapp.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.inmobiliariaapp.databinding.FragmentDetalleInmuebleBinding;

public class DetalleInmuebleFragment extends Fragment {

    private DetalleInmuebleViewModel vm;
    private FragmentDetalleInmuebleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetalleInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(DetalleInmuebleViewModel.class);

        vm.obtenerInmuebles(getArguments());

        vm.getInmueble().observe(getViewLifecycleOwner(), inmueble -> {
            if(inmueble != null) {
                binding.tvCodigoInmueble.setText(String.valueOf(inmueble.getIdInmueble()));
                Glide.with(binding.ivFotoInmueble.getContext()).load(inmueble.getImagen()).into(binding.ivFotoInmueble);
                binding.tvDireccionInmueble.setText(inmueble.getDireccion());
                binding.tvPrecioInmueble.setText(String.valueOf(inmueble.getPrecio()));
                binding.tvAmbientesInmueble.setText(String.valueOf(inmueble.getAmbientes()));
                binding.tvUsoInmueble.setText(inmueble.getUso());
                binding.tvTipoInmueble.setText(inmueble.getTipo());
                if(inmueble.isEstado()){
                    binding.cbDisponible.setChecked(true);
                    binding.cbDisponible.setText("Disponible");
                }else{
                    binding.cbDisponible.setChecked(false);
                    binding.cbDisponible.setText("No Disponible");
                }
            }
        });
        return root;
    }

}
