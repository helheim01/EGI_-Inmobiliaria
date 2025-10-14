package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.ImpuestoProvincial;
import com.properties.EGI_Properties.entity.Provincia;
import com.properties.EGI_Properties.repository.RepositoryImpuestoProvincial;
import com.properties.EGI_Properties.repository.RepositoryProvincia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImpuestoProvincialService implements ICrud<ImpuestoProvincial> {

    private static final Logger logger = LoggerFactory.getLogger(ImpuestoProvincialService.class);

    @Autowired
    private RepositoryImpuestoProvincial repositoryImpuestoProvincial;

    @Autowired
    private RepositoryProvincia repositoryProvincia;

    @Transactional
    @Override
    public ImpuestoProvincial agregar(ImpuestoProvincial impuesto) {
        if (impuesto.getProvincia() == null || impuesto.getPorcentaje() == null) {
            throw new IllegalArgumentException("Los campos 'provincia' y 'porcentaje' son obligatorios.");
        }

        // 🔹 Obtener la provincia desde la base de datos para evitar entidad 'detached'
        Provincia provincia = repositoryProvincia.findById(impuesto.getProvincia().getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe una provincia con ID " + impuesto.getProvincia().getId())
                );

        impuesto.setProvincia(provincia);

        ImpuestoProvincial guardado = repositoryImpuestoProvincial.save(impuesto);
        logger.info("✅ Impuesto provincial agregado con éxito: {} ({}%)",
                guardado.getProvincia().getNombre(), guardado.getPorcentaje());
        return guardado;
    }

    // ------------------ MODIFICAR ------------------
    @Transactional
    @Override
    public ImpuestoProvincial modificar(ImpuestoProvincial impuesto) {
        ImpuestoProvincial existente = repositoryImpuestoProvincial.findById(impuesto.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe un impuesto provincial con ID " + impuesto.getId()));

        // 🔹 Si viene una provincia, validarla y asociar la persistente
        if (impuesto.getProvincia() != null) {
            Provincia provincia = repositoryProvincia.findById(impuesto.getProvincia().getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "No existe una provincia con ID " + impuesto.getProvincia().getId())
                    );
            existente.setProvincia(provincia);
        }

        existente.setPorcentaje(impuesto.getPorcentaje());

        ImpuestoProvincial actualizado = repositoryImpuestoProvincial.save(existente);
        logger.info("✅ Impuesto provincial actualizado: {} ({}%)",
                actualizado.getProvincia().getNombre(), actualizado.getPorcentaje());
        return actualizado;
    }

    // ------------------ BUSCAR ------------------
    @Override
    public ImpuestoProvincial buscar(Integer id) {
        ImpuestoProvincial impuesto = repositoryImpuestoProvincial.findById(id).orElse(null);
        if (impuesto == null) {
            logger.warn("⚠️ No se encontró impuesto provincial con ID: {}", id);
        } else {
            logger.info("🔍 Impuesto provincial encontrado: {}", impuesto.getProvincia().getNombre());
        }
        return impuesto;
    }

    // ------------------ ELIMINAR ------------------
    @Transactional
    @Override
    public void eliminar(Integer id) {
        if (!repositoryImpuestoProvincial.existsById(id)) {
            throw new IllegalArgumentException("No existe un impuesto provincial con ID " + id);
        }
        repositoryImpuestoProvincial.deleteById(id);
        logger.info("🗑️ Impuesto provincial eliminado con ID: {}", id);
    }

    // ------------------ LISTAR ------------------
    @Override
    public List<ImpuestoProvincial> listar() {
        List<ImpuestoProvincial> impuestos = repositoryImpuestoProvincial.findAll();
        logger.info("📋 Se listaron {} impuestos provinciales.", impuestos.size());
        return impuestos;
    }
}
