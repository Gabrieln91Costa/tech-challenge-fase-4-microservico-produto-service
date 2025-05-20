package com.microservico.produtoservice.application.service;

import com.microservico.produtoservice.application.usecase.CriarProduto;
import com.microservico.produtoservice.application.usecase.AtualizarProduto;
import com.microservico.produtoservice.application.usecase.BuscarProduto;
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
            throw new RuntimeException("SKU já cadastrado");
        }
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarProduto(Long id, Produto produto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());
        // SKU geralmente não é alterado, mas pode ser incluído se necessário
        // produtoExistente.setSku(produto.getSku());

        return produtoRepository.save(produtoExistente);
    }

    @Override
    public Optional<Produto> porId(Long id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> porSku(String sku) {
        return produtoRepository.findBySku(sku);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
}
