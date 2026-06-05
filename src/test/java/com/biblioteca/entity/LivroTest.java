package com.biblioteca.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes unitários - Entidade Livro")
class LivroTest {

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

    @Test
    @DisplayName("Deve instanciar Livro vazio sem erros")
    void livro_deveInstanciarVazio() {
        Livro livroVazio = new Livro();

        assertNull(livroVazio.getIdLivro());
        assertNull(livroVazio.getTitulo());
        assertNull(livroVazio.getIsbn());
        assertNull(livroVazio.getAutor());
        assertNull(livroVazio.getCategoria());
    }

    @Test
    @DisplayName("Deve preencher e retornar todos os campos corretamente")
    void livro_deveTerTodosOsCamposPreenchidos() {
        assertAll("Todos os campos do livro",
                () -> assertEquals(1L, livro.getIdLivro()),
                () -> assertEquals("Effective Java", livro.getTitulo()),
                () -> assertEquals("978-0134685991", livro.getIsbn()),
                () -> assertNotNull(livro.getAutor()),
                () -> assertNotNull(livro.getCategoria())
        );
    }

    @Test
    @DisplayName("setIdLivro / getIdLivro - deve atualizar corretamente")
    void setGetIdLivro_deveAtualizarCorretamente() {
        livro.setIdLivro(99L);
        assertEquals(99L, livro.getIdLivro());
    }

    @Test
    @DisplayName("setTitulo / getTitulo - deve atualizar corretamente")
    void setGetTitulo_deveAtualizarCorretamente() {
        livro.setTitulo("Clean Code");
        assertEquals("Clean Code", livro.getTitulo());
    }

    @Test
    @DisplayName("setIsbn / getIsbn - deve atualizar corretamente")
    void setGetIsbn_deveAtualizarCorretamente() {
        livro.setIsbn("978-0132350884");
        assertEquals("978-0132350884", livro.getIsbn());
    }

    @Test
    @DisplayName("setAutor / getAutor - deve atualizar corretamente")
    void setGetAutor_deveAtualizarCorretamente() {
        Autor novoAutor = new Autor();
        novoAutor.setIdAutor(2L);
        novoAutor.setNomeAutor("Robert C. Martin");

        livro.setAutor(novoAutor);

        assertEquals(2L, livro.getAutor().getIdAutor());
        assertEquals("Robert C. Martin", livro.getAutor().getNomeAutor());
    }

    @Test
    @DisplayName("setCategoria / getCategoria - deve atualizar corretamente")
    void setGetCategoria_deveAtualizarCorretamente() {
        Categoria novaCategoria = new Categoria();
        novaCategoria.setIdCategoria(2L);
        novaCategoria.setNomeCategoria("Arquitetura");

        livro.setCategoria(novaCategoria);

        assertEquals(2L, livro.getCategoria().getIdCategoria());
        assertEquals("Arquitetura", livro.getCategoria().getNomeCategoria());
    }

    @Test
    @DisplayName("setTitulo - deve aceitar valor nulo")
    void setTitulo_deveAceitarNull() {
        livro.setTitulo(null);
        assertNull(livro.getTitulo());
    }

    @Test
    @DisplayName("setIsbn - deve aceitar valor nulo")
    void setIsbn_deveAceitarNull() {
        livro.setIsbn(null);
        assertNull(livro.getIsbn());
    }

    @Test
    @DisplayName("setAutor - deve aceitar valor nulo")
    void setAutor_deveAceitarNull() {
        livro.setAutor(null);
        assertNull(livro.getAutor());
    }

    @Test
    @DisplayName("setCategoria - deve aceitar valor nulo")
    void setCategoria_deveAceitarNull() {
        livro.setCategoria(null);
        assertNull(livro.getCategoria());
    }
}