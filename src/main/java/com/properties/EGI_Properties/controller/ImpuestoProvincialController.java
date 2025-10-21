package com.properties.EGI_Properties.controller;

import com.properties.EGI_Properties.entity.ImpuestoProvincial;
import com.properties.EGI_Properties.service.ImpuestoProvincialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/impuestos")
public class ImpuestoProvincialController {

    @Autowired
    private ImpuestoProvincialService impuestoProvincialService;

    // GET /impuestos/listar
    @GetMapping("/listar")
    public ResponseEntity<List<ImpuestoProvincial>> listar() {
        List<ImpuestoProvincial> listaImpuestos = impuestoProvincialService.listar();
        return ResponseEntity.ok(listaImpuestos);
    }

    // GET /impuestos/buscar/{id}
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ImpuestoProvincial> buscar(@PathVariable Integer id) {
        ImpuestoProvincial impuesto = impuestoProvincialService.buscar(id);
        return ResponseEntity.ok(impuesto);
    }

    // POST /impuestos/agregar
    @PostMapping("/agregar")
    public ResponseEntity<ImpuestoProvincial> agregar(@RequestBody ImpuestoProvincial impuestoProvincial) {
        ImpuestoProvincial nuevoImpuesto = impuestoProvincialService.agregar(impuestoProvincial);
        return ResponseEntity.ok(nuevoImpuesto);
    }

    // PUT /impuestos/modificar
    @PutMapping("/modificar")
    public ResponseEntity<ImpuestoProvincial> modificar(@RequestBody ImpuestoProvincial impuestoProvincial) {
        ImpuestoProvincial impuestoModificado = impuestoProvincialService.modificar(impuestoProvincial);
        return ResponseEntity.ok(impuestoModificado);
    }

    // DELETE /impuestos/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        impuestoProvincialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
