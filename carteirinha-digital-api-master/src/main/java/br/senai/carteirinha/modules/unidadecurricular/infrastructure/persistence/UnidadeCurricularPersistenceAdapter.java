package br.senai.carteirinha.modules.unidadecurricular.infrastructure.persistence;

import br.senai.carteirinha.modules.unidadecurricular.application.port.out.UnidadeCurricularRepository;
import br.senai.carteirinha.modules.unidadecurricular.domain.UnidadeCurricular;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UnidadeCurricularPersistenceAdapter
    implements UnidadeCurricularRepository {

    private final SpringDataUnidadeCurricularRepository repository;

    public UnidadeCurricularPersistenceAdapter(
        SpringDataUnidadeCurricularRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public List<UnidadeCurricular> listarPorUsuario(
        UUID usuarioId
    ) {
        return repository
            .findByUsuarioIdOrderByNomeAsc(
                usuarioId
            )
            .stream()
            .map(this::toDomain)
            .toList();
    }

    private UnidadeCurricular toDomain(
        UnidadeCurricularJpaEntity entity
    ) {
        return new UnidadeCurricular(
            entity.getId(),
            entity.getUsuarioId(),
            entity.getNome(),
            entity.getProfessor(),
            entity.getNota1(),
            entity.getNota2(),
            entity.getFaltas()
        );
    }
}