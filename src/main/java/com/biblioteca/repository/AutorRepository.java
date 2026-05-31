package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNomeAutor(String nomeAutor);

}