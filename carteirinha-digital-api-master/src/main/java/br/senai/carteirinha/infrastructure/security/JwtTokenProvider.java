package br.senai.carteirinha.infrastructure.security;

import br.senai.carteirinha.modules.usuario.application.port.out.TokenProviderPort;
import br.senai.carteirinha.modules.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class JwtTokenProvider
    implements TokenProviderPort {

    private final JwtEncoder jwtEncoder;
    private final String issuer;
    private final long expirationMinutes;

    public JwtTokenProvider(
        JwtEncoder jwtEncoder,
        @Value("${app.jwt.issuer}")
        String issuer,
        @Value("${app.jwt.expiration-minutes}")
        long expirationMinutes
    ) {
        this.jwtEncoder = jwtEncoder;
        this.issuer = issuer;
        this.expirationMinutes = expirationMinutes;
    }

    @Override
    public String gerarPara(
        Usuario usuario
    ) {

        Instant agora = Instant.now();

        JwtClaimsSet claims =
            JwtClaimsSet
                .builder()
                .issuer(issuer)
                .issuedAt(agora)
                .expiresAt(
                    agora.plus(
                        expirationMinutes,
                        ChronoUnit.MINUTES
                    )
                )
                .subject(usuario.login())
                .claim(
                    "usuarioId",
                    usuario.id().toString()
                )
                .claim(
                    "nome",
                    usuario.nome()
                )
                .claim(
                    "matricula",
                    usuario.matricula()
                )
                .build();

        JwsHeader header =
            JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        return jwtEncoder
            .encode(
                JwtEncoderParameters.from(
                    header,
                    claims
                )
            )
            .getTokenValue();
    }
}