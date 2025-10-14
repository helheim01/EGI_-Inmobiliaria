package com.properties.EGI_Properties.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Propiedad")
public class Propiedad implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Integer id;

    @Column(name = "nombre_propiedad", nullable = false)
    @NotBlank(message = "El nombre de la propiedad es obligatorio")
    private String nombre;

    @Column(name = "descripcion_propiedad", nullable = false)
    @NotBlank(message = "La descripción de la propiedad es obligatoria")
    private String descripcion;

    @Column(name = "precio_base", nullable = false)
    @NotNull(message = "El precio base es obligatorio")
    @Positive(message = "El precio debe ser positivo")
    private Double precioBase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    private Moneda moneda; // ARS o USD

    @Column(name = "superficie_m2", nullable = false)
    @NotNull(message = "La superficie es obligatoria")
    @Positive(message = "La superficie debe ser positiva")
    private Double superficieM2;

    @Column(name = "ambientes", nullable = false)
    @NotNull(message = "Los ambientes son obligatorios")
    @Min(value = 1, message = "Debe tener al menos 1 ambiente")
    private Integer ambientes;

    @Column(name = "fecha_publicacion", nullable = false, updatable = false)
    private LocalDateTime fechaPublicacion;

    // N:1 con Ubicacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ubicacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "propiedades"})
    private Ubicacion ubicacion;

    // N:1 con TipoPropiedad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipopropiedad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "propiedades"})
    private TipoPropiedad tipoPropiedad;

    // 1:N con ImagenPropiedad
    @OneToMany(mappedBy = "propiedad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @OrderBy("orden ASC")
    @JsonManagedReference //dueño de la relacion q serializa
    private List<ImagenPropiedad> imagenes = new ArrayList<>();

    // Autoasignación de fecha
    @PrePersist
    protected void onCreate() {
        fechaPublicacion = LocalDateTime.now();
    }
}
