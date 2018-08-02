package com.tvaztecagioj.myapplication;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nombre,apellido,contraseña,direccion,telefono,correo;
    private int edad;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String contraseña, String direccion, String telefono, int edad,String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.telefono = telefono;
        this.edad = edad;
        this.correo=correo;
    }

    @Exclude
    public Map<String, Object> toMap(String correo) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("apellido", apellido);
        result.put("contraseña", contraseña);
        result.put("direccion", direccion);
        result.put("telefono", telefono);
        result.put("edad", edad);
        result.put("correo", correo);
        return result;
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
