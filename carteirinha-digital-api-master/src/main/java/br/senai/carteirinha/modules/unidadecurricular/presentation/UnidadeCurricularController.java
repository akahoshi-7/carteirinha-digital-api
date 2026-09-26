package br.senai.carteirinha.modules.unidadecurricular.presentation;

import br.senai.carteirinha.modules.unidadecurricular.application.dto.UnidadeCurricularResponseDto;
import br.senai.carteirinha.modules.unidadecurricular.application.port.in.ListarUnidadesCurricularesUseCase;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/unidades-curriculares")
public class UnidadeCurricularController {

    private final ListarUnidadesCurricularesUseCase
        listarUnidadesCurriculares;

    public UnidadeCurricularController(
        ListarUnidadesCurricularesUseCase
            listarUnidadesCurriculares
    ) {
        this.listarUnidadesCurriculares =
            listarUnidadesCurriculares;
    }

    @GetMapping
    public List<UnidadeCurricularResponseDto> listar(
        @AuthenticationPrincipal
        Jwt jwt
    ) {

        String usuarioId =
            jwt.getClaimAsString(
                "usuarioId"
            );

        UUID id =
            UUID.fromString(
                usuarioId
            );

        return listarUnidadesCurriculares
            .listarPorUsuario(id)
            .stream()
            .map(
                UnidadeCurricularResponseDto::from
            )
            .toList();
    }
}