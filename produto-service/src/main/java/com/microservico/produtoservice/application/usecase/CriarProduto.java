package com.microservico.produtoservice.application.usecase;

import com.microservico.produtoservice.domain.model.Produto;

public interface CriarProduto {
    Produto criarProduto(Produto produto);
}
