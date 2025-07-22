package org.example.practica1_1.Controlador;

import org.example.practica1_1.Model.Universidad;
import org.example.practica1_1.repo.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/universidades")
public class UniversidadController {


        @Autowired
        private UniversidadRepository repo;

        @GetMapping
        public List<Universidad> listar() { return repo.findAll(); }

        @PostMapping
        public Universidad crear(@RequestBody Universidad u) { return repo.save(u); }

        @PutMapping("/{id}")
        public ResponseEntity<Universidad> actualizar(@PathVariable Long id, @RequestBody Universidad u) {
            return repo.findById(id).map(existente -> {
                existente.setNombre(u.getNombre());
                existente.setCiudad(u.getCiudad());
                return ResponseEntity.ok(repo.save(existente));
            }).orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public void eliminar(@PathVariable Long id) { repo.deleteById(id); }
    }

