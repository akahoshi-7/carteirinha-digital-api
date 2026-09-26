package br.senai.carteirinha.modules.unidadecurricular.application.dto;

import br.senai.carteirinha.modules.unidadecurricular.domain.UnidadeCurricular;

public record UnidadeCurricularResponseDto(
    String id,
    String nome,
    String professor,
    double nota1,
    double nota2,
    double media,
    int faltas
) {

    public static UnidadeCurricularResponseDto from(
        UnidadeCurricular unidade
    ) {
        return new UnidadeCurricularResponseDto(
            unidade.id().toString(),
            unidade.nome(),
            unidade.professor(),
            unidade.nota1(),
            unidade.nota2(),
            unidade.media(),
            unidade.faltas()
        );
    }
}