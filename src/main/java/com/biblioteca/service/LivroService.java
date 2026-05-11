package com.biblioteca.service;

/*
 * Importação da lista do Java.
 */
import java.util.List;

import org.springframework.stereotype.Service;

import com.biblioteca.entity.Livro;
import com.biblioteca.repository.LivroRepository;

/*
 * @Service
 *
 * Diz ao Spring:
 *
 * "essa classe é uma camada de serviço"
 *
 * O Spring gerencia ela automaticamente.
 */
@Service
public class LivroService {

    /*
     * Repository usado pelo Service.
     */
    private final LivroRepository repository;

    /*
     * Construtor com Injeção de Dependência.
     *
     * O Spring entrega automaticamente
     * o repository aqui.
     */
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    /*
     * Método salvar.
     *
     * Aqui poderiam existir:
     *
     * validações
     * regras de negócio
     * verificações
     */
    public Livro salvar(Livro livro) {

        /*
         * Salva no banco.
         */
        return repository.save(livro);
    }

    /*
     * Método listar.
     *
     * Busca todos os livros.
     */
    public List<Livro> listar() {
        return repository.findAll();
    }
    /*
 * Busca livro por ID.
 */
public Livro buscarPorId(Long id) {

    /*
     * findById()
     *
     * Busca pelo ID.
     *
     * orElseThrow()
     *
     * Se não encontrar,
     * lança erro.
     */
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
}
/*
 * Método para deletar livro.
 */
public void deletar(Long id) {

    /*
     * Primeiro buscamos o livro.
     *
     * Isso evita tentar deletar
     * algo que não existe.
     */
    Livro livro = buscarPorId(id);

    /*
     * delete()
     *
     * Remove do banco.
     */
    repository.delete(livro);
}
/*
 * Método atualizar.
 */
public Livro atualizar(Long id, Livro livroAtualizado) {

    /*
     * Busca o livro no banco.
     *
     * Se não existir,
     * lança erro.
     */
    Livro livroExistente = buscarPorId(id);

    /*
     * Atualiza os campos.
     */
    livroExistente.setTitulo(livroAtualizado.getTitulo());

    livroExistente.setAutor(livroAtualizado.getAutor());

    livroExistente.setIsbn(livroAtualizado.getIsbn());

    livroExistente.setCategoria(livroAtualizado.getCategoria());

    /*
     * Salva novamente.
     */
    return repository.save(livroExistente);
}
}
