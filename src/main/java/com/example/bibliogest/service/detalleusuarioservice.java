package com.example.bibliogest.service;

import com.example.bibliogest.entity.detalleusuario;
import com.example.bibliogest.repository.detalleusuariorepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class detalleusuarioservice {

    private final detalleusuariorepository repo;

    public detalleusuarioservice(detalleusuariorepository repo) {
        this.repo = repo;
    }

    public detalleusuario guardar(detalleusuario detalle) {
        return repo.save(detalle);
    }

    public Optional<detalleusuario> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
