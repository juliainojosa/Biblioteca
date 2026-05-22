package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.biblioteca.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    List<Usuario> findByNomeUsuario(String nomeUsuario);

}