package com.properties.EGI_Properties.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Provincia")
public class Provincia implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_provincia")
    private Integer id;

    @Column(name = "nombre_provincia", nullable = false)
    @NotBlank(message = "El nombre de la provincia es obligatorio")
    private String nombre;

    // Relación 1:1 inversa (Provincia ↔ ImpuestoProvincial)
    @OneToOne(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference //para evitar recursividad al buscar una
    private ImpuestoProvincial impuestoProvincial;

    // Relación 1:N con Ubicacion
    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Ubicacion> ubicaciones = new ArrayList<>();
}