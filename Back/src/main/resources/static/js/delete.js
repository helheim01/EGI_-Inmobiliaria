const form = document.getElementById("formEliminar");
const btnEliminar = document.getElementById("btnEliminar");

btnEliminar.addEventListener("click", async () => {
    const id = document.getElementById("id").value.trim();

    if (!id) {
        alert("Por favor, ingresá un ID válido");
        return;
    }

    try {
        // ✅ Verificar existencia de la propiedad con el endpoint correcto
        const verificarResp = await fetch(`http://localhost:8080/propiedades/buscar/${id}`);
        if (!verificarResp.ok) throw new Error("No se encontró una propiedad con ese ID");

        const propiedad = await verificarResp.json();

        const confirmacion = confirm(
            `¿Estás seguro de que querés eliminar la propiedad "${propiedad.nombre}" (ID: ${id})?`
        );
        if (!confirmacion) return;

        // ✅ Eliminar la propiedad
        const resp = await fetch(`http://localhost:8080/propiedades/eliminar/${id}`, {
            method: "DELETE"
        });

        if (resp.ok) {
            const msg = await resp.text();
            alert(msg || "Propiedad eliminada correctamente");
            form.reset();
        } else {
            const msg = await resp.text();
            throw new Error(msg || "No se pudo eliminar la propiedad");
        }

    } catch (err) {
        console.error(err);
        alert("Error: " + err.message);
    }
});
