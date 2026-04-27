package com.rr.plataformaead.entity.enums;

public enum PermissaoUsuario {
    ADMIN ("Usuário Admin"),
    INSTRUTOR ("Usuário Instrutor"),
    ALUNO ("Usuário Aluno");

    private final String descricao;

    private PermissaoUsuario(String descricao) {
        this.descricao = descricao;
    }
}
