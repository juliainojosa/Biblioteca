package com.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Usuario;
import com.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // LISTAR TODOS OS USUÁRIOS
    public Iterable<Usuario> listarUsuarios() {

        return usuarioRepository.findAll();
    }

    // BUSCAR USUÁRIO POR ID
    public Optional<Usuario> buscarPorId(Long id) {

        return usuarioRepository.findById(id);
    }

    // CADASTRAR USUÁRIO
    public Usuario cadastrarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    // ATUALIZAR USUÁRIO
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {

        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {

            Usuario usuario = usuarioExistente.get();

            usuario.setNomeUsuario(usuarioAtualizado.getNomeUsuario());

            usuario.setEmail(usuarioAtualizado.getEmail());

            usuario.setTelefone(usuarioAtualizado.getTelefone());

            return usuarioRepository.save(usuario);
        }

        return null;
    }

    // DELETAR USUÁRIO
    public void deletarUsuario(Long id) {

        usuarioRepository.deleteById(id);
    }
}