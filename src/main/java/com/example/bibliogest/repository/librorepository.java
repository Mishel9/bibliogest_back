package com.example.bibliogest.repository;

import com.example.bibliogest.entity.libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface librorepository extends JpaRepository<libro, Long> {

    // Buscar por título
    List<libro> findByTituloContainingIgnoreCase(String titulo);

    // Buscar por autor
    List<libro> findByAutorContainingIgnoreCase(String autor);

    // Buscar solo disponibles
    List<libro> findByDisponibleTrue();
}
