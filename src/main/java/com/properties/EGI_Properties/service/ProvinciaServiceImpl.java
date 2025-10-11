package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.Provincia;
import com.properties.EGI_Properties.repository.RepositoryProvincia;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProvinciaServiceImpl implements IProvinciaService{
    @Autowired
    private RepositoryProvincia repositoryProvincia;

    @Override
    public Provincia getProvinciaById(Integer id) {
        return repositoryProvincia.findById(id).orElse(null);
    }

    @Override
    public List<Provincia> getAllProvincias() {
        return repositoryProvincia.findAll();
    }

    @Override
    public String saveProvincia(Provincia provincia) {
        repositoryProvincia.save(provincia);
        return "Provincia guardada con éxito";
    }

    @Override
    public String updateProvincia(Integer id, Provincia provincia) {
        Provincia existente=repositoryProvincia.findById(id).orElse(null);
        if(existente!=null){
            existente.setNombre(provincia.getNombre());
            existente.setUbicaciones(provincia.getUbicaciones());
            existente.setImpuestoProvincial(provincia.getImpuestoProvincial());
            repositoryProvincia.save(existente);
            return "Provincia actualizada con exito";
        }
        return "No existe una provincia con ese ID";
    }

    @Override
    public String deleteProvincia(Integer id) {
        Provincia existente=repositoryProvincia.findById(id).orElse(null);
        if(existente!=null){
            repositoryProvincia.delete(existente);
            return "Provincia eliminada con exito";
        }
        return "No existe una provincia con ese ID";
    }
}
