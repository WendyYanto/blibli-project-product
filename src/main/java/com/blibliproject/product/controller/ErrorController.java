package com.blibliproject.product.controller;

import com.blibliproject.product.exception.ApiKeyNotFoundException;
import com.blibliproject.product.exception.ApiKeyNotIdentifiedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ApiKeyNotFoundException.class)
    public String ApiKeyNotFound(ApiKeyNotFoundException e){
        return "APIKEY NOT FOUND";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ApiKeyNotIdentifiedException.class)
    public String ApiKeyNotIdentified(ApiKeyNotIdentifiedException e){
        return "APIKEY NOT IDENTIFIED";
    }

}
