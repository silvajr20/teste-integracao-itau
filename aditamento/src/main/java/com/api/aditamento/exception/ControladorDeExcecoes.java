package com.api.aditamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControladorDeExcecoes extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AditaDataPagamentoException.class)
    public ResponseEntity<AditaDataPagamentoException> aditamentoDataPagamento(AditaDataPagamentoException pagamentoException){
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new AditaDataPagamentoException(pagamentoException.getMessage()));
    }
    @ExceptionHandler(AditaQuantidadeParcelasException.class)
    public ResponseEntity<AditaQuantidadeParcelasException> aditamentoQuantidadeParcelas(AditaQuantidadeParcelasException parcelaException){
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new AditaQuantidadeParcelasException(parcelaException.getMessage()));
    }
}
