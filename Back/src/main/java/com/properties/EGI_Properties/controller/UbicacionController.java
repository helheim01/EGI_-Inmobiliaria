package com.properties.EGI_Properties.controller;

import com.properties.EGI_Properties.entity.Ubicacion;
import com.properties.EGI_Properties.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // permite cualquier origen
@RestController
@Validated
@RequestMapping("/ubicaciones")
public class UbicacionController {
    @Autowired
    private UbicacionService ubicacionService;

    // GET /ubicaciones/listar
    @GetMapping("/listar")
    public ResponseEntity<List<Ubicacion>> listar() {
        List<Ubicacion> listaUbicaciones = ubicacionService.listar();
        return ResponseEntity.ok(listaUbicaciones);
    }

    // GET /ubicaciones/buscar/{id}
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Ubicacion> buscar(@PathVariable Integer id) {
        Ubicacion ubicacion = ubicacionService.buscar(id);
        return ResponseEntity.ok(ubicacion);
    }

    // POST /ubicaciones/agregar
    @PostMapping("/agregar")
    public ResponseEntity<Ubicacion> agregar(@RequestBody Ubicacion ubicacion) {
        Ubicacion ubicacionAgregada = ubicacionService.agregar(ubicacion);
        return ResponseEntity.ok(ubicacionAgregada);
    }

    // PUT /ubicaciones/modificar
    @PutMapping("/modificar")
    public ResponseEntity<Ubicacion> modificar(@RequestBody Ubicacion ubicacion) {
        Ubicacion ubicacionModificada = ubicacionService.modificar(ubicacion);
        return ResponseEntity.ok(ubicacionModificada);
    }

    // DELETE /ubicaciones/eliminar/{id}
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        ubicacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
