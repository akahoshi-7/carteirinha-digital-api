package br.senai.carteirinha.modules.usuario.domain;

public class CredenciaisInvalidasException extends RuntimeException {
    public CredenciaisInvalidasException() {
        super("Login ou senha inválidos");
    }
}
