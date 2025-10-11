package com.properties.EGI_Properties.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "impuestoProvincial")
public class ImpuestoProvincial implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // PK compartida con Provincia
    @Id
    @Column(name = "id_provincia")
    private Integer id;

    // Lado dueño de la relación 1:1
    @OneToOne
    @MapsId // Indica que la PK viene de Provincia
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    @Column(name = "porcentaje", nullable = false, precision = 5, scale = 2)
    @NotNull(message = "El porcentaje es obligatorio")
    @DecimalMin(value = "0.0", message = "El porcentaje debe ser positivo")
    private Double porcentaje;
}