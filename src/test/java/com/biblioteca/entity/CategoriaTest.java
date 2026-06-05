package com.biblioteca.entity;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class CategoriaTest {

    @Test
    void deveCriarCategoriaComGettersESetters() {

        Categoria categoria = new Categoria();

        categoria.setIdCategoria(1L);
        categoria.setNomeCategoria("Programação");
        categoria.setLivros(new ArrayList<>());

        assertEquals(1L, categoria.getIdCategoria());
        assertEquals("Programação", categoria.getNomeCategoria());
        assertNotNull(categoria.getLivros());
    }
}