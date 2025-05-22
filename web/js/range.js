const rangoInput = document.getElementById('rangoPrecio');
const precioOutput = document.getElementById('precioOutput');

// Función para actualizar el valor del output
function actualizarPrecio() {
    precioOutput.textContent = rangoInput.value;
}

// Agregar un event listener para actualizar el output cuando se mueva el rango
rangoInput.addEventListener('input', actualizarPrecio);

// Inicializar el valor del output
actualizarPrecio();

/* para que el boton de busqueda te redirija a buscarExperiencias.hmtl */
function redirigir() {
    window.location.href = 'buscarExperiencias.html';
}