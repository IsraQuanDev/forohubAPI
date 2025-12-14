package com.project.forohub.dto;

import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicoResponseDTO {

    private Long id;
    private String titulo;
    private String mensaje;
    private Instant fechaCreacion;
    private String status;
    private String autor;
    private String curso;

}
