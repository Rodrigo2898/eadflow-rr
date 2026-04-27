package com.rr.plataformaead.entity;

import com.rr.plataformaead.entity.enums.PermissaoUsuario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_permissoes")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_permissoes_id_sequence")
    @SequenceGenerator(
            name = "tb_permissoes_id_sequence",
            sequenceName = "tb_permissoes_id_sequence",
            allocationSize = 1
    )
    @Column(name = "id_permissao")
    private Long idPermissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome_permissao", updatable = false, unique = true, nullable = false)
    private PermissaoUsuario permissaoUsuario;
}
