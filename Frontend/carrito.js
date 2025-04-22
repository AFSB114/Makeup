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
        productoCard.classList.add("card", "mb-2");

        productoCard.innerHTML = `
          <div class="card-body d-flex justify-content-between align-items-center">
            <div>
              <h6 class="card-title">${item.product.name}</h6>
              <p class="card-text mb-0">Cantidad: ${item.stock}</p>
            </div>
            <button class="btn btn-sm btn-danger" onclick="eliminarDelCarrito(${item.cartId})">❌</button>
          </div>
        `;

        offcanvasBody.appendChild(productoCard);
      });
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
