package com.example.inmobiliariaapp.ui.inmueble;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.inmobiliariaapp.R;
import com.example.inmobiliariaapp.databinding.ItemInmuebleBinding;
import com.example.inmobiliariaapp.models.Inmueble;

import java.util.ArrayList;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder> {

    private ArrayList<Inmueble> inmuebles;
    private LayoutInflater inflater;

    public InmuebleAdapter(ArrayList<Inmueble> inmuebles, LayoutInflater inflater) {
        this.inmuebles = inmuebles;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInmuebleBinding binding = ItemInmuebleBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter.ViewHolder holder, int position) {
        Inmueble inmueble = inmuebles.get(position);
        holder.binding.tvDireccionInmueble.setText(inmueble.getDireccion());
        holder.binding.tvPrecioInmueble.setText(String.valueOf(inmueble.getPrecio()));
        Glide.with(holder.binding.ivFotoInmueble.getContext()).load(inmueble.getImagen()).into(holder.binding.ivFotoInmueble);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemInmuebleBinding binding;
        public ViewHolder(@NonNull ItemInmuebleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmuebles.get(getAdapterPosition()));
                Navigation.findNavController(v).navigate(R.id.detalleInmuebleFragment, bundle);
            });
        }

    }
}
