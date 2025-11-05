const btnBuscar = document.getElementById("btnBuscar");
const btnGuardar = document.getElementById("btnGuardar");
const form = document.getElementById("formPropiedad");

// ✅ Validaciones en tiempo real (mientras el usuario escribe)
const camposSoloNumeros = ["precioBase", "superficieM2", "ambientes", "ubicacion", "tipoPropiedad", "orden"];
camposSoloNumeros.forEach(id => {
  const input = document.getElementById(id);
  input.addEventListener("input", () => {
    input.value = input.value.replace(/[^0-9.,]/g, "");
  });
});

btnBuscar.addEventListener("click", async () => {
  const id = document.getElementById("idBuscar").value.trim();
  if (!id) {
    alert("Por favor, ingresá un ID válido");
    return;
  }

  try {
    const resp = await fetch(`http://localhost:8080/propiedades/buscar/${id}`);
    if (!resp.ok) throw new Error("No se encontró una propiedad con ese ID");

    const propiedad = await resp.json();

    // Mostrar formulario y cargar datos
    form.style.display = "flex";

    document.getElementById("id").value = propiedad.id;
    document.getElementById("nombre").value = propiedad.nombre;
    document.getElementById("descripcion").value = propiedad.descripcion;
    document.getElementById("precioBase").value = propiedad.precioBase;
    document.getElementById("superficieM2").value = propiedad.superficieM2;
    document.getElementById("ambientes").value = propiedad.ambientes;
    document.getElementById("ubicacion").value = propiedad.ubicacion?.id || "";
    document.getElementById("tipoPropiedad").value = propiedad.tipoPropiedad?.id || "";

    if (propiedad.imagenes && propiedad.imagenes.length > 0) {
      document.getElementById("urlImagen").value = propiedad.imagenes[0].urlImagen;
      document.getElementById("orden").value = propiedad.imagenes[0].orden;
    } else {
      document.getElementById("urlImagen").value = "";
      document.getElementById("orden").value = 1;
    }

  } catch (err) {
    console.error(err);
    alert("Error: " + err.message);
  }
});

btnGuardar.addEventListener("click", async () => {
  // 1️⃣ Obtener valores del formulario
  const id = document.getElementById("id").value.trim();
  const nombre = document.getElementById("nombre").value.trim();
  const descripcion = document.getElementById("descripcion").value.trim();
  const precioBaseStr = document.getElementById("precioBase").value.trim();
  const superficieStr = document.getElementById("superficieM2").value.trim();
  const ambientesStr = document.getElementById("ambientes").value.trim();
  const ubicacionId = parseInt(document.getElementById("ubicacion").value, 10);
  const tipoId = parseInt(document.getElementById("tipoPropiedad").value, 10);
  const urlImagen = document.getElementById("urlImagen").value.trim();
  const ordenStr = document.getElementById("orden").value.trim();

  // 2️⃣ Validaciones numéricas
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

  // 3️⃣ Convertir a números después de validar
  const precioBase = parseFloat(precioBaseStr.replace(",", "."));
  const superficieM2 = parseFloat(superficieStr.replace(",", "."));
  const ambientes = parseInt(ambientesStr, 10);
  const orden = parseInt(ordenStr, 10);

  // 4️⃣ Validación final de campos vacíos o incorrectos
  if (!id || !nombre || !descripcion || isNaN(precioBase) || isNaN(superficieM2) ||
      isNaN(ambientes) || isNaN(ubicacionId) || isNaN(tipoId) ||
      !urlImagen || isNaN(orden)) {
    alert("Por favor completá todos los campos correctamente.");
    return;
  }

  try {
    // 5️⃣ Crear objeto propiedad actualizado
    const propiedadActualizada = {
      id: parseInt(id),
      nombre,
      descripcion,
      precioBase,
      moneda: "ARS",
      superficieM2,
      ambientes,
      ubicacion: { id: ubicacionId },
      tipoPropiedad: { id: tipoId }
    };

    // 6️⃣ PUT para actualizar propiedad
    const putResp = await fetch(`http://localhost:8080/propiedades/modificar`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(propiedadActualizada)
    });

    if (!putResp.ok) {
      const msg = await putResp.text();
      throw new Error(msg || "Error al actualizar la propiedad");
    }

    // 7️⃣ Crear objeto imagen asociado
    const imagenObj = {
      urlImagen,
      orden,
      propiedad: { id: parseInt(id) }
    };

    // 8️⃣ POST para guardar/actualizar imagen
    const imgResp = await fetch(`http://localhost:8080/imagenes/agregar`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(imagenObj)
    });

    if (!imgResp.ok) {
      const msg = await imgResp.text();
      throw new Error(msg || "Error al actualizar la imagen");
    }

    alert("Propiedad actualizada correctamente.");
    form.reset();
    form.style.display = "none";

  } catch (err) {
    console.error(err);
    alert("Error: " + err.message);
  }
});
