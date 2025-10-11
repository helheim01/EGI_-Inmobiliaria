package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.ImpuestoProvincial;
import com.properties.EGI_Properties.repository.RepositoryImpuestoProvincial;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImpuestoProvincialServiceImpl implements IImpuestoProvincialService{

    @Autowired
    private RepositoryImpuestoProvincial repositoryImpuestoProvincial;

    @Override
    public ImpuestoProvincial getImpuestoProvincialById(Integer id) {
        return repositoryImpuestoProvincial.findById(id).orElse(null);
    }

    @Override
    public List<ImpuestoProvincial> getAllImpuestoProvincial() {
        return repositoryImpuestoProvincial.findAll();
    }

    @Override
    public String saveImpuestoProvincial(ImpuestoProvincial impuestoProvincial) {
        repositoryImpuestoProvincial.save(impuestoProvincial);
        return "Impuesto provincial guardado con éxito";
    }

    @Override
    public String updateImpuestoProvincial(Integer id, ImpuestoProvincial impuestoProvincial) {
        ImpuestoProvincial existente=repositoryImpuestoProvincial.findById(id).orElse(null);
        if(existente!=null){
            existente.setPorcentaje(impuestoProvincial.getPorcentaje());
            existente.setProvincia(impuestoProvincial.getProvincia());
            repositoryImpuestoProvincial.save(existente);
            return "Impuesto provincial actualizado con éxito";
        }
        return "No existe un impuesto provincial con ese ID";
    }

    @Override
    public String deleteImpuestoProvincial(Integer id) {
        ImpuestoProvincial existente=repositoryImpuestoProvincial.findById(id).orElse(null);
        if(existente!=null){
            repositoryImpuestoProvincial.delete(existente);
            return "Impuesto provincial eliminado con éxito";
        }
        return "No existe un impuesto provincial con ese ID";
    }
}
