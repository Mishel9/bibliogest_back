package com.example.bibliogest.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class usuariolibrofavoritoid implements Serializable {

    private Long usuarioId;
    private Long libroId;

    // equals() y hashCode() son necesarios
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof usuariolibrofavoritoid that)) return false;
        return Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(libroId, that.libroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, libroId);
    }
}
