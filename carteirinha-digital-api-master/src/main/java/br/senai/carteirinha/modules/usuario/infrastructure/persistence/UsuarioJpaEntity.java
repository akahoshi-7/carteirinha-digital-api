package br.senai.carteirinha.modules.usuario.infrastructure.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.UUID;

@Entity
@Table(
    name = "usuarios",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_usuario_login",
            columnNames = "login"
        ),
        @UniqueConstraint(
            name = "uk_usuario_matricula",
            columnNames = "matricula"
        )
    }
)
public class UsuarioJpaEntity {

    @Id
    private UUID id;

    @Column(
        nullable = false,
        length = 80
    )
    private String login;

    @Column(
        name = "senha_hash",
        nullable = false
    )
    private String senhaHash;

    @Column(
        nullable = false,
        length = 120
    )
    private String nome;

    @Column(
        nullable = false,
        length = 30
    )
    private String matricula;

    @Column(
        nullable = false,
        length = 150
    )
    private String curso;

    @Column(
        nullable = false,
        length = 50
    )
    private String turma;

    @Column(nullable = false)
    private boolean ativo;

    protected UsuarioJpaEntity() {
    }

    public UsuarioJpaEntity(
        UUID id,
        String login,
        String senhaHash,
        String nome,
        String matricula,
        String curso,
        String turma,
        boolean ativo
    ) {
        this.id = id;
        this.login = login;
        this.senhaHash = senhaHash;
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.turma = turma;
        this.ativo = ativo;
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public String getTurma() {
        return turma;
    }

    public boolean isAtivo() {
        return ativo;
    }
}