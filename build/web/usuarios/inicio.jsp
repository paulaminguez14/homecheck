<%-- 
    Document   : inicio
    Created on : 10 mar 2025, 13:38:27
    Author     : pauladominguez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-ES">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HomeCheck</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
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

    <main class="mainInicio">
        <section class="seccionInicio">
            <div class="tituloInicio">
                <h1>HomeCkeck</h1>
                <h2>Tu casa, tu seguridad</h2>
            </div>
            <div>
                <form class="buscadorInicio">
                    <input type="text" id="ciudad" placeholder="Ciudad...">
                    <div class="selectFlecha">
                        <select id="tipo">
                            <optgroup>
                                <option label="Selecciona la duración del contrato"></option>
                                <option label="1 mes" value="1"></option>
                                <option label="2 meses" value="2"></option>
                                <option label="3 meses" value="3"></option>
                                <option label="4 meses" value="4"></option>
                                <option label="+ 5 meses" value="+5"></option>
                            </optgroup>
                        </select>
                        <i></i>
                    </div>
                    <label for="rangoPrecio" id="rango">Rango de precios</label>
                    <div class="rangoPrecio">
                        <input type="range" id="rangoPrecio" min="0" max="2000" step="50" value="500" style="width: 80%;">
                        <output for="rangoPrecio" id="precioOutput">500</output>
                    </div>
                    <label for="fecha" id="fecha">Fecha de mudanza</label>
                    <input type="date" id="fecha">
                    <div class="botonBusqueda">
                        <!-- que el onclick me lleve al html "buscarExperiencias.html" -->
                        <button id="buscar" onclick="redirigir(); return false;">Buscar</button>
                    </div>
                </form>                
            </div>
            <div class="footerInicio">
                <h3>Paula Domínguez Bernabé</h3>
                <h4>Copyright © 2025 HomeCheck. Todos los derechos reservados.</h4>
            </div>
        </section>
    </main>

    <footer>

    </footer>
    <script src="js/range.js"></script>
</body>
</html>