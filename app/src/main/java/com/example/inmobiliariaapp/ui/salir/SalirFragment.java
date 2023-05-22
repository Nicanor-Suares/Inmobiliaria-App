package com.example.inmobiliariaapp.ui.salir;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.inmobiliariaapp.databinding.FragmentSalirBinding;

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;
    private SalirViewModel vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new SalirViewModel(getActivity().getApplication());

        salir(getContext());
        return root;
    }

    private void salir(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Salir")
                .setMessage("¿Cerrar Sesión?")
                .setPositiveButton("Si", (dialog, which) -> { vm.logout(); })
                .setNegativeButton("No", (dialog, which) -> { dialog.dismiss(); })
                .show();
    }

}
