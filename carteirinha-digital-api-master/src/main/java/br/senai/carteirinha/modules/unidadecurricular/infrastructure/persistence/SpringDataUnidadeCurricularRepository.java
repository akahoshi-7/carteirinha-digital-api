package br.senai.carteirinha.modules.unidadecurricular.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataUnidadeCurricularRepository
    extends JpaRepository<
        UnidadeCurricularJpaEntity,
        UUID
    > {

    List<UnidadeCurricularJpaEntity>
    findByUsuarioIdOrderByNomeAsc(
        UUID usuarioId
    );
}