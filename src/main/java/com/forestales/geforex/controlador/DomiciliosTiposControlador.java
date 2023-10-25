package com.forestales.geforex.controlador;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forestales.geforex.modelo.For000Domiciliostipos;
import com.forestales.geforex.repositorio.DomiciliosTiposRepositorio;
import com.forestales.geforex.excepciones.ResourceNotFoundException;

@RestController
@RequestMapping("/dot1/")
// @CrossOrigin(origins = "http://localhost:4200")
public class DomiciliosTiposControlador {
    @Autowired
    DomiciliosTiposRepositorio repository;

    @GetMapping("/listar")
    public ResponseEntity<List<For000Domiciliostipos>> getAll() {
        try {
            List<For000Domiciliostipos> items = new ArrayList<For000Domiciliostipos>();

            repository.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recuperar/{id}")
    public ResponseEntity<For000Domiciliostipos> getById(@PathVariable("id") Long id) {
        Optional<For000Domiciliostipos> existingItemOptional = repository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<For000Domiciliostipos> create(@RequestBody For000Domiciliostipos item) {
        try {
            For000Domiciliostipos savedItem = repository.save(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<For000Domiciliostipos> update(@PathVariable("id") Long id,
            @RequestBody For000Domiciliostipos expedientesEstados) {
        Optional<For000Domiciliostipos> existingItemOptional = repository.findById(id);
        if (existingItemOptional.isPresent()) {
            For000Domiciliostipos existingItem = existingItemOptional.get();
            System.out
                    .println("TODO for developer - update logic is unique to entity and must be implemented manually.");
            // existingItem.setSomeField(item.getSomeField());
            return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
