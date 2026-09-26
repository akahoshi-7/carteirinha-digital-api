package br.senai.carteirinha.modules.usuario.application;

import br.senai.carteirinha.modules.usuario.application.dto.LoginRequestDto;
import br.senai.carteirinha.modules.usuario.application.service.AutenticarUsuarioService;
import br.senai.carteirinha.modules.usuario.domain.CredenciaisInvalidasException;
import br.senai.carteirinha.modules.usuario.domain.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AutenticarUsuarioServiceTest {

    private static final UUID ID =
        UUID.fromString(
            "00000000-0000-0000-0000-000000000001"
        );

    @Test
    void deveAutenticarSemPrecisarDeSpringJpaOuJwtReal() {

        Usuario usuario =
            usuarioAtivo();

        var service =
            new AutenticarUsuarioService(
                login ->
                    Optional.of(usuario),
                (senhaPura, hash) ->
                    senhaPura.equals("123")
                        && hash.equals(
                            "hash-da-senha"
                        ),
                user ->
                    "token-gerado-pelo-adaptador"
            );

        var resultado =
            service.autenticar(
                new LoginRequestDto(
                    " aluno ",
                    "123"
                )
            );

        assertEquals(
            ID.toString(),
            resultado.id()
        );

        assertEquals(
            "Rafael Costa",
            resultado.nome()
        );

        assertEquals(
            "2026000001",
            resultado.matricula()
        );

        assertEquals(
            "Desenvolvimento de Sistemas",
            resultado.curso()
        );

        assertEquals(
            "2DEVEST-A",
            resultado.turma()
        );

        assertEquals(
            "token-gerado-pelo-adaptador",
            resultado.token()
        );
    }

    @Test
    void deveRecusarQuandoUsuarioNaoExiste() {

        var service =
            new AutenticarUsuarioService(
                login ->
                    Optional.empty(),
                (senha, hash) ->
                    true,
                usuario ->
                    "token"
            );

        assertThrows(
            CredenciaisInvalidasException.class,
            () ->
                service.autenticar(
                    new LoginRequestDto(
                        "desconhecido",
                        "123"
                    )
                )
        );
    }

    @Test
    void deveRecusarSenhaIncorreta() {

        var service =
            new AutenticarUsuarioService(
                login ->
                    Optional.of(
                        usuarioAtivo()
                    ),
                (senha, hash) ->
                    false,
                usuario ->
                    "token"
            );

        assertThrows(
            CredenciaisInvalidasException.class,
            () ->
                service.autenticar(
                    new LoginRequestDto(
                        "aluno",
                        "senha-errada"
                    )
                )
        );
    }

    @Test
    void deveRecusarUsuarioInativo() {

        Usuario inativo =
            new Usuario(
                ID,
                "aluno",
                "hash-da-senha",
                "Rafael Costa",
                "2026000001",
                "Desenvolvimento de Sistemas",
                "2DEVEST-A",
                false
            );

        var service =
            new AutenticarUsuarioService(
                login ->
                    Optional.of(inativo),
                (senha, hash) ->
                    true,
                usuario ->
                    "token"
            );

        assertThrows(
            CredenciaisInvalidasException.class,
            () ->
                service.autenticar(
                    new LoginRequestDto(
                        "aluno",
                        "123"
                    )
                )
        );
    }

    private Usuario usuarioAtivo() {

        return new Usuario(
            ID,
            "aluno",
            "hash-da-senha",
            "Rafael Costa",
            "2026000001",
            "Desenvolvimento de Sistemas",
            "2DEVEST-A",
            true
        );
    }
}