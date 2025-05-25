ALTER TABLE dieta
MODIFY COLUMN user_acad_id BIGINT NULL;

INSERT INTO personais (name, email, senha, telefone, cref, imagem_url, role) VALUES
('Carlos Silva', 'carlos.silva1@example.com', '123456', '11999990001', '12312-3/SP', NULL, 'PERSONAL'),
('Fernanda Lima', 'fernanda.lima2@example.com', '123456', '11999990002', '54321-9/SP', NULL, 'PERSONAL'),
('João Pereira', 'joao.pereira3@example.com', '123456', '11999990003', '98765-2/SP', NULL, 'PERSONAL'),
('Mariana Santos', 'mariana.santos4@example.com', '123456', '11999990004', '19283-1/SP', NULL, 'PERSONAL'),
('Ricardo Alves', 'ricardo.alves5@example.com', '123456', '11999990005', '45678-5/SP', NULL, 'PERSONAL'),
('Patrícia Souza', 'patricia.souza6@example.com', '123456', '11999990006', '78901-7/SP', NULL, 'PERSONAL'),
('Eduardo Gomes', 'eduardo.gomes7@example.com', '123456', '11999990007', '23456-8/SP', NULL, 'PERSONAL'),
('Aline Costa', 'aline.costa8@example.com', '123456', '11999990008', '34567-4/SP', NULL, 'PERSONAL'),
('Marcelo Rocha', 'marcelo.rocha9@example.com', '123456', '11999990009', '67890-0/SP', NULL, 'PERSONAL'),
('Larissa Martins', 'larissa.martins10@example.com', '123456', '11999990010', '11223-6/SP', NULL, 'PERSONAL');
