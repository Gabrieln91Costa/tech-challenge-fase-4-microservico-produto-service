package com.microservico.produtoservice.infrastructure.controller;

import com.microservico.produtoservice.application.service.ProdutoService;
import com.microservico.produtoservice.domain.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criarCliente(@RequestBody Produto cliente) {
        return produtoService.criarProduto(cliente);
    }

    @GetMapping("/{id}")
    public Produto buscarCliente(@PathVariable Long id) {
        return produtoService.porId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @GetMapping
    public List<Produto> listarClientes() {
        return produtoService.listarTodos();
    }

    @PutMapping("/{id}")
    public Produto atualizarCliente(@PathVariable Long id, @RequestBody Produto cliente) {
        return produtoService.atualizarProduto(id, cliente);
    }
}
