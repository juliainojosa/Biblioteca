package com.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.biblioteca.entity.Livro;
import com.biblioteca.repository.LivroRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        Optional<Livro> resultado = livroService.buscarPorId(1L);

        assertEquals("Dom Casmurro", resultado.get().getTitulo());
    }

    @Test
    void deveListarLivros() {

        Livro livro1 = new Livro();
        livro1.setTitulo("Livro 1");

        Livro livro2 = new Livro();
        livro2.setTitulo("Livro 2");

        List<Livro> lista = Arrays.asList(livro1, livro2);

        when(livroRepository.findAll()).thenReturn(lista);

        List<Livro> resultado = livroService.listarLivros();

        assertEquals(2, resultado.size());
    }

    @Test
    void deveCadastrarLivro() {

        Livro livro = new Livro();
        livro.setTitulo("Novo Livro");

        when(livroRepository.save(livro)).thenReturn(livro);

        Livro resultado = livroService.cadastrarLivro(livro);

        assertEquals("Novo Livro", resultado.getTitulo());
    }
}