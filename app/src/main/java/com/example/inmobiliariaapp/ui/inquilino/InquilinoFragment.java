package com.example.inmobiliariaapp.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.inmobiliariaapp.databinding.FragmentInquilinoBinding;

public class InquilinoFragment extends Fragment {

    private FragmentInquilinoBinding binding;
    private InquilinoViewModel vm;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(InquilinoViewModel.class);
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getInmuebles().observe(getViewLifecycleOwner(), inmuebles -> {
            GridLayoutManager grid = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
            binding.rvInquilinos.setLayoutManager(grid);
            InquilinoAdapter adapter = new InquilinoAdapter(inmuebles, inflater);
            binding.rvInquilinos.setAdapter(adapter);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
