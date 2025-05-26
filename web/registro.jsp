<%-- 
    Document   : registro
    Created on : 10 mar 2025, 19:48:14
    Author     : pauladominguez
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HomeCheck</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <header>
            <nav class="logoCabecera">
                <img src="${pageContext.request.contextPath}/imagenes/logo.png" style="width: 130px;">
            </nav>
            <c:if test="${not empty sessionScope.usuario}">
                <!-- Botón hamburguesa -->
                <div class="menu-toggle" onclick="toggleMenu()" style="cursor: pointer;">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="white" viewBox="0 0 24 24">
                    <path d="M3 6h18M3 12h18M3 18h18" stroke="white" stroke-width="2" stroke-linecap="round"/>
                    </svg>
                </div>
                <nav class='menuCabecera' id="menu">
                    <ul style="list-style: none; margin: 0; padding: 10px; display: flex; flex-direction: column; gap: 10px;">
                        <li>
                            <a href="ControladorBuscarExperiencias"><svg  xmlns="http://www.w3.org/2000/svg"  width="20"  height="20"  viewBox="0 0 24 24"  fill="none"  stroke="black"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-home"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 12l-2 0l9 -9l9 9l-2 0" /><path d="M5 12v7a2 2 0 0 0 2 2h10a2 2 0 0 0 2 -2v-7" /><path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v6" /></svg> Explorar viviendas</a>
                        </li>
                        <li>
                            <a href="ControladorPublicarVivienda"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="black"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-speakerphone"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M18 8a3 3 0 0 1 0 6" /><path d="M10 8v11a1 1 0 0 1 -1 1h-1a1 1 0 0 1 -1 -1v-5" /><path d="M12 8h0l4.524 -3.77a.9 .9 0 0 1 1.476 .692v12.156a.9 .9 0 0 1 -1.476 .692l-4.524 -3.77h-8a1 1 0 0 1 -1 -1v-4a1 1 0 0 1 1 -1h8" /></svg> Publicar propiedad</a>
                        </li>
                        <li>
                            <a href="ControladorComoFunciona"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="black"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-question-mark"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M8 8a3.5 3 0 0 1 3.5 -3h1a3.5 3 0 0 1 3.5 3a3 3 0 0 1 -2 3a3 4 0 0 0 -2 4" /><path d="M12 19l0 .01" /></svg> Cómo funciona</a>
                        </li>
                        <li class="menu-usuario">
                            <div class="dropdown">
                                <button class="dropdown-toggle" style="display: flex; align-items: center; justify-content: center; font-size: medium; font-weight: bold;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                         fill="black" class="icon icon-tabler icons-tabler-filled icon-tabler-user">
                                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                                    <path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z"/>
                                    <path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z"/>
                                    </svg><p style="color: black; margin-left: 5px; margin-right: 10px;">Hola, ${usuario.nombre}</p>
                                    <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="black"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-chevron-compact-down"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M4 11l8 3l8 -3" /></svg>
                                </button>
                                <div class="dropdown-menu">
                                    <div class="divDropDown">
                                        <a href="/HomeCheck/Controladores.Usuarios/ControladorComentarios?idUsuario=${idUsuario}" class="dropdown-item">Mi puntuación</a>
                                        <a href="/HomeCheck/Controladores.Usuarios/ControladorListarPisosPropios" class="dropdown-item">Ver mis Pisos</a>
                                        <c:if test="${usuario.rol == 'ADMIN'}">
                                            <a href="/HomeCheck/Controladores.Admin/ControladorInicioAdmin" class="dropdown-item">Panel de Administración</a>
                                        </c:if>
                                        <a href="/HomeCheck/ControladorLogin?accion=logout" class="dropdown-item">Cerrar Sesión</a>
                                        <form method="post" action="ControladorUsuarios?eliminar=true" onsubmit="return confirm('¿Estás seguro de que deseas eliminar esta cuenta?');">
                                            <input type="hidden" name="idUsuario" value="${idUsuario}">
                                            <input type="submit" name="eliminar" value="Eliminar mi cuenta" class="dropdown-item" style="background-color: transparent; border: none; cursor: pointer; font-size: medium; font-weight: bold;">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </header>
        <main class="mainLogin">
            <section class="inicioSesion">
                <div class="divRegistro">
                    <h1>${empty idUsuario ? "Crear" : "Editar"} Usuario</h1>
                    <br>
                    <form method="post" class="formularioRegistro" action="ControladorUsuarios">
                        <c:if test="${not empty idUsuario}">
                            <input type="hidden" name="idUsuario" value="${idUsuario}">
                        </c:if>
                        <input type="hidden" name="activo" value="1">
                        <input type="hidden" name="tipo" value="USUARIO">

                        <div class="datosRegistro">
                            <label for="nombre">Nombre</label> 
                            <input type="text" name="nombre" value="${not empty nombre ? nombre : ''}" maxlength="20" required="">
                        </div>
                        <div class="datosRegistro">
                            <label for="apellidos">Apellidos</label>
                            <input type="text" name="apellidos" value="${not empty apellidos ? apellidos : ''}" maxlength="20" required="">
                        </div>
                        <div class="datosRegistro">
                            <label for="email">Email</label>
                            <input type="email" name="email" value="${not empty email ? email : ''}" maxlength="50" required="">
                        </div>
                        <div class="datosRegistro">
                            <label for="password">Contraseña</label>
                            <input type="password" name="password" value="${not empty password ? password : ''}" maxlength="30" required="">
                        </div>
                        <div class="datosRegistro">
                            <label for="password2">Repetir contraseña</label>
                            <input type="password" name="password2" value="${not empty password2 ? password2 : ''}" maxlength="30" required="">
                        </div>
                        <div class="datosRegistro">
                            <label for="telefono">Teléfono:</label>
                            <input type="text" name="telefono" value="${not empty telefono ? telefono : ''}" maxlength="9" pattern="\d{9}" required="">
                        </div>

                        <input class="botonInicioSesion" type="submit" name="${empty idUsuario ? 'crear' : 'editar'}" value="Guardar">
                    </form>

                    <br>

                    <% if (request.getAttribute("mensaje") != null) {%>
                    <div style="color: green; font-weight: bold; margin: 30px;">
                        <%= request.getAttribute("mensaje")%>
                    </div>
                    <% } %>

                    <% if (request.getAttribute("error") != null) {%>
                    <div style="color: red; font-weight: bold; margin: 30px;">
                        <%= request.getAttribute("error")%>
                    </div>
                    <% }%>
                    <c:if test="${not empty sessionScope.error}">
                        <div class="error">${sessionScope.error}</div>
                        <c:remove var="error" scope="session"/>
                    </c:if>

                    <a href="#" onclick="history.back(); setTimeout(() => location.reload(), 100);" class="enlaceVolverPublicarVivienda">Volver</a>
                </div>
            </section>
        </main>
        <c:if test="${not empty idUsuario}">
            <footer class="footer">
                <div class="footer-container">
                    <div class="footer-column">
                        <h3>Asistencia</h3>
                        <ul>
                            <li><a href="#">Centro de ayuda</a></li>
                            <li><a href="#">AirCover</a></li>
                            <li><a href="#">Lucha contra la discriminación</a></li>
                            <li><a href="#">Ayuda a la discapacidad</a></li>
                            <li><a href="#">Opciones de cancelación</a></li>
                            <li><a href="#">¿Problemas en el barrio?</a></li>
                        </ul>
                    </div>

                    <div class="footer-column">
                        <h3>Cómo ser anfitrión</h3>
                        <ul>
                            <li><a href="#">Pon tu casa en HomeCheck</a></li>
                            <li><a href="#">AirCover para anfitriones</a></li>
                            <li><a href="#">Recursos para anfitriones</a></li>
                            <li><a href="#">Foro de la comunidad</a></li>
                            <li><a href="#">Ser un anfitrión responsable</a></li>
                            <li><a href="#">Clase gratuita sobre cómo ser anfitrión</a></li>
                            <li><a href="#">Busca un coanfitrión</a></li>
                        </ul>
                    </div>

                    <div class="footer-column">
                        <h3>HomeCheck</h3>
                        <ul>
                            <li><a href="#">Lanzamiento de verano de 2025</a></li>
                            <li><a href="#">Newsroom</a></li>
                            <li><a href="#">Nuevas funciones</a></li>
                            <li><a href="#">Empleo</a></li>
                            <li><a href="#">Inversores</a></li>
                            <li><a href="#">Tarjetas regalo</a></li>
                            <li><a href="#">Estancias con HomeCheck.org</a></li>
                        </ul>
                    </div>
                </div>

                <div class="footer-bottom">
                    <p>© 2025 HomeCheck, Inc. · <a href="#">Privacidad</a> · <a href="#">Condiciones</a> · <a href="#">Mapa del sitio</a> · <a href="#">Datos de la empresa</a></p>
                    <div class="footer-locale">
                        <span>🌐 Español (ES)</span>
                        <span>€ EUR</span>
                        <a href="#"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--!Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M512 256C512 114.6 397.4 0 256 0S0 114.6 0 256C0 376 82.7 476.8 194.2 504.5V334.2H141.4V256h52.8V222.3c0-87.1 39.4-127.5 125-127.5c16.2 0 44.2 3.2 55.7 6.4V172c-6-.6-16.5-1-29.6-1c-42 0-58.2 15.9-58.2 57.2V256h83.6l-14.4 78.2H287V510.1C413.8 494.8 512 386.9 512 256h0z"/></svg></a>
                        <a href="#"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--!Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64zm297.1 84L257.3 234.6 379.4 396H283.8L209 298.1 123.3 396H75.8l111-126.9L69.7 116h98l67.7 89.5L313.6 116h47.5zM323.3 367.6L153.4 142.9H125.1L296.9 367.6h26.3z"/></svg></a>
                        <a href="#"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--!Font Awesome Free 6.7.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M194.4 211.7a53.3 53.3 0 1 0 59.3 88.7 53.3 53.3 0 1 0 -59.3-88.7zm142.3-68.4c-5.2-5.2-11.5-9.3-18.4-12c-18.1-7.1-57.6-6.8-83.1-6.5c-4.1 0-7.9 .1-11.2 .1c-3.3 0-7.2 0-11.4-.1c-25.5-.3-64.8-.7-82.9 6.5c-6.9 2.7-13.1 6.8-18.4 12s-9.3 11.5-12 18.4c-7.1 18.1-6.7 57.7-6.5 83.2c0 4.1 .1 7.9 .1 11.1s0 7-.1 11.1c-.2 25.5-.6 65.1 6.5 83.2c2.7 6.9 6.8 13.1 12 18.4s11.5 9.3 18.4 12c18.1 7.1 57.6 6.8 83.1 6.5c4.1 0 7.9-.1 11.2-.1c3.3 0 7.2 0 11.4 .1c25.5 .3 64.8 .7 82.9-6.5c6.9-2.7 13.1-6.8 18.4-12s9.3-11.5 12-18.4c7.2-18 6.8-57.4 6.5-83c0-4.2-.1-8.1-.1-11.4s0-7.1 .1-11.4c.3-25.5 .7-64.9-6.5-83l0 0c-2.7-6.9-6.8-13.1-12-18.4zm-67.1 44.5A82 82 0 1 1 178.4 324.2a82 82 0 1 1 91.1-136.4zm29.2-1.3c-3.1-2.1-5.6-5.1-7.1-8.6s-1.8-7.3-1.1-11.1s2.6-7.1 5.2-9.8s6.1-4.5 9.8-5.2s7.6-.4 11.1 1.1s6.5 3.9 8.6 7s3.2 6.8 3.2 10.6c0 2.5-.5 5-1.4 7.3s-2.4 4.4-4.1 6.2s-3.9 3.2-6.2 4.2s-4.8 1.5-7.3 1.5l0 0c-3.8 0-7.5-1.1-10.6-3.2zM448 96c0-35.3-28.7-64-64-64H64C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V96zM357 389c-18.7 18.7-41.4 24.6-67 25.9c-26.4 1.5-105.6 1.5-132 0c-25.6-1.3-48.3-7.2-67-25.9s-24.6-41.4-25.8-67c-1.5-26.4-1.5-105.6 0-132c1.3-25.6 7.1-48.3 25.8-67s41.5-24.6 67-25.8c26.4-1.5 105.6-1.5 132 0c25.6 1.3 48.3 7.1 67 25.8s24.6 41.4 25.8 67c1.5 26.3 1.5 105.4 0 131.9c-1.3 25.6-7.1 48.3-25.8 67z"/></svg></a>
                    </div>
                </div>
            </footer>
        </c:if>
        <script>
            function toggleMenu() {
                const menu = document.getElementById('menu');
                menu.classList.toggle('active');
            }

            document.addEventListener('click', function (event) {
                const menu = document.getElementById('menu');
                const toggle = document.querySelector('.menu-toggle');
                if (!menu.contains(event.target) && !toggle.contains(event.target)) {
                    menu.classList.remove('active');
                }
            });
        </script>
        <script src="${pageContext.request.contextPath}/js/validarFormulario.js"></script>
    </body>
</html>