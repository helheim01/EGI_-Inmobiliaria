package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.TipoPropiedad;
import com.properties.EGI_Properties.repository.RepositoryTipoPropiedad;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TipoPropiedadServiceImpl implements ITipoPropiedadService{
    @Autowired
    private RepositoryTipoPropiedad repositoryTipoPropiedad;

    @Override
    public TipoPropiedad getTipoPropiedadById(Integer id) {
        return repositoryTipoPropiedad.findById(id).orElse(null);
    }

    @Override
    public List<TipoPropiedad> getAllTipoPropiedad() {
        return repositoryTipoPropiedad.findAll();
    }

    @Override
    public String saveTipoPropiedad(TipoPropiedad tipoPropiedad) {
        repositoryTipoPropiedad.save(tipoPropiedad);
        return "Tipo de propiedad guardada con exito";
    }

    @Override
    public String updateTipoPropiedad(Integer id, TipoPropiedad tipoPropiedad) {
        TipoPropiedad existente=repositoryTipoPropiedad.findById(id).orElse(null);
        if(existente!=null){
            existente.setNombre(tipoPropiedad.getNombre());
            existente.setPropiedades(tipoPropiedad.getPropiedades());
            repositoryTipoPropiedad.save(existente);
            return "Tipo de propiedad actualizada con exito";
        }
        return "No existe un tipo de propiedad con ese ID";
    }

    @Override
    public String deleteTipoPropiedad(Integer id) {
        TipoPropiedad existente=repositoryTipoPropiedad.findById(id).orElse(null);
        if(existente!=null){
            repositoryTipoPropiedad.delete(existente);
            return "Tipo de propiedad eliminada con exito";
        }
        return "No existe un tipo de propiedad con ese ID";
    }
}
