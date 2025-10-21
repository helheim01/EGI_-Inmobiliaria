package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.ImagenPropiedad;
import com.properties.EGI_Properties.repository.RepositoryImagenPropiedad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ImagenPropiedadService implements ICrud<ImagenPropiedad> {
    private static final Logger logger = LoggerFactory.getLogger(ImagenPropiedadService.class);// Creo un logger específico para esta clase, para imprimir en la consola

    @Autowired
    private RepositoryImagenPropiedad repositoryImagenPropiedad; //Inyecto el repositorio, permitiendo acceder a la base de datos

    // Para agregar
    @Transactional // Con esto cada operación se ejecuta dentro de una transacción. Si falla algo, todo se revierte
    @Override
    public ImagenPropiedad agregar(ImagenPropiedad imagenPropiedad) {
        if (imagenPropiedad.getUrlImagen() == null || imagenPropiedad.getUrlImagen().isEmpty()) { //veo que la url no sea nula o vacia
            throw new IllegalArgumentException("La URL de la imagen es obligatoria.");
        }
        ImagenPropiedad guardada = repositoryImagenPropiedad.save(imagenPropiedad); // Guardo la imagen en la bd y devuelvo la entidad guardada (con ID generado)
        logger.info("Imagen agregada correctamente con ID: {}", guardada.getId()); // Loggeo la operación para confirmar

        return guardada; // Devuelvo la imagen guardada
    }

    // Para modificar
    @Transactional
    @Override
    public ImagenPropiedad modificar(ImagenPropiedad imagenPropiedad) {
        if (imagenPropiedad.getId() == null) { // Verifico que el ID exista
            throw new IllegalArgumentException("Debe especificar el ID para modificar una imagen.");
        }
        ImagenPropiedad existente = repositoryImagenPropiedad.findById(imagenPropiedad.getId())
                .orElseThrow(() -> new IllegalArgumentException("No existe una imagen con el ID especificado.")); // Si no existe, tiro una excepción

        existente.setUrlImagen(imagenPropiedad.getUrlImagen());
        existente.setOrden(imagenPropiedad.getOrden());
        existente.setPropiedad(imagenPropiedad.getPropiedad());
        // Actualizo los campos

        ImagenPropiedad actualizada = repositoryImagenPropiedad.save(existente); // Guardo cambios en la bd y devuelvo la entidad actualizada

        logger.info("Imagen modificada correctamente con ID: {}", actualizada.getId());
        return actualizada;
    }

    // Para buscar con id
    @Transactional(readOnly = true)
    @Override
    public ImagenPropiedad buscar(Integer id) {
        ImagenPropiedad imagen = repositoryImagenPropiedad.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una imagen con ID: " + id));
        logger.info("Imagen encontrada con ID: {}", id);
        return imagen;
    }

    // Para eliminar
    @Transactional
    @Override
    public void eliminar(Integer id) {
        if (!repositoryImagenPropiedad.existsById(id)) { // Chequeo que la imagen exista antes de eliminarla
            throw new IllegalArgumentException("No existe una imagen con ID: " + id);
        }
        repositoryImagenPropiedad.deleteById(id); // Elimino por ID
        logger.info("Imagen eliminada correctamente con ID: {}", id);
    }

    // Para mostrar todos
    @Transactional(readOnly = true)
    @Override
    public List<ImagenPropiedad> listar() {
        List<ImagenPropiedad> imagenes = repositoryImagenPropiedad.findAll();
        logger.info("Se listaron {} imágenes de propiedad.", imagenes.size());
        return imagenes;
    }
}
