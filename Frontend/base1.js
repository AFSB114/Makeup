async function agregarproduct(e) {
    e.preventDefault();

    // Obtener los valores
    const nombre = document.getElementById("nombre").value;
    const descripcion = document.getElementById("descripcion").value.trim();
    const precio = document.getElementById("precio").value.trim();
    const cantidades = document.getElementById("cantidades").value.trim();
    const image = document.getElementById("image").value;
    const categoria = document.getElementById("categoria").value;

    // Validación: Descripción entre 50 y 200 caracteres
    if (descripcion.length < 50 || descripcion.length > 200) {
        alert("La descripción debe tener entre 50 y 200 caracteres.");
        return;
    }

    // Validación: Precio debe ser un número válido (permite decimales)
    if (!/^\d+(\.\d{1,2})?$/.test(precio)) {
        alert("El precio debe ser un número válido (puede tener hasta 2 decimales).");
        return;
    }

    // Validación: Cantidades deben ser un número entero
    if (!/^\d+$/.test(cantidades)) {
        alert("La cantidad debe ser un número entero.");
        return;
    }

    // Si todas las validaciones pasan, enviar el producto
    let headersList = {
        "Accept": "*/*",
        "User-Agent": "web",
        "Content-Type": "application/json"
    };

    let bodyContent = JSON.stringify({
        "name": nombre,
        "description": descripcion,
        "price": parseFloat(precio),
        "stock": parseInt(cantidades),
        "image": image,
        "categoryId": parseInt(categoria)
    });

    try {
        let response = await fetch("http://localhost:8085/api/v1/products/", {
            method: "POST",
            body: bodyContent,
            headers: headersList
        });

        let data = await response.json();
        console.log(data);

        if (response.ok) {
            alert("Producto agregado correctamente");
            window.location.href = "productos.html";
        } else {
            alert("Error al agregar el producto: " + data.message);
        }
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Hubo un problema al intentar agregar el producto.");
    }
}
