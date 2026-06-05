package com.biblioteca.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.EmprestimoRepository;

@ExtendWith(MockitoExtension.class)
class EmprestimoServiceTest {

    @Mock
    private EmprestimoRepository repository;

    @InjectMocks
    private EmprestimoService service;

    private Emprestimo emprestimo;

    @BeforeEach
    void setup() {

        emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));
    }

    @Test
    void listarEmprestimos() {

        when(repository.findAll())
                .thenReturn(List.of(emprestimo));

        Iterable<Emprestimo> resultado =
                service.listarEmprestimos();

        assertNotNull(resultado);
        verify(repository).findAll();
    }

    @Test
    void buscarPorIdExistente() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(emprestimo));

        Emprestimo resultado =
                service.buscarPorId(1L);

        assertEquals(1L,
                resultado.getIdEmprestimo());
    }

    @Test
    void buscarPorIdInexistente() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.buscarPorId(1L));
    }

    @Test
    void cadastrarEmprestimo() {

        when(repository.save(any(Emprestimo.class)))
                .thenReturn(emprestimo);

        Emprestimo resultado =
                service.cadastrarEmprestimo(emprestimo);

        assertNotNull(resultado);
    }

    @Test
    void atualizarEmprestimo() {

        Emprestimo atualizado =
                new Emprestimo();

        atualizado.setDataEmprestimo(
                LocalDate.now());

        atualizado.setDataDevolucao(
                LocalDate.now().plusDays(15));

        atualizado.setDevolvido(true);

        when(repository.findById(1L))
                .thenReturn(Optional.of(emprestimo));

        when(repository.save(any(Emprestimo.class)))
                .thenReturn(emprestimo);

        service.atualizarEmprestimo(
                1L,
                atualizado);

        verify(repository).save(any(Emprestimo.class));
    }

    @Test
    void deletarEmprestimo() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(emprestimo));

        service.deletarEmprestimo(1L);

        verify(repository).delete(emprestimo);
    }
}