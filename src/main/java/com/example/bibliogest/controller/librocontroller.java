package com.example.bibliogest.controller;

import com.example.bibliogest.entity.libro;
import com.example.bibliogest.service.libroservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class librocontroller {

    @Autowired
    private libroservice libroservice;

    // 📌 Listar todos los libros
    @GetMapping
    public List<libro> listar() {
        return libroservice.obtenerTodos();
    }

    // 📌 Obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<libro> obtenerPorId(@PathVariable Long id) {
        Optional<libro> resultado = libroservice.obtenerPorId(id);
        return resultado.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 📌 Crear un nuevo libro (sin token ni login requerido)
    @PostMapping
    public ResponseEntity<libro> guardar(@RequestBody libro l) {
        libro nuevo = libroservice.guardar(l);
        return ResponseEntity.ok(nuevo);
    }

    // 📌 Actualizar parcialmente un libro (solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<libro> actualizarParcial(@PathVariable Long id, @RequestBody libro datosActualizados) {
        libro actualizado = libroservice.actualizarParcial(id, datosActualizados);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // 📌 Eliminar un libro por ID (solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroservice.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
