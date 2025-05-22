/*
 * ControladorEditarMiVivienda
 */
package Controladores.Usuarios;

import Modelo.Entidades.FotoVivienda;
import Modelo.Entidades.Usuario;
import Modelo.Entidades.Vivienda;
import Modelo.Servicio.ServicioVivienda;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "ControladorEditarMiVivienda", urlPatterns = {"/Controladores.Usuarios/ControladorEditarMiVivienda"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15 // 15 MB
)
public class ControladorEditarMiVivienda extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        if (usuario == null) {
            response.sendRedirect("ControladorLogin");
            return;
        }

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("ControladorMisViviendas");
            return;
        }

        Long id = Long.parseLong(idStr);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);

        Vivienda vivienda = servicioVivienda.findVivienda(id);
        emf.close();

        if (vivienda == null || !vivienda.getCasero().getId().equals(usuario.getId())) {
            response.sendRedirect("ControladorMisViviendas");
            return;
        }

        request.setAttribute("vivienda", vivienda);
        request.getRequestDispatcher("/usuarios/editarMiVivienda.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HomeCheckPU");
        ServicioVivienda servicioVivienda = new ServicioVivienda(emf);

        try {
            Long idVivienda = Long.parseLong(request.getParameter("id"));
            Vivienda vivienda = servicioVivienda.findVivienda(idVivienda);

            if (vivienda == null) {
                request.setAttribute("error", "No se encontró la vivienda.");
                request.getRequestDispatcher("/usuarios/editarMiVivienda.jsp").forward(request, response);
                return;
            }

            vivienda.setDireccion(request.getParameter("direccion"));
            vivienda.setPrecio(Double.parseDouble(request.getParameter("precio")));
            vivienda.setCiudad(request.getParameter("ciudad"));
            vivienda.setProvincia(request.getParameter("provincia"));
            vivienda.setCodigoPostal(request.getParameter("codigoPostal"));
            vivienda.setDescripcion(request.getParameter("descripcion"));
            vivienda.setHabitaciones(Integer.parseInt(request.getParameter("habitaciones")));
            vivienda.setBaños(Integer.parseInt(request.getParameter("banos")));
            vivienda.setAmueblado(request.getParameter("amueblado") != null);
            vivienda.setActivo(request.getParameter("disponible") != null);

            // Procesar nuevas fotos (opcional)
            List<FotoVivienda> nuevasFotos = new ArrayList<>();
            Collection<Part> partes = request.getParts();
            for (Part parte : partes) {
                if ("foto".equals(parte.getName()) && parte.getSize() > 0) {
                    String nombreArchivo = generarNombreUnico(parte.getSubmittedFileName(), vivienda.getId());
                    String rutaRelativa = "imagenes/viviendas/" + nombreArchivo;

                    FotoVivienda foto = new FotoVivienda();
                    foto.setRuta(rutaRelativa);
                    foto.setVivienda(vivienda);

                    String rutaExterna = "/Users/pauladominguez/Desktop/HomeCheck/web/imagenes/viviendas/";
                    File archivo = new File(rutaExterna, nombreArchivo);
                    parte.write(archivo.getAbsolutePath());

                    nuevasFotos.add(foto);
                }
            }

            if (!nuevasFotos.isEmpty()) {
                vivienda.getFotos().addAll(nuevasFotos);
            }

            servicioVivienda.edit(vivienda);
            request.setAttribute("vivienda", vivienda);
            request.setAttribute("exito", "Los cambios se guardaron correctamente.");
            request.getRequestDispatcher("/usuarios/editarMiVivienda.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al actualizar la vivienda: " + e.getMessage());
            request.getRequestDispatcher("/usuarios/editarMiVivienda.jsp").forward(request, response);
        } finally {
            emf.close();
        }
    }

    private String generarNombreUnico(String nombreOriginal, Long idVivienda) {
        String extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
        long timestamp = System.currentTimeMillis();
        return "foto_" + idVivienda + "_" + timestamp + extension;
    }

    @Override
    public String getServletInfo() {
        return "Controlador para editar una vivienda con imágenes opcionales.";
    }
}
