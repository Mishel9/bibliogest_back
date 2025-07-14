package com.example.bibliogest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class detalleusuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private int edad;
    private String email;

    private String telefono;
    private String direccion;

    @OneToOne(mappedBy = "detalle", cascade = CascadeType.ALL)
    private usuario usuario;
}
