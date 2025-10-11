package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.Provincia;

import java.util.List;

public interface IProvinciaService {
    Provincia getProvinciaById(Integer id);
    List<Provincia> getAllProvincias();
    String saveProvincia(Provincia provincia);
    String updateProvincia(Integer id, Provincia provincia);
    String deleteProvincia(Integer id);
}
