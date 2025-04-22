function form(e) {
    e.preventDefault(); // Evita el envío por defecto del formulario

    // Obtener los valores de los campos
    var correo = document.getElementById("email").value;
    var telefono = document.getElementById("telefono").value;

    // Validar el correo (debe contener '@')
    var regexCorreo = /\S+@\S+\.\S+/;
    if (!regexCorreo.test(correo)) {
        alert("Por favor, ingrese un correo electrónico válido.");
        return; // Detiene la ejecución y no hace la solicitud
    }

    // Validar el teléfono (solo números)
    var regexTelefono = /^[0-9]+$/;
    if (!regexTelefono.test(telefono)) {
        alert("El teléfono solo debe contener números.");
        return; // Detiene la ejecución y no hace la solicitud
    }

    // Si las validaciones son correctas, hacer la solicitud POST
    return new Promise(async (resolve) => {
        let headersList = {
            "Accept": "*/*",
            "User-Agent": "web",
            "Content-Type": "application/json"
        };

        let bodyContent = JSON.stringify({
            "name": document.getElementById("nombre").value,
            "email": correo,  // Usar el correo validado
            "password": document.getElementById("password").value,
            "address": document.getElementById("direccion").value,
            "phone": telefono,  // Usar el teléfono validado
            "role": document.getElementById("rol").value
        });

        let response = await fetch("http://localhost:8085/api/v1/user/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        if (response.ok) {
            alert("Usuario registrado correctamente");

            document.querySelector(".form-container").style.display = "none";

            setTimeout(() => {
                window.location.href = "login.html";
            }, 1000);

        } else {
            const errorText = await response.text();
            alert("Error al registrar el usuario: " + errorText);
        }

        let data = await response.text();
        console.log(data);
    });
}
