package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.ImagenPropiedad;

import java.util.List;

public interface IImagenPropiedadService {
    ImagenPropiedad getImagenPropiedadById(Integer id);
    List<ImagenPropiedad> getAllImagenPropiedad();
    String saveImagenPropiedad(ImagenPropiedad imagenPropiedad);
    String updateImagenPropiedad(Integer id, ImagenPropiedad imagenPropiedad);
    String deleteImagenPropiedad(Integer id);
}
