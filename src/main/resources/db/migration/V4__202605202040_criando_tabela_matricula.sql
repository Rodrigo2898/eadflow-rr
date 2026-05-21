-- Matricula

CREATE TABLE IF NOT EXISTS tb_matricula (
    id_matricula BIGINT PRIMARY KEY,
    id_aluno BIGINT NOT NULL,
    id_curso BIGINT NOT NULL,
    data_matricula TIMESTAMP NOT NULL,
    nota_final DECIMAL(5,2) NULL,
    data_conclusao TIMESTAMP NULL,
    status_curso VARCHAR(30) NOT NULL,

    FOREIGN KEY (id_aluno)
        REFERENCES tb_usuario (id_usuario),

    FOREIGN KEY (id_curso)
        REFERENCES tb_curso (id_curso)
);

CREATE SEQUENCE IF NOT EXISTS tb_matricula_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE tb_matricula
    ALTER COLUMN id_matricula SET DEFAULT nextval('tb_matricula_sequence');

ALTER TABLE tb_matricula
    ADD CONSTRAINT check_status_curso
        CHECK (status_curso IN ('CURSANDO', 'CONCLUIDO', 'TRANCADO', 'CANCELADO'));