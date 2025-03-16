package org.skypro.skyshop.controller;

import org.skypro.skyshop.errors.ShopError;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e) {
        return new ResponseEntity<>(new ShopError(e), HttpStatus.NOT_FOUND);
    }
}
