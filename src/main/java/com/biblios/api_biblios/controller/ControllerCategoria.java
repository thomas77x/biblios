package com.biblios.api_biblios.controller;

import com.biblios.api_biblios.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerCategoria {

    public List<Categoria> getCategorias() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM categoria");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(fillCategoria(rs));
            }
        }
        return lista;
    }

    public Categoria getCategoriaById(int idCategoria) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM categoria WHERE idCategoria = ?")) {
            pst.setInt(1, idCategoria);
            ResultSet rs = pst.executeQuery();
            return rs.next() ? fillCategoria(rs) : null;
        }
    }

    public int insertarCategoria(Categoria c) throws SQLException {
        String sql = "INSERT INTO categoria (nombre) VALUES (?)";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, c.getNombre());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public boolean actualizarCategoria(Categoria c) throws SQLException {
        String sql = "UPDATE categoria SET nombre = ? WHERE idCategoria = ?";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, c.getNombre());
            pst.setInt(2, c.getIdCategoria());
            return pst.executeUpdate() > 0;
        }
    }

    public boolean eliminarCategoria(int idCategoria) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM categoria WHERE idCategoria = ?")) {
            pst.setInt(1, idCategoria);
            return pst.executeUpdate() > 0;
        }
    }

    private Categoria fillCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("idCategoria"));
        c.setNombre(rs.getString("nombre"));
        return c;
    }
}
