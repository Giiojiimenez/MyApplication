package com.tvaztecagioj.myapplication;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String nombre,apellido,contraseña,direccion,telefono;
    private int edad;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String contraseña, String direccion, String telefono, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.telefono = telefono;
        this.edad = edad;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nombre", nombre);
        result.put("apellido", apellido);
        result.put("contraseña", contraseña);
        result.put("direccion", direccion);
        result.put("telefono", telefono);
        result.put("edad", edad);

        return result;
    }
}
