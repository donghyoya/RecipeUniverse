package com.recipe.universe.global.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(AccessException.class)
    public ResponseEntity<String> handleRuntimeException(AccessException ex) {
        // 403 AccessException 반환
        return new ResponseEntity<>("Access Denied: " + ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        // 404 Not Found 반환
        return new ResponseEntity<>("Resource Not Found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        log.warn("500: ", ex);
        // 500 Internal Server Error 반환
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
