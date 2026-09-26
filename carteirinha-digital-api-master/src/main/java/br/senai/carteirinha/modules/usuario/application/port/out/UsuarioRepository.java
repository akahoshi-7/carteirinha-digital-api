package br.senai.carteirinha.modules.usuario.application.port.out;

import br.senai.carteirinha.modules.usuario.domain.Usuario;

import java.util.Optional;

@FunctionalInterface
public interface UsuarioRepository {
    Optional<Usuario> buscarPorLogin(String login);
}
