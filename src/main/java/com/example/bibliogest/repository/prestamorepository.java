package com.example.bibliogest.repository;

import com.example.bibliogest.entity.prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface prestamorepository extends JpaRepository<prestamo, Long> {

    // Buscar todos los préstamos por nombre de usuario (campo de texto)
    List<prestamo> findByNombre(String nombre);

    // Buscar todos los préstamos por título del libro (campo de texto)
    List<prestamo> findByLibro(String libro);
}
