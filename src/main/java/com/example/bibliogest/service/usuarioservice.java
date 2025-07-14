package com.example.bibliogest.service;

import com.example.bibliogest.entity.usuario;
import com.example.bibliogest.repository.usuariorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class usuarioservice {

    @Autowired
    private usuariorepository usuariorepository;

    public List<usuario> obtenerTodos() {
        return usuariorepository.findAll();
    }

    public Optional<usuario> obtenerPorId(Long id) {
        return usuariorepository.findById(id);
    }

    public usuario guardar(usuario u) {
        return usuariorepository.save(u);
    }

    public usuario actualizarParcial(Long id, usuario datosActualizados) {
        Optional<usuario> optional = usuariorepository.findById(id);
        if (optional.isPresent()) {
            usuario existente = optional.get();

            // Reemplazamos username por email
            if (datosActualizados.getEmail() != null) {
                existente.setEmail(datosActualizados.getEmail());
            }

            if (datosActualizados.getPassword() != null) {
                existente.setPassword(datosActualizados.getPassword());
            }

            if (datosActualizados.getRol() != null) {
                existente.setRol(datosActualizados.getRol());
            }

            return usuariorepository.save(existente);
        }
        return null;
    }

    public void eliminar(Long id) {
        usuariorepository.deleteById(id);
    }
}
