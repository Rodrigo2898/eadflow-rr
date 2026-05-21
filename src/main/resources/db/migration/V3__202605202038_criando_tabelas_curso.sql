-- Curso

CREATE TABLE IF NOT EXISTS tb_curso (
    id_curso BIGINT PRIMARY KEY,
    id_instrutor BIGINT NOT NULL ,
    titulo_curso VARCHAR(100) NOT NULL,
    descricao_curso VARCHAR(250) NULL,
    categoria_curso VARCHAR(20) NOT NULL,
    nivel_curso VARCHAR(20) NOT NULL,
    data_criacao TIMESTAMP NOT NULL,
    ativo BOOLEAN DEFAULT FALSE,
    thumbnail_url TEXT NULL,
    preco_curso DECIMAL(8,2) NOT NULL,
    data_atualizacao TIMESTAMP NOT NULL,

    FOREIGN KEY (id_instrutor)
        REFERENCES tb_usuario (id_usuario)
);

CREATE SEQUENCE IF NOT EXISTS tb_curso_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE tb_curso
    ALTER COLUMN id_curso SET DEFAULT nextval('tb_curso_sequence');

ALTER TABLE tb_curso
    ADD CONSTRAINT check_nivel_curso
        CHECK (nivel_curso IN ('INICIANTE', 'INTERMEDIARIO', 'AVANÇADO'));
