package com.microservico.produtoservice.application.usecase;

import com.microservico.produtoservice.domain.model.Produto;

import java.util.Optional;

public interface BuscarProduto {
    Optional<Produto> porId(String id);
    Optional<Produto> porSku(String sku);
}
