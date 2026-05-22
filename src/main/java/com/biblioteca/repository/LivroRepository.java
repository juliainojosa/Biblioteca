package com.biblioteca.repository; //É um pacote que contém as interfaces de repositório para acessar os dados do banco de dados. Ele é responsável por fornecer métodos para realizar operações de CRUD (Create, Read, Update, Delete) e consultas personalizadas relacionadas à entidade "Livro".

import java.util.List; //Importa a classe List da biblioteca java.

import org.springframework.data.repository.CrudRepository;//Importa a interface CrudRepository do Spring Data, que fornece métodos para realizar operações de CRUD (Create, Read, Update, Delete) em entidades. 

import com.biblioteca.entity.Livro;//Importa a classe Livro do pacote com.biblioteca.entity, que representa a entidade "Livro" no sistema.

public interface LivroRepository extends CrudRepository<Livro, Long> {

    List<Livro> findByTitulo(String titulo);

List<Livro> findByTituloContaining(String titulo);//Define um método personalizado para buscar livros pelo título. O Spring Data irá gerar a implementação desse método com base na convenção de nomenclatura, permitindo que você encontre livros cujo título contenha a string fornecida.

}//Define a interface LivroRepository que estende a interface CrudRepository, especificando a entidade "Livro" e o tipo do identificador (Long). 

//Em Java, uma interface é um contrato que define o que uma classe deve fazer, mas não como ela fará. Já uma interface CRUD Repository (muito usada no ecossistema Spring Boot/Data) é uma interface pronta fornecida pelo framework que automatiza a manipulação de dados no banco, fornecendo métodos de criação, leitura, atualização e exclusão sem que você precise escrevê-los.