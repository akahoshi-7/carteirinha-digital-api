package br.senai.carteirinha.modules.usuario.application.port.out;

import br.senai.carteirinha.modules.usuario.domain.Usuario;

@FunctionalInterface
public interface TokenProviderPort {
    String gerarPara(Usuario usuario);
}
