package com.biblioteca.repository;

import org.springframework.data.repository.CrudRepository;

import com.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {

}