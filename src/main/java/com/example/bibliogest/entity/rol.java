package com.example.bibliogest.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL)
    private Set<usuario> usuarios;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Set<usuario> getUsuarios() { return usuarios; }
    public void setUsuarios(Set<usuario> usuarios) { this.usuarios = usuarios; }
}
