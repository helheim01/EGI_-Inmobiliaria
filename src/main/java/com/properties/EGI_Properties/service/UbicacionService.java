package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.Ubicacion;
import com.properties.EGI_Properties.repository.RepositoryUbicacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UbicacionService implements ICrud<Ubicacion> {

    private static final Logger logger = LoggerFactory.getLogger(UbicacionService.class);

    @Autowired
    private RepositoryUbicacion repositoryUbicacion;

    // ------------------ AGREGAR ------------------
    @Transactional
    @Override
    public Ubicacion agregar(Ubicacion ubicacion) {
        if (ubicacion.getBarrio() == null || ubicacion.getProvincia() == null || ubicacion.getMunicipio() == null) {
            throw new IllegalArgumentException("Barrio, municipio y provincia son obligatorios.");
        }

        Ubicacion guardada = repositoryUbicacion.save(ubicacion);
        logger.info("Ubicación agregada con éxito: {} - {}", guardada.getBarrio(), guardada.getProvincia());
        return guardada;
    }

    // ------------------ MODIFICAR ------------------
    @Transactional
    @Override
    public Ubicacion modificar(Ubicacion ubicacion) {
        if (ubicacion.getId() == null) {
            throw new IllegalArgumentException("Se requiere un ID para modificar la ubicación.");
        }

        Ubicacion existente = repositoryUbicacion.findById(ubicacion.getId())
                .orElseThrow(() -> new IllegalArgumentException("No existe una ubicación con ID " + ubicacion.getId()));

        existente.setBarrio(ubicacion.getBarrio());
        existente.setMunicipio(ubicacion.getMunicipio());
        existente.setProvincia(ubicacion.getProvincia());
        existente.setPropiedades(ubicacion.getPropiedades());

        Ubicacion actualizada = repositoryUbicacion.save(existente);
        logger.info("Ubicación modificada con éxito: {} - {}", actualizada.getBarrio(), actualizada.getProvincia());
        return actualizada;
    }

    // ------------------ BUSCAR ------------------
    @Override
    public Ubicacion buscar(Integer id) {
        Ubicacion ubicacion = repositoryUbicacion.findById(id).orElse(null);
        if (ubicacion == null) {
            logger.warn("No se encontró ubicación con ID: {}", id);
        } else {
            logger.info("Ubicación encontrada: {} - {}", ubicacion.getBarrio(), ubicacion.getProvincia());
        }
        return ubicacion;
    }

    // ------------------ ELIMINAR ------------------
    @Transactional
    @Override
    public void eliminar(Integer id) {
        if (!repositoryUbicacion.existsById(id)) {
            throw new IllegalArgumentException("No existe una ubicación con ID " + id);
        }
        repositoryUbicacion.deleteById(id);
        logger.info("Ubicación eliminada con ID: {}", id);
    }

    // ------------------ LISTAR ------------------
    @Override
    public List<Ubicacion> listar() {
        List<Ubicacion> ubicaciones = repositoryUbicacion.findAll();
        logger.info("Se listaron {} ubicaciones.", ubicaciones.size());
        return ubicaciones;
    }
}
