package com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Usuario;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // LISTAR
    public Iterable<Usuario> listarUsuarios() {

        return usuarioRepository.findAll();
    }

    // BUSCAR
    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado"));
    }

    // CADASTRAR
    public Usuario cadastrarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    // ATUALIZAR
    public Usuario atualizarUsuario(
            Long id,
            Usuario usuarioAtualizado) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado"));

        usuario.setNomeUsuario(
                usuarioAtualizado.getNomeUsuario());

        usuario.setEmail(
                usuarioAtualizado.getEmail());

        usuario.setTelefone(
                usuarioAtualizado.getTelefone());

        return usuarioRepository.save(usuario);
    }

    // DELETAR
    public void deletarUsuario(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }
}