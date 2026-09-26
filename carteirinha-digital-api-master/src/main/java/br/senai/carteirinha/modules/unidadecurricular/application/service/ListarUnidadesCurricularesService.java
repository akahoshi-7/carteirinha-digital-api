package br.senai.carteirinha.modules.unidadecurricular.application.service;

import br.senai.carteirinha.modules.unidadecurricular.application.port.in.ListarUnidadesCurricularesUseCase;
import br.senai.carteirinha.modules.unidadecurricular.application.port.out.UnidadeCurricularRepository;
import br.senai.carteirinha.modules.unidadecurricular.domain.UnidadeCurricular;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class ListarUnidadesCurricularesService
    implements ListarUnidadesCurricularesUseCase {

    private final UnidadeCurricularRepository repository;

    public ListarUnidadesCurricularesService(
        UnidadeCurricularRepository repository
    ) {
        this.repository =
            Objects.requireNonNull(repository);
    }

    @Override
    public List<UnidadeCurricular> listarPorUsuario(
        UUID usuarioId
    ) {
        return repository.listarPorUsuario(
            Objects.requireNonNull(usuarioId)
        );
    }
}