document.addEventListener("DOMContentLoaded", async function () {
  const container = document.getElementById("productosContainer");
  if (!container) return;

  try {
    const res = await fetch("http://localhost:8085/api/v1/products/");
    const productos = await res.json();

    const productosPorCategoria = {};

    productos.forEach(product => {
      const categoriaNombre = product.category?.name?.trim() || 'Sin Categoría';

      if (!productosPorCategoria[categoriaNombre]) {
        productosPorCategoria[categoriaNombre] = [];
      }

      productosPorCategoria[categoriaNombre].push(product);
    });

    container.innerHTML = '';

    for (const categoria in productosPorCategoria) {
      const seccionCategoria = document.createElement('div');
      seccionCategoria.classList.add('mb-5');

      const tituloCategoria = document.createElement('h3');
      tituloCategoria.textContent = categoria;
      tituloCategoria.classList.add('text-center', 'mb-4', 'text-primary');
      seccionCategoria.appendChild(tituloCategoria);

      const filaProductos = document.createElement('div');
      filaProductos.classList.add('row', 'g-3');

      productosPorCategoria[categoria].forEach(product => {
        const productoDiv = document.createElement('div');
        productoDiv.className = 'col-md-3 mb-4';

        productoDiv.innerHTML = `
          <div class="card h-100" data-categoria="${product.category.name.trim()}">
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
        `;

        filaProductos.appendChild(productoDiv);
      });

      seccionCategoria.appendChild(filaProductos);
      container.appendChild(seccionCategoria);
    }

  } catch (err) {
    container.innerHTML = "<p class='text-danger'>Error al cargar productos.</p>";
    console.error(err);
  }
});

