package com.biblioteca.repository;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Testes de integração - LivroRepository")
class LivroRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LivroRepository livroRepository;

    private Livro livro;
    private Autor autor;
    private Categoria categoria;

    @BeforeEach
    void setUp() {
        autor = new Autor();
        autor.setNomeAutor("Joshua Bloch");
        autor.setNacionalidade("Americano");
        entityManager.persist(autor);

        categoria = new Categoria();
        categoria.setNomeCategoria("Programação");
        entityManager.persist(categoria);

        livro = new Livro();
        livro.setTitulo("Effective Java");
        livro.setIsbn("978-0134685991");
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        entityManager.persist(livro);

        entityManager.flush();
    }

    // =========================================================
    // findAll()
    // =========================================================

    @Test
    @DisplayName("findAll - deve retornar todos os livros persistidos")
    void findAll_deveRetornarTodosOsLivros() {
        Livro livro2 = new Livro();
        livro2.setTitulo("Clean Code");
        livro2.setIsbn("978-0132350884");
        livro2.setAutor(autor);
        livro2.setCategoria(categoria);
        entityManager.persistAndFlush(livro2);

        List<Livro> lista = new ArrayList<>();
        livroRepository.findAll().forEach(lista::add);

        assertEquals(2, lista.size());
    }

    // =========================================================
    // findById()
    // =========================================================

    @Test
    @DisplayName("findById - deve retornar livro quando ID existe")
    void findById_deveRetornarLivroQuandoIdExiste() {
        Optional<Livro> resultado = livroRepository.findById(livro.getIdLivro());

        assertTrue(resultado.isPresent());
        assertEquals("Effective Java", resultado.get().getTitulo());
        assertEquals("978-0134685991", resultado.get().getIsbn());
    }

    @Test
    @DisplayName("findById - deve retornar Optional vazio quando ID não existe")
    void findById_deveRetornarVazioQuandoIdNaoExiste() {
        Optional<Livro> resultado = livroRepository.findById(999L);

        assertFalse(resultado.isPresent());
    }

    // =========================================================
    // save()
    // =========================================================

    @Test
    @DisplayName("save - deve persistir novo livro com ID gerado automaticamente")
    void save_devePersistirNovoLivroComId() {
        Livro novoLivro = new Livro();
        novoLivro.setTitulo("Design Patterns");
        novoLivro.setIsbn("978-0201633610");
        novoLivro.setAutor(autor);
        novoLivro.setCategoria(categoria);

        Livro salvo = livroRepository.save(novoLivro);

        assertNotNull(salvo.getIdLivro());
        assertEquals("Design Patterns", salvo.getTitulo());
        assertEquals("978-0201633610", salvo.getIsbn());
    }

    @Test
    @DisplayName("save - deve atualizar livro existente")
    void save_deveAtualizarLivroExistente() {
        livro.setTitulo("Effective Java - 3rd Edition");
        livroRepository.save(livro);
        entityManager.flush();
        entityManager.clear();

        Livro atualizado = entityManager.find(Livro.class, livro.getIdLivro());
        assertEquals("Effective Java - 3rd Edition", atualizado.getTitulo());
    }

    // =========================================================
    // delete() / deleteById()
    // =========================================================

    @Test
    @DisplayName("delete - deve remover livro pelo objeto")
    void delete_deveDeletarLivroExistente() {
        Long id = livro.getIdLivro();

        livroRepository.delete(livro);
        entityManager.flush();
        entityManager.clear();

        assertNull(entityManager.find(Livro.class, id));
    }

    @Test
    @DisplayName("deleteById - deve remover livro pelo ID")
    void deleteById_deveDeletarLivroPeloId() {
        Long id = livro.getIdLivro();

        livroRepository.deleteById(id);
        entityManager.flush();
        entityManager.clear();

        assertFalse(livroRepository.findById(id).isPresent());
    }

    // =========================================================
    // findByTituloContaining()
    // =========================================================

    @Test
    @DisplayName("findByTituloContaining - deve retornar livros com título parcial")
    void findByTituloContaining_deveRetornarLivrosComTituloContendo() {
        List<Livro> resultado = livroRepository.findByTituloContaining("Java");

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).getTitulo().contains("Java"));
    }

    @Test
    @DisplayName("findByTituloContaining - deve retornar lista vazia quando não encontrado")
    void findByTituloContaining_deveRetornarVazioQuandoNaoEncontrado() {
        List<Livro> resultado = livroRepository.findByTituloContaining("XYZ_INEXISTENTE");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("findByTituloContaining - deve retornar múltiplos livros com mesmo termo")
    void findByTituloContaining_deveRetornarMultiplosResultados() {
        Livro livro2 = new Livro();
        livro2.setTitulo("Java EE 8");
        livro2.setIsbn("978-1111111111");
        livro2.setAutor(autor);
        livro2.setCategoria(categoria);
        entityManager.persistAndFlush(livro2);

        List<Livro> resultado = livroRepository.findByTituloContaining("Java");

        assertEquals(2, resultado.size());
    }

    // =========================================================
    // findByTitulo()
    // =========================================================

    @Test
    @DisplayName("findByTitulo - deve retornar livro com título exato")
    void findByTitulo_deveRetornarLivroComTituloExato() {
        List<Livro> resultado = livroRepository.findByTitulo("Effective Java");

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Effective Java", resultado.get(0).getTitulo());
    }

    @Test
    @DisplayName("findByTitulo - deve retornar vazio quando título não existe")
    void findByTitulo_deveRetornarVazioQuandoTituloInexistente() {
        List<Livro> resultado = livroRepository.findByTitulo("Título Inexistente");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    // =========================================================
    // count() / existsById()
    // =========================================================

    @Test
    @DisplayName("count - deve retornar contagem correta de livros")
    void count_deveRetornarContagemCorreta() {
        assertEquals(1L, livroRepository.count());
    }

    @Test
    @DisplayName("existsById - deve retornar true quando livro existe")
    void existsById_deveRetornarTrueQuandoExiste() {
        assertTrue(livroRepository.existsById(livro.getIdLivro()));
    }

    @Test
    @DisplayName("existsById - deve retornar false quando livro não existe")
    void existsById_deveRetornarFalseQuandoNaoExiste() {
        assertFalse(livroRepository.existsById(999L));
    }
}