package com.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.entity.Livro;
import com.biblioteca.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

    @Test
    void deveBuscarLivroPorId() {

        Livro livro = new Livro();

        livro.setIdLivro(1L);
        livro.setTitulo("Dom Casmurro");

        when(livroRepository.findById(1L))
                .thenReturn(Optional.of(livro));

        Livro resultado = livroService.buscarPorId(1L);

        assertEquals("Dom Casmurro", resultado.getTitulo());
    }
}