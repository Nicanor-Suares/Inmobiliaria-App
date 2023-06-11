package com.example.inmobiliariaapp.models;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int idInquilino;
    private Long DNI;
    private String nombre;
    private String apellido;
    private String lugarDeTrabajo;
    private String telefono;

    public Inquilino() {}

    public Inquilino(int idInquilino, Long DNI, String nombre, String apellido, String lugarDeTrabajo, String telefono) {
        this.idInquilino = idInquilino;
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.lugarDeTrabajo = lugarDeTrabajo;
        this.telefono = telefono;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Long getDNI() {
        return DNI;
    }

    public void setDNI(Long DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(String lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
