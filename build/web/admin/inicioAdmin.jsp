<%-- 
    Document   : inicioAdmin
    Created on : 15 abr 2025, 17:56:46
    Author     : pauladominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-ES">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>HomeCheck</title>
        <link rel="stylesheet" type="text/css" href="../css/style.css">
    </head>
    <body>
        <header>
            <nav class="logoCabecera">
                LOGO
            </nav>
            <nav class="menuCabecera">
                <ul>
                    <li>
                        <a href="Controladores.Usuarios/ControladorBuscarExperiencias"><svg  xmlns="http://www.w3.org/2000/svg"  width="20"  height="20"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-home"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M5 12l-2 0l9 -9l9 9l-2 0" /><path d="M5 12v7a2 2 0 0 0 2 2h10a2 2 0 0 0 2 -2v-7" /><path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v6" /></svg> Explorar viviendas</a>
                    </li>
                    <li>
                        <a href="Controladores.Usuarios/ControladorPublicarVivienda"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-speakerphone"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M18 8a3 3 0 0 1 0 6" /><path d="M10 8v11a1 1 0 0 1 -1 1h-1a1 1 0 0 1 -1 -1v-5" /><path d="M12 8h0l4.524 -3.77a.9 .9 0 0 1 1.476 .692v12.156a.9 .9 0 0 1 -1.476 .692l-4.524 -3.77h-8a1 1 0 0 1 -1 -1v-4a1 1 0 0 1 1 -1h8" /></svg> Publicar propiedad</a>
                    </li>
                    <li>
                        <a href="buscarExperiencias.html"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="none"  stroke="whitesmoke"  stroke-width="2"  stroke-linecap="round"  stroke-linejoin="round"  class="icon icon-tabler icons-tabler-outline icon-tabler-question-mark"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M8 8a3.5 3 0 0 1 3.5 -3h1a3.5 3 0 0 1 3.5 3a3 3 0 0 1 -2 3a3 4 0 0 0 -2 4" /><path d="M12 19l0 .01" /></svg> Cómo funciona</a>
                    </li>
                    <li>
                        <a href="Controladores.Usuarios/ControladorUsuarios?editar=true"><svg  xmlns="http://www.w3.org/2000/svg"  width="24"  height="24"  viewBox="0 0 24 24"  fill="whitesmoke"  class="icon icon-tabler icons-tabler-filled icon-tabler-user"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 2a5 5 0 1 1 -5 5l.005 -.217a5 5 0 0 1 4.995 -4.783z" /><path d="M14 14a5 5 0 0 1 5 5v1a2 2 0 0 1 -2 2h-10a2 2 0 0 1 -2 -2v-1a5 5 0 0 1 5 -5h4z" /></svg></a>
                    </li>
                    <li>
                        <a href="/HomeCheck/ControladorLogin?accion=logout" class="boton-cerrar-sesion">Cerrar Sesión</a>
                    </li>
                </ul>
            </nav>
        </header>

        <main class="mainInicioAdmin">
            <h2>Portal de Administrador</h2>
            <section class="botonesAdministrador">
                <div>
                    <button class="botonAdmin" onclick="location.href='ControladorGraficaPisos'">Ver gráfica de cantidad pisos por ciudad</button>
                    <button class="botonAdmin" onclick="location.href='ControladorGraficaPisosAlquilados'">Ver gráfica de cantidad de pisos alquilados por ciudad</button>
                </div>
                <div>
                    <button class="botonAdmin" onclick="location.href='ControladorGestionUsuarios'">Gestionar Usuarios</button>
                    <button class="botonAdmin" onclick="location.href='ControladorGestionValoraciones'">Gestionar Valoraciones</button>
                </div>
                <div>
                    <button class="botonAdmin" onclick="location.href='ControladorGestionComentarios'">Gestionar Comentarios</button>
                    <button class="botonAdmin" onclick="location.href='ControladorGestionPisos'">Gestionar Pisos</button>
                </div>
                <div>
                    <button class="botonAdmin" onclick="location.href='ControladorDescargarCodigos'">Descargar códigos de la app</button>
                    <button class="botonAdmin" onclick="location.href='ControladorPlanEmpresa'">Ver plan de Empresa</button>
                </div>
            </section>
        </main>

        <footer>

        </footer>
        <script src="js/range.js"></script>
    </body>
</html>
