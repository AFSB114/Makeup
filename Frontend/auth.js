document.addEventListener("DOMContentLoaded", function () {
    const userId = localStorage.getItem("userId");
  
    const yesLoginElements = document.querySelectorAll(".yes-login");
    const noLoginElements = document.querySelectorAll(".no-login");
  
    if (userId) {
      yesLoginElements.forEach(el => el.classList.remove("d-none"));
      noLoginElements.forEach(el => el.classList.add("d-none"));
    } else {
      yesLoginElements.forEach(el => el.classList.add("d-none"));
      noLoginElements.forEach(el => el.classList.remove("d-none"));
    }
  });
  
  function logout() {
    localStorage.clear();
    const carritoBody = document.querySelector("#carritoOffcanvas .offcanvas-body");
    if (carritoBody) {
      carritoBody.innerHTML = `
        <h6 class="text-secondary">DE COMPRAS</h6>
        <p class="text-muted">Inicia sesión para ver tu carrito</p>
      `;
    }
    window.location.href = "index.html"; 
  }
  