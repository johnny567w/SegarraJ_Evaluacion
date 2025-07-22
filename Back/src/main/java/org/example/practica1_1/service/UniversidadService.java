package org.example.practica1_1.service;


import org.example.practica1_1.Model.Universidad;
import org.example.practica1_1.repo.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversidadService {

    @Autowired
    private UniversidadRepository universidadRepository;

    public List<Universidad> listarTodas() {
        return universidadRepository.findAll();
    }

    public Optional<Universidad> obtenerPorId(Long id) {
        return universidadRepository.findById(id);
    }

    public Universidad guardar(Universidad universidad) {
        return universidadRepository.save(universidad);
    }

    public Optional<Universidad> actualizar(Long id, Universidad nuevaUniversidad) {
        return universidadRepository.findById(id).map(u -> {
            u.setNombre(nuevaUniversidad.getNombre());
            u.setCiudad(nuevaUniversidad.getCiudad());
            return universidadRepository.save(u);
        });
    }

    public void eliminar(Long id) {
        universidadRepository.deleteById(id);
    }
}
