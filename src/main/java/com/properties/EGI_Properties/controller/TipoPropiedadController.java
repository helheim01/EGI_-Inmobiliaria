package com.properties.EGI_Properties.controller;

import com.properties.EGI_Properties.entity.TipoPropiedad;
import com.properties.EGI_Properties.entity.Ubicacion;
import com.properties.EGI_Properties.service.TipoPropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/tipopropiedades")
public class TipoPropiedadController {
    @Autowired
    private TipoPropiedadService tipoPropiedadService;

    @GetMapping("/listar")
    public ResponseEntity<List<TipoPropiedad>> listar() {
        List<TipoPropiedad>tipoPropiedades=tipoPropiedadService.listar();
        return ResponseEntity.ok(tipoPropiedades);
    }

    // POST /tipopropiedades/agregar
    @PostMapping("/agregar")
    public ResponseEntity<TipoPropiedad> agregar(@RequestBody TipoPropiedad tipoPropiedad) {
        TipoPropiedad tipoPropiedadAgregada = tipoPropiedadService.agregar(tipoPropiedad);
        return ResponseEntity.ok(tipoPropiedadAgregada);
    }

    // PUT /tipopropiedades/modificar
    @PutMapping("/modificar")
    public ResponseEntity<TipoPropiedad> modificar(@RequestBody TipoPropiedad tipoPropiedad) {
        TipoPropiedad tipoPropiedadModificada = tipoPropiedadService.modificar(tipoPropiedad);
        return ResponseEntity.ok(tipoPropiedadModificada);
    }

    // DELETE /provincias/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoPropiedadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
