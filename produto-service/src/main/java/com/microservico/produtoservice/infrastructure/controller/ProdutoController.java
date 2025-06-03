package com.microservico.produtoservice.infrastructure.controller;

import com.microservico.produtoservice.application.service.ProdutoService;
import com.microservico.produtoservice.domain.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criarProduto(@RequestBody Produto produto) {
        Produto produtoCriado = produtoService.criarProduto(produto);
        System.out.println("📦ETAPA 02 - Produto cadastrado com sucesso: " + produtoCriado.getNome());
        return produtoCriado;
    }


    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable String id) {
        return produtoService.porId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable String id, @RequestBody Produto produtoAtualizado) {
        return produtoService.atualizarProduto(id, produtoAtualizado);
    }

    @GetMapping("/sku/{sku}")
    public Produto buscarPorSku(@PathVariable String sku) {
        return produtoService.porSku(sku)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produto com SKU " + sku + " não encontrado"));
    }
}
