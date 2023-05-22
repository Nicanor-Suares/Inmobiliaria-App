package com.example.inmobiliariaapp.ui.contrato;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariaapp.databinding.ItemPagoBinding;
import com.example.inmobiliariaapp.models.Pago;

import java.util.ArrayList;


public class PagoAdapter extends RecyclerView.Adapter<PagoAdapter.ViewHolder> {

    private ArrayList<Pago> pagos;
    private LayoutInflater inflater;

    public PagoAdapter(ArrayList<Pago> pagos, LayoutInflater inflater) {
        this.pagos = pagos;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPagoBinding binding = ItemPagoBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pago pago = pagos.get(position);
        holder.binding.tvCodigoPago.setText(String.valueOf(pago.getIdPago()));
        holder.binding.tvFechaPago.setText(pago.getFechaDePago());
        holder.binding.tvCodigoContrato.setText(String.valueOf(pago.getContrato().getIdContrato()));
        holder.binding.tvImportePago.setText(String.valueOf(pago.getImporte()));
        holder.binding.tvNroPago.setText(String.valueOf(pago.getNumero()));
    }

    @Override
    public int getItemCount() { return pagos.size(); };

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPagoBinding binding;

        public ViewHolder(@NonNull ItemPagoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}