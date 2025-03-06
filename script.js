document.addEventListener("DOMContentLoaded", function () {
    console.log("Página cargada correctamente");
    
    const form = document.querySelector("form");
    form.addEventListener("submit", function (event) {
        event.preventDefault();
        alert("Formulario enviado correctamente");
    });
});