package com.rr.plataformaead.entity;

import com.rr.plataformaead.entity.enums.StatusCurso;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_matricula")
public class Matricula {

    @Id
    @SequenceGenerator(
            name = "tb_matricula_id_sequence",
            sequenceName = "tb_matricula_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_matricula_id_sequence")
    private Long idMatricula;

    @Column(name = "data_matricula", nullable = false)
    private LocalDateTime dataMatricula;

    @Column(name = "nota_final", precision = 5, scale = 2)
    private BigDecimal notaFinal;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_curso", nullable = false, length = 30)
    private StatusCurso statusCurso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno", nullable = false)
    private Usuario aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
}
