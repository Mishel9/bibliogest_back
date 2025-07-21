package com.example.bibliogest.controller;

import com.example.bibliogest.entity.libro;
import com.example.bibliogest.entity.usuario;
import com.example.bibliogest.entity.usuariolibrofavorito;
import com.example.bibliogest.entity.usuariolibrofavoritoid;
import com.example.bibliogest.repository.librorepository;
import com.example.bibliogest.repository.usuariolibrofavoritorepository;
import com.example.bibliogest.repository.usuariorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
public class usuariolibrofavoritocontroller {

    @Autowired
    private usuariolibrofavoritorepository favoritoRepo;

    @Autowired
    private usuariorepository usuarioRepo;

    @Autowired
    private librorepository libroRepo;

    // 📌 Agregar libro a favoritos
    @PostMapping
    public ResponseEntity<String> agregarFavorito(@RequestParam Long usuarioId, @RequestParam Long libroId) {
        usuariolibrofavoritoid id = new usuariolibrofavoritoid(usuarioId, libroId);

        if (favoritoRepo.existsById(id)) {
            return ResponseEntity.badRequest().body("El libro ya está marcado como favorito.");
        }

        usuario usuario = usuarioRepo.findById(usuarioId).orElse(null);
        libro libro = libroRepo.findById(libroId).orElse(null);

        if (usuario == null || libro == null) {
            return ResponseEntity.badRequest().body("Usuario o libro no encontrados.");
        }

        usuariolibrofavorito favorito = new usuariolibrofavorito();
        favorito.setId(id);
        favorito.setUsuario(usuario);
        favorito.setLibro(libro);

        favoritoRepo.save(favorito);
        return ResponseEntity.ok("Libro agregado a favoritos.");
    }

    // 📌 Eliminar libro de favoritos
    @DeleteMapping
    public ResponseEntity<String> eliminarFavorito(@RequestParam Long usuarioId, @RequestParam Long libroId) {
        usuariolibrofavoritoid id = new usuariolibrofavoritoid(usuarioId, libroId);
        if (!favoritoRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        favoritoRepo.deleteById(id);
        return ResponseEntity.ok("Libro eliminado de favoritos.");
    }

    // 📌 Verificar si un libro es favorito
    @GetMapping("/existe")
    public boolean existeFavorito(@RequestParam Long usuarioId, @RequestParam Long libroId) {
        usuariolibrofavoritoid id = new usuariolibrofavoritoid(usuarioId, libroId);
        return favoritoRepo.existsById(id);
    }

    // 📌 Listar todos los favoritos de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<usuariolibrofavorito> listarFavoritos(@PathVariable Long usuarioId) {
        return favoritoRepo.findByUsuarioId(usuarioId);
    }

    // 📌 Listar TODOS los libros marcados como favoritos (sin token)
    @GetMapping
    public List<libro> obtenerTodosLosFavoritos() {
        List<usuariolibrofavorito> favoritos = favoritoRepo.findAll();
        return favoritos.stream()
                .map(usuariolibrofavorito::getLibro)
                .toList();
    }
}
