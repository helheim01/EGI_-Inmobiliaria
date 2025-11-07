-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-11-2025 a las 20:10:03
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `egipropiedades`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagen_propiedad`
--

CREATE TABLE `imagen_propiedad` (
  `id_imagen` int(11) NOT NULL,
  `orden` int(11) DEFAULT NULL,
  `url_imagen` varchar(255) NOT NULL,
  `id_propiedad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `imagen_propiedad`
--

INSERT INTO `imagen_propiedad` (`id_imagen`, `orden`, `url_imagen`, `id_propiedad`) VALUES
(1, 1, 'https://media.istockphoto.com/id/1483504981/es/foto/casa-moderna-con-una-extensi%C3%B3n-c%C3%BAbica-y-garaje.webp?s=2048x2048&w=is&k=20&c=esdV0m6r7IuZ3KdsqeirUek2Pxi6XcdCQuUE7ndnMv4=', 1),
(41, 1, 'https://www.shutterstock.com/shutterstock/photos/2594770359/display_1500/stock-photo-modern-house-exterior-day-light-in-housing-complex-d-rendering-2594770359.jpg', 2),
(42, 1, 'https://www.shutterstock.com/shutterstock/photos/2536797095/display_1500/stock-photo-modern-luxury-home-with-a-spacious-patio-and-pool-at-dusk-showcasing-elegant-architecture-and-2536797095.jpg', 3),
(43, 1, 'https://www.shutterstock.com/shutterstock/photos/2550827187/display_1500/stock-photo-doha-qatar-october-a-row-of-residential-townhomes-or-townhouses-in-middle-east-2550827187.jpg', 4),
(44, 1, 'https://www.shutterstock.com/shutterstock/photos/2536796753/display_1500/stock-photo-modern-luxury-home-with-pool-and-patio-in-a-serene-suburban-setting-at-sunset-showcasing-2536796753.jpg', 5),
(45, 1, 'https://www.shutterstock.com/shutterstock/photos/2538026843/display_1500/stock-photo-new-elegant-modern-single-family-house-terraced-family-homes-in-newly-developed-housing-estate-2538026843.jpg', 6),
(46, 1, 'https://www.shutterstock.com/shutterstock/photos/2460011239/display_1500/stock-photo-beautiful-residential-house-morning-view-luxury-villa-d-house-rendering-beautiful-villa-2460011239.jpg', 7),
(47, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 8),
(48, 1, 'https://www.shutterstock.com/shutterstock/photos/2477435401/display_1500/stock-photo-luxurious-villa-with-pool-and-outdoor-seating-at-sunset-2477435401.jpg', 9),
(49, 1, 'https://www.shutterstock.com/shutterstock/photos/2477435401/display_1500/stock-photo-luxurious-villa-with-pool-and-outdoor-seating-at-sunset-2477435401.jpg', 10),
(50, 1, 'https://www.shutterstock.com/shutterstock/photos/2538026843/display_1500/stock-photo-new-elegant-modern-single-family-house-terraced-family-homes-in-newly-developed-housing-estate-2538026843.jpg', 14),
(51, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 16),
(52, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 17),
(53, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 18),
(54, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 19),
(55, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 20),
(56, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 21),
(57, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 22),
(58, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 23),
(59, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 24),
(60, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 25),
(61, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 26),
(62, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 27),
(63, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 28),
(64, 1, 'https://www.shutterstock.com/shutterstock/photos/2441673825/display_1500/stock-photo-modern-contemporary-style-small-wooden-terrace-in-lush-garden-with-house-interior-background-d-2441673825.jpg', 14),
(65, 2, 'https://www.shutterstock.com/shutterstock/photos/2536797095/display_1500/stock-photo-modern-luxury-home-with-a-spacious-patio-and-pool-at-dusk-showcasing-elegant-architecture-and-2536797095.jpg', 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuesto_provincial`
--

CREATE TABLE `impuesto_provincial` (
  `id_provincia` int(11) NOT NULL,
  `porcentaje` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `impuesto_provincial`
--

INSERT INTO `impuesto_provincial` (`id_provincia`, `porcentaje`) VALUES
(1, 3),
(7, 1.5),
(21, 0.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propiedad`
--

CREATE TABLE `propiedad` (
  `id_propiedad` int(11) NOT NULL,
  `ambientes` int(11) NOT NULL,
  `descripcion_propiedad` varchar(255) NOT NULL,
  `fecha_publicacion` datetime(6) NOT NULL,
  `moneda` enum('ARS','USD') NOT NULL,
  `nombre_propiedad` varchar(255) NOT NULL,
  `precio_base` double NOT NULL,
  `superficie_m2` double NOT NULL,
  `id_tipopropiedad` int(11) NOT NULL,
  `id_ubicacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `propiedad`
--

INSERT INTO `propiedad` (`id_propiedad`, `ambientes`, `descripcion_propiedad`, `fecha_publicacion`, `moneda`, `nombre_propiedad`, `precio_base`, `superficie_m2`, `id_tipopropiedad`, `id_ubicacion`) VALUES
(1, 5, 'Casa céntrica, 3 dormitorios, 2 baños', '2025-10-14 14:23:58.000000', 'ARS', 'Casa en el Centro', 1500000, 120.5, 1, 1),
(2, 4, 'Casa céntrica, 2 dormitorios, 1 baño', '2025-10-17 13:00:37.000000', 'ARS', 'Casa en el Centro', 90000, 60.5, 1, 2),
(3, 5, 'Casa céntrica, 4 dormitorios, 2 baños', '2025-10-21 11:38:44.000000', 'ARS', 'Casa en el Centro', 250000, 120.5, 1, 3),
(4, 5, 'Casa céntrica, 2 dormitorios, 1 baños', '2025-10-21 11:49:07.000000', 'ARS', 'Casa en el Centro', 100000, 120.5, 1, 4),
(5, 5, 'Casa céntrica, 2 dormitorios, 1 baños', '2025-10-21 11:49:58.000000', 'ARS', 'Casa en el Centro', 100000, 120.5, 1, 5),
(6, 5, 'Casa céntrica, 2 dormitorios, 1 baños', '2025-10-21 11:50:59.000000', 'ARS', 'Casa en el Centro', 100000, 120.5, 1, 6),
(7, 5, 'Casa céntrica, 2 dormitorios, 1 baños', '2025-10-21 11:52:14.000000', 'ARS', 'Casa en el Centro', 100000, 120.5, 1, 7),
(8, 5, 'Casa céntrica, 2 dormitorios, 1 baños', '2025-10-21 11:52:33.000000', 'ARS', 'Casa en el Centro', 100000, 120.5, 1, 8),
(9, 5, 'Casa céntrica, 2 dormitorios, 1 baños', '2025-10-21 11:52:51.000000', 'ARS', 'Casa en el Centro', 100000, 120.5, 1, 9),
(10, 5, 'Casa céntrica, 2 dormitorios, 1 baños', '2025-10-21 20:30:56.000000', 'ARS', 'Casa en el Centro', 100000, 120.5, 1, 10),
(14, 3, 'Prueba validaciones 2', '2025-10-28 12:56:55.000000', 'ARS', 'Prueba validaciones 2', 14000000, 60, 1, 11),
(16, 2, 'a', '2025-11-07 14:06:53.000000', 'ARS', 'a', 2, 2, 1, 12),
(17, 2, 's', '2025-11-07 14:07:10.000000', 'ARS', 's', 2, 2, 1, 13),
(18, 1, 'd', '2025-11-07 14:07:25.000000', 'ARS', 'd', 1, 1, 1, 14),
(19, 1, 'f', '2025-11-07 14:07:41.000000', 'ARS', 'f', 1, 1, 1, 15),
(20, 1, 'g', '2025-11-07 14:07:54.000000', 'ARS', 'g', 1, 1, 1, 16),
(21, 1, 'h', '2025-11-07 14:10:18.000000', 'ARS', 'h', 1, 1, 1, 17),
(22, 1, 'j', '2025-11-07 14:10:30.000000', 'ARS', 'j', 1, 1, 1, 18),
(23, 1, 'k', '2025-11-07 14:10:45.000000', 'ARS', 'k', 1, 1, 1, 19),
(24, 1, 'l', '2025-11-07 14:10:58.000000', 'ARS', 'l', 1, 1, 1, 20),
(25, 1, 'z', '2025-11-07 14:11:11.000000', 'ARS', 'z', 1, 1, 1, 21),
(26, 1, 'x', '2025-11-07 14:11:24.000000', 'ARS', 'x', 1, 1, 1, 23),
(27, 1, 'c', '2025-11-07 14:11:36.000000', 'ARS', 'c', 1, 1, 1, 24),
(28, 1, 'v', '2025-11-07 14:11:50.000000', 'ARS', 'v', 1, 1, 1, 22);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincia`
--

CREATE TABLE `provincia` (
  `id_provincia` int(11) NOT NULL,
  `nombre_provincia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `provincia`
--

INSERT INTO `provincia` (`id_provincia`, `nombre_provincia`) VALUES
(1, 'Mendoza'),
(2, 'CABA'),
(3, 'Buenos Aires'),
(4, 'Catamarca'),
(5, 'Chaco'),
(6, 'Chubut'),
(7, 'Córdoba'),
(8, 'Corrientes'),
(9, 'Entre Ríos'),
(10, 'Formosa'),
(11, 'Jujuy'),
(12, 'La Pampa'),
(13, 'La Rioja'),
(14, 'Misiones'),
(15, 'Neuquén'),
(16, 'Río Negro'),
(17, 'Salta'),
(18, 'San Juan'),
(19, 'San Luis'),
(20, 'Santa Cruz'),
(21, 'Santa Fe'),
(22, 'Santiago del Estero'),
(23, 'Tierra del Fuego'),
(24, 'Tucumán');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_propiedad`
--

CREATE TABLE `tipo_propiedad` (
  `id_tipopropiedad` int(11) NOT NULL,
  `nombre_tipopropiedad` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tipo_propiedad`
--

INSERT INTO `tipo_propiedad` (`id_tipopropiedad`, `nombre_tipopropiedad`) VALUES
(1, 'Casa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicacion`
--

CREATE TABLE `ubicacion` (
  `id_ubicacion` int(11) NOT NULL,
  `nombre_barrio` varchar(255) NOT NULL,
  `nombre_municipio` varchar(255) NOT NULL,
  `id_provincia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `ubicacion`
--

INSERT INTO `ubicacion` (`id_ubicacion`, `nombre_barrio`, `nombre_municipio`, `id_provincia`) VALUES
(1, 'Centro', 'Ciudad de Mendoza', 1),
(2, 'Centro CABA', 'CABA', 2),
(3, 'Centro Buenos Aires', 'Buenos Aires', 3),
(4, 'Centro Catamarca', 'Catamarca', 4),
(5, 'Centro Chaco', 'Chaco', 5),
(6, 'Centro Chubut', 'Chubut', 6),
(7, 'Centro Córdoba', 'Córdoba', 7),
(8, 'Centro Corrientes', 'Corrientes', 8),
(9, 'Centro Entre Ríos', 'Entre Ríos', 9),
(10, 'Centro Formosa', 'Formosa', 10),
(11, 'Jujuy', 'Jujuy', 11),
(12, 'Centro La Pampa', 'La Pampa', 12),
(13, 'Centro La Rioja', 'La Rioja', 13),
(14, 'Centro Misiones', 'Misiones', 14),
(15, 'Centro Neuquén', 'Neuquén', 15),
(16, 'Centro Río Negro', 'Río Negro', 16),
(17, 'Centro Salta', 'Salta', 17),
(18, 'Centro San Juan', 'San Juan', 18),
(19, 'Centro San Luis', 'San Luis', 19),
(20, 'Centro Santa Cruz', 'Santa Cruz', 20),
(21, 'Centro Santa Fe', 'Santa Fe', 21),
(22, 'Centro Santiago del Estero', 'Santiago del Estero', 22),
(23, 'Centro Tierra del Fuego', 'Tierra del Fuego', 23),
(24, 'Centro Tucumán', 'Tucumán', 24);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `imagen_propiedad`
--
ALTER TABLE `imagen_propiedad`
  ADD PRIMARY KEY (`id_imagen`),
  ADD KEY `FKjxb8n3hvocstf214d2u67f3w2` (`id_propiedad`);

--
-- Indices de la tabla `impuesto_provincial`
--
ALTER TABLE `impuesto_provincial`
  ADD PRIMARY KEY (`id_provincia`);

--
-- Indices de la tabla `propiedad`
--
ALTER TABLE `propiedad`
  ADD PRIMARY KEY (`id_propiedad`),
  ADD KEY `FK70e6xwxan5n7jrk3k5fxn63qy` (`id_tipopropiedad`),
  ADD KEY `FKngd4tfio7os015r7bewhwxj2k` (`id_ubicacion`);

--
-- Indices de la tabla `provincia`
--
ALTER TABLE `provincia`
  ADD PRIMARY KEY (`id_provincia`);

--
-- Indices de la tabla `tipo_propiedad`
--
ALTER TABLE `tipo_propiedad`
  ADD PRIMARY KEY (`id_tipopropiedad`);

--
-- Indices de la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  ADD PRIMARY KEY (`id_ubicacion`),
  ADD KEY `FKk52vtin2x1ijf8h3rjlpxgyr2` (`id_provincia`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `imagen_propiedad`
--
ALTER TABLE `imagen_propiedad`
  MODIFY `id_imagen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT de la tabla `propiedad`
--
ALTER TABLE `propiedad`
  MODIFY `id_propiedad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `provincia`
--
ALTER TABLE `provincia`
  MODIFY `id_provincia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `tipo_propiedad`
--
ALTER TABLE `tipo_propiedad`
  MODIFY `id_tipopropiedad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  MODIFY `id_ubicacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `imagen_propiedad`
--
ALTER TABLE `imagen_propiedad`
  ADD CONSTRAINT `FKjxb8n3hvocstf214d2u67f3w2` FOREIGN KEY (`id_propiedad`) REFERENCES `propiedad` (`id_propiedad`);

--
-- Filtros para la tabla `impuesto_provincial`
--
ALTER TABLE `impuesto_provincial`
  ADD CONSTRAINT `FKlkm0s2uwk3e5oawuv1bgi19iq` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id_provincia`);

--
-- Filtros para la tabla `propiedad`
--
ALTER TABLE `propiedad`
  ADD CONSTRAINT `FK70e6xwxan5n7jrk3k5fxn63qy` FOREIGN KEY (`id_tipopropiedad`) REFERENCES `tipo_propiedad` (`id_tipopropiedad`),
  ADD CONSTRAINT `FKngd4tfio7os015r7bewhwxj2k` FOREIGN KEY (`id_ubicacion`) REFERENCES `ubicacion` (`id_ubicacion`);

--
-- Filtros para la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  ADD CONSTRAINT `FKk52vtin2x1ijf8h3rjlpxgyr2` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id_provincia`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
