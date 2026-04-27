CREATE TYPE tipo_pessoa AS ENUM (
    'FISICA',
    'JURIDICA'
);

CREATE TYPE permissao_usuario AS ENUM (
    'ADMIN',
    'INSTRUTOR',
    'ALUNO'
);

CREATE TABLE tb_usuario (
                            id_usuario BIGINT PRIMARY KEY,
                            tipo_pessoa tipo_pessoa,
                            email VARCHAR UNIQUE NOT NULL,
                            senha_hash VARCHAR NOT NULL,
                            ativo BOOLEAN DEFAULT TRUE,
                            email_verificado BOOLEAN DEFAULT FALSE,
                            avatar_url VARCHAR,
                            data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            ultimo_acesso TIMESTAMP
);

CREATE TABLE tb_permissoes (
                               id_permissao BIGINT PRIMARY KEY,
                               nome_permissao permissao_usuario NOT NULL
);

CREATE TABLE usuario_permissoes (
                                    id_usuario BIGINT NOT NULL,
                                    id_permissao BIGINT NOT NULL,
                                    PRIMARY KEY (id_usuario, id_permissao),
                                    CONSTRAINT fk_usuario
                                        FOREIGN KEY (id_usuario)
                                            REFERENCES tb_usuario(id_usuario)
                                            ON DELETE CASCADE,
                                    CONSTRAINT fk_permissao
                                        FOREIGN KEY (id_permissao)
                                            REFERENCES tb_permissoes(id_permissao)
                                            ON DELETE CASCADE
);

CREATE TABLE pessoa_fisica (
                               id_pessoa_fisica BIGINT PRIMARY KEY,
                               nome VARCHAR,
                               cpf VARCHAR UNIQUE,
                               data_nascimento TIMESTAMP,
                               FOREIGN KEY (id_pessoa_fisica)
                                   REFERENCES tb_usuario (id_usuario)
);

CREATE TABLE pessoa_juridica (
                                 id_pessoa_juridica BIGINT PRIMARY KEY,
                                 razao_social VARCHAR,
                                 cnpj VARCHAR UNIQUE,
                                 data_abertura TIMESTAMP,
                                 FOREIGN KEY (id_pessoa_juridica)
                                     REFERENCES tb_usuario (id_usuario)
);
