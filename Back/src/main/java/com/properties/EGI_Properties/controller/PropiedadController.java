package com.properties.EGI_Properties.controller;

import com.properties.EGI_Properties.entity.Propiedad;
import com.properties.EGI_Properties.service.PropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/propiedades")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    // GET /propiedades/listar
    @GetMapping("/listar")
    public ResponseEntity<List<Propiedad>> listar() {
        List<Propiedad> listaPropiedades = propiedadService.listar();
        return ResponseEntity.ok(listaPropiedades);
    }

    // GET /propiedades/buscar/{id}
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Propiedad> buscar(@PathVariable Integer id) {
        Propiedad propiedad = propiedadService.buscar(id);
        return ResponseEntity.ok(propiedad);
    }

    // POST /propiedades/agregar
    @PostMapping("/agregar")
    public ResponseEntity<Propiedad> agregar(@RequestBody Propiedad propiedad) {
        Propiedad nuevaPropiedad = propiedadService.agregar(propiedad);
        return ResponseEntity.ok(nuevaPropiedad);
    }

    // PUT /propiedades/modificar
    @PutMapping("/modificar")
    public ResponseEntity<Propiedad> modificar(@RequestBody Propiedad propiedad) {
        Propiedad propiedadModificada = propiedadService.modificar(propiedad);
        return ResponseEntity.ok(propiedadModificada);
    }

    // DELETE /propiedades/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        propiedadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
