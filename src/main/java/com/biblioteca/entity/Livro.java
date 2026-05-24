package com.biblioteca.entity;

/*
 * Importações do JPA.
 * JPA = tecnologia usada para mapear objetos Java em tabelas do banco.
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity //Marca a classe como uma entidade do JPA, ou seja, uma tabela do banco.
public class Livro {

    @Id //PK

    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto-incremento do ID no banco.

    //Atributos
    private Long idLivro;//JPA já entende que isso é uma tabela primária por conta do @Id.

    //Constraints São regras que definem como os dados devem ser armazenados no banco. Ex: @Column e @NotBlank.

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "autor_id")//FK para a tabela Autor dentro da tabela Livro.
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

//Getters e Setters
    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}


