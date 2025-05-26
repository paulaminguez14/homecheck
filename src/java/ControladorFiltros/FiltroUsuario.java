/*
 * FiltroUsuario.
 * Controla el acceso a todos los recursos de empleado registrado.
 */
package ControladorFiltros;

import Modelo.Entidades.Usuario;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Paula
 */
@WebFilter(filterName = "FiltroUsuario", urlPatterns = {"/usuarios/*", "/descarga/*"}, dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.INCLUDE})
public class FiltroUsuario implements Filter {
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse res = (HttpServletResponse)response;
            HttpSession sesion = req.getSession();
            Usuario usuario = (Usuario)sesion.getAttribute("usuario");
            if (usuario == null || !usuario.isActivo()) { // Si no hay usuario registrado
                res.sendRedirect(req.getServletContext().getContextPath() + "/ControladorLogin");
                return;
            }

            chain.doFilter(request, response);
    }


    
}
