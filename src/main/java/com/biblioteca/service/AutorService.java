package com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Autor;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // LISTAR TODOS
    public Iterable<Autor> listarAutores() {

        return autorRepository.findAll();
    }

    // BUSCAR POR ID
    public Autor buscarPorId(Long id) {

        return autorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Autor não encontrado"));
    }

    // CADASTRAR
    public Autor cadastrarAutor(Autor autor) {

        return autorRepository.save(autor);
    }

    // ATUALIZAR
    public Autor atualizarAutor(
            Long id,
            Autor autorAtualizado) {

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Autor não encontrado"));

        autor.setNomeAutor(
                autorAtualizado.getNomeAutor());

        autor.setNacionalidade(
                autorAtualizado.getNacionalidade());

        return autorRepository.save(autor);
    }

    // DELETAR
    public void deletarAutor(Long id) {

        if (!autorRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Autor não encontrado");
        }

        autorRepository.deleteById(id);
    }
}