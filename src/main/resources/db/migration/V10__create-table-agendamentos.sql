CREATE TABLE agendamentos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_hora TIMESTAMP NOT NULL,
    academia_id BIGINT NOT NULL,
    personal_id BIGINT NOT NULL,
    FOREIGN KEY (academia_id) REFERENCES academias(id) ON DELETE CASCADE,
    FOREIGN KEY (personal_id) REFERENCES personais(id) ON DELETE CASCADE
);
