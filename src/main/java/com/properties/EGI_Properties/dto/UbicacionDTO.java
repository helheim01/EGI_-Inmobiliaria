package com.properties.EGI_Properties.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UbicacionDTO {
    private Integer id;
    private String municipio;
    private String barrio;
    private String nombreProvincia; // nombre de la provincia asociada
}
