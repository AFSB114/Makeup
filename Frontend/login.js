document.getElementById("loginForm").addEventListener("submit", function (event) {
  event.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  fetch("http://localhost:8085/api/v1/user/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email, password })
  })
    .then(res => {
      if (!res.ok) {
        throw new Error("Credenciales incorrectas");
      }
      return res.json();
    })
    .then(data => {
      localStorage.setItem("userId", data.id);
      localStorage.setItem("name", data.name);
      localStorage.setItem("email", data.email);
      alert("¡Sesión iniciada!");
      window.location.href = "index.html";
    })
    .catch(err => {
      console.error(err);
      alert("Error al iniciar sesión");
    });
});

