package com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.dto.UsuarioRequestDTO;
import com.biblioteca.dto.UsuarioResponseDTO;
import com.biblioteca.entity.Usuario;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // LISTAR
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // BUSCAR
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return toDTO(usuario);
    }

    // CADASTRAR
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.nomeUsuario());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());

        return toDTO(usuarioRepository.save(usuario));
    }

    // ATUALIZAR
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuario.setNomeUsuario(dto.nomeUsuario());
        usuario.setEmail(dto.email());
        usuario.setTelefone(dto.telefone());

        return toDTO(usuarioRepository.save(usuario));
    }

    // DELETAR
    public void deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);
    }

    // MAPPER
    private UsuarioResponseDTO toDTO(Usuario u) {
        return new UsuarioResponseDTO(
                u.getIdUsuario(),
                u.getNomeUsuario(),
                u.getEmail(),
                u.getTelefone()
        );
    }
}