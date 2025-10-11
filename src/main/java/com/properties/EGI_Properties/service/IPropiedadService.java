package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.Propiedad;

import java.util.List;

public interface IPropiedadService {
    Propiedad getPropiedadById(Integer id);
    List<Propiedad> getAllPropiedad();
    String savePropiedad(Propiedad propiedad);
    String updatePropiedad(Integer id, Propiedad propiedad);
    String deletePropiedad(Integer id);
}
