package br.senai.carteirinha.infrastructure.web;

import br.senai.carteirinha.modules.usuario.domain.CredenciaisInvalidasException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<ErrorResponseDto> credenciaisInvalidas(CredenciaisInvalidasException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> dadosInvalidos(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
            errors.putIfAbsent(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest()
            .body(new ErrorResponseDto("Preencha login e senha", errors));
    }
}
