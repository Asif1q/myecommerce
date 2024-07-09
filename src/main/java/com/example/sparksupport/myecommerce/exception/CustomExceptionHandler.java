package com.example.sparksupport.myecommerce.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


import java.util.Date;

@RestControllerAdvice
public class CustomExceptionHandler {

        private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
        String path = request.getDescription(false);
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "Product Not Found",
                ex.getMessage() + " at " + path);
        log.info("ProductNotFoundException encountered : "+errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleSaleNotFoundException(SaleNotFoundException ex, WebRequest request) {
        String path = request.getDescription(false);
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "Sale Not Found",
                ex.getMessage() + " at " + path);
        log.info("SaleNotFoundException encountered : "+errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String path = request.getDescription(false);
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "Bad Request",
                ex.getMessage() + " at " + path);
        log.info("IllegalArgumentException encountered : "+errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex, WebRequest request) {
        String path = request.getDescription(false);
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "Internal Server Error",
                ex.getMessage() + " at " + path);
        log.info("GenericException triggered : "+errorDetails);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
