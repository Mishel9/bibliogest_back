package com.example.bibliogest.repository;

import com.example.bibliogest.entity.usuariolibrofavorito;
import com.example.bibliogest.entity.usuariolibrofavoritoid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface usuariolibrofavoritorepository extends JpaRepository<usuariolibrofavorito, usuariolibrofavoritoid> {

    boolean existsById(usuariolibrofavoritoid id);

    void deleteById(usuariolibrofavoritoid id);

    List<usuariolibrofavorito> findByUsuarioId(Long usuarioId);

    // ✅ Método que necesitas para compilar correctamente
    void deleteByIdUsuarioIdAndIdLibroId(Long usuarioId, Long libroId);
}
