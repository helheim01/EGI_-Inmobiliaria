package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.Propiedad;
import com.properties.EGI_Properties.repository.RepositoryPropiedad;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PropiedadServiceImpl implements IPropiedadService{
    @Autowired
    private RepositoryPropiedad repositoryPropiedad;

    @Override
    public Propiedad getPropiedadById(Integer id) {
        return repositoryPropiedad.findById(id).orElse(null);
    }

    @Override
    public List<Propiedad> getAllPropiedad() {
        return repositoryPropiedad.findAll();
    }

    @Override
    public String savePropiedad(Propiedad propiedad) {
        repositoryPropiedad.save(propiedad);
        return "Propiedad guardada con éxito";
    }

    @Override
    public String updatePropiedad(Integer id, Propiedad propiedad) {
        Propiedad existente=repositoryPropiedad.findById(id).orElse(null);
        if(existente!=null){
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
            return "Propiedad actualizada con éxito";
        }
        return "No existe una propiedad con ese ID";
    }

    @Override
    public String deletePropiedad(Integer id) {
        Propiedad existente=repositoryPropiedad.findById(id).orElse(null);
        if(existente!=null){
            repositoryPropiedad.delete(existente);
            return "Propiedad eliminada con éxito";
        }
        return "No existe una propiedad con ese ID";
    }
}
