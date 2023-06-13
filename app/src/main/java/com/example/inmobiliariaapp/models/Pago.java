package com.example.inmobiliariaapp.models;

import java.io.Serializable;

public class Pago implements Serializable {

    private int idPago;
    private int nroPago;
    private int idContrato;
    private int monto;
    private String fechaPago;

    public Pago() {}

    public Pago(int idPago, int numero, int idContrato, int monto, String fechaPago) {
        this.idPago = idPago;
        this.nroPago = numero;
        this.idContrato = idContrato;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getNumero() {
        return nroPago;
    }

    public void setNumero(int numero) {
        this.nroPago = numero;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public double getImporte() {
        return monto;
    }

    public void setImporte(int importe) {
        this.monto = importe;
    }

    public String getFechaDePago() {
        return fechaPago;
    }

    public void setFechaDePago(String fechaDePago) {
        this.fechaPago = fechaDePago;
    }
}