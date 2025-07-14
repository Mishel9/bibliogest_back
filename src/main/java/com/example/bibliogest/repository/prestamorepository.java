package com.example.bibliogest.repository;

import com.example.bibliogest.entity.prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface prestamorepository extends JpaRepository<prestamo, Long> {

    // Buscar todos los préstamos de un usuario
    List<prestamo> findByUsuarioId(Long usuarioId);

    // Buscar todos los préstamos de un libro
    List<prestamo> findByLibroId(Long libroId);
}
