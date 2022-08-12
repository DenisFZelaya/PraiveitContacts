package com.example.praiveitcontacts.Models;

public class Usuario {
    private  int Id;
    private String Nombre;
    private String Apellido;
    private String Genero;
    private String Usuario;
    private String Correo;
    private String Telefono;
    private String Contrasena;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String apellido, String genero, String usuario, String correo, String telefono, String contrasena) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Genero = genero;
        Usuario = usuario;
        Correo = correo;
        Telefono = telefono;
        Contrasena = contrasena;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }
}
