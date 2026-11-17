package com.biblios.api_biblios.controller;

import com.biblios.api_biblios.model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ControllerAlumno {

    public List<Alumno> getAlumnos() throws SQLException {
        List<Alumno> lista = new ArrayList<>();
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM alumno");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                lista.add(fillAlumno(rs));
            }
        }
        return lista;
    }

    public Alumno getAlumnoById(int idAlumno) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM alumno WHERE idAlumno = ?")) {
            pst.setInt(1, idAlumno);
            ResultSet rs = pst.executeQuery();
            return rs.next() ? fillAlumno(rs) : null;
        }
    }

    public int insertarAlumno(Alumno a) throws SQLException {
        String sql = "INSERT INTO alumno (matricula, nombre, apellido) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, a.getMatricula());
            pst.setString(2, a.getNombre());
            pst.setString(3, a.getApellido());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            return rs.next() ? rs.getInt(1) : 0;
        }
    }

    public boolean actualizarAlumno(Alumno a) throws SQLException {
        String sql = "UPDATE alumno SET matricula = ?, nombre = ?, apellido = ? WHERE idAlumno = ?";
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, a.getMatricula());
            pst.setString(2, a.getNombre());
            pst.setString(3, a.getApellido());
            pst.setInt(4, a.getIdAlumno());
            return pst.executeUpdate() > 0;
        }
    }

    public boolean eliminarAlumno(int idAlumno) throws SQLException {
        try (Connection conn = ConnectionMysql.open();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM alumno WHERE idAlumno = ?")) {
            pst.setInt(1, idAlumno);
            return pst.executeUpdate() > 0;
        }
    }

    private Alumno fillAlumno(ResultSet rs) throws SQLException {
        Alumno a = new Alumno();
        a.setIdAlumno(rs.getInt("idAlumno"));
        a.setMatricula(rs.getString("matricula"));
        a.setNombre(rs.getString("nombre"));
        a.setApellido(rs.getString("apellido"));
        return a;
    }
}
