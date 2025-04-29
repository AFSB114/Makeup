document.addEventListener('DOMContentLoaded', function() {
    const inputBuscar = document.getElementById('inputBuscar');
    const productosContainer = document.getElementById('productosContainer');
    if (!inputBuscar || !productosContainer) return;
  
    inputBuscar.addEventListener('keydown', function(e) {
      if (e.key !== 'Enter') return;
      e.preventDefault();
  
      const texto = inputBuscar.value.trim().toLowerCase();
      const cols = Array.from(productosContainer.querySelectorAll('.col-md-3'));
  
      if (!texto) {
        cols.forEach(col => {
          col.style.display = '';
          col.querySelector('.card').classList.remove('resaltar');
        });
        return;
      }
  
      let anyFound = false;
      cols.forEach(col => {
        const card = col.querySelector('.card');
        const name = card.querySelector('.card-title')?.textContent.trim().toLowerCase() || '';
  
        if (name.includes(texto)) {
          col.style.display = '';
          card.classList.add('resaltar');
          anyFound = true;
        } else {
          col.style.display = 'none';
          card.classList.remove('resaltar');
        }
      });
  
      if (!anyFound) {
        alert(`No se encontraron productos con el nombre "${texto}".`);
       
        cols.forEach(col => {
          col.style.display = '';
          col.querySelector('.card').classList.remove('resaltar');
        });
      }
    });
  });
  