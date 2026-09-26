package br.senai.carteirinha.modules.usuario.presentation;

import br.senai.carteirinha.modules.usuario.application.dto.LoginRequestDto;
import br.senai.carteirinha.modules.usuario.application.dto.LoginResponseDto;
import br.senai.carteirinha.modules.usuario.application.port.in.AutenticarUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação")
public class AuthController {
    private final AutenticarUsuarioUseCase autenticarUsuario;

    public AuthController(AutenticarUsuarioUseCase autenticarUsuario) {
        this.autenticarUsuario = autenticarUsuario;
    }

    @PostMapping("/login")
    @Operation(summary = "Autentica o aluno e devolve seus dados e o token JWT")
    @ApiResponse(responseCode = "200", description = "Login realizado")
    @ApiResponse(responseCode = "400", description = "Login ou senha não preenchidos")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(autenticarUsuario.autenticar(request));
    }
}
