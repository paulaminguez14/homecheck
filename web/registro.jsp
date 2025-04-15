<%-- 
    Document   : registro
    Created on : 10 mar 2025, 19:48:14
    Author     : pauladominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                LOGO
            </nav>
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

                        <input class="botonInicioSesion" type="submit" name="${empty idUsuario ? 'crear' : 'editar'}" value="Aceptar">
                        <c:if test="${not empty idUsuario}">
                            <a href="/HomeCheck/Controladores.Usuarios/ControladorComentarios?idUsuario=${idUsuario}" class="botonComentarios">Mi puntuación</a>
                        </c:if>
                    </form>

                    <c:if test="${not empty idUsuario}">
                        <br>
                        <form method="post" action="ControladorUsuarios?eliminar=true">
                            <input type="hidden" name="idUsuario" value="${idUsuario}">
                            <input type="submit" name="eliminar" value="Eliminar" class="botonEliminar"
                                   onclick="return confirm('¿Estás seguro de que deseas eliminar el usuario ${idUsuario}?');">
                        </form>
                    </c:if>

                    <br>
                    <c:if test="${not empty sessionScope.error}">
                        <div class="error">${sessionScope.error}</div>
                        <c:remove var="error" scope="session"/>
                    </c:if>

                    <a href="/HomeCheck/ControladorLogin" class="enlaceVolver">Volver</a>
                </div>
            </section>
        </main>

        <footer>

        </footer>
        <script src="js/range.js"></script>
    </body>
</html>
