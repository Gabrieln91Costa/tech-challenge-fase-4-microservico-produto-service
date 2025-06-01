package com.microservico.produtoservice.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "produtos")  // Define a coleção no MongoDB
public class Produto {

    @Id
    private String id;  // MongoDB usa ID como String (ObjectId)

    private String nome;

    private String sku;

    private Double preco;
}
