package com.microservico.produtoservice.application.service;

import com.microservico.produtoservice.application.usecase.CriarProduto;
import com.microservico.produtoservice.application.usecase.AtualizarProduto;
import com.microservico.produtoservice.application.usecase.BuscarProduto;
import com.microservico.produtoservice.domain.exception.DuplicatedSkuException;
import com.microservico.produtoservice.domain.exception.ProdutoNotFoundException;
import com.microservico.produtoservice.domain.model.Produto;
import com.microservico.produtoservice.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements CriarProduto, AtualizarProduto, BuscarProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto criarProduto(Produto produto) {
        if (produtoRepository.findBySku(produto.getSku()).isPresent()) {
            throw new DuplicatedSkuException("SKU já cadastrado. Por favor, verifique e tente novamente.");
        }
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarProduto(String id, Produto produto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado"));

        if (!produtoExistente.getSku().equals(produto.getSku())) {
            produtoRepository.findBySku(produto.getSku()).ifPresent(p -> {
                throw new DuplicatedSkuException("SKU já cadastrado por outro produto");
            });
            produtoExistente.setSku(produto.getSku());
        }

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());

        return produtoRepository.save(produtoExistente);
    }

    @Override
    public Optional<Produto> porId(String id) {
        return produtoRepository.findById(id);
    }

    @Override
    public Optional<Produto> porSku(String sku) {
        return produtoRepository.findBySku(sku);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
}
