package com.biblioteca.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.repository.EmprestimoRepository;

@ExtendWith(MockitoExtension.class)
public class EmprestimoServiceTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Test
    void deveBuscarEmprestimoPorId() {

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setIdEmprestimo(1L);

        when(emprestimoRepository.findById(1L))
                .thenReturn(Optional.of(emprestimo));

        Optional<Emprestimo> resultado =
                emprestimoService.buscarPorId(1L);

        assertEquals(1L, resultado.get().getIdEmprestimo());
    }

    @Test
    void deveListarEmprestimos() {

        Emprestimo e1 = new Emprestimo();
        Emprestimo e2 = new Emprestimo();

        Iterable<Emprestimo> lista =
                Arrays.asList(e1, e2);

        when(emprestimoRepository.findAll())
                .thenReturn(lista);

        Iterable<Emprestimo> resultado =
                emprestimoService.listarEmprestimos();

        int quantidade = 0;

        for (Emprestimo e : resultado) {
            quantidade++;
        }

        assertEquals(2, quantidade);
    }

    @Test
    void deveCadastrarEmprestimo() {

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setDataEmprestimo(LocalDate.now());

        when(emprestimoRepository.save(emprestimo))
                .thenReturn(emprestimo);

        Emprestimo resultado =
                emprestimoService.cadastrarEmprestimo(emprestimo);

        assertEquals(
                emprestimo.getDataEmprestimo(),
                resultado.getDataEmprestimo()
        );
    }
}