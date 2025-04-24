-- Adiciona a coluna personal_id
ALTER TABLE dieta ADD COLUMN personal_id BIGINT;

-- Cria a foreign key para a tabela personais
ALTER TABLE dieta
    ADD CONSTRAINT fk_dieta_personal
    FOREIGN KEY (personal_id)
    REFERENCES personais(id)
    ON DELETE CASCADE;

-- Adiciona a constraint para garantir que apenas um tipo de autor esteja presente
ALTER TABLE dieta
    ADD CONSTRAINT chk_apenas_um_autor
    CHECK (
        (user_acad_id IS NOT NULL AND personal_id IS NULL) OR
        (user_acad_id IS NULL AND personal_id IS NOT NULL)
    );
