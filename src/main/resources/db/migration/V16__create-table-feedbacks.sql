CREATE TABLE avaliacao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nota INT NOT NULL,
    comentario TEXT,
    usuario_id BIGINT NOT NULL,
    tipo_entidade VARCHAR(50) NOT NULL,
    entidade_id BIGINT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_avaliacao_unica UNIQUE (usuario_id, tipo_entidade, entidade_id)
);
