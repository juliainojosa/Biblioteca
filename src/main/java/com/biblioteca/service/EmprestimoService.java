package com.biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Emprestimo;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    // LISTAR TODOS OS EMPRÉSTIMOS
    public Iterable<Emprestimo> listarEmprestimos() {

        return emprestimoRepository.findAll();
    }

    // BUSCAR EMPRÉSTIMO POR ID
    public Emprestimo buscarPorId(Long id) {

        return emprestimoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Empréstimo não encontrado"));
    }

    // CADASTRAR EMPRÉSTIMO
    public Emprestimo cadastrarEmprestimo(
            Emprestimo emprestimo) {

        return emprestimoRepository.save(emprestimo);
    }

    // ATUALIZAR EMPRÉSTIMO
    public Emprestimo atualizarEmprestimo(
            Long id,
            Emprestimo emprestimoAtualizado) {

        Emprestimo emprestimo = buscarPorId(id);

        emprestimo.setDataEmprestimo(
                emprestimoAtualizado.getDataEmprestimo());

        emprestimo.setDataDevolucao(
                emprestimoAtualizado.getDataDevolucao());

        emprestimo.setDevolvido(
                emprestimoAtualizado.isDevolvido());

        emprestimo.setLivro(
                emprestimoAtualizado.getLivro());

        emprestimo.setUsuario(
                emprestimoAtualizado.getUsuario());

        return emprestimoRepository.save(emprestimo);
    }

    // DELETAR EMPRÉSTIMO
    public void deletarEmprestimo(Long id) {

        Emprestimo emprestimo = buscarPorId(id);

        emprestimoRepository.delete(emprestimo);
    }
}