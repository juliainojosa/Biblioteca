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
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idCategoria;

    @Column(nullable = false, unique = true)
    private String nomeCategoria;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Livro> livros;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public List<Livro> getLivros() {
    return livros;
}

public void setLivros(List<Livro> livros) {
    this.livros = livros;
}
}
