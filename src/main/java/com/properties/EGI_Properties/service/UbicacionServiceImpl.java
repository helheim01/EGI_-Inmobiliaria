package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.Ubicacion;
import com.properties.EGI_Properties.repository.RepositoryUbicacion;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UbicacionServiceImpl implements IUbicacionService{
    @Autowired
    private RepositoryUbicacion repositoryUbicacion;
    @Override
    public Ubicacion getUbicacionById(Integer id) {
        return repositoryUbicacion.findById(id).orElse(null);
    }

    @Override
    public List<Ubicacion> getAllUbicaciones() {
        return repositoryUbicacion.findAll();
    }

    @Override
    public String saveUbicacion(Ubicacion ubicacion) {
        repositoryUbicacion.save(ubicacion);
        return "Ubicacion guardada con exito";
    }

    @Override
    public String updateUbicacion(Integer id, Ubicacion ubicacion) {
        Ubicacion existente=repositoryUbicacion.findById(id).orElse(null);
        if(existente!=null){
            existente.setBarrio(ubicacion.getBarrio());
            existente.setMunicipio(ubicacion.getMunicipio());
            existente.setProvincia(ubicacion.getProvincia());
            existente.setPropiedades(ubicacion.getPropiedades());
            repositoryUbicacion.save(existente);
            return "Ubicacion actualizada con exito";
        }
        return "No existe una ubicacion con ese ID";
    }

    @Override
    public String deleteUbicacion(Integer id) {
        Ubicacion existente=repositoryUbicacion.findById(id).orElse(null);
        if(existente!=null){
            repositoryUbicacion.delete(existente);
            return "Ubicacion eliminada con exito";
        }
        return "No existe una ubicacion con ese ID";
    }
}
