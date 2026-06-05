package com.biblioteca.repository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.biblioteca.entity.Autor;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.entity.Livro;
import com.biblioteca.entity.Usuario;

@DataJpaTest
@DisplayName("Testes de integração - EmprestimoRepository")
class EmprestimoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmprestimoRepository repository;

    private Emprestimo emprestimo;

    @BeforeEach
    void setup() {

        Autor autor = new Autor();
        autor.setNomeAutor("Autor");
        entityManager.persist(autor);

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("TI");
        entityManager.persist(categoria);

        Livro livro = new Livro();
        livro.setTitulo("Livro Teste");
        livro.setIsbn("123456");
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        entityManager.persist(livro);

        Usuario usuario = new Usuario();
        usuario.setNomeUsuario("Maria");
        usuario.setEmail("maria@email.com");
        entityManager.persist(usuario);

        emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));

        entityManager.persistAndFlush(emprestimo);
    }

    @Test
    void findById() {

        Optional<Emprestimo> resultado =
                repository.findById(
                        emprestimo.getIdEmprestimo());

        assertTrue(resultado.isPresent());
    }

    @Test
    void save() {

        Emprestimo salvo =
                repository.save(emprestimo);

        assertNotNull(
                salvo.getIdEmprestimo());
    }

    @Test
    void existsById() {

        assertTrue(
                repository.existsById(
                        emprestimo.getIdEmprestimo()));
    }

    @Test
    void count() {

        assertEquals(1,
                repository.count());
    }

    @Test
    void delete() {

        Long id =
                emprestimo.getIdEmprestimo();

        repository.delete(emprestimo);

        assertFalse(
                repository.findById(id)
                        .isPresent());
    }
}