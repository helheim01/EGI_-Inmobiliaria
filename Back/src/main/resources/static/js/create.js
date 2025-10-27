// Obtener elementos
const botonRegistrar  = document.getElementById("btnRegistrar");
const formPropiedad   = document.getElementById("formPropiedad");

botonRegistrar.addEventListener("click", async () => {
    // 1️⃣ Obtener valores del formulario
    const nombre        = document.getElementById("nombre").value.trim();
    const descripcion   = document.getElementById("descripcion").value.trim();
    const precioBase    = parseFloat(document.getElementById("precioBase").value);
    const superficieM2  = parseFloat(document.getElementById("superficieM2").value);
    const ambientes     = parseInt(document.getElementById("ambientes").value, 10);
    const ubicacionId   = parseInt(document.getElementById("ubicacion").value, 10);
    const tipoPropiedadId = parseInt(document.getElementById("tipoPropiedad").value, 10);
    const urlImagen     = document.getElementById("urlImagen").value.trim();
    const orden         = parseInt(document.getElementById("orden").value, 10);

    // 2️⃣ Validación básica
    if (!nombre || !descripcion || isNaN(precioBase) || isNaN(superficieM2) ||
        isNaN(ambientes) || isNaN(ubicacionId) || isNaN(tipoPropiedadId) ||
        !urlImagen || isNaN(orden)) {
        alert("Por favor completá todos los campos correctamente.");
        return;
    }

    // 3️⃣ Crear JSON de propiedad
    const propiedad = {
        nombre,
        descripcion,
        precioBase,
        moneda: "ARS",
        superficieM2,
        ambientes,
        ubicacion: { id: ubicacionId },
        tipoPropiedad: { id: tipoPropiedadId }
    };

    try {
        // 4️⃣ POST para crear propiedad
        const respPropiedad = await fetch("http://localhost:8080/propiedades/agregar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(propiedad)
        });

        if (!respPropiedad.ok) {
            const msg = await respPropiedad.text();
            throw new Error(msg || "Error al crear propiedad");
        }

        const propiedadCreada = await respPropiedad.json();
        const propiedadId = propiedadCreada.id;

        // 5️⃣ Crear JSON de imagen
        const imagen = {
            urlImagen,
            orden,
            propiedad: { id: propiedadId }
        };

        // 6️⃣ POST para crear imagen asociada
        const respImagen = await fetch("http://localhost:8080/imagenes/agregar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(imagen)
        });

        if (!respImagen.ok) {
            const msg = await respImagen.text();
            throw new Error(msg || "Error al agregar imagen");
        }

        alert("Propiedad y imagen guardadas correctamente.");
        formPropiedad.reset();

    } catch (err) {
        console.error(err);
        alert("Ocurrió un error: " + err.message);
    }
});
