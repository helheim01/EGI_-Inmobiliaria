package com.properties.EGI_Properties.entity;

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
@Table(name = "Ubicacion")
public class Ubicacion implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer id;

    @Column(name = "nombre_municipio", nullable = false)
    @NotBlank(message = "El nombre del municipio es obligatorio")
    private String municipio;

    @Column(name = "nombre_barrio", nullable = false)
    @NotBlank(message = "El nombre del barrio es obligatorio")
    private String barrio;

    // N:1 con Provincia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;

    // 1:N con Propiedad
    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Propiedad> propiedades = new ArrayList<>();
}
