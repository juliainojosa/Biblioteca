package com.biblioteca.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Livro;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes unitários - LivroService")
class LivroServiceTest {

    @Mock
    private LivroRepository livroRepository;

    @InjectMocks
    private LivroService livroService;

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
    // listarLivros()
    // =========================================================

    @Test
    @DisplayName("listarLivros - deve retornar lista com todos os livros")
    void listarLivros_deveRetornarListaComTodosOsLivros() {
        Livro livro2 = new Livro();
        livro2.setIdLivro(2L);
        livro2.setTitulo("Clean Code");
        livro2.setIsbn("978-0132350884");
        livro2.setAutor(autor);
        livro2.setCategoria(categoria);

        when(livroRepository.findAll()).thenReturn(Arrays.asList(livro, livro2));

        List<Livro> resultado = livroService.listarLivros();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Effective Java", resultado.get(0).getTitulo());
        assertEquals("Clean Code", resultado.get(1).getTitulo());
        verify(livroRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("listarLivros - deve retornar lista vazia quando não há livros")
    void listarLivros_deveRetornarListaVaziaQuandoNaoHaLivros() {
        when(livroRepository.findAll()).thenReturn(Collections.emptyList());

        List<Livro> resultado = livroService.listarLivros();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(livroRepository, times(1)).findAll();
    }

    // =========================================================
    // buscarPorId()
    // =========================================================

    @Test
    @DisplayName("buscarPorId - deve retornar livro quando ID existe")
    void buscarPorId_deveRetornarLivroQuandoIdExiste() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        Livro resultado = livroService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdLivro());
        assertEquals("Effective Java", resultado.getTitulo());
        assertEquals("978-0134685991", resultado.getIsbn());
        verify(livroRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("buscarPorId - deve lançar ResourceNotFoundException quando ID não existe")
    void buscarPorId_deveLancarExcecaoQuandoIdNaoExiste() {
        when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> livroService.buscarPorId(99L)
        );

        assertEquals("Livro não encontrado", ex.getMessage());
        verify(livroRepository, times(1)).findById(99L);
    }

    // =========================================================
    // cadastrarLivro()
    // =========================================================

    @Test
    @DisplayName("cadastrarLivro - deve salvar e retornar o livro")
    void cadastrarLivro_deveSalvarERetornarLivro() {
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro resultado = livroService.cadastrarLivro(livro);

        assertNotNull(resultado);
        assertEquals("Effective Java", resultado.getTitulo());
        assertEquals("978-0134685991", resultado.getIsbn());
        verify(livroRepository, times(1)).save(livro);
    }

    @Test
    @DisplayName("cadastrarLivro - deve chamar save exatamente uma vez")
    void cadastrarLivro_deveChamarSaveUmaVez() {
        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        livroService.cadastrarLivro(livro);

        verify(livroRepository, times(1)).save(any(Livro.class));
        verifyNoMoreInteractions(livroRepository);
    }

    // =========================================================
    // buscarPorTitulo()
    // =========================================================

    @Test
    @DisplayName("buscarPorTitulo - deve retornar livros que contenham o termo")
    void buscarPorTitulo_deveRetornarLivrosContendo() {
        when(livroRepository.findByTituloContaining("Java"))
                .thenReturn(Arrays.asList(livro));

        List<Livro> resultado = livroService.buscarPorTitulo("Java");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).getTitulo().contains("Java"));
        verify(livroRepository, times(1)).findByTituloContaining("Java");
    }

    @Test
    @DisplayName("buscarPorTitulo - deve retornar lista vazia quando não encontrado")
    void buscarPorTitulo_deveRetornarListaVaziaQuandoNaoEncontrado() {
        when(livroRepository.findByTituloContaining("XYZ"))
                .thenReturn(Collections.emptyList());

        List<Livro> resultado = livroService.buscarPorTitulo("XYZ");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(livroRepository, times(1)).findByTituloContaining("XYZ");
    }

    // =========================================================
    // atualizarLivro()
    // =========================================================

    @Test
    @DisplayName("atualizarLivro - deve atualizar e retornar livro com novos dados")
    void atualizarLivro_deveAtualizarERetornarLivroAtualizado() {
        Livro dadosNovos = new Livro();
        dadosNovos.setTitulo("Effective Java 3rd Edition");
        dadosNovos.setIsbn("978-0134685991-3");
        dadosNovos.setAutor(autor);
        dadosNovos.setCategoria(categoria);

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(livroRepository.save(any(Livro.class))).thenAnswer(inv -> inv.getArgument(0));

        Livro resultado = livroService.atualizarLivro(1L, dadosNovos);

        assertNotNull(resultado);
        assertEquals("Effective Java 3rd Edition", resultado.getTitulo());
        assertEquals("978-0134685991-3", resultado.getIsbn());
        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, times(1)).save(any(Livro.class));
    }

    @Test
    @DisplayName("atualizarLivro - deve atualizar todos os campos corretamente")
    void atualizarLivro_deveAtualizarTodosOsCampos() {
        Autor novoAutor = new Autor();
        novoAutor.setIdAutor(2L);
        novoAutor.setNomeAutor("Robert C. Martin");

        Categoria novaCategoria = new Categoria();
        novaCategoria.setIdCategoria(2L);
        novaCategoria.setNomeCategoria("Arquitetura");

        Livro dadosNovos = new Livro();
        dadosNovos.setTitulo("Clean Code");
        dadosNovos.setIsbn("978-0132350884");
        dadosNovos.setAutor(novoAutor);
        dadosNovos.setCategoria(novaCategoria);

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        when(livroRepository.save(any(Livro.class))).thenAnswer(inv -> inv.getArgument(0));

        Livro resultado = livroService.atualizarLivro(1L, dadosNovos);

        assertEquals("Clean Code", resultado.getTitulo());
        assertEquals("978-0132350884", resultado.getIsbn());
        assertEquals("Robert C. Martin", resultado.getAutor().getNomeAutor());
        assertEquals("Arquitetura", resultado.getCategoria().getNomeCategoria());
    }

    @Test
    @DisplayName("atualizarLivro - deve lançar exceção quando ID não existe")
    void atualizarLivro_deveLancarExcecaoQuandoIdNaoExiste() {
        when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> livroService.atualizarLivro(99L, livro)
        );

        verify(livroRepository, times(1)).findById(99L);
        verify(livroRepository, never()).save(any(Livro.class));
    }

    // =========================================================
    // deletarLivro()
    // =========================================================

    @Test
    @DisplayName("deletarLivro - deve deletar o livro quando ID existe")
    void deletarLivro_deveDeletarQuandoIdExiste() {
        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));
        doNothing().when(livroRepository).delete(livro);

        assertDoesNotThrow(() -> livroService.deletarLivro(1L));

        verify(livroRepository, times(1)).findById(1L);
        verify(livroRepository, times(1)).delete(livro);
    }

    @Test
    @DisplayName("deletarLivro - deve lançar exceção quando ID não existe")
    void deletarLivro_deveLancarExcecaoQuandoIdNaoExiste() {
        when(livroRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> livroService.deletarLivro(99L)
        );

        verify(livroRepository, times(1)).findById(99L);
        verify(livroRepository, never()).delete(any(Livro.class));
    }
}