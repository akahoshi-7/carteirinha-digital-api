package br.senai.carteirinha.modules.usuario.infrastructure.config;

import br.senai.carteirinha.modules.usuario.application.port.in.AutenticarUsuarioUseCase;
import br.senai.carteirinha.modules.usuario.application.port.out.PasswordEncoderPort;
import br.senai.carteirinha.modules.usuario.application.port.out.TokenProviderPort;
import br.senai.carteirinha.modules.usuario.application.port.out.UsuarioRepository;
import br.senai.carteirinha.modules.usuario.application.service.AutenticarUsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Ponto de composição do módulo de usuário. */
@Configuration
public class UsuarioModuleConfig {
    @Bean
    AutenticarUsuarioUseCase autenticarUsuarioUseCase(
        UsuarioRepository usuarioRepository,
        PasswordEncoderPort passwordEncoder,
        TokenProviderPort tokenProvider
    ) {
        return new AutenticarUsuarioService(usuarioRepository, passwordEncoder, tokenProvider);
    }
}
