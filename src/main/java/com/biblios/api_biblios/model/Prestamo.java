package com.biblios.api_biblios.model;

public class Prestamo {
    private int idPrestamo;
    private int idLibro;
    private int idAlumno;
    private int idBibliotecario;
    private String fechaPrestamo;
    private String fechaDevolucion;

    public Prestamo() {}

    public int getIdPrestamo() {
        return idPrestamo;
    }
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }
    public int getIdLibro() {
        return idLibro;
    }
    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    public int getIdAlumno() {
        return idAlumno;
    }
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    public int getIdBibliotecario() {
        return idBibliotecario;
    }
    public void setIdBibliotecario(int idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }
    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
