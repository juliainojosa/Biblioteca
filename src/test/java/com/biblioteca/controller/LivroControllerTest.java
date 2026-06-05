package com.biblioteca.controller;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Livro;
import com.biblioteca.exception.GlobalExceptionHandler;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.service.LivroService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LivroController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("Testes unitários - LivroController")
class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Spring Boot 3.4+ substitui @MockBean por @MockitoBean
    @MockitoBean
    private LivroService livroService;

    @Autowired
    private ObjectMapper objectMapper;

    private Livro livro;
    private Autor autor;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        autor = new Autor();
        autor.setIdAutor(1L);
        autor.setNomeAutor("Joshua Bloch");
        autor.setNacionalidade("Americano");

        categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNomeCategoria("Programação");

        livro = new Livro();
        livro.setIdLivro(1L);
        livro.setTitulo("Effective Java");
        livro.setIsbn("978-0134685991");
        livro.setAutor(autor);
        livro.setCategoria(categoria);
    }

    // =========================================================
    // GET /livros
    // =========================================================

    @Test
    @DisplayName("GET /livros - deve retornar lista de livros com status 200")
    void listarLivros_deveRetornarListaComStatus200() throws Exception {
        Livro livro2 = new Livro();
        livro2.setIdLivro(2L);
        livro2.setTitulo("Clean Code");
        livro2.setIsbn("978-0132350884");
        livro2.setAutor(autor);
        livro2.setCategoria(categoria);

        when(livroService.listarLivros()).thenReturn(Arrays.asList(livro, livro2));

        mockMvc.perform(get("/livros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].titulo", is("Effective Java")))
                .andExpect(jsonPath("$[1].titulo", is("Clean Code")));

        verify(livroService, times(1)).listarLivros();
    }

    @Test
    @DisplayName("GET /livros - deve retornar lista vazia com status 200")
    void listarLivros_deveRetornarListaVaziaComStatus200() throws Exception {
        when(livroService.listarLivros()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/livros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // =========================================================
    // GET /livros/{id}
    // =========================================================

    @Test
    @DisplayName("GET /livros/{id} - deve retornar livro com status 200")
    void buscarPorId_deveRetornarLivroComStatus200() throws Exception {
        when(livroService.buscarPorId(1L)).thenReturn(livro);

        mockMvc.perform(get("/livros/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idLivro", is(1)))
                .andExpect(jsonPath("$.titulo", is("Effective Java")))
                .andExpect(jsonPath("$.isbn", is("978-0134685991")));

        verify(livroService, times(1)).buscarPorId(1L);
    }

    @Test
    @DisplayName("GET /livros/{id} - deve retornar 404 com mensagem quando não encontrado")
    void buscarPorId_deveRetornar404ComMensagem() throws Exception {
        when(livroService.buscarPorId(anyLong()))
                .thenThrow(new ResourceNotFoundException("Livro não encontrado"));

        mockMvc.perform(get("/livros/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem", is("Livro não encontrado")));

        verify(livroService, times(1)).buscarPorId(99L);
    }

    // =========================================================
    // POST /livros
    // =========================================================

    @Test
    @DisplayName("POST /livros - deve cadastrar livro e retornar status 201")
    void cadastrarLivro_deveSalvarERetornarStatus201() throws Exception {
        when(livroService.cadastrarLivro(any(Livro.class))).thenReturn(livro);

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livro)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idLivro", is(1)))
                .andExpect(jsonPath("$.titulo", is("Effective Java")))
                .andExpect(jsonPath("$.isbn", is("978-0134685991")));

        verify(livroService, times(1)).cadastrarLivro(any(Livro.class));
    }

    // =========================================================
    // GET /livros/buscar?titulo=
    // =========================================================

    @Test
    @DisplayName("GET /livros/buscar - deve retornar livros encontrados")
    void buscarPorTitulo_deveRetornarLivrosComStatus200() throws Exception {
        when(livroService.buscarPorTitulo("Java")).thenReturn(Arrays.asList(livro));

        mockMvc.perform(get("/livros/buscar")
                        .param("titulo", "Java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].titulo", is("Effective Java")));

        verify(livroService, times(1)).buscarPorTitulo("Java");
    }

    @Test
    @DisplayName("GET /livros/buscar - deve retornar lista vazia quando não encontrado")
    void buscarPorTitulo_deveRetornarListaVaziaQuandoNaoEncontrado() throws Exception {
        when(livroService.buscarPorTitulo("XYZ")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/livros/buscar")
                        .param("titulo", "XYZ")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // =========================================================
    // PUT /livros/{id}
    // =========================================================

    @Test
    @DisplayName("PUT /livros/{id} - deve atualizar livro e retornar status 200")
    void atualizarLivro_deveAtualizarERetornarStatus200() throws Exception {
        Livro livroAtualizado = new Livro();
        livroAtualizado.setIdLivro(1L);
        livroAtualizado.setTitulo("Effective Java 3rd Edition");
        livroAtualizado.setIsbn("978-0134685991-3");
        livroAtualizado.setAutor(autor);
        livroAtualizado.setCategoria(categoria);

        when(livroService.atualizarLivro(eq(1L), any(Livro.class))).thenReturn(livroAtualizado);

        mockMvc.perform(put("/livros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livroAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo", is("Effective Java 3rd Edition")))
                .andExpect(jsonPath("$.isbn", is("978-0134685991-3")));

        verify(livroService, times(1)).atualizarLivro(eq(1L), any(Livro.class));
    }

    @Test
    @DisplayName("PUT /livros/{id} - deve retornar 404 com mensagem quando não encontrado")
    void atualizarLivro_deveRetornar404ComMensagem() throws Exception {
        when(livroService.atualizarLivro(anyLong(), any(Livro.class)))
                .thenThrow(new ResourceNotFoundException("Livro não encontrado"));

        mockMvc.perform(put("/livros/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livro)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem", is("Livro não encontrado")));
    }

    // =========================================================
    // DELETE /livros/{id}
    // =========================================================

    @Test
    @DisplayName("DELETE /livros/{id} - deve deletar e retornar status 204")
    void deletarLivro_deveDeletarERetornarStatus204() throws Exception {
        doNothing().when(livroService).deletarLivro(1L);

        mockMvc.perform(delete("/livros/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(livroService, times(1)).deletarLivro(1L);
    }

    @Test
    @DisplayName("DELETE /livros/{id} - deve retornar 404 com mensagem quando não encontrado")
    void deletarLivro_deveRetornar404ComMensagem() throws Exception {
        doThrow(new ResourceNotFoundException("Livro não encontrado"))
                .when(livroService).deletarLivro(anyLong());

        mockMvc.perform(delete("/livros/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensagem", is("Livro não encontrado")));

        verify(livroService, times(1)).deletarLivro(99L);
    }
}