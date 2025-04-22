document.addEventListener("DOMContentLoaded", async function () {
  const container = document.getElementById("productosContainer");
  if (!container) return;

  try {
    const res = await fetch("http://localhost:8085/api/v1/products/");
    const productos = await res.json();
    
    container.innerHTML = productos.map(product => `
      <div class="col-md-3 mb-4">
        <div class="card h-100">
          <a href="producto.html?id=${product.product_id}">
            <div class="img-container">
              <img src="${product.image}" class="card-img-top producto-img" alt="${product.name}">
            </div>
            <div class="card-body text-center">
              <h6 class="card-title">${product.name}</h6>
              <p class="card-text fw-bold">$${product.price}</p>
            </div>
          </a>
        </div>
      </div>
    `).join('');

  } catch (err) {
    container.innerHTML = "<p class='text-danger'>Error al cargar productos.</p>";
    console.error(err);
  }
});
