package com.biblioteca.controller;

import com.biblioteca.entity.Usuario;
import com.biblioteca.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /usuarios")
    void listarUsuarios() throws Exception {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(1L);
        usuario.setNomeUsuario("Maria");

        when(usuarioService.listarUsuarios())
                .thenReturn(List.of(usuario));

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /usuarios/{id}")
    void buscarPorId() throws Exception {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(1L);
        usuario.setNomeUsuario("Maria");

        when(usuarioService.buscarPorId(1L))
                .thenReturn(usuario);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.nomeUsuario")
                                .value("Maria"));
    }

    @Test
    @DisplayName("POST /usuarios")
    void cadastrarUsuario() throws Exception {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(1L);
        usuario.setNomeUsuario("Maria");

        when(usuarioService.cadastrarUsuario(any()))
                .thenReturn(usuario);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /usuarios/{id}")
    void atualizarUsuario() throws Exception {

        Usuario usuario = new Usuario();

        usuario.setIdUsuario(1L);
        usuario.setNomeUsuario("Maria Silva");

        when(usuarioService.atualizarUsuario(
                eq(1L),
                any()))
                .thenReturn(usuario);

        mockMvc.perform(put("/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /usuarios/{id}")
    void deletarUsuario() throws Exception {

        doNothing().when(usuarioService)
                .deletarUsuario(1L);

        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}