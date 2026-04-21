package com.rr.plataformaead.entity;

import com.rr.plataformaead.entity.enums.TipoPessoa;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_usuario_id_usuario_seq")
    @SequenceGenerator(
            name = "tb_usuario_id_usuario_seq",
            sequenceName = "tb_usuario_id_usuario_seq",
            allocationSize = 1
    )
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;

    @Column(nullable = false)
    private String email;

    @Column(name = "senha_hash",nullable = false)
    private String senha;
    
    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "email_verificado")
    private Boolean emailVerificado = false;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "ultimo_acesso")
    private LocalDateTime ultimoAcesso;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_permissoes",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao"))
    private Set<Role> roles = new HashSet<>();
}
