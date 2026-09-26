package br.senai.carteirinha.infrastructure.security;

import br.senai.carteirinha.modules.usuario.application.port.out.PasswordEncoderPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {
    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordEncoderAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean corresponde(String senhaPura, String senhaHash) {
        return passwordEncoder.matches(senhaPura, senhaHash);
    }
}
