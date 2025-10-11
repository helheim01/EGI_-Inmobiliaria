package com.properties.EGI_Properties.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProvinciaDTO {
    private Integer id;
    private String nombre;
    private Double porcentajeImpuesto;
    private List<String> ubicaciones; // o List<UbicacionDTO> si tenés otro DTO
}
