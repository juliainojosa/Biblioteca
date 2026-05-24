package com.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entity.Usuario;
import com.biblioteca.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // LISTAR TODOS OS USUÁRIOS
    @GetMapping
    public Iterable<Usuario> listarUsuarios() {

        return usuarioService.listarUsuarios();
    }

    // BUSCAR USUÁRIO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {

        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        if (usuario.isPresent()) {

            return ResponseEntity.ok(usuario.get());
        }

        return ResponseEntity.notFound().build();
    }

    // CADASTRAR USUÁRIO
    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario) {

        return usuarioService.cadastrarUsuario(usuario);
    }

    // ATUALIZAR USUÁRIO
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable Long id,
            @RequestBody Usuario usuarioAtualizado) {

        Usuario usuario = usuarioService.atualizarUsuario(id, usuarioAtualizado);

        if (usuario != null) {

            return ResponseEntity.ok(usuario);
        }

        return ResponseEntity.notFound().build();
    }

    // DELETAR USUÁRIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {

        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        if (usuario.isPresent()) {

            usuarioService.deletarUsuario(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}