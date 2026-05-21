package com.rr.plataformaead.entity.enums;

public enum NivelCurso {
    INICIANTE("Nível iniciante"),
    INTERMEDIARIO("Nível intermediário"),
    AVANCADO("Nível avançado");

    private final String descricao;

    NivelCurso(String descricao) {
        this.descricao = descricao;
    }
}
