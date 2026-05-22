package com.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entity.Livro;
import com.biblioteca.repository.LivroRepository;

@RestController//Anotação que indica que esta classe é um controlador REST, ou seja, ela lida com requisições HTTP e retorna respostas em formato JSON ou XML.

@RequestMapping("/livros")//Anotação que define o caminho base para as rotas deste controlador. Todas as rotas definidas nesta classe terão o prefixo "/livros". Por exemplo, uma rota definida como @GetMapping("/todos") seria acessível através do caminho "/livros/todos".

public class LivroController {

    @Autowired// Spring deve injetar automaticamente uma instância do LivroRepository nesta classe. Isso permite que o controlador acesse os métodos do repositório para realizar operações de banco de dados relacionadas à entidade "Livro".

    private LivroRepository livroRepository;

    // LISTAR TODOS OS LIVROS
    @GetMapping//Anotação que indica que este método deve ser executado quando uma requisição HTTP GET for feita para o caminho "/livros". Isso significa que, quando um cliente fizer uma requisição GET para "/livros", este método será chamado para processar a solicitação e retornar a resposta apropriada.
    public Iterable<Livro>
    
     listarLivros() {
        return livroRepository.findAll();
    }
    

    // BUSCAR LIVRO POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {

        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        }

        return ResponseEntity.notFound().build();
    }
    
    // CADASTRAR LIVRO
    @PostMapping
    public Livro cadastrarLivro(@RequestBody Livro livro) {
        return livroRepository.save(livro);

        
    }

    @GetMapping("/buscar")
public List<Livro> buscarPorTitulo(@RequestParam String titulo) {

    return livroRepository.findByTituloContaining(titulo); //O método findByTituloContaining é um método de consulta personalizada que você deve definir no seu LivroRepository. Ele busca livros cujo título contenha a string fornecida como parâmetro. @RequestParam é usado para extrair o valor do parâmetro "titulo" da URL da requisição. Por exemplo, se um cliente fizer uma requisição GET para "/livros/buscar?titulo=Java", este método será chamado e buscará livros cujo título contenha a palavra "Java".

}

@PutMapping("/{id}")
public ResponseEntity<Livro> atualizarLivro(
        @PathVariable Long id,
        @RequestBody Livro livroAtualizado) {

    Optional<Livro> livroExistente = livroRepository.findById(id);

    if (livroExistente.isPresent()) {

        Livro livro = livroExistente.get();

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setIsbn(livroAtualizado.getIsbn());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setCategoria(livroAtualizado.getCategoria());

        Livro livroSalvo = livroRepository.save(livro);

        return ResponseEntity.ok(livroSalvo);
    }

    return ResponseEntity.notFound().build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {

    Optional<Livro> livro = livroRepository.findById(id);

    if (livro.isPresent()) {

        livroRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    return ResponseEntity.notFound().build();
}
    
}
