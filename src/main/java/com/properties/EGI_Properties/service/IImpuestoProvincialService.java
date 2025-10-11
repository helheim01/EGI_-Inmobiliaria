package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.ImpuestoProvincial;

import java.util.List;

public interface IImpuestoProvincialService {
    ImpuestoProvincial getImpuestoProvincialById(Integer id);
    List<ImpuestoProvincial> getAllImpuestoProvincial();
    String saveImpuestoProvincial(ImpuestoProvincial impuestoProvincial);
    String updateImpuestoProvincial(Integer id, ImpuestoProvincial impuestoProvincial);
    String deleteImpuestoProvincial(Integer id);
}
