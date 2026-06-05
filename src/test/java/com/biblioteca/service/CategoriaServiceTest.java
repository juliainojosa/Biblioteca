package com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.entity.Categoria;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {

        categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNomeCategoria("Programação");
    }

    @Test
    void listarCategorias() {

        when(categoriaRepository.findAll())
                .thenReturn(List.of(categoria));

        Iterable<Categoria> resultado =
                categoriaService.listarCategorias();

        assertNotNull(resultado);
    }

    @Test
    void buscarPorId() {

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(categoria));

        Categoria resultado =
                categoriaService.buscarPorId(1L);

        assertEquals("Programação",
                resultado.getNomeCategoria());
    }

    @Test
    void buscarPorIdNaoEncontrado() {

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> categoriaService.buscarPorId(1L));
    }

    @Test
    void cadastrarCategoria() {

        when(categoriaRepository.save(any(Categoria.class)))
                .thenReturn(categoria);

        Categoria resultado =
                categoriaService.cadastrarCategoria(categoria);

        assertNotNull(resultado);
    }

    @Test
    void atualizarCategoria() {

        Categoria atualizada = new Categoria();
        atualizada.setNomeCategoria("Banco de Dados");

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(categoria));

        when(categoriaRepository.save(any(Categoria.class)))
                .thenAnswer(i -> i.getArgument(0));

        Categoria resultado =
                categoriaService.atualizarCategoria(1L, atualizada);

        assertEquals("Banco de Dados",
                resultado.getNomeCategoria());
    }

    @Test
    void deletarCategoria() {

        when(categoriaRepository.existsById(1L))
                .thenReturn(true);

        categoriaService.deletarCategoria(1L);

        verify(categoriaRepository)
                .deleteById(1L);
    }

    @Test
    void deletarCategoriaNaoEncontrada() {

        when(categoriaRepository.existsById(1L))
                .thenReturn(false);

        assertThrows(
                ResourceNotFoundException.class,
                () -> categoriaService.deletarCategoria(1L));
    }
}