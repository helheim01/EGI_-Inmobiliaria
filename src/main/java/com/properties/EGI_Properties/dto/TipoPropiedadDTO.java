package com.properties.EGI_Properties.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class TipoPropiedadDTO {
    private Integer id;
    private String nombre;
    private List<String> nombresPropiedades; // lista con los nombres de las propiedades asociadas
}
