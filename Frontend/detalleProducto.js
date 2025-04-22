document.addEventListener("DOMContentLoaded", async function () {
  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");
  const container = document.getElementById("detalleProducto");

  if (!id) {
    container.innerHTML = "<p class='text-danger'>Producto no encontrado.</p>";
    return;
  }

  try {
    const res = await fetch(`http://localhost:8085/api/v1/products/${id}`);
    const producto = await res.json();
    container.innerHTML = `
      <div class="col-md-5">
        <img src="${producto.image}" alt="${producto.name}" class="img-fluid">
      </div>
      <div class="col-md-7">
        <h2>${producto.name}</h2>
        <p class="text-muted">${producto.description || "Sin descripción"}</p>
        <h4 class="text-danger">$${producto.price}</h4>
        <div class="d-flex align-items-center mb-3">
          <button class="btn btn-outline-dark me-2" onclick="cambiarCantidad(-1)">-</button>
          <span id="cantidad" class="px-3">1</span>
          <button class="btn btn-outline-dark ms-2" onclick="cambiarCantidad(1)">+</button>
        </div>
        <div class="d-flex align-items-center">
          <button class="btn btn-primary me-3" id="btnAgregarCarrito">AGREGAR A LA BOLSA</button>
          <button class="btn btn-warning me-2" onclick="editarProducto(${producto.product_id})">Editar</button>
          <button class="btn btn-danger" onclick="eliminarProducto(${producto.product_id})">Eliminar</button>
        </div>
      </div>
    `;

    document.getElementById("btnAgregarCarrito").addEventListener("click", async () => {
      const cantidad = parseInt(document.getElementById("cantidad").textContent);
      const userId = parseInt(localStorage.getItem("userId"));

      if (!userId || isNaN(userId)) {
        alert("Debes iniciar sesión para agregar productos a la bolsa.");
        return;
      }

      try {
        const res = await fetch("http://localhost:8085/api/v1/cart/", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            userId: userId,
            productId: producto.product_id,
            stock: cantidad
          })
        });

        if (res.ok) {
          alert("Producto agregado a la bolsa.");
          
          if (typeof cargarCarrito === "function") {
            cargarCarrito(userId);
          }
        } else {
          alert("No se pudo agregar el producto al carrito.");
        }
      } catch (err) {
        console.error("Error al agregar al carrito:", err);
        alert("Hubo un error al intentar agregar el producto.");
      }
    });

  } catch (err) {
    container.innerHTML = "<p class='text-danger'>Error al cargar el producto.</p>";
    console.error(err);
  }
  
});

function cambiarCantidad(cambio) {
  const cantidadEl = document.getElementById("cantidad");
  let actual = parseInt(cantidadEl.textContent);
  actual = Math.max(1, actual + cambio);
  cantidadEl.textContent = actual;
}

async function editarProducto(productId) {
  const formContainer = document.getElementById("detalleProducto");
  const res = await fetch(`http://localhost:8085/api/v1/products/${productId}`);
  const producto = await res.json();

  formContainer.innerHTML = `
    <h3>Editar Producto</h3>
    <form id="formEditarProducto">
      <div class="mb-3">
        <label for="name" class="form-label"></label>
        <input type="text" class="form-control" id="name" value="${producto.name}">
      </div>
      <div class="mb-3">
        <label for="description" class="form-label"></label>
        <textarea class="form-control" id="description">${producto.description}</textarea>
      </div>
      <div class="mb-3">
        <label for="price" class="form-label"></label>
        <input type="number" class="form-control" id="price" value="${producto.price}">
      </div>
      <div class="mb-3">
        <label for="image" class="form-label"></label>
        <input type="text" class="form-control" id="image" value="${producto.image}">
      </div>
      <button type="submit" class="btn btn-success">Guardar cambios</button>
    </form>
  `;

  const form = document.getElementById("formEditarProducto");
  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    const updatedProduct = {
      name: document.getElementById("name").value,
      description: document.getElementById("description").value,
      price: document.getElementById("price").value,
      image: document.getElementById("image").value
    };

    try {
      const updateRes = await fetch(`http://localhost:8085/api/v1/products/${productId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(updatedProduct)
      });

      if (updateRes.ok) {
        alert("Producto actualizado exitosamente!");
        window.location.href = `producto.html?id=${productId}`;
      } else {
        alert("Error al actualizar el producto.");
      }
    } catch (err) {
      console.error(err);
      alert("Hubo un error al actualizar el producto.");
    }
  });
}

async function eliminarProducto(productId) {
  const confirmDelete = confirm("¿Estás seguro de que deseas eliminar este producto?");
  if (confirmDelete) {
    try {
      const deleteRes = await fetch(`http://localhost:8085/api/v1/products/${productId}`, {
        method: "DELETE"
      });

      if (deleteRes.ok) {
        alert("Producto eliminado exitosamente!");
        window.location.href = "productos.html";
      } else {
        alert("Error al eliminar el producto.");
      }
    } catch (err) {
      console.error(err);
      alert("Hubo un error al eliminar el producto.");
    }
  }
}
