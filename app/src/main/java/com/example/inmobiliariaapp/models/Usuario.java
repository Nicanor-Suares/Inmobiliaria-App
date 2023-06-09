package com.example.inmobiliariaapp.models;

public class Usuario {

    private String email;
    private String password;

    public Usuario(String usuario, String password) {
        this.email = usuario;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
