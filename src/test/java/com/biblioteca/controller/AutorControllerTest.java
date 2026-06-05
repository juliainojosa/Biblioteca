package com.biblioteca.controller;

import com.biblioteca.entity.Autor;
import com.biblioteca.service.AutorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutorController.class)
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorService autorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /autores")
    void listarAutores() throws Exception {

        Autor autor = new Autor();
        autor.setIdAutor(1L);
        autor.setNomeAutor("Joshua Bloch");

        when(autorService.listarAutores())
                .thenReturn(List.of(autor));

        mockMvc.perform(get("/autores"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /autores/{id}")
    void buscarPorId() throws Exception {

        Autor autor = new Autor();

        autor.setIdAutor(1L);
        autor.setNomeAutor("Joshua Bloch");

        when(autorService.buscarPorId(1L))
                .thenReturn(autor);

        mockMvc.perform(get("/autores/1"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.nomeAutor")
                                .value("Joshua Bloch"));
    }

    @Test
    @DisplayName("POST /autores")
    void cadastrarAutor() throws Exception {

        Autor autor = new Autor();

        autor.setIdAutor(1L);
        autor.setNomeAutor("Joshua Bloch");

        when(autorService.cadastrarAutor(
                org.mockito.ArgumentMatchers.any()))
                .thenReturn(autor);

        mockMvc.perform(post("/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(autor)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /autores/{id}")
    void atualizarAutor() throws Exception {

        Autor autor = new Autor();

        autor.setIdAutor(1L);
        autor.setNomeAutor("Martin Fowler");

        when(autorService.atualizarAutor(
                org.mockito.ArgumentMatchers.eq(1L),
                org.mockito.ArgumentMatchers.any()))
                .thenReturn(autor);

        mockMvc.perform(put("/autores/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(autor)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /autores/{id}")
    void deletarAutor() throws Exception {

        doNothing().when(autorService)
                .deletarAutor(1L);

        mockMvc.perform(delete("/autores/1"))
                .andExpect(status().isNoContent());
    }
}