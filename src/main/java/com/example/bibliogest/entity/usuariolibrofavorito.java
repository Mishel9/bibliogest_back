package com.example.bibliogest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario_libro_favorito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class usuariolibrofavorito {

    @EmbeddedId
    private usuariolibrofavoritoid id;

    @ManyToOne
    @MapsId("usuarioId") // <-- nombre del campo en usuariolibrofavoritoid
    @JoinColumn(name = "usuario_id")
    private usuario usuario;

    @ManyToOne
    @MapsId("libroId") // <-- nombre del campo en usuariolibrofavoritoid
    @JoinColumn(name = "libro_id")
    private libro libro;
}
