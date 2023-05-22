package com.example.inmobiliariaapp.ui.logout;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.inmobiliariaapp.R;
import com.example.inmobiliariaapp.databinding.FragmentLogoutBinding;

public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;
    private LogoutViewModel vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm = new LogoutViewModel(getActivity().getApplication());

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
