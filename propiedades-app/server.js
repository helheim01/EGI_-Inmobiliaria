// server.js
const express = require('express');
const axios = require('axios');
const path = require('path');

const app = express();
const port = 3000;

// Configurar EJS
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Carpeta pública
app.use(express.static(path.join(__dirname, 'public')));

// Ruta principal: lista de propiedades (usa tu API)
app.get('/', async (req, res) => {
  try {
    const response = await axios.get('http://localhost:8080/propiedades/listar');
    const propiedades = response.data || [];
    res.render('index', { propiedades });
  } catch (error) {
    console.error('Error al obtener lista:', error.message || error);
    res.status(500).send('Error al obtener las propiedades');
  }
});

// Ruta detalle: obtiene una propiedad por id y renderiza propiedad.ejs
app.get('/propiedad/:id', async (req, res) => {
  const id = req.params.id;
  try {
    const response = await axios.get(`http://localhost:8080/propiedades/buscar/${id}`);
    const propiedad = response.data;

    if (!propiedad || !propiedad.id) {
      return res.status(404).send('Propiedad no encontrada');
    }

    // Formateos útiles (fecha y fallback de imagen)
    const fechaPublicacion = propiedad.fechaPublicacion
      ? new Date(propiedad.fechaPublicacion).toLocaleString('es-AR')
      : '';

    const imagenPrincipal = (propiedad.imagenes && propiedad.imagenes.length > 0)
      ? propiedad.imagenes[0].urlImagen
      : '/images/fallback.jpg';

    // Puedes pasar más datos si los necesitas
    res.render('propiedad', {
      propiedad,
      fechaPublicacion,
      imagenPrincipal
    });
  } catch (error) {
    console.error('Error al obtener propiedad por id:', error.message || error);
    res.status(500).send('Error al obtener la propiedad');
  }
});

// Iniciar servidor
app.listen(port, () => {
  console.log(`Servidor corriendo en http://localhost:${port}`);
});
