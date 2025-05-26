ALTER TABLE avaliacao
ADD CONSTRAINT fk_avaliacao_user_academia
FOREIGN KEY (usuario_id) REFERENCES user_academia(id)
ON DELETE RESTRICT ON UPDATE CASCADE;
