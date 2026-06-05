package com.biblioteca.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.biblioteca.entity.Categoria;
import com.biblioteca.service.CategoriaService;

class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private Categoria categoria;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNomeCategoria("Programação");
    }

    @Test
    void listarCategorias() {

        when(categoriaService.listarCategorias())
                .thenReturn(List.of(categoria));

        Iterable<Categoria> resultado =
                categoriaController.listarCategorias();

        assertNotNull(resultado);
    }

    @Test
    void buscarPorId() {

        when(categoriaService.buscarPorId(1L))
                .thenReturn(categoria);

        ResponseEntity<Categoria> resposta =
                categoriaController.buscarPorId(1L);

        assertEquals(200,
                resposta.getStatusCode().value());
    }

    @Test
    void cadastrarCategoria() {

        when(categoriaService.cadastrarCategoria(categoria))
                .thenReturn(categoria);

        ResponseEntity<Categoria> resposta =
                categoriaController.cadastrarCategoria(categoria);

        assertEquals(201,
                resposta.getStatusCode().value());
    }

    @Test
    void atualizarCategoria() {

        when(categoriaService.atualizarCategoria(1L, categoria))
                .thenReturn(categoria);

        ResponseEntity<Categoria> resposta =
                categoriaController.atualizarCategoria(1L, categoria);

        assertEquals(200,
                resposta.getStatusCode().value());
    }

    @Test
    void deletarCategoria() {

        ResponseEntity<Void> resposta =
                categoriaController.deletarCategoria(1L);

        verify(categoriaService)
                .deletarCategoria(1L);

        assertEquals(204,
                resposta.getStatusCode().value());
    }
}