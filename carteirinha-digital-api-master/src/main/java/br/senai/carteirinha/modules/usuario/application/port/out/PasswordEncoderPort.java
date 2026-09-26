package br.senai.carteirinha.modules.usuario.application.port.out;

@FunctionalInterface
public interface PasswordEncoderPort {
    boolean corresponde(String senhaPura, String senhaHash);
}
