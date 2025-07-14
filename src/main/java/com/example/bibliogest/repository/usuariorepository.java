package com.example.bibliogest.repository;

import com.example.bibliogest.entity.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface usuariorepository extends JpaRepository<usuario, Long> {
    Optional<usuario> findByEmail(String email);
}
