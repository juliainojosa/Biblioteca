package com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.biblioteca.entity.Usuario;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.UsuarioRepository;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNomeUsuario("Maria");
        usuario.setEmail("maria@email.com");
        usuario.setTelefone("81999999999");
    }

    @Test
    @DisplayName("Listar usuários")
    void listarUsuarios() {

        when(usuarioRepository.findAll())
                .thenReturn(List.of(usuario));

        Iterable<Usuario> resultado =
                usuarioService.listarUsuarios();

        assertNotNull(resultado);

        verify(usuarioRepository).findAll();
    }

    @Test
    @DisplayName("Buscar usuário por ID existente")
    void buscarUsuarioExistente() {

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        Usuario resultado =
                usuarioService.buscarPorId(1L);

        assertEquals("Maria",
                resultado.getNomeUsuario());
    }

    @Test
    @DisplayName("Buscar usuário inexistente")
    void buscarUsuarioInexistente() {

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> usuarioService.buscarPorId(1L));
    }

    @Test
    @DisplayName("Cadastrar usuário")
    void cadastrarUsuario() {

        when(usuarioRepository.save(usuario))
                .thenReturn(usuario);

        Usuario resultado =
                usuarioService.cadastrarUsuario(usuario);

        assertNotNull(resultado);

        verify(usuarioRepository).save(usuario);
    }

    @Test
    @DisplayName("Atualizar usuário")
    void atualizarUsuario() {

        Usuario atualizado = new Usuario();

        atualizado.setNomeUsuario("Maria Silva");
        atualizado.setEmail("maria@gmail.com");
        atualizado.setTelefone("81888888888");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(usuarioRepository.save(any()))
                .thenReturn(usuario);

        Usuario resultado =
                usuarioService.atualizarUsuario(
                        1L,
                        atualizado);

        assertEquals(
                "Maria Silva",
                resultado.getNomeUsuario());
    }

    @Test
    @DisplayName("Deletar usuário")
    void deletarUsuario() {

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        usuarioService.deletarUsuario(1L);

        verify(usuarioRepository).delete(usuario);
    }

    @Test
    @DisplayName("Deletar usuário inexistente")
    void deletarUsuarioInexistente() {

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> usuarioService.deletarUsuario(1L));
    }
}