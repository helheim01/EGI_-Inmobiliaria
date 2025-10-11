package com.properties.EGI_Properties.service;

import com.properties.EGI_Properties.entity.ImagenPropiedad;
import com.properties.EGI_Properties.repository.RepositoryImagenPropiedad;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImagenPropiedadServiceImpl implements IImagenPropiedadService{
    @Autowired
    private RepositoryImagenPropiedad repositoryImagenPropiedad;

    @Override
    public ImagenPropiedad getImagenPropiedadById(Integer id) {
        return repositoryImagenPropiedad.findById(id).orElse(null);
    }

    @Override
    public List<ImagenPropiedad> getAllImagenPropiedad() {
        return repositoryImagenPropiedad.findAll();
    }

    @Override
    public String saveImagenPropiedad(ImagenPropiedad imagenPropiedad) {
        repositoryImagenPropiedad.save(imagenPropiedad);
        return "Imagen de propiedad guardada con exito";
    }

    @Override
    public String updateImagenPropiedad(Integer id, ImagenPropiedad imagenPropiedad) {
        ImagenPropiedad existente=repositoryImagenPropiedad.findById(id).orElse(null);
        if(existente!=null){
            existente.setUrlImagen(imagenPropiedad.getUrlImagen());
            existente.setOrden(imagenPropiedad.getOrden());
            existente.setPropiedad(imagenPropiedad.getPropiedad());
            repositoryImagenPropiedad.save(existente);
            return "Imagen de propiedad actualizada con exito";
        }
        return "No existe una imagen de propiedad con ese ID";
    }

    @Override
    public String deleteImagenPropiedad(Integer id) {
        ImagenPropiedad existente=repositoryImagenPropiedad.findById(id).orElse(null);
        if(existente!=null){
            repositoryImagenPropiedad.delete(existente);
            return "Imagen de propiedad eliminada con exito";
        }
        return "No existe una imagen de propiedad con ese ID";
    }
}
