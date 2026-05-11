package com.biblioteca.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.entity.Livro;
import com.biblioteca.service.LivroService;

/*
 * @RestController
 *
 * Controller REST da aplicação.
 */
@RestController

/*
 * Rota principal:
 *
 * /livros
 */
@RequestMapping("/livros")
public class LivroController {

    /*
     * Camada Service.
     */
    private final LivroService service;

    /*
     * Injeção de Dependência.
     */
    public LivroController(LivroService service) {
        this.service = service;
    }

    /*
     * Endpoint POST.
     *
     * Salva livro.
     */
    @PostMapping
    public Livro salvar(@RequestBody Livro livro) {

        /*
         * Agora o controller NÃO fala mais
         * diretamente com o banco.
         *
         * Ele chama o Service.
         */
        return service.salvar(livro);
    }

    /*
     * Endpoint GET.
     *
     * Lista livros.
     */
    @GetMapping
    public List<Livro> listar() {
        return service.listar();
    }

    /*
 * GET por ID.
 *
 * Exemplo:
 *
 * /livros/1
     */
    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id) {

        /*
     * @PathVariable
     *
     * Pega valor da URL.
         */
        return service.buscarPorId(id);
    }

    /*
 * Endpoint DELETE.
 *
 * Remove livro por ID.
     */
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {

        /*
     * Chama o service.
         */
        service.deletar(id);
    }
   /*
 * Endpoint PUT.
 *
 * Atualiza livro.
 */
@PutMapping("/{id}")
public Livro atualizar(
        @PathVariable Long id,

        @RequestBody Livro livro) {

    /*
     * Chama o service.
     */
    return service.atualizar(id, livro);
} 
}
