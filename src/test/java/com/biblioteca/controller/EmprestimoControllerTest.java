package com.biblioteca.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.service.EmprestimoService;

@WebMvcTest(EmprestimoController.class)
class EmprestimoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmprestimoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listarEmprestimos() throws Exception {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);

        when(service.listarEmprestimos())
                .thenReturn(List.of(emprestimo));

        mockMvc.perform(get("/emprestimos"))
                .andExpect(status().isOk());
    }

    @Test
    void buscarPorId() throws Exception {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);

        when(service.buscarPorId(1L))
                .thenReturn(emprestimo);

        mockMvc.perform(get("/emprestimos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void cadastrarEmprestimo() throws Exception {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);
        emprestimo.setDataEmprestimo(LocalDate.now());

        when(service.cadastrarEmprestimo(org.mockito.ArgumentMatchers.any()))
                .thenReturn(emprestimo);

        mockMvc.perform(post("/emprestimos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(emprestimo)))
                .andExpect(status().isCreated());
    }

    @Test
    void atualizarEmprestimo() throws Exception {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);

        when(service.atualizarEmprestimo(
                org.mockito.ArgumentMatchers.eq(1L),
                org.mockito.ArgumentMatchers.any()))
                .thenReturn(emprestimo);

        mockMvc.perform(put("/emprestimos/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(emprestimo)))
                .andExpect(status().isOk());
    }

    @Test
    void deletarEmprestimo() throws Exception {

        mockMvc.perform(delete("/emprestimos/1"))
                .andExpect(status().isNoContent());
    }
}