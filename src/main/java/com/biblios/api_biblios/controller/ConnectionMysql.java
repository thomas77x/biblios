package com.biblios.api_biblios.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysql {

    private static String env(String name, String porDefecto) {
        String valor = System.getenv(name);
        return (valor == null || valor.isBlank()) ? porDefecto : valor;
    }

    public static Connection open() throws SQLException {
        String url = "jdbc:mysql://" + env("DB_HOST", "127.0.0.1") + ":" + env("DB_PORT", "3306")
                + "/" + env("DB_NAME", "biblios_api")
                + "?useSSL=" + env("DB_SSL", "false")
                + "&allowPublicKeyRetrieval=true&characterEncoding=utf-8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL", e);
        }
        return DriverManager.getConnection(url, env("DB_USER", "root"), env("DB_PASSWORD", "root"));
    }
}
