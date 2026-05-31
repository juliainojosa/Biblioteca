package com.biblioteca.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.AutorRequestDTO;
import com.biblioteca.dto.AutorResponseDTO;
import com.biblioteca.entity.Autor;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // LISTAR TODOS
    public List<AutorResponseDTO> listarAutores() {

        return autorRepository.findAll().stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    // BUSCAR POR ID
    public AutorResponseDTO buscarPorId(Long id) {

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Autor não encontrado"));

        return converterParaResponseDTO(autor);
    }

    // CADASTRAR
    public AutorResponseDTO cadastrarAutor(AutorRequestDTO dto) {

        Autor autor = new Autor();

        autor.setNomeAutor(dto.nomeAutor());
        autor.setNacionalidade(dto.nacionalidade());

        Autor salvo = autorRepository.save(autor);

        return converterParaResponseDTO(salvo);
    }

    // ATUALIZAR
    public AutorResponseDTO atualizarAutor(Long id, AutorRequestDTO dto) {

        Autor autor = autorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Autor não encontrado"));

        autor.setNomeAutor(dto.nomeAutor());
        autor.setNacionalidade(dto.nacionalidade());

        Autor atualizado = autorRepository.save(autor);

        return converterParaResponseDTO(atualizado);
    }

    // DELETAR
    public void deletarAutor(Long id) {

        if (!autorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Autor não encontrado");
        }

        autorRepository.deleteById(id);
    }

    private AutorResponseDTO converterParaResponseDTO(Autor autor) {

        return new AutorResponseDTO(
                autor.getIdAutor(),
                autor.getNomeAutor(),
                autor.getNacionalidade()
        );
    }
}