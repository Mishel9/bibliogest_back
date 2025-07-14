package com.example.bibliogest.service;

import com.example.bibliogest.entity.libro;
import com.example.bibliogest.repository.librorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class libroservice {

    @Autowired
    private librorepository librorepository;

    public List<libro> obtenerTodos() {
        return librorepository.findAll();
    }

    public Optional<libro> obtenerPorId(Long id) {
        return librorepository.findById(id);
    }

    public libro guardar(libro l) {
        return librorepository.save(l);
    }

    public libro actualizarParcial(Long id, libro datosActualizados) {
        Optional<libro> optional = librorepository.findById(id);
        if (optional.isPresent()) {
            libro existente = optional.get();
            if (datosActualizados.getTitulo() != null) {
                existente.setTitulo(datosActualizados.getTitulo());
            }
            if (datosActualizados.getAutor() != null) {
                existente.setAutor(datosActualizados.getAutor());
            }
            if (datosActualizados.getAnioPublicacion() != 0) {
                existente.setAnioPublicacion(datosActualizados.getAnioPublicacion());
            }
            return librorepository.save(existente);
        }
        return null;
    }

    public void eliminar(Long id) {
        librorepository.deleteById(id);
    }
}
