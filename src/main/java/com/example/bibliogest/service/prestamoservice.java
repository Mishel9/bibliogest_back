package com.example.bibliogest.service;

import com.example.bibliogest.entity.prestamo;
import com.example.bibliogest.repository.prestamorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class prestamoservice {

    @Autowired
    private prestamorepository prestamorepository;

    public List<prestamo> obtenerTodos() {
        return prestamorepository.findAll();
    }

    public Optional<prestamo> obtenerPorId(Long id) {
        return prestamorepository.findById(id);
    }

    public prestamo guardar(prestamo p) {
        return prestamorepository.save(p);
    }

    public prestamo actualizarParcial(Long id, prestamo datosActualizados) {
        Optional<prestamo> optional = prestamorepository.findById(id);
        if (optional.isPresent()) {
            prestamo existente = optional.get();

            if (datosActualizados.getFechaPrestamo() != null) {
                existente.setFechaPrestamo(datosActualizados.getFechaPrestamo());
            }
            if (datosActualizados.getFechaDevolucion() != null) {
                existente.setFechaDevolucion(datosActualizados.getFechaDevolucion());
            }
            if (datosActualizados.getLibro() != null) {
                existente.setLibro(datosActualizados.getLibro());
            }
            if (datosActualizados.getNombre() != null) {
                existente.setNombre(datosActualizados.getNombre());
            }

            return prestamorepository.save(existente);
        }
        return null;
    }

    public void eliminar(Long id) {
        prestamorepository.deleteById(id);
    }
}
