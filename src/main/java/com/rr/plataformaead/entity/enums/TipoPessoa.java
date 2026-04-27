package com.rr.plataformaead.entity.enums;

public enum TipoPessoa {
    FISICA("Pessoa Fisica"),
    JURIDICA("Pessoa Juridica");

    private final String descricao;

    TipoPessoa(String label) {
        this.descricao = label;
    }
}
