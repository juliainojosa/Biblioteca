package com.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.entity.Emprestimo;
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
    public Optional<Emprestimo> buscarPorId(Long id) {

        return emprestimoRepository.findById(id);
    }

    // CADASTRAR EMPRÉSTIMO
    public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo) {

        return emprestimoRepository.save(emprestimo);
    }

    // ATUALIZAR EMPRÉSTIMO
    public Emprestimo atualizarEmprestimo(
            Long id,
            Emprestimo emprestimoAtualizado) {

        Optional<Emprestimo> emprestimoExistente =
                emprestimoRepository.findById(id);

        if (emprestimoExistente.isPresent()) {

            Emprestimo emprestimo = emprestimoExistente.get();

            // Atualiza os dados
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

        return null;
    }

    // DELETAR EMPRÉSTIMO
    public void deletarEmprestimo(Long id) {

        emprestimoRepository.deleteById(id);
    }
}