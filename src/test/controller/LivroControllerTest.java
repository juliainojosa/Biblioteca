package com.biblioteca.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import com.biblioteca.entity.Livro;
import com.biblioteca.service.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;

@WebMvcTest(LivroController.class)
public class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveListarLivros() throws Exception {

        Livro livro = new Livro();

        livro.setIdLivro(1L);
        livro.setTitulo("Dom Casmurro");

        when(livroService.listarLivros())
                .thenReturn(Arrays.asList(livro));

        mockMvc.perform(get("/livros"))
                .andExpect(status().isOk());
    }

    @Test
    void deveCadastrarLivro() throws Exception {

        Livro livro = new Livro();

        livro.setIdLivro(1L);
        livro.setTitulo("Dom Casmurro");

        when(livroService.cadastrarLivro(livro))
                .thenReturn(livro);

        mockMvc.perform(post("/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livro)))
                .andExpect(status().isOk());
    }
}