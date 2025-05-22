<%-- 
    Document   : editarMiVivienda
    Created on : 22 may 2025, 14:01:11
    Author     : pauladominguez
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Vivienda</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <header>
            <nav class="logoCabecera">
                <a href="../ControladorInicio"><img src="../imagenes/logo.png" style="width: 130px;"></a>
            </nav>
            <nav class="menuCabecera">
                <ul>
                    <li>
                        <a href="ControladorBuscarExperiencias"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="whitesmoke" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-home"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 12l-2 0l9 -9l9 9l-2 0" /><path d="M5 12v7a2 2 0 0 0 2 2h10a2 2 0 0 0 2 -2v-7" /><path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v6" /></svg> Explorar viviendas</a>
                    </li>
                    <li>
                        <a href="ControladorPublicarVivienda"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="whitesmoke" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-speakerphone"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M18 8a3 3 0 0 1 0 6" /><path d="M10 8v11a1 1 0 0 1 -1 1h-1a1 1 0 0 1 -1 -1v-5" /><path d="M12 8h0l4.524 -3.77a.9 .9 0 0 1 1.476 .692v12.156a.9 .9 0 0 1 -1.476 .692l-4.524 -3.77h-8a1 1 0 0 1 -1 -1v-4a1 1 0 0 1 1 -1h8" /></svg> Publicar propiedad</a>
                    </li>
                    <li>
                        <a href="ControladorComoFunciona"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="whitesmoke" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="icon icon-tabler icons-tabler-outline icon-tabler-question-mark"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M8 8a3.5 3 0 0 1 3.5 -3h1a3.5 3 0 0 1 3.5 3a3 3 0 0 1 -2 3a3 4 0 0 0 -2 4" /><path d="M12 19l0 .01" /></svg> Cómo funciona</a>
                    </li>
                    <li>
                        <a href="ControladorUsuarios?editar=true"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="whitesmoke" class="icon icon-tabler icons-tabler-filled icon-tabler-user"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z" /><path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z" /></svg></a>
                    </li>
                </ul>
            </nav>
        </header>

        <main class="mainPublicarVivienda">
            <div class="contenedorPublicarVivienda">
                <h1>Editar Vivienda</h1>

                <form action="ControladorEditarMiVivienda" method="post" class="formularioPublicarVivienda" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${vivienda.id}"/>

                    <div class="datosRegistro">
                        <label for="direccion">Dirección:</label>
                        <input type="text" id="direccion" name="direccion" value="${vivienda.direccion}" required>
                    </div>
                    <div class="datosRegistro">
                        <label for="precio">Precio:</label>
                        <input type="number" id="precio" name="precio" value="${vivienda.precio}" required>
                    </div>
                    <div class="datosRegistro">
                        <label for="ciudad">Ciudad:</label>
                        <input type="text" id="ciudad" name="ciudad" value="${vivienda.ciudad}" required>
                    </div>
                    <div class="datosRegistro">
                        <label for="provincia">Provincia:</label>
                        <input type="text" id="provincia" name="provincia" value="${vivienda.provincia}" required>
                    </div>
                    <div class="datosRegistro">
                        <label for="codigoPostal">Código Postal:</label>
                        <input type="text" id="codigoPostal" name="codigoPostal" value="${vivienda.codigoPostal}" required>
                    </div>
                    <div class="datosRegistro">
                        <label for="habitaciones">Habitaciones:</label>
                        <input type="number" id="habitaciones" name="habitaciones" value="${vivienda.habitaciones}" required>
                    </div>
                    <div class="datosRegistro">
                        <label for="banos">Baños:</label>
                        <input type="number" id="banos" name="banos" value="${vivienda.baños}" required>
                    </div>
                    <div class="datosRegistro">
                        <label for="amueblado">¿Está amueblada?</label>
                        <input type="checkbox" id="amueblado" name="amueblado" <c:if test="${vivienda.amueblado}">checked</c:if>>
                        </div>
                        <div class="datosRegistro">
                            <label for="disponible">¿Está disponible?</label>
                            <input type="checkbox" id="disponible" name="disponible" <c:if test="${vivienda.activo}">checked</c:if>>
                        </div>
                        <div class="datosRegistro">
                            <label for="descripcion">Descripción:</label>
                            <textarea class="descripcionVivienda" id="descripcion" name="descripcion" rows="4" cols="50">${vivienda.descripcion}</textarea>
                    </div>
                    <div class="datosRegistro">
                        <label for="foto">Actualizar fotos:</label>
                        <input style="background-color: white" type="file" id="foto" name="foto" accept="image/*" multiple>
                    </div>

                    <small>Deja en blanco si no deseas cambiar las fotos. Puedes seleccionar varias fotos manteniendo presionada Ctrl (Windows) o Cmd (Mac).</small>

                    <button type="submit" id="buscar">Guardar Cambios</button>
                </form>

                <form action="ControladorEliminarMiVivienda" method="post">
                    <input type="hidden" name="idVivienda" value="${vivienda.id}" />
                    <button type="submit" class="btnEliminar" id="buscar"
                            onclick="return confirm('¿Estás seguro de que deseas eliminar esta vivienda? Esta acción no se puede deshacer.');">
                        Eliminar Vivienda
                    </button>
                </form>


                <c:if test="${not empty error}">
                    <p style="color: red; font-weight: bold; margin: 30px;">${error}</p>
                </c:if>

                <c:if test="${not empty exito}">
                    <p style="color: green; font-weight: bold; margin: 30px;">${exito}</p>
                </c:if>

                <a href="javascript:history.back()" class="enlaceVolverPublicarVivienda">Volver</a>
            </div>
        </main>
    </body>
</html>