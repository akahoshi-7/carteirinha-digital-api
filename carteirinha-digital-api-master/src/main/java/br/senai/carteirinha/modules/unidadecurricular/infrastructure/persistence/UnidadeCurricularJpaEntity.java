package br.senai.carteirinha.modules.unidadecurricular.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(
    name = "unidades_curriculares",
    indexes = {
        @Index(
            name = "idx_uc_usuario_id",
            columnList = "usuario_id"
        )
    }
)
public class UnidadeCurricularJpaEntity {

    @Id
    private UUID id;

    @Column(
        name = "usuario_id",
        nullable = false
    )
    private UUID usuarioId;

    @Column(
        nullable = false,
        length = 150
    )
    private String nome;

    @Column(
        nullable = false,
        length = 120
    )
    private String professor;

    @Column(
        name = "nota_1",
        nullable = false
    )
    private double nota1;

    @Column(
        name = "nota_2",
        nullable = false
    )
    private double nota2;

    @Column(nullable = false)
    private int faltas;

    protected UnidadeCurricularJpaEntity() {
    }

    public UnidadeCurricularJpaEntity(
        UUID id,
        UUID usuarioId,
        String nome,
        String professor,
        double nota1,
        double nota2,
        int faltas
    ) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.professor = professor;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.faltas = faltas;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public String getProfessor() {
        return professor;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public int getFaltas() {
        return faltas;
    }
}