package com.biblioteca.repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biblioteca.entity.Categoria;

@DataJpaTest
@DisplayName("Testes de integração - CategoriaRepository")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    private Categoria categoria;

    @BeforeEach
    void setUp() {

        categoria = new Categoria();
        categoria.setNomeCategoria("Programação");

        categoriaRepository.save(categoria);
    }

    @Test
    void findByNomeCategoria() {

        List<Categoria> resultado =
                categoriaRepository.findByNomeCategoria("Programação");

        assertFalse(resultado.isEmpty());
        assertEquals("Programação",
                resultado.get(0).getNomeCategoria());
    }

    @Test
    void findByNomeCategoriaNaoEncontrada() {

        List<Categoria> resultado =
                categoriaRepository.findByNomeCategoria("XYZ");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void countCategorias() {

        assertEquals(1,
                categoriaRepository.count());
    }

    @Test
    void existsById() {

        assertTrue(
                categoriaRepository.existsById(
                        categoria.getIdCategoria()));
    }
}