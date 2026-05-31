package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.CategoriaRequestDTO;
import com.biblioteca.dto.CategoriaResponseDTO;
import com.biblioteca.entity.Categoria;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // LISTAR TODAS
    public List<CategoriaResponseDTO> listarCategorias() {

        return categoriaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // BUSCAR POR ID
    public CategoriaResponseDTO buscarPorId(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada"));

        return toResponse(categoria);
    }

    // CADASTRAR
    public CategoriaResponseDTO cadastrarCategoria(CategoriaRequestDTO dto) {

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(dto.nomeCategoria());

        Categoria salvo = categoriaRepository.save(categoria);

        return toResponse(salvo);
    }

    // ATUALIZAR
    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO dto) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoria não encontrada"));

        categoria.setNomeCategoria(dto.nomeCategoria());

        Categoria atualizado = categoriaRepository.save(categoria);

        return toResponse(atualizado);
    }

    // DELETAR
    public void deletarCategoria(Long id) {

        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada");
        }

        categoriaRepository.deleteById(id);
    }

    // CONVERSÃO
    private CategoriaResponseDTO toResponse(Categoria categoria) {

        return new CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNomeCategoria()
        );
    }
}