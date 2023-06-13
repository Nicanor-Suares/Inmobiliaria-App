package com.example.inmobiliariaapp.models;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {

    private int idContrato;
    private String fechaInicio;
    private String fechaFin;
    private boolean activo;
    private int inquilinoId;
    private Inquilino inquilinoContrato;
    private int inmuebleId;
    private Inmueble inmuebleContrato;

    public Contrato() {}
    public Contrato(int idContrato, String fechaInicio, String fechaFin, boolean activo, int inquilinoId , Inquilino inquilino, int inmuebleId , Inmueble inmueble) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activo = activo;
        this.inquilinoId = inquilinoId;
        this.inquilinoContrato = inquilino;
        this.inmuebleId = inmuebleId;
        this.inmuebleContrato = inmueble;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Inquilino getInquilino() {
        return inquilinoContrato;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilinoContrato = inquilino;
    }

    public Inmueble getInmueble() {
        return inmuebleContrato;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmuebleContrato = inmueble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return idContrato == contrato.idContrato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContrato);
    }
}
