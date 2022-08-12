package com.example.praiveitcontacts.Models;

public class Contactos {
    private  int Id;
    private  int IdUsuarioMaster;
    private String Nombre;
    private String Apellido;
    private String Genero;
    private String Correo;
    private String Telefono;
    private  String TelefonoFijo;
    private  String Direccion;

    public Contactos() {
    }

    public Contactos(int id, int idUsuarioMaster, String nombre, String apellido, String genero, String correo, String telefono, String telefonoFijo, String direccion) {
        Id = id;
        IdUsuarioMaster = idUsuarioMaster;
        Nombre = nombre;
        Apellido = apellido;
        Genero = genero;
        Correo = correo;
        Telefono = telefono;
        TelefonoFijo = telefonoFijo;
        Direccion = direccion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdUsuarioMaster() {
        return IdUsuarioMaster;
    }

    public void setIdUsuarioMaster(int idUsuarioMaster) {
        IdUsuarioMaster = idUsuarioMaster;
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

    public String getTelefonoFijo() {
        return TelefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        TelefonoFijo = telefonoFijo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
}
