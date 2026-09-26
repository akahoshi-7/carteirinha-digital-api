package br.senai.carteirinha.modules.unidadecurricular.application.port.out;

import br.senai.carteirinha.modules.unidadecurricular.domain.UnidadeCurricular;

import java.util.List;
import java.util.UUID;

public interface UnidadeCurricularRepository {

    List<UnidadeCurricular> listarPorUsuario(
        UUID usuarioId
    );
}