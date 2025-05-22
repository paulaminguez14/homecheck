<%-- 
    Document   : registro
    Created on : 22 may 2025, 19:48:14
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
                        <li class="menu-usuario">
                            <div class="dropdown">
                                <button class="dropdown-toggle" style="display: flex; align-items: center; justify-content: center; font-size: medium; font-weight: bold;">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                         fill="whitesmoke" class="icon icon-tabler icons-tabler-filled icon-tabler-user">
                                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                                    <path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z"/>
                                    <path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z"/>
                                    </svg><p style="color: white; margin-left: 5px; margin-right: 10px;">Hola, ${usuario.nombre}</p>
                                    <svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="white"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-chevron-compact-down"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M4 11l8 3l8 -3" /></svg>
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
                    <form method="post" class="formularioRegistro">
                        <c:if test="${not empty idUsuario}">
                            <input type="hidden" name="idUsuario" value="${idUsuario}">
                        </c:if>

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
                        <div class="datosRegistro">
                            <label for="activo">Activo</label>
                            <input type="checkbox" name="activo" <c:if test="${activo}">checked</c:if>>
                        </div>
                        <div class="datosRegistro">
                            <label for="tipo">Rol</label>
                            <div class="custom-select-wrapper">
                                <select name="tipo" class="custom-select">
                                    <option value="USUARIO" ${tipo == 'USUARIO' ? 'selected' : ''}>Usuario</option>
                                    <option value="ADMIN" ${tipo == 'ADMIN' ? 'selected' : ''}>Administrador</option>
                                </select>
                            </div>
                        </div>

                        <c:if test="${not empty idUsuario}">
                            <input type="submit" name="editar" value="Guardar cambios" style="background-color: white; font-weight: bold;">
                        </c:if>
                        <c:if test="${empty idUsuario}">
                            <input type="submit" name="crear" value="Crear cuenta">
                        </c:if>
                    </form>

                    <br>
                    <c:if test="${not empty mensaje}">
                        <p style="color: green; margin-bottom: 20px;">${mensaje}</p>
                    </c:if>
                    <c:if test="${not empty error}">
                        <p style="color: red; margin-bottom: 20px;">${error}</p>
                    </c:if>

                    <a href="ControladorGestionUsuarios" class="enlaceVolverPublicarVivienda">Volver</a>
                </div>
            </section>
        </main>

        <footer>

        </footer>
        <script src="js/range.js"></script>
    </body>
</html>