<%-- 
    Document   : comentarios
    Created on : 11 mar 2025, 19:40:32
    Author     : pauladominguez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentarios y Valoraciones</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <header>
            <nav class="logoCabecera">
                LOGO
            </nav>
            <nav class="menuCabecera">
                <ul>
                    <li>
                        <a href="ControladorBuscarExperiencias"><svg  xmlns="http://www.w3.org/2000/svg"  width="20"  height="20"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-home"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 12l-2 0l9 -9l9 9l-2 0" /><path d="M5 12v7a2 2 0 0 0 2 2h10a2 2 0 0 0 2 -2v-7" /><path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v6" /></svg> Explorar viviendas</a>
                    </li>
                    <li>
                        <a href="ControladorPublicarVivienda"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-speakerphone"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M18 8a3 3 0 0 1 0 6" /><path d="M10 8v11a1 1 0 0 1 -1 1h-1a1 1 0 0 1 -1 -1v-5" /><path d="M12 8h0l4.524 -3.77a.9 .9 0 0 1 1.476 .692v12.156a.9 .9 0 0 1 -1.476 .692l-4.524 -3.77h-8a1 1 0 0 1 -1 -1v-4a1 1 0 0 1 1 -1h8" /></svg> Publicar propiedad</a>
                    </li>
                    <li>
                        <a href="buscarExperiencias.html"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-question-mark"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M8 8a3.5 3 0 0 1 3.5 -3h1a3.5 3 0 0 1 3.5 3a3 3 0 0 1 -2 3a3 4 0 0 0 -2 4" /><path d="M12 19l0 .01" /></svg> Cómo funciona</a>
                    </li>
                    <li>
                        <a href="ControladorUsuarios?editar=true"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="whitesmoke"  class="icon icon-tabler icons-tabler-filled icon-tabler-user"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z" /><path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z" /></svg></a>
                    </li>
                    <li>
                        <a href="/HomeCheck/ControladorLogin?accion=logout" class="boton-cerrar-sesion">Cerrar Sesión</a>
                    </li>
                </ul>
            </nav>
        </header>

        <main class="mainComentarios">
            <h1>Comentarios y Valoraciones de Usuario</h1>

            <!-- Mostrar Opiniones -->
            <c:if test="${not empty opiniones}">
                <h2>Opiniones</h2>
                <div class="comentarios">
                    <ul>
                        <c:forEach var="opinion" items="${opiniones}">
                            <li>
                                <p>${opinion.comentario}</p>
                                <p><small>Autor: ${opinion.autor.nombre} | Fecha: ${opinion.fecha}</small></p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <!-- Mostrar Valoraciones -->
            <c:if test="${not empty valoraciones}">
                <h2>Valoraciones</h2>
                <div class="valoraciones">
                    <ul>
                        <c:forEach var="valoracion" items="${valoraciones}">
                            <li>
                                <p>${valoracion.puntuacion} estrellas</p>
                                <p><small>Autor: ${valoracion.autor.nombre} | Fecha: ${valoracion.fecha}</small></p>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <!-- Mensaje si no hay comentarios o valoraciones -->
            <c:if test="${empty opiniones && empty valoraciones}">
                <p>No hay opiniones o valoraciones disponibles para este usuario.</p>
            </c:if>

            <a href="/HomeCheck/Controladores.Usuarios/ControladorUsuarios?idUsuario=${param.idUsuario}" class="enlaceVolver">Volver al perfil</a>
        </main>

        <footer>

        </footer>
        <script src="js/range.js"></script>
    </body>
</html>