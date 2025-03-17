CREATE TABLE exercicios (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    series INT NOT NULL,
    repeticoes INT NOT NULL,
    grupo_muscular ENUM('PEITO', 'COSTA', 'PERNAS', 'BRACOS', 'OMBROS') NOT NULL,
    treino_id BIGINT NOT NULL,
    FOREIGN KEY (treino_id) REFERENCES treinos(id) ON DELETE CASCADE
);
