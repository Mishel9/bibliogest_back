package com.example.bibliogest.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    private int anioPublicacion;

    private String isbn;

    private boolean disponible;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<prestamo> prestamos;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<usuariolibrofavorito> usuariosFavoritos;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public List<prestamo> getPrestamos() { return prestamos; }
    public void setPrestamos(List<prestamo> prestamos) { this.prestamos = prestamos; }

    public List<usuariolibrofavorito> getUsuariosFavoritos() { return usuariosFavoritos; }
    public void setUsuariosFavoritos(List<usuariolibrofavorito> usuariosFavoritos) {
        this.usuariosFavoritos = usuariosFavoritos;
    }
}
