package com.biblioteca.repository;

/*
 * Importa a entity Livro.
 *
 * O Repository vai trabalhar com objetos Livro.
 */
import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entity.Livro;

/*
 * interface
 *
 * Interface define comportamentos.
 *
 * Ela funciona como um "contrato".
 *
 * Aqui estamos dizendo:
 *
 * "o LivroRepository terá todos os comportamentos do JpaRepository"
 */

/*
 * JpaRepository<Livro, Long>
 *
 * Livro = entidade manipulada
 *
 * Long = tipo do ID da entidade
 */
public interface LivroRepository extends JpaRepository<Livro, Long> {

}