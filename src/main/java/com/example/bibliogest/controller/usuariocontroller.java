package com.example.bibliogest.controller;

import com.example.bibliogest.entity.usuario;
import com.example.bibliogest.service.usuarioservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class usuariocontroller {

    @Autowired
    private usuarioservice usuarioservice;

    // 📌 Obtener todos los usuarios
    @GetMapping
    public List<usuario> listar() {
        return usuarioservice.obtenerTodos();
    }

    // 📌 Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<usuario> obtenerPorId(@PathVariable Long id) {
        Optional<usuario> resultado = usuarioservice.obtenerPorId(id);
        return resultado.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 📌 Crear nuevo usuario
    @PostMapping
    public ResponseEntity<usuario> guardar(@RequestBody usuario u) {
        usuario creado = usuarioservice.guardar(u);
        return ResponseEntity.ok(creado);
    }

    // 📌 Actualizar parcialmente un usuario
    @PatchMapping("/{id}")
    public ResponseEntity<usuario> actualizarParcial(@PathVariable Long id, @RequestBody usuario datosActualizados) {
        usuario actualizado = usuarioservice.actualizarParcial(id, datosActualizados);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // 📌 Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioservice.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
