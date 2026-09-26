package br.senai.carteirinha.modules.usuario.application.dto;

import jakarta.validation.constraints.NotBlank;

/** Credenciais recebidas pelo caso de uso de autenticação. */
public record LoginRequestDto(
    @NotBlank(message = "O login é obrigatório")
    String login,

    @NotBlank(message = "A senha é obrigatória")
    String senha
) {}
