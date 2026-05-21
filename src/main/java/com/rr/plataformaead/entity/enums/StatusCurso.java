package com.rr.plataformaead.entity.enums;

public enum StatusCurso {
    CURSANDO("Curso em andamento"),
    CONCLUIDO("Curso concluido"),
    TRANCADO("Curso trancado"),
    CANCELADO("Curso cancelado");

    private final String descricao;

    private StatusCurso(String labell) {
        this.descricao = labell;
    }
}
