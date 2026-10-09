package br.senai.carteirinha.modules.usuario.infrastructure.persistence;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class UsuarioDataInitializer {

    @Bean
    CommandLineRunner carregarUsuariosDeTeste(
        SpringDataUsuarioRepository repository,
        PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (repository.count() > 0) {
                return;
            }

            repository.save(
                new UsuarioJpaEntity(
                    UUID.fromString(
                        "00000000-0000-0000-0000-000000000001"
                    ),
                    "aluno",
                    passwordEncoder.encode("123"),
                    "Rafael Costa",
                    "2026000001",
                    "Desenvolvimento de Sistemas",
                    "2DEVEST-A",
                    true
                )
            );

            repository.save(
                new UsuarioJpaEntity(
                    UUID.fromString(
                        "00000000-0000-0000-0000-000000000002"
                    ),
                    "maria",
                    passwordEncoder.encode("456"),
                    "Mary clear",
                    "2026000002",
                    "Desenvolvimento de Sistemas",
                    "2DEVEST-B",
                    true
                )
            );
        };
    }
}