ALTER TABLE treinos
ADD COLUMN personal_id BIGINT;

ALTER TABLE treinos
ADD CONSTRAINT fk_treinos_personal
FOREIGN KEY (personal_id)
REFERENCES personais(id);
