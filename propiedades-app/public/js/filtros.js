document.addEventListener("DOMContentLoaded", async () => {
  const selectProvincia = document.getElementById("selectProvincia");
  const propiedadesContainer = document.querySelector(".main-content .row");

  let todasLasPropiedades = []; // almacenará todas las propiedades cargadas inicialmente
  let ubicaciones = []; // para guardar las ubicaciones del endpoint

  // 🟩 1. Cargar las provincias desde el endpoint
  async function cargarProvincias() {
    try {
      const response = await fetch("http://localhost:8080/ubicaciones/listar");
      ubicaciones = await response.json();

      // obtener las provincias únicas
      const provinciasUnicas = new Map();
      ubicaciones.forEach((u) => {
        if (u.provincia && !provinciasUnicas.has(u.provincia.id)) {
          provinciasUnicas.set(u.provincia.id, u.provincia.nombre);
        }
      });

      // llenar el select
      provinciasUnicas.forEach((nombre, id) => {
        const option = document.createElement("option");
        option.value = id;
        option.textContent = nombre;
        selectProvincia.appendChild(option);
      });
    } catch (error) {
      console.error("Error al cargar provincias:", error);
    }
  }

  // 🟩 2. Guardar las propiedades actuales del DOM (las renderizadas por EJS)
  function cargarPropiedadesDesdeDOM() {
    const cards = document.querySelectorAll(".propiedad-link");
    todasLasPropiedades = Array.from(cards).map((card) => {
      const nombre = card.querySelector("h3")?.textContent.trim();
      const direccion = card.querySelector("p")?.textContent.trim();
      const img = card.querySelector("img")?.src;
      const precioARS = card.querySelector(".ars")?.textContent.trim();
      const precioUSD = card.querySelector(".usd")?.textContent.trim();
      const url = card.href;

      return {
        nombre,
        direccion,
        img,
        precioARS,
        precioUSD,
        url,
      };
    });
  }

  // 🟩 3. Renderizar propiedades filtradas
  function renderizarPropiedades(propiedadesFiltradas) {
    propiedadesContainer.innerHTML = ""; // limpiar contenedor

    if (propiedadesFiltradas.length === 0) {
      propiedadesContainer.innerHTML = `
        <div class="col-md-12 text-center">
          <p>No se encontraron propiedades para esta provincia.</p>
        </div>`;
      return;
    }

    propiedadesFiltradas.forEach((p) => {
      const col = document.createElement("div");
      col.className = "col-sm-6 col-md-4";
      col.innerHTML = `
        <a href="${p.url}" class="propiedad-link">
          <div class="propiedad">
            <img src="${p.img}" alt="${p.nombre}" class="img-responsive">
            <div class="info">
              <h3>${p.nombre}</h3>
              <p>${p.direccion}</p>
              <p class="precio">
                <span class="ars">${p.precioARS}</span> | 
                <span class="usd">${p.precioUSD}</span>
              </p>
            </div>
          </div>
        </a>`;
      propiedadesContainer.appendChild(col);
    });
  }

  // 🟩 4. Filtrar al cambiar de provincia
  selectProvincia.addEventListener("change", async (e) => {
    const provinciaId = e.target.value;

    // si el usuario elige "todas las provincias", se muestran todas las propiedades iniciales
    if (!provinciaId) {
      renderizarPropiedades(todasLasPropiedades);
      return;
    }

    // buscar las propiedades asociadas a esa provincia
    const ubicacionSeleccionada = ubicaciones.filter(
      (u) => u.provincia.id === parseInt(provinciaId)
    );

    // unir todas las propiedades de esas ubicaciones
    const propiedadesDeProvincia = ubicacionSeleccionada.flatMap(
      (u) =>
        u.propiedades.map((prop) => ({
          nombre: prop.nombre,
          direccion: `Dirección: ${prop.ubicacion.barrio}, ${prop.ubicacion.municipio}`,
          img:
            prop.imagenes && prop.imagenes.length > 0
              ? prop.imagenes[0].urlImagen
              : "/images/fallback.jpg",
          precioARS: `ARS ${prop.precioBase.toLocaleString("es-AR")}`,
          precioUSD: prop.precioUSD_formatted
            ? `USD ${prop.precioUSD_formatted}`
            : "USD -",
          url: `/propiedad/${prop.id}`,
        }))
    );

    renderizarPropiedades(propiedadesDeProvincia);
  });

  // inicializar
  await cargarProvincias();
  cargarPropiedadesDesdeDOM();
});

// Evita que el dropdown se cierre al hacer click dentro del menú (ej. seleccionar en el <select>)
$(document).on('click', '.dropdown-menu', function(e) {
  e.stopPropagation();
});
