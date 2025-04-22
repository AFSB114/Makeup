document.addEventListener("DOMContentLoaded", function () {
    console.log("Página cargada correctamente");

    const form = document.querySelector("form");
    if (form) {
        form.addEventListener("submit", function (event) {
            event.preventDefault();
            alert("Formulario enviado correctamente");
        });
    }

    const container = document.querySelector(".comparador-container");
    const after = document.querySelector(".after");
    const slider = document.querySelector(".slider");

    if (container && after && slider) {
        let isDragging = false;

        slider.addEventListener("mousedown", () => {
            isDragging = true;
        });

        document.addEventListener("mousemove", (e) => {
            if (!isDragging) return;
            let rect = container.getBoundingClientRect();
            let x = e.clientX - rect.left;

            if (x < 0) x = 0;
            if (x > rect.width) x = rect.width;

            after.style.width = `${x}px`;
            slider.style.left = `${x}px`;
        });

        document.addEventListener("mouseup", () => {
            isDragging = false;
        });
    } else {
        console.warn("No se encontraron los elementos del comparador (esto es normal si no estás en esa página).");
    }
});
