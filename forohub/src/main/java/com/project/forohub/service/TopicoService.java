package com.project.forohub.service;

import com.project.forohub.dto.TopicoRequestDTO;
import com.project.forohub.dto.TopicoResponseDTO;
import com.project.forohub.entity.Topico;
import com.project.forohub.exception.DuplicateResourceException;
import com.project.forohub.exception.ResourceNotFoundException;
import com.project.forohub.repository.TopicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository repo;

    public TopicoResponseDTO crearTopico(TopicoRequestDTO dto) {

        repo.findByTituloAndMensaje(dto.getTitulo(), dto.getMensaje())
                .ifPresent(t -> {
                    throw new DuplicateResourceException("Tópico duplicado (título y mensaje).");
                });

        Topico topico = Topico.builder()
                .titulo(dto.getTitulo())
                .mensaje(dto.getMensaje())
                .autor(dto.getAutor())
                .curso(dto.getCurso())
                .build();

        return mapToDTO(repo.save(topico));
    }

    public List<TopicoResponseDTO> listarTopicos() {
        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList()); // ← evita error de compilación
    }

    public Page<TopicoResponseDTO> listarTopicosPaginado(Pageable pageable) {
        return repo.findAll(pageable).map(this::mapToDTO);
    }

    public TopicoResponseDTO detalle(Long id) {
        Topico t = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tópico no encontrado: " + id));
        return mapToDTO(t);
    }

    public TopicoResponseDTO actualizar(Long id, TopicoRequestDTO dto) {

        Topico existente = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tópico no encontrado: " + id));

        repo.findByTituloAndMensaje(dto.getTitulo(), dto.getMensaje())
                .ifPresent(t -> {
                    if (!t.getId().equals(id)) {
                        throw new DuplicateResourceException(
                                "Otro tópico con el mismo título y mensaje ya existe.");
                    }
                });

        existente.setTitulo(dto.getTitulo());
        existente.setMensaje(dto.getMensaje());
        existente.setAutor(dto.getAutor());
        existente.setCurso(dto.getCurso());

        return mapToDTO(repo.save(existente));
    }

    public void eliminar(Long id) {
        Topico existente = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tópico no encontrado: " + id));
        repo.delete(existente);
    }

    private TopicoResponseDTO mapToDTO(Topico t) {
        return TopicoResponseDTO.builder()
                .id(t.getId())
                .titulo(t.getTitulo())
                .mensaje(t.getMensaje())
                .fechaCreacion(t.getFechaCreacion())
                .status(String.valueOf(t.getStatus()))
                .autor(t.getAutor())
                .curso(t.getCurso())
                .build();
    }
}
