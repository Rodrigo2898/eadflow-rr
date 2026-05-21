package com.rr.plataformaead.entity;

import com.rr.plataformaead.entity.enums.NivelCurso;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @SequenceGenerator(
            name = "tb_curso_id_sequence",
            sequenceName = "tb_curso_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_curso_id_sequence")
    @Column(name = "id_curso")
    private int idCurso;

    @Column(name = "titulo_curso", nullable = false, length = 100)
    private String tituloCurso;

    @Column(name = "descricao_curso", length = 250)
    private String descricaoCurso;

    @Column(name = "categoria_curso", nullable = false, length = 20)
    private String categoriaCurso;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_curso")
    private NivelCurso nivelCurso;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "ativo")
    private Boolean ativo = false;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "preco_curso", nullable = false, precision = 8, scale = 2)
    private BigDecimal precoCurso;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_instrutor", nullable = false)
    private Usuario instrutor;

    @OneToMany(
            mappedBy = "curso",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Matricula> matriculas = new HashSet<>();
}
