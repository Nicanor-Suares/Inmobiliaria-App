package com.example.inmobiliariaapp.ui.contrato;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.inmobiliariaapp.databinding.FragmentContratoBinding;

public class ContratoFragment extends Fragment {

    private FragmentContratoBinding binding;
    private ContratoViewModel vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new ViewModelProvider(this).get(ContratoViewModel.class);

        vm.getInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            GridLayoutManager grid = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.rvContrato.setLayoutManager(grid);
            ContratoAdapter adapter = new ContratoAdapter(inmuebles, inflater);
            binding.rvContrato.setAdapter(adapter);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
