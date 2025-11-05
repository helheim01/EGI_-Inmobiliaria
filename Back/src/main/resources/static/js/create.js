// Obtener elementos
const botonRegistrar  = document.getElementById("btnRegistrar");
const formPropiedad   = document.getElementById("formPropiedad");

// ✅ Validaciones en tiempo real (mientras el usuario escribe)

// Campos que solo aceptan números o decimales
const camposSoloNumeros = ["precioBase", "superficieM2", "ambientes", "ubicacion", "tipoPropiedad", "orden"];
camposSoloNumeros.forEach(id => {
  const input = document.getElementById(id);
  input.addEventListener("input", () => {
    input.value = input.value.replace(/[^0-9.,]/g, "");
  });
});

botonRegistrar.addEventListener("click", async () => {
    // 1️⃣ Obtener valores del formulario
    const nombre        = document.getElementById("nombre").value.trim();
    const descripcion   = document.getElementById("descripcion").value.trim();
    const precioBaseStr = document.getElementById("precioBase").value.trim();
    const superficieStr = document.getElementById("superficieM2").value.trim();
    const ambientesStr  = document.getElementById("ambientes").value.trim();
    const ubicacionId   = parseInt(document.getElementById("ubicacion").value, 10);
    const tipoPropiedadId = parseInt(document.getElementById("tipoPropiedad").value, 10);
    const urlImagen     = document.getElementById("urlImagen").value.trim();
    const ordenStr      = document.getElementById("orden").value.trim();

    // 3️⃣ Validaciones numéricas (solo números o decimales)
    const soloNumeros = /^[0-9]+([.,][0-9]+)?$/;

    if (!soloNumeros.test(precioBaseStr)) {
        alert("El precio base debe ser un número válido.");
        return;
    }

    if (!soloNumeros.test(superficieStr)) {
        alert("La superficie debe ser un número válido.");
        return;
    }

    if (!soloNumeros.test(ambientesStr)) {
        alert("La cantidad de ambientes debe ser un número válido.");
        return;
    }

    if (!soloNumeros.test(ordenStr)) {
        alert("El orden debe ser un número válido.");
        return;
    }

    // 4️⃣ Convertir a números después de validar
    const precioBase   = parseFloat(precioBaseStr.replace(",", "."));
    const superficieM2 = parseFloat(superficieStr.replace(",", "."));
    const ambientes    = parseInt(ambientesStr, 10);
    const orden        = parseInt(ordenStr, 10);

    // 5️⃣ Validación final de campos vacíos o incorrectos
    if (!nombre || !descripcion || isNaN(precioBase) || isNaN(superficieM2) ||
        isNaN(ambientes) || isNaN(ubicacionId) || isNaN(tipoPropiedadId) ||
        !urlImagen || isNaN(orden)) {
        alert("Por favor completá todos los campos correctamente.");
        return;
    }

    // 6️⃣ Crear JSON de propiedad
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
        // 7️⃣ POST para crear propiedad
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

        // 8️⃣ Crear JSON de imagen
        const imagen = {
            urlImagen,
            orden,
            propiedad: { id: propiedadId }
        };

        // 9️⃣ POST para crear imagen asociada
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
