package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.biblioteca.entity.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long> {

    List<Autor> findByNomeAutor(String nomeAutor);

}