package com.project.forohub.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @Column(nullable = false)
    private Instant fechaCreacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTopico status;

    private String autor;
    private String curso;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = Instant.now();
        this.status = StatusTopico.ACTIVO;
    }
}
