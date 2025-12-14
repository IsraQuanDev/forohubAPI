package com.project.forohub.controller;

import com.project.forohub.dto.TopicoRequestDTO;
import com.project.forohub.dto.TopicoResponseDTO;
import com.project.forohub.service.TopicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
@Validated
public class TopicoController {

    private final TopicoService service;

    @PostMapping
    public ResponseEntity<TopicoResponseDTO> crear(
            @Valid @RequestBody TopicoRequestDTO dto) {

        TopicoResponseDTO created = service.crearTopico(dto);
        return ResponseEntity
                .created(URI.create("/topicos/" + created.getId()))
                .body(created);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponseDTO>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(
                page, size, Sort.by("fechaCreacion").ascending());

        return ResponseEntity.ok(service.listarTopicosPaginado(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> detalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.detalle(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TopicoRequestDTO dto) {

        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
