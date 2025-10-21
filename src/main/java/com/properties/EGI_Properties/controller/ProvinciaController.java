package com.properties.EGI_Properties.controller;

import com.properties.EGI_Properties.entity.Provincia;
import com.properties.EGI_Properties.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/provincias")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    // GET /provincias/listar
    @GetMapping("/listar")
    public ResponseEntity<List<Provincia>> listar() {
        List<Provincia> listaProvincias = provinciaService.listar();
        return ResponseEntity.ok(listaProvincias);
    }

    // GET /provincias/buscar/{id}
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Provincia> buscar(@PathVariable Integer id) {
        Provincia provincia = provinciaService.buscar(id);
        return ResponseEntity.ok(provincia);
    }

    // POST /provincias/agregar
    @PostMapping("/agregar")
    public ResponseEntity<Provincia> agregar(@RequestBody Provincia provincia) {
        Provincia provinciaAgregada = provinciaService.agregar(provincia);
        return ResponseEntity.ok(provinciaAgregada);
    }

    // PUT /provincias/modificar
    @PutMapping("/modificar")
    public ResponseEntity<Provincia> modificar(@RequestBody Provincia provincia) {
        Provincia provinciaModificada = provinciaService.modificar(provincia);
        return ResponseEntity.ok(provinciaModificada);
    }

    // DELETE /provincias/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        provinciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
