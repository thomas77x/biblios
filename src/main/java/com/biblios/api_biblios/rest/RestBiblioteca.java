package com.biblios.api_biblios.rest;

import com.biblios.api_biblios.controller.ControllerCategoria;
import com.biblios.api_biblios.controller.ControllerLibro;
import com.biblios.api_biblios.controller.ControllerAlumno;
import com.biblios.api_biblios.controller.ControllerBibliotecario;
import com.biblios.api_biblios.controller.ControllerPrestamo;
import com.biblios.api_biblios.model.Categoria;
import com.biblios.api_biblios.model.Libro;
import com.biblios.api_biblios.model.Alumno;
import com.biblios.api_biblios.model.Bibliotecario;
import com.biblios.api_biblios.model.Prestamo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("biblioteca")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestBiblioteca {

    private final ControllerCategoria categorias = new ControllerCategoria();
    private final ControllerLibro libros = new ControllerLibro();
    private final ControllerAlumno alumnos = new ControllerAlumno();
    private final ControllerBibliotecario bibliotecarios = new ControllerBibliotecario();
    private final ControllerPrestamo prestamos = new ControllerPrestamo();

    // ---------- Categoria ----------

    @GET
    @Path("getAllCategorias")
    public Response getAllCategorias() {
        try {
            return Response.ok(categorias.getCategorias()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener las categorías")).build();
        }
    }

    @GET
    @Path("getCategoriaById")
    public Response getCategoriaById(@QueryParam("idCategoria") int idCategoria) {
        try {
            Categoria categoria = categorias.getCategoriaById(idCategoria);
            if (categoria == null) {
                return Response.status(404).entity(Map.of("result", "Categoría no encontrada")).build();
            }
            return Response.ok(categoria).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener la categoría")).build();
        }
    }

    @POST
    @Path("insertCategoria")
    public Response insertCategoria(Categoria categoria) {
        try {
            categoria.setIdCategoria(categorias.insertarCategoria(categoria));
            return Response.status(201).entity(categoria).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al guardar la categoría")).build();
        }
    }

    @PUT
    @Path("updateCategoria")
    public Response updateCategoria(Categoria categoria) {
        try {
            if (!categorias.actualizarCategoria(categoria)) {
                return Response.status(404).entity(Map.of("result", "Categoría no encontrada")).build();
            }
            return Response.ok(categoria).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al actualizar la categoría")).build();
        }
    }

    @DELETE
    @Path("deleteCategoria")
    public Response deleteCategoria(@QueryParam("idCategoria") int idCategoria) {
        try {
            if (!categorias.eliminarCategoria(idCategoria)) {
                return Response.status(404).entity(Map.of("result", "Categoría no encontrada")).build();
            }
            return Response.ok(Map.of("result", "Categoría eliminada")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al eliminar la categoría")).build();
        }
    }

    // ---------- Libro ----------

    @GET
    @Path("getAllLibros")
    public Response getAllLibros() {
        try {
            return Response.ok(libros.getLibros()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener los libros")).build();
        }
    }

    @GET
    @Path("getLibroById")
    public Response getLibroById(@QueryParam("idLibro") int idLibro) {
        try {
            Libro libro = libros.getLibroById(idLibro);
            if (libro == null) {
                return Response.status(404).entity(Map.of("result", "Libro no encontrado")).build();
            }
            return Response.ok(libro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener el libro")).build();
        }
    }

    @POST
    @Path("insertLibro")
    public Response insertLibro(Libro libro) {
        try {
            libro.setIdLibro(libros.insertarLibro(libro));
            return Response.status(201).entity(libro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al guardar el libro")).build();
        }
    }

    @PUT
    @Path("updateLibro")
    public Response updateLibro(Libro libro) {
        try {
            if (!libros.actualizarLibro(libro)) {
                return Response.status(404).entity(Map.of("result", "Libro no encontrado")).build();
            }
            return Response.ok(libro).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al actualizar el libro")).build();
        }
    }

    @DELETE
    @Path("deleteLibro")
    public Response deleteLibro(@QueryParam("idLibro") int idLibro) {
        try {
            if (!libros.eliminarLibro(idLibro)) {
                return Response.status(404).entity(Map.of("result", "Libro no encontrado")).build();
            }
            return Response.ok(Map.of("result", "Libro eliminado")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al eliminar el libro")).build();
        }
    }

    // ---------- Alumno ----------

    @GET
    @Path("getAllAlumnos")
    public Response getAllAlumnos() {
        try {
            return Response.ok(alumnos.getAlumnos()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener los alumnos")).build();
        }
    }

    @GET
    @Path("getAlumnoById")
    public Response getAlumnoById(@QueryParam("idAlumno") int idAlumno) {
        try {
            Alumno alumno = alumnos.getAlumnoById(idAlumno);
            if (alumno == null) {
                return Response.status(404).entity(Map.of("result", "Alumno no encontrado")).build();
            }
            return Response.ok(alumno).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener el alumno")).build();
        }
    }

    @POST
    @Path("insertAlumno")
    public Response insertAlumno(Alumno alumno) {
        try {
            alumno.setIdAlumno(alumnos.insertarAlumno(alumno));
            return Response.status(201).entity(alumno).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al guardar el alumno")).build();
        }
    }

    @PUT
    @Path("updateAlumno")
    public Response updateAlumno(Alumno alumno) {
        try {
            if (!alumnos.actualizarAlumno(alumno)) {
                return Response.status(404).entity(Map.of("result", "Alumno no encontrado")).build();
            }
            return Response.ok(alumno).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al actualizar el alumno")).build();
        }
    }

    @DELETE
    @Path("deleteAlumno")
    public Response deleteAlumno(@QueryParam("idAlumno") int idAlumno) {
        try {
            if (!alumnos.eliminarAlumno(idAlumno)) {
                return Response.status(404).entity(Map.of("result", "Alumno no encontrado")).build();
            }
            return Response.ok(Map.of("result", "Alumno eliminado")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al eliminar el alumno")).build();
        }
    }

    // ---------- Bibliotecario ----------

    @GET
    @Path("getAllBibliotecarios")
    public Response getAllBibliotecarios() {
        try {
            return Response.ok(bibliotecarios.getBibliotecarios()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener los bibliotecarios")).build();
        }
    }

    @GET
    @Path("getBibliotecarioById")
    public Response getBibliotecarioById(@QueryParam("idBibliotecario") int idBibliotecario) {
        try {
            Bibliotecario bibliotecario = bibliotecarios.getBibliotecarioById(idBibliotecario);
            if (bibliotecario == null) {
                return Response.status(404).entity(Map.of("result", "Bibliotecario no encontrado")).build();
            }
            return Response.ok(bibliotecario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener el bibliotecario")).build();
        }
    }

    @POST
    @Path("insertBibliotecario")
    public Response insertBibliotecario(Bibliotecario bibliotecario) {
        try {
            bibliotecario.setIdBibliotecario(bibliotecarios.insertarBibliotecario(bibliotecario));
            return Response.status(201).entity(bibliotecario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al guardar el bibliotecario")).build();
        }
    }

    @PUT
    @Path("updateBibliotecario")
    public Response updateBibliotecario(Bibliotecario bibliotecario) {
        try {
            if (!bibliotecarios.actualizarBibliotecario(bibliotecario)) {
                return Response.status(404).entity(Map.of("result", "Bibliotecario no encontrado")).build();
            }
            return Response.ok(bibliotecario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al actualizar el bibliotecario")).build();
        }
    }

    @DELETE
    @Path("deleteBibliotecario")
    public Response deleteBibliotecario(@QueryParam("idBibliotecario") int idBibliotecario) {
        try {
            if (!bibliotecarios.eliminarBibliotecario(idBibliotecario)) {
                return Response.status(404).entity(Map.of("result", "Bibliotecario no encontrado")).build();
            }
            return Response.ok(Map.of("result", "Bibliotecario eliminado")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al eliminar el bibliotecario")).build();
        }
    }

    @POST
    @Path("login")
    public Response login(Bibliotecario datos) {
        try {
            Bibliotecario bibliotecario = bibliotecarios.login(datos.getUsuario(), datos.getPassword());
            if (bibliotecario == null) {
                return Response.status(401).entity(Map.of("result", "Usuario o contraseña incorrectos")).build();
            }
            return Response.ok(bibliotecario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al iniciar sesión")).build();
        }
    }

    // ---------- Prestamo ----------

    @GET
    @Path("getAllPrestamos")
    public Response getAllPrestamos() {
        try {
            return Response.ok(prestamos.getPrestamos()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener los préstamos")).build();
        }
    }

    @GET
    @Path("getPrestamoById")
    public Response getPrestamoById(@QueryParam("idPrestamo") int idPrestamo) {
        try {
            Prestamo prestamo = prestamos.getPrestamoById(idPrestamo);
            if (prestamo == null) {
                return Response.status(404).entity(Map.of("result", "Préstamo no encontrado")).build();
            }
            return Response.ok(prestamo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al obtener el préstamo")).build();
        }
    }

    @POST
    @Path("insertPrestamo")
    public Response insertPrestamo(Prestamo prestamo) {
        try {
            prestamo.setIdPrestamo(prestamos.insertarPrestamo(prestamo));
            return Response.status(201).entity(prestamo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al guardar el préstamo")).build();
        }
    }

    @PUT
    @Path("updatePrestamo")
    public Response updatePrestamo(Prestamo prestamo) {
        try {
            if (!prestamos.actualizarPrestamo(prestamo)) {
                return Response.status(404).entity(Map.of("result", "Préstamo no encontrado")).build();
            }
            return Response.ok(prestamo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al actualizar el préstamo")).build();
        }
    }

    @DELETE
    @Path("deletePrestamo")
    public Response deletePrestamo(@QueryParam("idPrestamo") int idPrestamo) {
        try {
            if (!prestamos.eliminarPrestamo(idPrestamo)) {
                return Response.status(404).entity(Map.of("result", "Préstamo no encontrado")).build();
            }
            return Response.ok(Map.of("result", "Préstamo eliminado")).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(Map.of("result", "Error al eliminar el préstamo")).build();
        }
    }
}
