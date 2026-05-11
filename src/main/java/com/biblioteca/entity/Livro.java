package com.biblioteca.entity;

/*
 * Importações do JPA.
 * JPA = tecnologia usada para mapear objetos Java em tabelas do banco.
 */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * @Entity
 *
 * Diz ao Spring:
 *
 * "essa classe representa uma tabela do banco de dados"
 *
 * Cada objeto Livro será uma linha da tabela.
 */
@Entity
public class Livro {

    /*
     * @Id
     *
     * Define a chave primária da tabela.
     *
     * Toda tabela precisa de um identificador único.
     */
    @Id

    /*
     * @GeneratedValue
     *
     * Faz o ID ser gerado automaticamente.
     *
     * GenerationType.IDENTITY:
     * o próprio banco cria os IDs.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Atributos da entidade.
     *
     * Cada atributo vira uma coluna no banco.
     */
    private String titulo;

    private String autor;

    private String isbn;

    private String categoria;

    /*
     * Getter do ID
     *
     * Getter = método para obter valor.
     */
    public Long getId() {
        return id;
    }

    /*
     * Setter do ID
     *
     * Setter = método para alterar valor.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /*
     * Getter do título
     */
    public String getTitulo() {
        return titulo;
    }

    /*
     * Setter do título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /*
     * Getter do autor
     */
    public String getAutor() {
        return autor;
    }

    /*
     * Setter do autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /*
     * Getter do ISBN
     */
    public String getIsbn() {
        return isbn;
    }

    /*
     * Setter do ISBN
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /*
     * Getter da categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /*
     * Setter da categoria
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}