const botonActualizar = document.getElementById("btnActualizar");
const form = document.getElementById("formPropiedad");

botonActualizar.addEventListener("click", async () => {
    const id          = document.getElementById("id").value.trim();
    const nombre      = document.getElementById("nombre").value.trim();
    const descripcion = document.getElementById("descripcion").value.trim();
    const precioBase  = parseFloat(document.getElementById("precioBase").value);
    const superficieM2= parseFloat(document.getElementById("superficieM2").value);
    const ambientes   = parseInt(document.getElementById("ambientes").value, 10);
    const ubicacionId = parseInt(document.getElementById("ubicacion").value, 10);
    const tipoId      = parseInt(document.getElementById("tipoPropiedad").value, 10);
    const urlImagen   = document.getElementById("urlImagen").value.trim();
    const orden       = parseInt(document.getElementById("orden").value, 10);

    // Validación
    if (!id || !nombre || !descripcion || isNaN(precioBase) || isNaN(superficieM2) ||
        isNaN(ambientes) || isNaN(ubicacionId) || isNaN(tipoId) || !urlImagen || isNaN(orden)) {
        alert("Por favor completá todos los campos correctamente");
        return;
    }

    try {
        // Verificar existencia de la propiedad
        const verificarResp = await fetch(`http://localhost:8080/propiedades/${id}`);
        if (!verificarResp.ok) throw new Error("No se encontró una propiedad con ese ID");

        // Preparar objeto propiedad actualizado
        const propiedadActualizada = {
            nombre,
            descripcion,
            precioBase,
            moneda: "ARS",
            superficieM2,
            ambientes,
            ubicacion: { id: ubicacionId },
            tipoPropiedad: { id: tipoId }
        };

        // PUT para actualizar propiedad
        const putResp = await fetch(`http://localhost:8080/propiedades/modificar/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(propiedadActualizada)
        });

        if (!putResp.ok) {
            const msg = await putResp.text();
            throw new Error(msg || "Error al actualizar la propiedad");
        }

        // Actualizar imagen
        const imagenObj = {
            urlImagen,
            orden,
            propiedad: { id: parseInt(id, 10) }
        };

        const imgResp = await fetch(`http://localhost:8080/imagenes/agregar`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(imagenObj)
        });

        if (!imgResp.ok) {
            const msg = await imgResp.text();
            throw new Error(msg || "Error al actualizar la imagen");
        }

        alert("Propiedad actualizada correctamente");
        form.reset();

    } catch (err) {
        console.error(err);
        alert("Error: " + err.message);
    }
});
