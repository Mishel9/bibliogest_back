package com.example.bibliogest.controller;

import com.example.bibliogest.entity.prestamo;
import com.example.bibliogest.service.prestamoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prestamos")
public class prestamocontroller {

    @Autowired
    private prestamoservice prestamoservice;

    // 📌 Listar todos los préstamos
    @GetMapping
    public List<prestamo> listar() {
        return prestamoservice.obtenerTodos();
    }

    // 📌 Obtener préstamo por ID
    @GetMapping("/{id}")
    public ResponseEntity<prestamo> obtenerPorId(@PathVariable Long id) {
        Optional<prestamo> resultado = prestamoservice.obtenerPorId(id);
        return resultado.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 📌 Crear un nuevo préstamo
    @PostMapping
    public ResponseEntity<prestamo> guardar(@RequestBody prestamo p) {
        // Si no se especifica la fecha de préstamo, usar la fecha actual
        if (p.getFechaPrestamo() == null) {
            p.setFechaPrestamo(LocalDate.now());
        }

        // Si no se especifica la fecha de devolución, se deja como null (sin cambio)
        prestamo nuevo = prestamoservice.guardar(p);
        return ResponseEntity.status(201).body(nuevo);
    }

    // 📌 Actualizar parcialmente un préstamo
    @PatchMapping("/{id}")
    public ResponseEntity<prestamo> actualizarParcial(@PathVariable Long id, @RequestBody prestamo datosActualizados) {
        prestamo actualizado = prestamoservice.actualizarParcial(id, datosActualizados);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // 📌 Eliminar un préstamo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        prestamoservice.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
