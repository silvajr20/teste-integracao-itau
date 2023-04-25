package com.api.aditamento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BusinessException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AditaDataPagamentoException.class)
    public ResponseEntity<AditaDataPagamentoException> pagamentoException(AditaDataPagamentoException pagamentoException){
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new AditaDataPagamentoException(pagamentoException.getMessage()));
    }
    @ExceptionHandler(AditaQuantidadeParcelasException.class)
    public ResponseEntity<AditaQuantidadeParcelasException> pagamentoException(AditaQuantidadeParcelasException parcelaException){
        return ResponseEntity.
                status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new AditaQuantidadeParcelasException(parcelaException.getMessage()));
    }
}
