document.addEventListener("DOMContentLoaded", async () => {
    const tabla = document.getElementById("tablaPropiedades").querySelector("tbody");

    try {
        const res = await fetch("http://localhost:8080/propiedades/listar");
        if (!res.ok) throw new Error("No se pudieron obtener las propiedades");

        const propiedades = await res.json();

        propiedades.forEach(prop => {
            const fila = document.createElement("tr");

            // Tomamos la primera imagen si existe
            const imagenUrl = prop.imagenes && prop.imagenes.length > 0
                ? `<img src="${prop.imagenes[0].urlImagen}" alt="Imagen propiedad">`
                : '-';

            fila.innerHTML = `
                <td>${prop.id}</td>
                <td>${prop.nombre}</td>
                <td>${prop.ambientes}</td>
                <td>${prop.descripcion}</td>
                <td>${prop.superficieM2}</td>
                <td>${prop.moneda} ${prop.precioBase.toLocaleString()}</td>
                <td>${imagenUrl}</td>
            `;

            tabla.appendChild(fila);
        });

    } catch (err) {
        console.error(err);
        alert("Error al cargar propiedades: " + err.message);
    }
});
