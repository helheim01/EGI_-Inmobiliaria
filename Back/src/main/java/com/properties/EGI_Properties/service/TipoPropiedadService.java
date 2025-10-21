package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.TipoPropiedad;
import com.properties.EGI_Properties.repository.RepositoryTipoPropiedad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoPropiedadService implements ICrud<TipoPropiedad> {

    private static final Logger logger = LoggerFactory.getLogger(TipoPropiedadService.class);

    @Autowired
    private RepositoryTipoPropiedad repositoryTipoPropiedad;

    // ------------------ AGREGAR ------------------
    @Transactional
    @Override
    public TipoPropiedad agregar(TipoPropiedad tipoPropiedad) {
        if (tipoPropiedad.getNombre() == null || tipoPropiedad.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del tipo de propiedad es obligatorio.");
        }

        TipoPropiedad guardada = repositoryTipoPropiedad.save(tipoPropiedad);
        logger.info("Tipo de propiedad agregada con éxito: {}", guardada.getNombre());
        return guardada;
    }

    // ------------------ MODIFICAR ------------------
    @Transactional
    @Override
    public TipoPropiedad modificar(TipoPropiedad tipoPropiedad) {
        if (tipoPropiedad.getId() == null) {
            throw new IllegalArgumentException("Se requiere un ID para modificar el tipo de propiedad.");
        }

        TipoPropiedad existente = repositoryTipoPropiedad.findById(tipoPropiedad.getId())
                .orElseThrow(() -> new IllegalArgumentException("No existe un tipo de propiedad con ID " + tipoPropiedad.getId()));

        existente.setNombre(tipoPropiedad.getNombre());
        existente.setPropiedades(tipoPropiedad.getPropiedades());

        TipoPropiedad actualizado = repositoryTipoPropiedad.save(existente);
        logger.info("Tipo de propiedad modificada con éxito: {}", actualizado.getNombre());
        return actualizado;
    }

    // ------------------ BUSCAR ------------------
    @Override
    public TipoPropiedad buscar(Integer id) {
        TipoPropiedad tipo = repositoryTipoPropiedad.findById(id).orElse(null);
        if (tipo == null) {
            logger.warn("No se encontró tipo de propiedad con ID: {}", id);
        } else {
            logger.info("Tipo de propiedad encontrada: {}", tipo.getNombre());
        }
        return tipo;
    }

    // ------------------ ELIMINAR ------------------
    @Transactional
    @Override
    public void eliminar(Integer id) {
        if (!repositoryTipoPropiedad.existsById(id)) {
            throw new IllegalArgumentException("No existe un tipo de propiedad con ID " + id);
        }
        repositoryTipoPropiedad.deleteById(id);
        logger.info("Tipo de propiedad eliminada con ID: {}", id);
    }

    // ------------------ LISTAR ------------------
    @Override
    public List<TipoPropiedad> listar() {
        List<TipoPropiedad> tipos = repositoryTipoPropiedad.findAll();
        logger.info("Se listaron {} tipos de propiedad.", tipos.size());
        return tipos;
    }
}
