package com.biblioteca.entity;

/*
 * Importações do JPA.
 * JPA = tecnologia usada para mapear objetos Java em tabelas do banco.
 */
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idAutor;

    @Column(nullable = false, length = 100)
    private String nomeAutor;

    @Column(length = 50)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor")
    @JsonIgnore //Evita que a lista de livros seja serializada em JSON, prevenindo problemas de recursão infinita ao converter o objeto Autor para JSON.
    private List<Livro> livros;//Lista de livros escritos por esse autor.
    //mappedBy = "autor" evita a criação de uma tabela intermediária, indicando que a relação é controlada pela entidade Livro.

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
