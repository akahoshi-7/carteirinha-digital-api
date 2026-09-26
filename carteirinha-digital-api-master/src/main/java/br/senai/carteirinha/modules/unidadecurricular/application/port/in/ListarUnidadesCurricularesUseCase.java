package br.senai.carteirinha.modules.unidadecurricular.application.port.in;

import br.senai.carteirinha.modules.unidadecurricular.domain.UnidadeCurricular;

import java.util.List;
import java.util.UUID;

public interface ListarUnidadesCurricularesUseCase {

    List<UnidadeCurricular> listarPorUsuario(
        UUID usuarioId
    );
}