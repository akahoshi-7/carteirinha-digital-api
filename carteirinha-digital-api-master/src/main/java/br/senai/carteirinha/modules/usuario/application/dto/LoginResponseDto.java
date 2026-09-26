package br.senai.carteirinha.modules.usuario.application.dto;

/**
 * Dados devolvidos ao aplicativo após a autenticação.
 */
public record LoginResponseDto(
    String id,
    String nome,
    String matricula,
    String curso,
    String turma,
    String token
) {
}