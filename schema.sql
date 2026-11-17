-- Esquema de la base de datos biblioteca_api (MySQL 8)
-- En una base de datos hospedada (TiDB Cloud, Aiven...) la base ya existe:
-- omite las dos primeras sentencias y ejecuta el resto sobre ella.
CREATE DATABASE IF NOT EXISTS biblios_api CHARACTER SET utf8mb4;
USE biblios_api;

CREATE TABLE IF NOT EXISTS categoria (
    idCategoria  INT AUTO_INCREMENT PRIMARY KEY,
    nombre       VARCHAR(50)  NOT NULL,
    CONSTRAINT uq_categoria_nombre UNIQUE (nombre)
);

CREATE TABLE IF NOT EXISTS libro (
    idLibro          INT AUTO_INCREMENT PRIMARY KEY,
    titulo           VARCHAR(150) NOT NULL,
    autor            VARCHAR(100) NOT NULL,
    idCategoria      INT          NOT NULL,
    CONSTRAINT fk_libro_categoria FOREIGN KEY (idCategoria) REFERENCES categoria (idCategoria)
);

CREATE TABLE IF NOT EXISTS alumno (
    idAlumno         INT AUTO_INCREMENT PRIMARY KEY,
    matricula        VARCHAR(20) NOT NULL,
    nombre           VARCHAR(40) NOT NULL,
    apellido         VARCHAR(40) NOT NULL,
    CONSTRAINT uq_alumno_matricula UNIQUE (matricula)
);

CREATE TABLE IF NOT EXISTS bibliotecario (
    idBibliotecario  INT AUTO_INCREMENT PRIMARY KEY,
    nombre           VARCHAR(40) NOT NULL,
    apellido         VARCHAR(40) NOT NULL,
    usuario          VARCHAR(100) NOT NULL,
    password         VARCHAR(100) NOT NULL
    CONSTRAINT uq_bibliotecario_usuario UNIQUE (usuario)s
);

CREATE TABLE IF NOT EXISTS prestamo (
    idPrestamo       INT AUTO_INCREMENT PRIMARY KEY,
    idLibro          INT      NOT NULL,
    idAlumno         INT      NOT NULL,
    idBibliotecario  INT      NOT NULL,
    fechaPrestamo    DATETIME NOT NULL,
    fechaDevolucion  DATETIME NULL,
    CONSTRAINT fk_prestamo_libro         FOREIGN KEY (idLibro)         REFERENCES libro (idLibro),
    CONSTRAINT fk_prestamo_alumno        FOREIGN KEY (idAlumno)        REFERENCES alumno (idAlumno),
    CONSTRAINT fk_prestamo_bibliotecario FOREIGN KEY (idBibliotecario) REFERENCES bibliotecario (idBibliotecario)
);
