package com.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Autor;
import com.biblioteca.repository.AutorRepository;

@Service // Indica que esta classe é uma camada de serviço
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // LISTAR TODOS OS AUTORES
    public Iterable<Autor> listarAutores() {

        return autorRepository.findAll();
    }

    // BUSCAR AUTOR POR ID
    public Optional<Autor> buscarPorId(Long id) {

        return autorRepository.findById(id);
    }

    // CADASTRAR AUTOR
    public Autor cadastrarAutor(Autor autor) {

        return autorRepository.save(autor);
    }

    // ATUALIZAR AUTOR
    public Autor atualizarAutor(Long id, Autor autorAtualizado) {

        Optional<Autor> autorExistente = autorRepository.findById(id);

        if (autorExistente.isPresent()) {

            Autor autor = autorExistente.get();

            // Atualiza os dados
            autor.setNomeAutor(autorAtualizado.getNomeAutor());

            autor.setNacionalidade(autorAtualizado.getNacionalidade());

            return autorRepository.save(autor);
        }

        return null;
    }

    // DELETAR AUTOR
    public void deletarAutor(Long id) {

        autorRepository.deleteById(id);
    }
}