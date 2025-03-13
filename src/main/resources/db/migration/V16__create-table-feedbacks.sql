CREATE TABLE feedbacks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    descricao TEXT NOT NULL,
    estrelas INT NOT NULL CHECK (estrelas BETWEEN 1 AND 5),
    user_id BIGINT NOT NULL,
    academia_id BIGINT,
    personal_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user_academia(id) ON DELETE CASCADE,
    FOREIGN KEY (academia_id) REFERENCES academias(id) ON DELETE CASCADE,
    FOREIGN KEY (personal_id) REFERENCES personais(id) ON DELETE CASCADE
);
