package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.Provincia;
import com.properties.EGI_Properties.repository.RepositoryProvincia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProvinciaService implements ICrud<Provincia> {

    private static final Logger logger = LoggerFactory.getLogger(ProvinciaService.class);

    @Autowired
    private RepositoryProvincia repositoryProvincia;

    // ------------------ AGREGAR ------------------
    @Transactional
    @Override
    public Provincia agregar(Provincia provincia) {
        if (provincia.getNombre() == null || provincia.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la provincia es obligatorio.");
        }

        Provincia guardada = repositoryProvincia.save(provincia);
        logger.info("Provincia agregada con éxito: {}", guardada.getNombre());
        return guardada;
    }

    // ------------------ MODIFICAR ------------------
    @Transactional
    @Override
    public Provincia modificar(Provincia provincia) {
        if (provincia.getId() == null) {
            throw new IllegalArgumentException("Se requiere un ID para modificar la provincia.");
        }

        Provincia existente = repositoryProvincia.findById(provincia.getId())
                .orElseThrow(() -> new IllegalArgumentException("No existe una provincia con ID " + provincia.getId()));

        existente.setNombre(provincia.getNombre());
        existente.setUbicaciones(provincia.getUbicaciones());
        existente.setImpuestoProvincial(provincia.getImpuestoProvincial());

        Provincia actualizada = repositoryProvincia.save(existente);
        logger.info("Provincia modificada con éxito: {}", actualizada.getNombre());
        return actualizada;
    }

    // ------------------ BUSCAR ------------------
    @Override
    public Provincia buscar(Integer id) {
        Provincia provincia = repositoryProvincia.findById(id).orElse(null);
        if (provincia == null) {
            logger.warn("No se encontró provincia con ID: {}", id);
        } else {
            logger.info("Provincia encontrada: {}", provincia.getNombre());
        }
        return provincia;
    }

    // ------------------ ELIMINAR ------------------
    @Transactional
    @Override
    public void eliminar(Integer id) {
        if (!repositoryProvincia.existsById(id)) {
            throw new IllegalArgumentException("No existe una provincia con ID " + id);
        }
        repositoryProvincia.deleteById(id);
        logger.info("Provincia eliminada con ID: {}", id);
    }

    // ------------------ LISTAR ------------------
    @Override
    public List<Provincia> listar() {
        List<Provincia> provincias = repositoryProvincia.findAll();
        logger.info("Se listaron {} provincias.", provincias.size());
        return provincias;
    }
}
