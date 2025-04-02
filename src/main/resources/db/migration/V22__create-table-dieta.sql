CREATE TABLE dieta (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    calorias INTEGER NOT NULL,
    objetivo VARCHAR(20) NOT NULL CHECK (objetivo IN ('BULKING', 'CUTTING')),
    user_acad_id BIGINT NOT NULL,
    FOREIGN KEY (user_acad_id) REFERENCES user_academia(id) ON DELETE CASCADE
);
