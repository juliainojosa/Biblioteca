package com.biblioteca.service;

// Importa a classe List para trabalhar com listas
import java.util.List;
import java.util.ArrayList;

// Faz o Spring gerenciar essa classe automaticamente
import org.springframework.beans.factory.annotation.Autowired;

// Indica que esta classe é uma camada de SERVICE
// Responsável pelas regras de negócio
import org.springframework.stereotype.Service;

// Importa a entidade Livro
import com.biblioteca.entity.Livro;

// Importa a exceção personalizada
import com.biblioteca.exception.ResourceNotFoundException;

// Importa o repository
import com.biblioteca.repository.LivroRepository;

@Service
public class LivroService {

    // Injeta automaticamente o repository
    @Autowired
    private LivroRepository livroRepository;

    // =====================================================
    // LISTAR TODOS OS LIVROS
    // =====================================================

    public List<Livro> listarLivros() {

        // Busca todos os livros no banco e converte para List
        Iterable<Livro> iterable = livroRepository.findAll();
        List<Livro> lista = new ArrayList<>();
        iterable.forEach(lista::add);
        return lista;
    }

    // =====================================================
    // BUSCAR LIVRO POR ID
    // =====================================================

    public Livro buscarPorId(Long id) {

        // Procura o livro pelo ID
        // Se não encontrar -> lança erro 404 personalizado
        return livroRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Livro não encontrado"));
    }

    // =====================================================
    // CADASTRAR LIVRO
    // =====================================================

    public Livro cadastrarLivro(Livro livro) {

        // Salva o livro no banco
        return livroRepository.save(livro);
    }

    // =====================================================
    // BUSCAR LIVRO PELO TÍTULO
    // =====================================================

    public List<Livro> buscarPorTitulo(String titulo) {

        // Busca livros que contenham o texto digitado
        return livroRepository.findByTituloContaining(titulo);
    }

    // =====================================================
    // ATUALIZAR LIVRO
    // =====================================================

    public Livro atualizarLivro(Long id, Livro livroAtualizado) {

        // Primeiro procura o livro existente
        Livro livroExistente = buscarPorId(id);

        // Atualiza os dados
        livroExistente.setTitulo(livroAtualizado.getTitulo());

        livroExistente.setIsbn(livroAtualizado.getIsbn());

        livroExistente.setAutor(livroAtualizado.getAutor());

        livroExistente.setCategoria(livroAtualizado.getCategoria());

        // Salva no banco já atualizado
        return livroRepository.save(livroExistente);
    }

    // =====================================================
    // DELETAR LIVRO
    // =====================================================

    public void deletarLivro(Long id) {

        // Busca o livro
        Livro livro = buscarPorId(id);

        // Remove do banco
        livroRepository.delete(livro);
    }
}