package com.example.bibliogest.service;

import com.example.bibliogest.entity.usuariolibrofavorito;
import com.example.bibliogest.entity.usuariolibrofavoritoid;
import com.example.bibliogest.repository.usuariolibrofavoritorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class usuariolibrofavoritoservice {

    @Autowired
    private usuariolibrofavoritorepository repo;

    public usuariolibrofavorito guardar(usuariolibrofavorito favorito) {
        return repo.save(favorito);
    }

    public boolean existeFavorito(usuariolibrofavoritoid id) {
        return repo.existsById(id);
    }

    public void eliminarFavorito(usuariolibrofavoritoid id) {
        repo.deleteById(id);
    }

    public List<usuariolibrofavorito> obtenerFavoritosPorUsuario(Long usuarioId) {
        return repo.findByUsuarioId(usuarioId);
    }

    public void eliminarPorUsuarioYLibro(Long usuarioId, Long libroId) {
        // 🔧 Cambio importante aquí:
        repo.deleteByIdUsuarioIdAndIdLibroId(usuarioId, libroId);
    }
}
