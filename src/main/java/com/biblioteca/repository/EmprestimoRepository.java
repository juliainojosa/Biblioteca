package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {

    List<Emprestimo> findByDevolvido(boolean devolvido);

}