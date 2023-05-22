package com.example.inmobiliariaapp.ui.inquilino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliariaapp.R;
import com.example.inmobiliariaapp.databinding.ItemInquilinoBinding;
import com.example.inmobiliariaapp.models.Inmueble;
import com.example.inmobiliariaapp.models.Inquilino;

import java.util.ArrayList;

public class InquilinoAdapter extends RecyclerView.Adapter<InquilinoAdapter.ViewHolder> {

    private ArrayList<Inquilino> inquilinos;
    private ArrayList<Inmueble> inmuebles;
    private LayoutInflater inflater;

    public InquilinoAdapter(ArrayList<Inmueble> inmuebles, LayoutInflater inflater){
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInquilinoBinding binding = ItemInquilinoBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InquilinoAdapter.ViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        holder.binding.tvDireccionInmueble.setText(inmueble.getDireccion());
        Glide.with(holder.binding.ivFotoInmueble.getContext()).load(inmueble.getImagen()).into(holder.binding.ivFotoInmueble);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemInquilinoBinding binding;
        public ViewHolder(@NonNull ItemInquilinoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.btnVer.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmuebles.get(getAdapterPosition()));
                Navigation.findNavController(v).navigate(R.id.detalleInquilinoFragment, bundle);
            });
        }
    }

}
