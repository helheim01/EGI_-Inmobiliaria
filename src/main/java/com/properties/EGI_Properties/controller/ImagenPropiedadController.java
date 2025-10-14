package com.properties.EGI_Properties.controller;

import com.properties.EGI_Properties.entity.ImagenPropiedad;
import com.properties.EGI_Properties.service.ImagenPropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/imagenes")
public class ImagenPropiedadController {

    @Autowired
    private ImagenPropiedadService imagenPropiedadService;

    // GET /imagenes/listar
    @GetMapping("/listar")
    public ResponseEntity<List<ImagenPropiedad>> listar() {
        List<ImagenPropiedad> listaImagenes = imagenPropiedadService.listar();
        return ResponseEntity.ok(listaImagenes);
    }

    // GET /imagenes/buscar/{id}
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ImagenPropiedad> buscar(@PathVariable Integer id) {
        ImagenPropiedad imagen = imagenPropiedadService.buscar(id);
        return ResponseEntity.ok(imagen);
    }

    // POST /imagenes/agregar
    @PostMapping("/agregar")
    public ResponseEntity<ImagenPropiedad> agregar(@RequestBody ImagenPropiedad imagenPropiedad) {
        ImagenPropiedad nuevaImagen = imagenPropiedadService.agregar(imagenPropiedad);
        return ResponseEntity.ok(nuevaImagen);
    }

    // PUT /imagenes/modificar
    @PutMapping("/modificar")
    public ResponseEntity<ImagenPropiedad> modificar(@RequestBody ImagenPropiedad imagenPropiedad) {
        ImagenPropiedad imagenModificada = imagenPropiedadService.modificar(imagenPropiedad);
        return ResponseEntity.ok(imagenModificada);
    }

    // DELETE /imagenes/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        imagenPropiedadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
