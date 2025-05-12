CREATE TABLE servicos_externos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    tipo VARCHAR(100),
    descricao TEXT,
    contato VARCHAR(255),
    link VARCHAR(500),
    academia_id BIGINT NOT NULL,
    CONSTRAINT fk_servico_academia FOREIGN KEY (academia_id) REFERENCES academias(id) ON DELETE CASCADE
);
