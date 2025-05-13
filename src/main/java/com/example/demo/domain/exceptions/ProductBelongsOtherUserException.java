package com.example.demo.domain.exceptions;

public class ProductBelongsOtherUserException extends RuntimeException{
    public ProductBelongsOtherUserException(long productId) { super(); }
}
