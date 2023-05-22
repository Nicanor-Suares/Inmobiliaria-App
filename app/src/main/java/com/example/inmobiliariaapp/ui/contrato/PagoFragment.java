package com.example.inmobiliariaapp.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.inmobiliariaapp.databinding.FragmentPagoBinding;

public class PagoFragment extends Fragment {
    private PagoViewModel vm;
    private FragmentPagoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPagoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(PagoViewModel.class);

        vm.obtenerPagos(getArguments());

        vm.getPagos().observe(getViewLifecycleOwner(), pagos -> {
            GridLayoutManager grid = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.rvPagos.setLayoutManager(grid);
            PagoAdapter adapter = new PagoAdapter(pagos, inflater);
            binding.rvPagos.setAdapter(adapter);
        });

        return root;
    }
}
