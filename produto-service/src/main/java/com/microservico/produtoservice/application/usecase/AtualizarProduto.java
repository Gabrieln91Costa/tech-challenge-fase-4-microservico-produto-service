package com.microservico.produtoservice.application.usecase;

import com.microservico.produtoservice.domain.model.Produto;

public interface AtualizarProduto {
    Produto atualizarProduto(String id, Produto produto);
}
