package com.properties.EGI_Properties.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PropiedadDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precioBase;
    private String moneda;               // "ARG" o "USD"
    private Double superficieM2;
    private Integer ambientes;
    private LocalDateTime fechaPublicacion;

    // Relaciones simplificadas
    private String tipoPropiedad;         // nombre del tipo (ej: "Casa", "Departamento")
    private String ubicacionMunicipio;    // municipio de la ubicación
    private String ubicacionBarrio;       // barrio de la ubicación
    private String provincia;             // nombre de la provincia
    private List<String> imagenes;        // URLs de las imágenes asociadas
}
