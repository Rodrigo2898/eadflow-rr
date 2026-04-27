

CREATE SEQUENCE tb_usuario_id_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE tb_usuario
    ALTER COLUMN id_usuario SET DEFAULT nextval('tb_usuario_id_sequence');

CREATE SEQUENCE tb_permissoes_id_sequence START WITH 1 INCREMENT BY 1;

ALTER TABLE tb_permissoes
    ALTER COLUMN id_permissao SET DEFAULT nextval('tb_permissoes_id_sequence');

ALTER TABLE tb_usuario
    ALTER COLUMN tipo_pessoa SET NOT NULL;

ALTER TABLE pessoa_fisica
    ALTER COLUMN cpf SET NOT NULL;

ALTER TABLE pessoa_fisica
    ALTER COLUMN nome SET NOT NULL;

ALTER TABLE pessoa_fisica
    ALTER COLUMN data_nascimento SET NOT NULL;

ALTER TABLE pessoa_juridica
    ALTER COLUMN cnpj SET NOT NULL;

ALTER TABLE pessoa_juridica
    ALTER COLUMN razao_social SET NOT NULL;

ALTER TABLE pessoa_juridica
    ALTER COLUMN data_abertura SET NOT NULL;

INSERT INTO tb_permissoes (nome_permissao)
VALUES
    ('ADMIN'),
    ('INSTRUTOR'),
    ('ALUNO');