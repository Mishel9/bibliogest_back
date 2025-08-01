package com.example.bibliogest.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private rol rol;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalle_id", referencedColumnName = "id")
    private detalleusuario detalle;

    // ❌ Eliminado:
    // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    // private List<prestamo> prestamos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<usuariolibrofavorito> librosFavoritos;
}
