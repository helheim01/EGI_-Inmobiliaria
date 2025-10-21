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
@Table(name = "TipoPropiedad")
public class TipoPropiedad implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipopropiedad")
    private Integer id;

    @Column(name = "nombre_tipopropiedad", nullable = false)
    @NotBlank(message = "El nombre del tipo de propiedad es obligatorio")
    private String nombre;

    // 1:N con Propiedad
    @OneToMany(mappedBy = "tipoPropiedad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Propiedad> propiedades = new ArrayList<>();
}
