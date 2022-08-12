package com.example.praiveitcontacts.Models;

public class Login {
    private String Usuario;
    private String Contrasena;

    public Login() {
        //
    }

    public Login(String usuario, String contrasena) {
        Usuario = usuario;
        Contrasena = contrasena;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }
}
