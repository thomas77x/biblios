package com.biblios.api_biblios.controller;

import com.biblios.api_biblios.model.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerPrestamo {

    public List<Prestamo> getPrestamos() throws SQLException {
        List<Prestamo> lista = new ArrayList<>();
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM prestamo");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(fillPrestamo(rs));
            }
        }
        return lista;
    }

    public Prestamo getPrestamoById(int idPrestamo) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM prestamo WHERE idPrestamo = ?")) {
            pst.setInt(1, idPrestamo);
            ResultSet rs = pst.executeQuery();
            return rs.next() ? fillPrestamo(rs) : null;
        }
    }

    public int insertarPrestamo(Prestamo p) throws SQLException {
        String sql = "INSERT INTO prestamo (idLibro, idAlumno, idBibliotecario, fechaPrestamo, fechaDevolucion) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, p.getIdLibro());
            pst.setInt(2, p.getIdAlumno());
            pst.setInt(3, p.getIdBibliotecario());
            pst.setString(4, p.getFechaPrestamo());
            pst.setString(5, p.getFechaDevolucion());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public boolean actualizarPrestamo(Prestamo p) throws SQLException {
        String sql = "UPDATE prestamo SET idLibro = ?, idAlumno = ?, idBibliotecario = ?, "
                   + "fechaPrestamo = ?, fechaDevolucion = ? WHERE idPrestamo = ?";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, p.getIdLibro());
            pst.setInt(2, p.getIdAlumno());
            pst.setInt(3, p.getIdBibliotecario());
            pst.setString(4, p.getFechaPrestamo());
            pst.setString(5, p.getFechaDevolucion());
            pst.setInt(6, p.getIdPrestamo());
            return pst.executeUpdate() > 0;
        }
    }

    public boolean eliminarPrestamo(int idPrestamo) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM prestamo WHERE idPrestamo = ?")) {
            pst.setInt(1, idPrestamo);
            return pst.executeUpdate() > 0;
        }
    }

    private Prestamo fillPrestamo(ResultSet rs) throws SQLException {
        Prestamo p = new Prestamo();
        p.setIdPrestamo(rs.getInt("idPrestamo"));
        p.setIdLibro(rs.getInt("idLibro"));
        p.setIdAlumno(rs.getInt("idAlumno"));
        p.setIdBibliotecario(rs.getInt("idBibliotecario"));
        p.setFechaPrestamo(rs.getString("fechaPrestamo"));
        p.setFechaDevolucion(rs.getString("fechaDevolucion"));
        return p;
    }
}
