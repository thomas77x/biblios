package com.biblios.api_biblios.controller;

import com.biblios.api_biblios.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerLibro {

    public List<Libro> getLibros() throws SQLException {
        List<Libro> lista = new ArrayList<>();
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM libro");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(fillLibro(rs));
            }
        }
        return lista;
    }

    public Libro getLibroById(int idLibro) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM libro WHERE idLibro = ?")) {
            pst.setInt(1, idLibro);
            ResultSet rs = pst.executeQuery();
            return rs.next() ? fillLibro(rs) : null;
        }
    }

    public int insertarLibro(Libro l) throws SQLException {
        String sql = "INSERT INTO libro (titulo, autor, idCategoria) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, l.getTitulo());
            pst.setString(2, l.getAutor());
            pst.setInt(3, l.getIdCategoria());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public boolean actualizarLibro(Libro l) throws SQLException {
        String sql = "UPDATE libro SET titulo = ?, autor = ?, idCategoria = ? WHERE idLibro = ?";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, l.getTitulo());
            pst.setString(2, l.getAutor());
            pst.setInt(3, l.getIdCategoria());
            pst.setInt(4, l.getIdLibro());
            return pst.executeUpdate() > 0;
        }
    }

    public boolean eliminarLibro(int idLibro) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM libro WHERE idLibro = ?")) {
            pst.setInt(1, idLibro);
            return pst.executeUpdate() > 0;
        }
    }

    private Libro fillLibro(ResultSet rs) throws SQLException {
        Libro l = new Libro();
        l.setIdLibro(rs.getInt("idLibro"));
        l.setTitulo(rs.getString("titulo"));
        l.setAutor(rs.getString("autor"));
        l.setIdCategoria(rs.getInt("idCategoria"));
        return l;
    }
}
