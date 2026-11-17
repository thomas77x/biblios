package com.biblios.api_biblios.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bibliotecario {
    private int idBibliotecario;
    private String nombre;
    private String apellido;
    private String usuario;

    // Se recibe en insert/update pero nunca se devuelve en las respuestas JSON
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Bibliotecario() {}

    public int getIdBibliotecario() {
        return idBibliotecario;
    }
    public void setIdBibliotecario(int idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
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
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
