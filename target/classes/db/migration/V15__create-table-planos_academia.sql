CREATE TABLE planos_academia (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    academia_id BIGINT NOT NULL,
    FOREIGN KEY (academia_id) REFERENCES academias(id) ON DELETE CASCADE
);
