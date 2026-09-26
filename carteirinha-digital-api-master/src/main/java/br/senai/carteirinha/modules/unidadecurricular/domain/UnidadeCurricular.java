package br.senai.carteirinha.modules.unidadecurricular.domain;

import java.util.Objects;
import java.util.UUID;

public final class UnidadeCurricular {

    private final UUID id;
    private final UUID usuarioId;
    private final String nome;
    private final String professor;
    private final double nota1;
    private final double nota2;
    private final int faltas;

    public UnidadeCurricular(
        UUID id,
        UUID usuarioId,
        String nome,
        String professor,
        double nota1,
        double nota2,
        int faltas
    ) {
        this.id = Objects.requireNonNull(id);
        this.usuarioId = Objects.requireNonNull(usuarioId);
        this.nome = exigirTexto(nome, "nome");
        this.professor = exigirTexto(
            professor,
            "professor"
        );

        validarNota(nota1, "nota1");
        validarNota(nota2, "nota2");

        if (faltas < 0) {
            throw new IllegalArgumentException(
                "faltas não pode ser negativo"
            );
        }

        this.nota1 = nota1;
        this.nota2 = nota2;
        this.faltas = faltas;
    }

    public UUID id() {
        return id;
    }

    public UUID usuarioId() {
        return usuarioId;
    }

    public String nome() {
        return nome;
    }

    public String professor() {
        return professor;
    }

    public double nota1() {
        return nota1;
    }

    public double nota2() {
        return nota2;
    }

    public double media() {

        double valor =
            (nota1 + nota2) / 2.0;

        return Math.round(
            valor * 100.0
        ) / 100.0;
    }

    public int faltas() {
        return faltas;
    }

    private static String exigirTexto(
        String valor,
        String campo
    ) {

        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                campo + " não pode estar vazio"
            );
        }

        return valor;
    }

    private static void validarNota(
        double nota,
        String campo
    ) {

        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException(
                campo + " deve estar entre 0 e 10"
            );
        }
    }
}