package com.example.bibliogest.repository;

import com.example.bibliogest.entity.rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface rolrepository extends JpaRepository<rol, Long> {
    rol findByNombre(String nombre);
}
