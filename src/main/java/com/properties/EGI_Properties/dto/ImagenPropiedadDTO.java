package com.properties.EGI_Properties.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImagenPropiedadDTO {
    private Integer id;
    private String urlImagen;
    private String nombrePropiedad; // nombre de la propiedad asociada
}
