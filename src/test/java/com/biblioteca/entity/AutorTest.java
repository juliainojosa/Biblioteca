package com.biblioteca.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutorTest {

    @Test
    @DisplayName("Deve criar autor corretamente")
    void deveCriarAutorCorretamente() {

        Autor autor = new Autor();

        autor.setIdAutor(1L);
        autor.setNomeAutor("Joshua Bloch");
        autor.setNacionalidade("Americano");

        assertEquals(1L, autor.getIdAutor());
        assertEquals("Joshua Bloch", autor.getNomeAutor());
        assertEquals("Americano", autor.getNacionalidade());
    }

    @Test
    @DisplayName("Deve alterar dados do autor")
    void deveAlterarDadosAutor() {

        Autor autor = new Autor();

        autor.setNomeAutor("Autor Antigo");
        autor.setNacionalidade("Brasil");

        autor.setNomeAutor("Autor Novo");
        autor.setNacionalidade("Estados Unidos");

        assertEquals("Autor Novo", autor.getNomeAutor());
        assertEquals("Estados Unidos", autor.getNacionalidade());
    }
}