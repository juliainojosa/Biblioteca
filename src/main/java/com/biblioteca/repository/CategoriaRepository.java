package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.biblioteca.entity.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

    List<Categoria> findByNomeCategoria(String nomeCategoria);

}