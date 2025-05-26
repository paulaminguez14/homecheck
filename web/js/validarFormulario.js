document.querySelector('.formularioRegistro').addEventListener('submit', function (event) {
    const nombre = document.querySelector('[name="nombre"]').value.trim();
    const apellidos = document.querySelector('[name="apellidos"]').value.trim();
    const email = document.querySelector('[name="email"]').value.trim();
    const password = document.querySelector('[name="password"]').value;
    const password2 = document.querySelector('[name="password2"]').value;
    const telefono = document.querySelector('[name="telefono"]').value.trim();

    // Validación de campos vacíos
    if (!nombre || !apellidos || !email || !password || !password2 || !telefono) {
        alert('Todos los campos son obligatorios.');
        event.preventDefault();
        return;
    }

    // Validación de email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert('Introduce un email válido.');
        event.preventDefault();
        return;
    }

    // Validación de contraseñas iguales
    if (password !== password2) {
        alert('Las contraseñas no coinciden.');
        event.preventDefault();
        return;
    }

    // Validación de longitud mínima de contraseña
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9]).{8,}$/;

    if (!passwordRegex.test(password)) {
        alert('La contraseña debe tener al menos 8 caracteres, incluyendo una letra mayúscula, una minúscula, un número y un carácter especial.');
        event.preventDefault();
        return;
    }

    // Validación de teléfono
    const telefonoRegex = /^\d{9}$/;
    if (!telefonoRegex.test(telefono)) {
        alert('Introduce un número de teléfono válido de 9 dígitos.');
        event.preventDefault();
        return;
    }
});