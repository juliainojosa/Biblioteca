package com.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Categoria;
import com.biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // LISTAR TODAS AS CATEGORIAS
    public Iterable<Categoria> listarCategorias() {

        return categoriaRepository.findAll();
    }

    // BUSCAR CATEGORIA POR ID
    public Optional<Categoria> buscarPorId(Long id) {

        return categoriaRepository.findById(id);
    }

    // CADASTRAR CATEGORIA
    public Categoria cadastrarCategoria(Categoria categoria) {

        return categoriaRepository.save(categoria);
    }

    // ATUALIZAR CATEGORIA
    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {

        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente.isPresent()) {

            Categoria categoria = categoriaExistente.get();

            categoria.setNomeCategoria(categoriaAtualizada.getNomeCategoria());

            return categoriaRepository.save(categoria);
        }

        return null;
    }

    // DELETAR CATEGORIA
    public void deletarCategoria(Long id) {

        categoriaRepository.deleteById(id);
    }
}