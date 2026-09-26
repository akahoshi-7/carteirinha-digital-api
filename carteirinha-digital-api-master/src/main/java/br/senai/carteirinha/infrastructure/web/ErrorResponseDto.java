package br.senai.carteirinha.infrastructure.web;

import java.util.Map;

public record ErrorResponseDto(String message, Map<String, String> errors) {
    public ErrorResponseDto(String message) {
        this(message, null);
    }
}
