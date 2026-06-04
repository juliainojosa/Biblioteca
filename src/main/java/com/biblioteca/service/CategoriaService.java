package com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Categoria;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // LISTAR TODAS
    public Iterable<Categoria> listarCategorias() {

        return categoriaRepository.findAll();
    }

    // BUSCAR POR ID
    public Categoria buscarPorId(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Categoria não encontrada"));
    }

    // CADASTRAR
    public Categoria cadastrarCategoria(
            Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    // ATUALIZAR
    public Categoria atualizarCategoria(
            Long id,
            Categoria categoriaAtualizada) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Categoria não encontrada"));

        categoria.setNomeCategoria(
                categoriaAtualizada.getNomeCategoria());

        return categoriaRepository.save(categoria);
    }

    // DELETAR
    public void deletarCategoria(Long id) {

        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Categoria não encontrada");
        }

        categoriaRepository.deleteById(id);
    }
}