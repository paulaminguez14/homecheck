<%-- 
    Document   : login
    Created on : 10 mar 2025, 13:46:29
    Author     : pauladominguez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                <img src="imagenes/logo.png" style="width: 130px;">
            </nav>
        </header>

        <main class="mainLogin">
            <section class="inicioSesion">
                <div class="divInicioSesion">
                    <h1 class="tituloInicioSesion">Bienvenido</h1>
                    <h3 class="subtituloInicioSesion">Inicio de sesión</h3>
                    <form method="POST" class="formularioInicioSesion">
                        <label for="email" class="emailLogin" style="margin-right: 40px">Email</label>
                        <input type="email" name="email" required="">
                        <br><br>
                        <label for="password" class="passwordLogin">Contraseña</label>
                        <input type="password" name="password" required="">
                        <br><br>
                        <input type="submit" value="Iniciar Sesión" class="botonInicioSesion">
                        <a href="/HomeCheck/Controladores.Usuarios/ControladorUsuarios?crear=true" class="enlaceRegistro">¿Aún no te has registrado?</a>
                    </form>
                    <br><br>
                    <c:if test="${not empty requestScope.error}">
                        <div class="error" style="color: black;">${requestScope.error}</div>
                    </c:if>
                </div>
            </section>
        </main>

        <footer>

        </footer>
        <script src="js/range.js"></script>
    </body>
</html>
