// server.js
require('dotenv').config(); // Carga variables del .env
const express = require('express');
const axios = require('axios');
const path = require('path');

const app = express();
const port = process.env.PORT || 3000;

// Configurar EJS
app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));

// Carpeta pública
app.use(express.static(path.join(__dirname, 'public')));

// URL de ExchangeRate-API con tu API Key
const EXCHANGE_API_KEY = process.env.EXCHANGE_API_KEY;
const EXCHANGE_URL = `https://v6.exchangerate-api.com/v6/${EXCHANGE_API_KEY}/latest/USD`;

// Utilidad para formatear ARS
function formatARS(amount) {
  return Number(amount).toLocaleString('es-AR', { minimumFractionDigits: 0, maximumFractionDigits: 0 });
}

// Ruta principal: lista de propiedades
app.get('/', async (req, res) => {
  try {
    const response = await axios.get('http://localhost:8080/propiedades/listar');
    let propiedades = response.data || [];

    // Obtener tasa USD -> ARS
    let rateARS = null;
    try {
      const ratesResp = await axios.get(EXCHANGE_URL);
      rateARS = ratesResp.data.conversion_rates.ARS || null;
    } catch (err) {
      console.warn('No se pudo obtener la tasa de cambio. Se mostrarán solo precios en ARS.');
    }

    // Calcular precioUSD
    propiedades = propiedades.map(p => {
      const precioBase = Number(p.precioBase) || 0;

      let precioUSD_formatted = null;
      if (p.moneda === 'ARS' && rateARS) {
        precioUSD_formatted = (precioBase / rateARS).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
      } else if (p.moneda === 'USD') {
        precioUSD_formatted = precioBase.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
      }

      return {
        ...p,
        precioBase_formatted: formatARS(precioBase),
        precioUSD_formatted
      };
    });

    res.render('index', { propiedades });

  } catch (error) {
    console.error('Error al obtener lista de propiedades:', error.message || error);
    res.status(500).send('Error al obtener las propiedades');
  }
});

// Ruta sobre nosotros
app.get('/sobre-nosotros', (req, res) => {
  res.render('sobre-nosotros');
});

// Ruta servicios
app.get('/servicios', (req, res) => {
  res.render('servicios');
});

// Ruta contacto
app.get('/contacto', (req, res) => {
  res.render('contacto');
});

// Ruta detalle: propiedad individual
app.get('/propiedad/:id', async (req, res) => {
  const id = req.params.id;
  try {
    const response = await axios.get(`http://localhost:8080/propiedades/buscar/${id}`);
    const propiedad = response.data;

    if (!propiedad || !propiedad.id) {
      return res.status(404).send('Propiedad no encontrada');
    }

    // Formateos útiles
    const fechaPublicacion = propiedad.fechaPublicacion
      ? new Date(propiedad.fechaPublicacion).toLocaleString('es-AR')
      : '';

    const imagenPrincipal = (propiedad.imagenes && propiedad.imagenes.length > 0)
      ? propiedad.imagenes[0].urlImagen
      : '/images/fallback.jpg';

    // Calcular precio en USD si viene en ARS
    let precioUSD_formatted = null;
    try {
      if (propiedad.moneda === 'ARS') {
        const ratesResp = await axios.get(EXCHANGE_URL);
        const rateARS = ratesResp.data.conversion_rates.ARS || null;
        if (rateARS) {
          precioUSD_formatted = (propiedad.precioBase / rateARS).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
        }
      } else if (propiedad.moneda === 'USD') {
        precioUSD_formatted = propiedad.precioBase.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
      }
    } catch (err) {
      console.warn('No se pudo obtener la tasa de cambio para propiedad detalle');
    }

    const precioBase_formatted = Number(propiedad.precioBase || 0).toLocaleString('es-AR', { minimumFractionDigits: 0, maximumFractionDigits: 0 });

    // Renderizar propiedad.ejs
    res.render('propiedad', {
      propiedad,
      fechaPublicacion,
      imagenPrincipal,
      precioBase_formatted,
      precioUSD_formatted
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