package com.properties.EGI_Properties.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImpuestoProvincialDTO {
    private Integer id;              // id_provincia (PK compartida)
    private Double porcentaje;       // porcentaje del impuesto
    private String nombreProvincia;  // opcional, para mostrar la provincia asociada
}
