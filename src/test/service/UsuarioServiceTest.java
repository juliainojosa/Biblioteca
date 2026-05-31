package com.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.biblioteca.entity.Usuario;
import com.biblioteca.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveBuscarUsuarioPorId() {

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNome("Maria");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.buscarPorId(1L);

        assertEquals("Maria", resultado.get().getNome());
    }

    @Test
    void deveListarUsuarios() {

        Usuario usuario1 = new Usuario();
        usuario1.setNome("João");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Ana");

        List<Usuario> lista = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(lista);

        List<Usuario> resultado = usuarioService.listarUsuarios();

        assertEquals(2, resultado.size());
    }

    @Test
    void deveCadastrarUsuario() {

        Usuario usuario = new Usuario();
        usuario.setNome("Carlos");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.cadastrarUsuario(usuario);

        assertEquals("Carlos", resultado.getNome());
    }
}