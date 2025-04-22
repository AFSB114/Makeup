document.addEventListener("DOMContentLoaded", function () {
    mostrarUsuarios();
});

async function mostrarUsuarios() {
    let container = document.getElementById("usuariosContainer");
    container.innerHTML = "<p class='text-center'>Cargando usuarios...</p>";

    try {
        let response = await fetch("http://localhost:8085/api/v1/user/");
        if (!response.ok) throw new Error("Error al obtener usuarios");

        let usuarios = await response.json();
        if (usuarios.length === 0) {
            container.innerHTML = "<p class='text-center'>No hay usuarios registrados.</p>";
            return;
        }

        let tablaHTML = `
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Contraseña</th>
                            <th>Dirección</th>
                            <th>Teléfono</th>
                            <th>Rol</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${usuarios.map(user => `
                            <tr id="fila-${user.id}">
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td>${user.password}</td>
                                <td>${user.address}</td>
                                <td>${user.phone}</td>
                                <td>${user.role}</td>
                                <td>
                                    <button class="btn btn-warning btn-sm" 
                                        onclick="editarUsuario(${user.id}, '${encodeURIComponent(user.name)}', '${encodeURIComponent(user.email)}', '${encodeURIComponent(user.password)}', '${encodeURIComponent(user.address)}', '${encodeURIComponent(user.phone)}', '${encodeURIComponent(user.role)}')">
                                        Editar
                                    </button>
                                    <button class="btn btn-danger btn-sm" onclick="eliminarUsuario(${user.id})">Eliminar</button>
                                </td>
                            </tr>
                        `).join('')}
                    </tbody>
                </table>
            </div>
        `;
        container.innerHTML = tablaHTML;
    } catch (error) {
        container.innerHTML = "<p class='text-danger text-center'>Error al cargar usuarios.</p>";
        console.error(error);
    }
}

async function eliminarUsuario(id) {
    if (!confirm("¿Estás seguro de que quieres eliminar este usuario?")) return;

    try {
        let response = await fetch(`http://localhost:8085/api/v1/user/${id}`, {
            method: "DELETE",
        });

        if (!response.ok) throw new Error("Error al eliminar usuario");

        alert("Usuario eliminado correctamente");
        document.getElementById(`fila-${id}`).remove(); 
    } catch (error) {
        alert("Error al eliminar usuario: " + error.message);
    }
}

function editarUsuario(id, nombre, email, password, direccion, telefono, rol) {
    let container = document.getElementById("editarContainer");
    container.innerHTML = `
        <div class="form-container p-4 rounded shadow-lg text-center bg-light">
            <h2 class="mb-3">Editar Usuario</h2>
            <form id="editarForm" onsubmit="return guardarEdicion(event, ${id})">
                <div class="mb-3 text-start">
                    <label>Nombre:</label>
                    <input type="text" class="form-control" id="editNombre" value="${decodeURIComponent(nombre)}" required>
                </div>
                <div class="mb-3 text-start">
                    <label>Email:</label>
                    <input type="email" class="form-control" id="editEmail" value="${decodeURIComponent(email)}" required>
                </div>
                <div class="mb-3 text-start">
                    <label>Contraseña:</label>
                    <input type="password" class="form-control" id="editPassword" value="${decodeURIComponent(password)}" required>
                </div>
                <div class="mb-3 text-start">
                    <label>Dirección:</label>
                    <input type="text" class="form-control" id="editDireccion" value="${decodeURIComponent(direccion)}" required>
                </div>
                <div class="mb-3 text-start">
                    <label>Teléfono:</label>
                    <input type="text" class="form-control" id="editTelefono" value="${decodeURIComponent(telefono)}" required>
                </div>
                <div class="mb-3 text-start">
                    <label>Rol:</label>
                    <input type="text" class="form-control" id="editRol" value="${decodeURIComponent(rol)}" required>
                </div>
                <button type="submit" class="btn btn-success w-100">Guardar Cambios</button>
            </form>
        </div>
    `;
}

async function guardarEdicion(event, id) {
    event.preventDefault();

    let nombre = document.getElementById("editNombre").value;
    let email = document.getElementById("editEmail").value;
    let password = document.getElementById("editPassword").value;
    let direccion = document.getElementById("editDireccion").value;
    let telefono = document.getElementById("editTelefono").value;
    let rol = document.getElementById("editRol").value;

    let bodyContent = JSON.stringify({
        name: nombre,
        email: email,
        password: password,
        address: direccion,
        phone: telefono,
        role: rol
    });

    try {
        let response = await fetch(`http://localhost:8085/api/v1/user/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: bodyContent,
        });

        if (!response.ok) throw new Error("Error al actualizar usuario");

        alert("Usuario actualizado correctamente");
        location.reload(); 
    } catch (error) {
        alert("Error al actualizar usuario: " + error.message);
    }
}
