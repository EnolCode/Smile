package com.Smile.demo.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Smile.demo.entitys.Tratamiento;
import com.Smile.demo.services.TratamientoServiceImpl;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/tratamiento")
public class TratamientoController {
    @Autowired
    private TratamientoServiceImpl tratamientoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<Tratamiento> oTratamiento = tratamientoService.findById(id);
        if (oTratamiento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oTratamiento.get());
    }

    @PostMapping(value = "", consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody Tratamiento tratamiento) {
        return ResponseEntity.status(201).body(tratamientoService.save(tratamiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Tratamiento tratamientoDetails, @PathVariable Long id) {
        Optional<Tratamiento> tratamiento = tratamientoService.findById(id);
        if (!tratamiento.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tratamiento.get().setTratamiento(tratamientoDetails.getTratamiento());

        return ResponseEntity.status(201).body(tratamientoService.save(tratamiento.get()));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!tratamientoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tratamientoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(tratamientoService.findAll());
    }

}