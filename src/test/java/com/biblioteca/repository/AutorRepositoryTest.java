package com.biblioteca.repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.biblioteca.entity.Autor;

@DataJpaTest
@DisplayName("Testes de integração - AutorRepository")
class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;

    @Test
    @DisplayName("Salvar autor")
    void salvarAutor() {

        Autor autor = new Autor();

        autor.setNomeAutor("Joshua Bloch");
        autor.setNacionalidade("Americano");

        Autor salvo =
                autorRepository.save(autor);

        assertNotNull(salvo.getIdAutor());
    }

    @Test
    @DisplayName("Buscar autor por nome")
    void buscarPorNome() {

        Autor autor = new Autor();

        autor.setNomeAutor("Joshua Bloch");
        autor.setNacionalidade("Americano");

        autorRepository.save(autor);

        List<Autor> resultado =
                autorRepository.findByNomeAutor(
                        "Joshua Bloch");

        assertFalse(resultado.isEmpty());
    }

    @Test
    @DisplayName("Buscar nome inexistente")
    void buscarNomeInexistente() {

        List<Autor> resultado =
                autorRepository.findByNomeAutor(
                        "NaoExiste");

        assertTrue(resultado.isEmpty());
    }
}