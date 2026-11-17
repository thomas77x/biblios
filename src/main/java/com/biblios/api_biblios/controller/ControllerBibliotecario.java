package com.biblios.api_biblios.controller;

import com.biblios.api_biblios.model.Bibliotecario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerBibliotecario {

    public List<Bibliotecario> getBibliotecarios() throws SQLException {
        List<Bibliotecario> lista = new ArrayList<>();
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM bibliotecario");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(fillBibliotecario(rs));
            }
        }
        return lista;
    }

    public Bibliotecario getBibliotecarioById(int idBibliotecario) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM bibliotecario WHERE idBibliotecario = ?")) {
            pst.setInt(1, idBibliotecario);
            ResultSet rs = pst.executeQuery();
            return rs.next() ? fillBibliotecario(rs) : null;
        }
    }

    public Bibliotecario login(String usuario, String password) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM bibliotecario WHERE usuario = ? AND password = ?")) {
            pst.setString(1, usuario);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next() ? fillBibliotecario(rs) : null;
        }
    }

    public int insertarBibliotecario(Bibliotecario b) throws SQLException {
        String sql = "INSERT INTO bibliotecario (nombre, apellido, usuario, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, b.getNombre());
            pst.setString(2, b.getApellido());
            pst.setString(3, b.getUsuario());
            pst.setString(4, b.getPassword());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public boolean actualizarBibliotecario(Bibliotecario b) throws SQLException {
        String sql = "UPDATE bibliotecario SET nombre = ?, apellido = ?, usuario = ?, password = ? "
                   + "WHERE idBibliotecario = ?";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, b.getNombre());
            pst.setString(2, b.getApellido());
            pst.setString(3, b.getUsuario());
            pst.setString(4, b.getPassword());
            pst.setInt(5, b.getIdBibliotecario());
            return pst.executeUpdate() > 0;
        }
    }

    public boolean eliminarBibliotecario(int idBibliotecario) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM bibliotecario WHERE idBibliotecario = ?")) {
            pst.setInt(1, idBibliotecario);
            return pst.executeUpdate() > 0;
        }
    }

    private Bibliotecario fillBibliotecario(ResultSet rs) throws SQLException {
        Bibliotecario b = new Bibliotecario();
        b.setIdBibliotecario(rs.getInt("idBibliotecario"));
        b.setNombre(rs.getString("nombre"));
        b.setApellido(rs.getString("apellido"));
        b.setUsuario(rs.getString("usuario"));
        return b;
    }
}
