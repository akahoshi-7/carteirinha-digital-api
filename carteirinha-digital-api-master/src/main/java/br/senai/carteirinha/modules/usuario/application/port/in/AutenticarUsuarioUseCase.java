package br.senai.carteirinha.modules.usuario.application.port.in;

import br.senai.carteirinha.modules.usuario.application.dto.LoginRequestDto;
import br.senai.carteirinha.modules.usuario.application.dto.LoginResponseDto;

public interface AutenticarUsuarioUseCase {
    LoginResponseDto autenticar(LoginRequestDto credenciais);
}
