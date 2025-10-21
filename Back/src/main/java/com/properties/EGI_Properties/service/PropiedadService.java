package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.Propiedad;
import com.properties.EGI_Properties.repository.RepositoryPropiedad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PropiedadService implements ICrud<Propiedad> {

    private static final Logger logger = LoggerFactory.getLogger(PropiedadService.class);

    @Autowired
    private RepositoryPropiedad repositoryPropiedad;

    // ------------------ AGREGAR ------------------
    @Transactional
    @Override
    public Propiedad agregar(Propiedad propiedad) {
        if (propiedad.getNombre() == null || propiedad.getPrecioBase() == null) {
            throw new IllegalArgumentException("Nombre y precio base son obligatorios.");
        }

        Propiedad guardada = repositoryPropiedad.save(propiedad);
        logger.info("Propiedad agregada con éxito: {}", guardada.getNombre());
        return guardada;
    }

    // ------------------ MODIFICAR ------------------
    @Transactional
    @Override
    public Propiedad modificar(Propiedad propiedad) {
        if (propiedad.getId() == null) {
            throw new IllegalArgumentException("Se requiere un ID para modificar la propiedad.");
        }

        Propiedad existente = repositoryPropiedad.findById(propiedad.getId())
                .orElseThrow(() -> new IllegalArgumentException("No existe una propiedad con ID " + propiedad.getId()));

        existente.setNombre(propiedad.getNombre());
        existente.setDescripcion(propiedad.getDescripcion());
        existente.setPrecioBase(propiedad.getPrecioBase());
        existente.setAmbientes(propiedad.getAmbientes());
        existente.setSuperficieM2(propiedad.getSuperficieM2());
        existente.setFechaPublicacion(propiedad.getFechaPublicacion());
        existente.setMoneda(propiedad.getMoneda());
        existente.setImagenes(propiedad.getImagenes());
        existente.setUbicacion(propiedad.getUbicacion());
        existente.setTipoPropiedad(propiedad.getTipoPropiedad());

        Propiedad actualizada = repositoryPropiedad.save(existente);
        logger.info("Propiedad modificada con éxito: {}", actualizada.getNombre());
        return actualizada;
    }

    // ------------------ BUSCAR ------------------
    @Override
    public Propiedad buscar(Integer id) {
        Propiedad propiedad = repositoryPropiedad.findById(id).orElse(null);
        if (propiedad == null) {
            logger.warn("No se encontró propiedad con ID: {}", id);
        } else {
            logger.info("Propiedad encontrada: {}", propiedad.getNombre());
        }
        return propiedad;
    }

    // ------------------ ELIMINAR ------------------
    @Transactional
    @Override
    public void eliminar(Integer id) {
        if (!repositoryPropiedad.existsById(id)) {
            throw new IllegalArgumentException("No existe una propiedad con ID " + id);
        }
        repositoryPropiedad.deleteById(id);
        logger.info("Propiedad eliminada con ID: {}", id);
    }

    // ------------------ LISTAR ------------------
    @Override
    public List<Propiedad> listar() {
        List<Propiedad> propiedades = repositoryPropiedad.findAll();
        logger.info("Se listaron {} propiedades.", propiedades.size());
        return propiedades;
    }
}
