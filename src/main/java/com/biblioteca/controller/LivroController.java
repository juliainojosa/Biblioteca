package com.biblioteca.controller;

// Importa List
import java.util.List;

// Faz injeção automática de dependência
import org.springframework.beans.factory.annotation.Autowired;

// Classe usada para retornar respostas HTTP
import org.springframework.http.ResponseEntity;

// Importações das anotações REST
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Importa entidade Livro
import com.biblioteca.entity.Livro;

// Importa o service
import com.biblioteca.service.LivroService;

@RestController
// Define que esta classe é um Controller REST
// Ela responde requisições HTTP em JSON

@RequestMapping("/livros")
// Caminho base da API
// Tudo aqui começa com /livros
public class LivroController {

    @Autowired
    // Injeta automaticamente o LivroService
    private LivroService livroService;

    // =====================================================
    // LISTAR TODOS OS LIVROS
    // =====================================================

    @GetMapping
    // GET http://localhost:8080/livros
    public List<Livro> listarLivros() {

        // Chama o service
        return livroService.listarLivros();
    }

    // =====================================================
    // BUSCAR LIVRO POR ID
    // =====================================================

    @GetMapping("/{id}")
    // Exemplo:
    // GET http://localhost:8080/livros/1
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {

        // @PathVariable pega o ID da URL

        Livro livro = livroService.buscarPorId(id);

        // Retorna 200 OK
        return ResponseEntity.ok(livro);
    }

    // =====================================================
    // CADASTRAR LIVRO
    // =====================================================

    @PostMapping
    // POST http://localhost:8080/livros
    public Livro cadastrarLivro(@RequestBody Livro livro) {

        // @RequestBody pega o JSON enviado

        return livroService.cadastrarLivro(livro);
    }

    // =====================================================
    // BUSCAR LIVRO POR TÍTULO
    // =====================================================

    @GetMapping("/buscar")
    // Exemplo:
    // GET /livros/buscar?titulo=Java
    public List<Livro> buscarPorTitulo(@RequestParam String titulo) {

        // @RequestParam pega parâmetro da URL

        return livroService.buscarPorTitulo(titulo);
    }

    // =====================================================
    // ATUALIZAR LIVRO
    // =====================================================

    @PutMapping("/{id}")
    // PUT http://localhost:8080/livros/1
    public ResponseEntity<Livro> atualizarLivro(
            @PathVariable Long id,
            @RequestBody Livro livroAtualizado) {

        Livro livro = livroService.atualizarLivro(id, livroAtualizado);

        return ResponseEntity.ok(livro);
    }

    // =====================================================
    // DELETAR LIVRO
    // =====================================================

    @DeleteMapping("/{id}")
    // DELETE http://localhost:8080/livros/1
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {

        livroService.deletarLivro(id);

        // Retorna HTTP 204
        return ResponseEntity.noContent().build();
    }
}