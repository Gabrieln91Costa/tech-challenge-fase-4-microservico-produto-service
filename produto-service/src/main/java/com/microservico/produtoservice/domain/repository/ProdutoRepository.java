package com.microservico.produtoservice.domain.repository;

import com.microservico.produtoservice.domain.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

    Optional<Produto> findBySku(String sku);
}
