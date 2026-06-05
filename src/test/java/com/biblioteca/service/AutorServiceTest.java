package com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.biblioteca.entity.Autor;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.AutorRepository;

class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorService autorService;

    private Autor autor;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        autor = new Autor();
        autor.setIdAutor(1L);
        autor.setNomeAutor("Joshua Bloch");
        autor.setNacionalidade("Americano");
    }

    @Test
    @DisplayName("Listar autores")
    void listarAutores() {

        when(autorRepository.findAll())
                .thenReturn(List.of(autor));

        Iterable<Autor> resultado =
                autorService.listarAutores();

        assertNotNull(resultado);

        verify(autorRepository)
                .findAll();
    }

    @Test
    @DisplayName("Buscar autor por id existente")
    void buscarAutorExistente() {

        when(autorRepository.findById(1L))
                .thenReturn(Optional.of(autor));

        Autor resultado =
                autorService.buscarPorId(1L);

        assertEquals("Joshua Bloch",
                resultado.getNomeAutor());
    }

    @Test
    @DisplayName("Buscar autor inexistente")
    void buscarAutorInexistente() {

        when(autorRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> autorService.buscarPorId(1L));
    }

    @Test
    @DisplayName("Cadastrar autor")
    void cadastrarAutor() {

        when(autorRepository.save(autor))
                .thenReturn(autor);

        Autor resultado =
                autorService.cadastrarAutor(autor);

        assertNotNull(resultado);

        verify(autorRepository)
                .save(autor);
    }

    @Test
    @DisplayName("Atualizar autor")
    void atualizarAutor() {

        Autor atualizado = new Autor();
        atualizado.setNomeAutor("Martin Fowler");
        atualizado.setNacionalidade("Inglês");

        when(autorRepository.findById(1L))
                .thenReturn(Optional.of(autor));

        when(autorRepository.save(any()))
                .thenReturn(autor);

        Autor resultado =
                autorService.atualizarAutor(
                        1L,
                        atualizado);

        assertEquals(
                "Martin Fowler",
                resultado.getNomeAutor());
    }

    @Test
    @DisplayName("Deletar autor")
    void deletarAutor() {

        when(autorRepository.existsById(1L))
                .thenReturn(true);

        autorService.deletarAutor(1L);

        verify(autorRepository)
                .deleteById(1L);
    }

    @Test
    @DisplayName("Deletar autor inexistente")
    void deletarAutorInexistente() {

        when(autorRepository.existsById(1L))
                .thenReturn(false);

        assertThrows(
                ResourceNotFoundException.class,
                () -> autorService.deletarAutor(1L));
    }
}