package com.microservico.produtoservice.domain.exception;

public class DuplicatedSkuException extends RuntimeException {
    public DuplicatedSkuException(String message) {
        super(message);
    }
}
