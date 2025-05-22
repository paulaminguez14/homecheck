<%-- 
    Document   : comentarios
    Created on : 11 mar 2025, 19:40:32
    Author     : pauladominguez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentarios y Valoraciones</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <!-- RateYo CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">

        <!-- jQuery y RateYo JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
    </head>
    <body>
        <header>
            <nav class="logoCabecera">
                <a href="../ControladorInicio"><img src="../imagenes/logo.png" style="width: 130px;"></a>
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
                        <a href="ControladorComoFunciona"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-question-mark"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M8 8a3.5 3 0 0 1 3.5 -3h1a3.5 3 0 0 1 3.5 3a3 3 0 0 1 -2 3a3 4 0 0 0 -2 4" /><path d="M12 19l0 .01" /></svg> Cómo funciona</a>
                    </li>
                    <li>
                        <a href="ControladorUsuarios?editar=true"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="whitesmoke"  class="icon icon-tabler icons-tabler-filled icon-tabler-user"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z" /><path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z" /></svg></a>
                    </li>
                </ul>
            </nav>
        </header>

        <main class="mainDetalleVivienda" style="background-color: rgba(255, 255, 255, 0.95);">
                
            <a href="/HomeCheck/Controladores.Usuarios/ControladorUsuarios?idUsuario=${param.idUsuario}" 
               class="enlaceVolver" style="color: black; text-shadow: none;">Volver al perfil</a>

            <div class="divComentarios">
                <c:set var="sumaPuntuaciones" value="0" />
                <c:forEach var="v" items="${valoraciones}">
                    <c:set var="sumaPuntuaciones" value="${sumaPuntuaciones + v.puntuacion}" />
                </c:forEach>

                <c:set var="totalValoracionesUsuario" value="${fn:length(valoraciones)}" />
                <c:set var="mediaUsuario" value="${totalValoracionesUsuario > 0 ? sumaPuntuaciones / totalValoracionesUsuario : 0}" />
                <h1 style="margin-bottom: 10px;">Comentarios y Valoraciones del Usuario</h1>
                
                <fmt:formatNumber value="${mediaUsuario}" maxFractionDigits="2" minFractionDigits="2" var="mediaUsuarioFormateada" />
                <div style="display: flex; align-items: center;justify-content: center; gap: 10px; margin-bottom: 30px;">
                    <c:choose>
                        <c:when test="${totalValoracionesUsuario > 0}">
                            <div id="rating-casero" style="margin: 20px 0;"></div>
                            ${mediaUsuarioFormateada} / 5 (${totalValoracionesUsuario} valoraciones)
                        </c:when>
                        <c:otherwise>
                            No hay valoraciones todavía.
                        </c:otherwise>
                    </c:choose>
                </div>

                <c:if test="${empty comentarios}">
                    <p>No hay comentarios todavía. Sé el primero en comentar.</p>
                </c:if>
                <c:if test="${not empty comentarios or not empty valoraciones}">
                    <c:forEach var="comentario" items="${comentarios}">
                        <div class="comentario" style="border-bottom: 1px solid #ccc; padding: 10px 0; text-align: left; margin-bottom: 50px;">
                            <p><strong>${comentario.autor.nombre} ${comentario.autor.apellidos}</strong></p>
                            <p>${comentario.comentario}</p>
                        </div>
                    </c:forEach>
                </c:if>

                <!-- Mensaje si no hay comentarios o valoraciones -->
                <c:if test="${empty comentarios && empty valoraciones}">
                    <p style="margin-bottom: 20px;">No hay valoraciones disponibles para este usuario.</p>
                </c:if>
            </div>
        </main>
        <script>
            $(function () {
                // Para el usuario
                const mediaUsuarioStr = '${mediaUsuario}';
                const mediaUsuario = parseFloat(mediaUsuarioStr.replace(',', '.')) || 0;

                $("#rating-casero").rateYo({
                    rating: mediaUsuario.toFixed(2),
                    readOnly: true,
                    starWidth: "30px",
                    ratedFill: "#ffc107",
                    normalFill: "#ddd",
                    precision: 2
                });
            });
        </script>
    </body>
</html>