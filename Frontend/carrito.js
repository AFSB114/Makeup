document.addEventListener("DOMContentLoaded", () => {
  const userId = parseInt(localStorage.getItem("userId"));
  const offcanvasBody = document.querySelector("#carritoOffcanvas .offcanvas-body");

  if (!userId || isNaN(userId)) {
    if (offcanvasBody) {
      offcanvasBody.innerHTML = `
        <div class="alert alert-warning text-center">
          Debes iniciar sesión para ver tu carrito.
        </div>
      `;
    }
    return;
  }

  cargarCarrito(userId);
});

function cargarCarrito(userId) {
  fetch(`http://localhost:8085/api/v1/cart/user/${userId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("No se pudo obtener el carrito");
      }
      return response.json();
    })
    .then(data => {
      const offcanvasBody = document.querySelector("#carritoOffcanvas .offcanvas-body");
      offcanvasBody.innerHTML = "";

      if (data.length === 0) {
        offcanvasBody.innerHTML = `
          <p class="text-muted text-center">Tu carrito está vacío.</p>
        `;
        return;
      }

      data.forEach(item => {
        const productoCard = document.createElement("div");
        productoCard.classList.add("producto-item");
        productoCard.id = `producto-${item.cartId}`; 
        productoCard.innerHTML = `
          <div class="flex-grow-1">
            <p class="mb-1">${item.product.name}</p>
            <div class="cantidad-container">
              <button class="btn btn-sm btn-outline-secondary" onclick="cambiarCantidad(${item.cartId}, -1)">-</button>
              <span class="cantidad-text">${item.stock}</span>
              <button class="btn btn-sm btn-outline-secondary" onclick="cambiarCantidad(${item.cartId}, 1)">+</button>
            </div>
          </div>
          <div class="text-end">
            <strong>$ ${item.product.price}</strong><br />
            <button class="btn btn-sm btn-outline-danger ms-2" onclick="eliminarDelCarrito(${item.cartId})">&#128465;</button>
          </div>
        `;

        offcanvasBody.appendChild(productoCard);
      });

      const verCarritoBtn = document.createElement("div");
      verCarritoBtn.classList.add("text-center", "mt-4");
      verCarritoBtn.innerHTML = `
        <button class="btn btn-primary" onclick="irAlCarrito()">Ver todo el carrito</button>
      `;
      offcanvasBody.appendChild(verCarritoBtn);
    })
    .catch(error => {
      console.error("Error al cargar el carrito:", error);
      const offcanvasBody = document.querySelector("#carritoOffcanvas .offcanvas-body");
      if (offcanvasBody) {
        offcanvasBody.innerHTML = `
          <div class="alert alert-danger text-center">
            Error al cargar el carrito. Intenta nuevamente.
          </div>
        `;
      }
    });
}

function cambiarCantidad(cartId, incremento) {
  const cantidadElement = document.querySelector(`#producto-${cartId} .cantidad-text`);
  let cantidad = parseInt(cantidadElement.textContent);

  if (cantidad + incremento > 0) {
    cantidad += incremento;
    cantidadElement.textContent = cantidad;

    fetch(`http://localhost:8085/api/v1/cart/update/${cartId}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ quantity: cantidad })
    })
      .then(response => {
        if (!response.ok) {
          throw new Error("No se pudo actualizar la cantidad");
        }
        const userId = parseInt(localStorage.getItem("userId"));
        cargarCarrito(userId);
      })
      .catch(error => {
        console.error("Error al actualizar la cantidad:", error);
      });
  }
}

function eliminarDelCarrito(cartId) {
  fetch(`http://localhost:8085/api/v1/cart/${cartId}`, {
    method: "DELETE"
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("No se pudo eliminar el producto del carrito");
      }
      const userId = parseInt(localStorage.getItem("userId"));
      cargarCarrito(userId);
    })
    .catch(error => {
      console.error("Error al eliminar del carrito:", error);
    });
}

function irAlCarrito() {
  window.location.href = "carrito2.html"; 
}
