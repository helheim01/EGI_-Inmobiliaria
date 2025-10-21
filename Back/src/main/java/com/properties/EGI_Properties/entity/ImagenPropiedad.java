package com.properties.EGI_Properties.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "ImagenPropiedad")
public class ImagenPropiedad implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Integer id;

    @Column(name = "url_imagen", nullable = false, length = 255)
    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String urlImagen;

    @Column(name = "orden")
    private Integer orden;

    // N:1 con Propiedad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonBackReference //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "imagenes"}) //con esto nos ahorramos usar dto
    private Propiedad propiedad;
}
