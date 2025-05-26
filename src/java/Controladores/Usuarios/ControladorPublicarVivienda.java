/*
 * ControladorPublicarVivienda
 */
package Controladores.Usuarios;

import Modelo.Entidades.Ciudades;
import Modelo.Entidades.FotoVivienda;
import Modelo.Entidades.Provincias;
import Modelo.Entidades.Usuario;
import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioCiudad;
import Modelo.Servicio.ServicioProvincia;
import Modelo.Servicio.ServicioVivienda;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "ControladorPublicarVivienda", urlPatterns = {"/Controladores.Usuarios/ControladorPublicarVivienda"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB (umbral antes de escribir en disco)
        maxFileSize = 1024 * 1024 * 50, // 50 MB por archivo
        maxRequestSize = 1024L * 1024L * 1024L // 1 GB total por solicitud
)

public class ControladorPublicarVivienda extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        getServletContext().setAttribute("emf", emf);

        if (emf == null) {
            request.setAttribute("error", "Error interno: EntityManagerFactory no está disponible.");
            request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
            return;
        }

        try {
            ServicioProvincia servicioProvincia = new ServicioProvincia(emf);
            ServicioCiudad servicioCiudad = new ServicioCiudad(emf);

            List<Provincias> provincias = servicioProvincia.obtenerProvincias();
            List<Ciudades> ciudades = servicioCiudad.obtenerCiudades();

            request.setAttribute("provincias", provincias);
            request.setAttribute("ciudades", ciudades);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al publicar la vivienda: " + e.getMessage());
            request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("HomeCheckPU");
            getServletContext().setAttribute("emf", emf);
        }
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);

        Vivienda vivienda = new Vivienda();
        vivienda.setComentarios(new ArrayList<>());
        vivienda.setValoraciones(new ArrayList<>());
        vivienda.setDireccion(request.getParameter("direccion"));

        String precioStr = request.getParameter("precio");
        vivienda.setPrecio((precioStr != null && !precioStr.isEmpty()) ? Double.parseDouble(precioStr) : 0.0);

        vivienda.setCiudad(request.getParameter("ciudad"));
        vivienda.setProvincia(request.getParameter("provincia"));
        vivienda.setCodigoPostal(request.getParameter("codigoPostal"));
        vivienda.setDescripcion(request.getParameter("descripcion"));

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario != null) {
            vivienda.setCasero(usuario);
        } else {
            request.setAttribute("error", "No estás logueado. Por favor, inicia sesión.");
            request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
            return;
        }

        String habitacionesStr = request.getParameter("habitaciones");
        vivienda.setHabitaciones((habitacionesStr != null && !habitacionesStr.isEmpty()) ? Integer.parseInt(habitacionesStr) : 0);

        String banosStr = request.getParameter("banos");
        vivienda.setBaños((banosStr != null && !banosStr.isEmpty()) ? Integer.parseInt(banosStr) : 0);

        String amuebladoStr = request.getParameter("amueblado");
        vivienda.setAmueblado(amuebladoStr != null && !amuebladoStr.isEmpty());

        try {
            // 1. Guardar primero la vivienda sin imagen para obtener el ID
            servicioVivienda.create(vivienda); // esto asigna el ID vía JPA

            List<FotoVivienda> listaFotos = new ArrayList<>();
            // Procesar imágenes subidas (pueden ser varias)
            Collection<Part> partes = request.getParts();
            for (Part parte : partes) {
                if ("foto".equals(parte.getName()) && parte.getSize() > 0) {
                    String nombreArchivo = generarNombreUnico(parte.getSubmittedFileName(), vivienda.getId());

                    String rutaRelativa = "imagenes/viviendas/" + nombreArchivo;

                    FotoVivienda foto = new FotoVivienda();
                    foto.setRuta(rutaRelativa);
                    foto.setVivienda(vivienda);

                    // Ruta externa absoluta en Mac
                    String rutaExterna = "/opt/tomcat/webapps/HomeCheck/imagenes/viviendas/";
                    File directorioExterno = new File(rutaExterna);
                    if (!directorioExterno.exists()) {
                        directorioExterno.mkdirs();
                    }
                    File archivoExterno = new File(directorioExterno, nombreArchivo);
                    parte.write(archivoExterno.getAbsolutePath());

                    listaFotos.add(foto);
                }
            }

            vivienda.setFotos(listaFotos);
            servicioVivienda.edit(vivienda);

            String ciudad = request.getParameter("ciudad");
            if (ciudad == null || ciudad.trim().isEmpty()) {
                request.setAttribute("error", "Debe seleccionar una ciudad.");
                request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
            }

            request.setAttribute("exito", "Vivienda publicada correctamente.");
            request.getRequestDispatcher("/usuarios/viviendaPublicada.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // para consola
            request.setAttribute("error", "Error al publicar vivienda: " + e.getMessage());
            request.getRequestDispatcher("/usuarios/publicarVivienda.jsp").forward(request, response);
        } finally {
            emf.close();
        }
    }

    private String generarNombreUnico(String nombreOriginal, Long idVivienda) {
        if (nombreOriginal == null || !nombreOriginal.contains(".")) {
            return "foto_" + idVivienda + "_" + UUID.randomUUID();
        }
        String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        return "foto_" + idVivienda + "_" + UUID.randomUUID() + extension;
    }

    @Override
    public String getServletInfo() {
        return "Controlador para publicar vivienda con imagen";
    }
}
