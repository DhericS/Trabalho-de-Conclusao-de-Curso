INSERT INTO user_acad_admin (name, email, senha, telefone, cnpj, academia_id, role) VALUES
('Admin Academia Alpha', 'admin1@academia.com', '123456', '11990000001', '12345678000191', 81, 'USERACADADMIN'),
('Admin CrossFit Power', 'admin2@academia.com', '123456', '11990000002', '23456789000182', 82, 'USERACADADMIN'),
('Admin Fitness Total', 'admin3@academia.com', '123456', '11990000003', '34567890000173', 83, 'USERACADADMIN'),
('Admin Academia Beta', 'admin4@academia.com', '123456', '11990000004', '45678900000164', 84, 'USERACADADMIN'),
('Admin CrossFit Elite', 'admin5@academia.com', '123456', '11990000005', '56789000000155', 85, 'USERACADADMIN'),
('Admin Gym Central', 'admin6@academia.com', '123456', '11990000006', '67890000000146', 86, 'USERACADADMIN'),
('Admin Academia Delta', 'admin7@academia.com', '123456', '11990000007', '78900000000137', 87, 'USERACADADMIN'),
('Admin CrossFit Max', 'admin8@academia.com', '123456', '11990000008', '89000000000128', 88, 'USERACADADMIN'),
('Admin Fitness Pro', 'admin9@academia.com', '123456', '11990000009', '90000000000119', 89, 'USERACADADMIN'),
('Admin Academia Omega', 'admin10@academia.com', '123456', '11990000010', '10000000000110', 90, 'USERACADADMIN');




-- Academia Alpha (id = 81)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(81, 'ESTACIONAMENTO'),
(81, 'VESTIARIO'),
(81, 'WIFI');

-- CrossFit Power (id = 82)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(82, 'ACESSIBILIDADE'),
(82, 'ARMARIOS'),
(82, 'AR_CONDICIONADO');

-- Fitness Total (id = 83)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(83, 'ESTACIONAMENTO'),
(83, 'BEBEDOURO'),
(83, 'WIFI');

-- Academia Beta (id = 84)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(84, 'VESTIARIO'),
(84, 'ARMARIOS');

-- CrossFit Elite (id = 85)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(85, 'ACESSIBILIDADE'),
(85, 'AR_CONDICIONADO'),
(85, 'BEBEDOURO');

-- Gym Central (id = 86)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(86, 'ESTACIONAMENTO'),
(86, 'WIFI'),
(86, 'ARMARIOS');

-- Academia Delta (id = 87)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(87, 'VESTIARIO'),
(87, 'BEBEDOURO');

-- CrossFit Max (id = 88)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(88, 'ESTACIONAMENTO'),
(88, 'ACESSIBILIDADE');

-- Fitness Pro (id = 89)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(89, 'ARMARIOS'),
(89, 'AR_CONDICIONADO'),
(89, 'WIFI');

-- Academia Omega (id = 90)
INSERT INTO academia_estruturas (academia_id, estrutura) VALUES
(90, 'VESTIARIO'),
(90, 'BEBEDOURO'),
(90, 'WIFI');



-- Academia Alpha (id = 81)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(81, 'AVALIACAO_INICIAL'),
(81, 'PERSONAL'),
(81, 'WHATSAPP');

-- CrossFit Power (id = 82)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(82, 'TREINOS'),
(82, 'APP'),
(82, 'VINT_QUATRO_HORAS');

-- Fitness Total (id = 83)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(83, 'NUTRICIONAL'),
(83, 'PERSONAL');

-- Academia Beta (id = 84)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(84, 'AVALIACAO_INICIAL'),
(84, 'WHATSAPP');

-- CrossFit Elite (id = 85)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(85, 'TREINOS'),
(85, 'VINT_QUATRO_HORAS'),
(85, 'APP');

-- Gym Central (id = 86)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(86, 'PERSONAL'),
(86, 'WHATSAPP');

-- Academia Delta (id = 87)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(87, 'AVALIACAO_INICIAL'),
(87, 'NUTRICIONAL');

-- CrossFit Max (id = 88)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(88, 'TREINOS'),
(88, 'PERSONAL');

-- Fitness Pro (id = 89)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(89, 'APP'),
(89, 'WHATSAPP');

-- Academia Omega (id = 90)
INSERT INTO academia_servicos (academia_id, servico) VALUES
(90, 'AVALIACAO_INICIAL'),
(90, 'TREINOS');


INSERT INTO atividades (nome, academia_id, dia_semana, horario) VALUES
('Musculação Básica', 81, 'SEGUNDA', '08:00 - 09:00'),
('CrossFit Intensivo', 82, 'TERCA', '18:00 - 19:00'),
('Pilates Funcional', 83, 'QUARTA', '20:00 - 21:00'),
('Spinning Avançado', 84, 'QUINTA', '08:00 - 09:00'),
('Yoga Relaxante', 85, 'SEXTA', '18:00 - 19:00'),
('Treino Funcional', 86, 'SABADO', '20:00 - 21:00'),
('Alongamento e Flexibilidade', 87, 'DOMINGO', '08:00 - 09:00'),
('Aula de Zumba', 88, 'SEGUNDA', '18:00 - 19:00'),
('Natação para Iniciantes', 89, 'TERCA', '20:00 - 21:00'),
('Treinamento de Força', 90, 'QUARTA', '08:00 - 09:00');


INSERT INTO avaliacao (nota, comentario, usuario_id, tipo_entidade, entidade_id, data_criacao) VALUES
(5, 'Excelente personal, muito dedicado.', 64, 'PERSONAL', 51, NOW()),
(4, 'Dieta eficiente e fácil de seguir.', 65, 'DIETA', 42, NOW()),
(3, 'Treino desafiador, mas gratificante.', 66, 'TREINO', 31, NOW()),
(5, 'Muito satisfeito com o acompanhamento.', 67, 'PERSONAL', 54, NOW()),
(4, 'Dieta balanceada e saborosa.', 68, 'DIETA', 45, NOW()),
(2, 'Treino poderia ser mais variado.', 69, 'TREINO', 34, NOW()),
(5, 'Profissional super atencioso.', 70, 'PERSONAL', 57, NOW()),
(3, 'Dieta com resultados razoáveis.', 71, 'DIETA', 48, NOW()),
(4, 'Treino muito bom para o meu nível.', 72, 'TREINO', 37, NOW()),
(5, 'Atendimento excelente e resultados ótimos.', 73, 'PERSONAL', 60, NOW());



INSERT INTO treino_cardio (treino_id, cardio) VALUES
(29, 'CORRIDA'),
(29, 'BICICLETA'),
(30, 'ELIPTICO'),
(31, 'REMO'),
(32, 'ESCADA'),
(33, 'CORRIDA'),
(34, 'BICICLETA'),
(35, 'ELIPTICO'),
(36, 'REMO'),
(37, 'CORRIDA'),
(38, 'ESCADA');

INSERT INTO treino_hipertrofia (treino_id, hipertrofia) VALUES
(29, 'PESO_LIVRE'),
(30, 'MAQUINAS'),
(31, 'SUPINO'),
(32, 'ANILHAS'),
(33, 'RACKS'),
(34, 'PESO_LIVRE'),
(35, 'MAQUINAS'),
(36, 'SUPINO'),
(37, 'ANILHAS'),
(38, 'RACKS');

INSERT INTO treino_tipos (treino_id, tipo_treino) VALUES
(29, 'MUSCULACAO'),
(30, 'FUNCIONAL'),
(31, 'CROSSFIT'),
(32, 'HIIT'),
(33, 'CALISTENIA'),
(34, 'MUSCULACAO'),
(35, 'FUNCIONAL'),
(36, 'CROSSFIT'),
(37, 'HIIT'),
(38, 'CALISTENIA');


INSERT INTO exercicios (nome, series, repeticoes, grupo_muscular, treino_id) VALUES
-- Exercícios do Treino 29
('Supino Reto', 4, 10, 'PEITO', 29),
('Agachamento Livre', 4, 12, 'PERNAS', 29),
('Remada Curvada', 3, 10, 'COSTA', 29),
('Desenvolvimento de Ombros', 3, 12, 'OMBROS', 29),
('Rosca Direta', 3, 15, 'BRACOS', 29),
('Leg Press', 4, 12, 'PERNAS', 29),
('Puxada na Barra', 3, 10, 'COSTA', 29),
('Elevação Lateral', 3, 15, 'OMBROS', 29),
('Tríceps Pulley', 3, 12, 'BRACOS', 29),
('Prancha para Core (focado em ombros e braços)', 4, 20, 'OMBROS', 29),

-- Exercícios do Treino 30
('Cadeira Extensora', 4, 15, 'PERNAS', 30),
('Leg Press', 4, 12, 'PERNAS', 30),
('Afundo', 3, 10, 'PERNAS', 30),
('Stiff', 4, 12, 'PERNAS', 30),
('Panturrilha Sentada', 3, 20, 'PERNAS', 30),
('Rosca Martelo', 3, 15, 'BRACOS', 30),
('Tríceps Testa', 3, 12, 'BRACOS', 30),
('Remada Unilateral', 3, 10, 'COSTA', 30),
('Crucifixo', 3, 12, 'PEITO', 30),
('Abdominal Infra substituído por Exercício de Ombros', 4, 20, 'OMBROS', 30),

-- Exercícios do Treino 31
('Burpee (exercício funcional)', 4, 15, 'PERNAS', 31),
('Pull-up', 3, 10, 'COSTA', 31),
('Flexão de Braços', 4, 15, 'PEITO', 31),
('Agachamento com Salto', 3, 12, 'PERNAS', 31),
('Mountain Climbers (core e ombros)', 4, 20, 'OMBROS', 31),
('Prancha para Estabilização', 3, 1, 'OMBROS', 31),
('Pistol Squat', 3, 10, 'PERNAS', 31),
('Flexão Diamante', 3, 12, 'BRACOS', 31),
('Elevação de Quadril', 4, 15, 'PERNAS', 31),
('Corrida no Lugar (exercício cardiovascular leve)', 5, 30, 'PERNAS', 31),

-- Exercícios do Treino 32
('Corrida na Esteira substituída por Agachamento', 1, 30, 'PERNAS', 32),
('Pular Corda substituído por Flexão', 3, 100, 'BRACOS', 32),
('Remada Sentada', 4, 12, 'COSTA', 32),
('Supino Inclinado', 3, 10, 'PEITO', 32),
('Agachamento Livre', 4, 12, 'PERNAS', 32),
('Desenvolvimento Militar', 3, 12, 'OMBROS', 32),
('Rosca Direta', 3, 15, 'BRACOS', 32),
('Tríceps Pulley', 3, 12, 'BRACOS', 32),
('Exercício para Ombros substitui Abdominal', 4, 20, 'OMBROS', 32),
('Prancha para Estabilização', 3, 1, 'OMBROS', 32),

-- Exercícios do Treino 33
('Sprints substituído por Agachamento', 6, 20, 'PERNAS', 33),
('Flexão de Braços', 4, 15, 'PEITO', 33),
('Pistol Squat', 3, 10, 'PERNAS', 33),
('Burpees', 4, 15, 'PERNAS', 33),
('Mountain Climbers (core e ombros)', 4, 20, 'OMBROS', 33),
('Prancha para Estabilização', 3, 1, 'OMBROS', 33),
('Rosca Martelo', 3, 15, 'BRACOS', 33),
('Tríceps Testa', 3, 12, 'BRACOS', 33),
('Elevação Lateral', 3, 15, 'OMBROS', 33),
('Remada Curvada', 3, 10, 'COSTA', 33),

-- Exercícios do Treino 34
('Supino Reto', 4, 10, 'PEITO', 34),
('Agachamento Livre', 4, 12, 'PERNAS', 34),
('Remada Curvada', 3, 10, 'COSTA', 34),
('Desenvolvimento de Ombros', 3, 12, 'OMBROS', 34),
('Rosca Direta', 3, 15, 'BRACOS', 34),
('Leg Press', 4, 12, 'PERNAS', 34),
('Puxada na Barra', 3, 10, 'COSTA', 34),
('Elevação Lateral', 3, 15, 'OMBROS', 34),
('Tríceps Pulley', 3, 12, 'BRACOS', 34),
('Exercício para Ombros substitui Abdominal', 4, 20, 'OMBROS', 34),

-- Exercícios do Treino 35
('Desenvolvimento Militar', 4, 12, 'OMBROS', 35),
('Rosca Direta', 3, 15, 'BRACOS', 35),
('Tríceps Testa', 3, 12, 'BRACOS', 35),
('Elevação Lateral', 3, 15, 'OMBROS', 35),
('Supino Reto', 4, 10, 'PEITO', 35),
('Remada Curvada', 3, 10, 'COSTA', 35),
('Agachamento Livre', 4, 12, 'PERNAS', 35),
('Leg Press', 4, 12, 'PERNAS', 35),
('Abdominal Infra substituído por Ombros', 4, 20, 'OMBROS', 35),
('Prancha para Estabilização', 3, 1, 'OMBROS', 35),

-- Exercícios do Treino 36
('Flexão de Braços', 4, 15, 'PEITO', 36),
('Burpees', 4, 15, 'PERNAS', 36),
('Pull-up', 3, 10, 'COSTA', 36),
('Agachamento com Salto', 3, 12, 'PERNAS', 36),
('Mountain Climbers (core e ombros)', 4, 20, 'OMBROS', 36),
('Prancha para Estabilização', 3, 1, 'OMBROS', 36),
('Pistol Squat', 3, 10, 'PERNAS', 36),
('Flexão Diamante', 3, 12, 'BRACOS', 36),
('Elevação de Quadril', 4, 15, 'PERNAS', 36),
('Corrida no Lugar substituído por Pernas', 5, 30, 'PERNAS', 36),

-- Exercícios do Treino 37
('Agachamento Livre', 4, 12, 'PERNAS', 37),
('Leg Press', 4, 12, 'PERNAS', 37),
('Stiff', 4, 12, 'PERNAS', 37),
('Rosca Martelo', 3, 15, 'BRACOS', 37),
('Tríceps Testa', 3, 12, 'BRACOS', 37),
('Remada Unilateral', 3, 10, 'COSTA', 37),
('Crucifixo', 3, 12, 'PEITO', 37),
('Elevação Lateral', 3, 15, 'OMBROS', 37),
('Abdominal Infra substituído por Ombros', 4, 20, 'OMBROS', 37),
('Prancha para Estabilização', 3, 1, 'OMBROS', 37),

-- Exercícios do Treino 38
('Corrida na Esteira substituída por Pernas', 1, 30, 'PERNAS', 38),
('Pular Corda substituído por Braços', 3, 100, 'BRACOS', 38),
('Remada Sentada', 4, 12, 'COSTA', 38),
('Supino Inclinado', 3, 10, 'PEITO', 38),
('Agachamento Livre', 4, 12, 'PERNAS', 38),
('Desenvolvimento Militar', 3, 12, 'OMBROS', 38),
('Rosca Direta', 3, 15, 'BRACOS', 38),
('Tríceps Pulley', 3, 12, 'BRACOS', 38),
('Abdominal Supra substituído por Ombros', 4, 20, 'OMBROS', 38),
('Prancha para Estabilização', 3, 1, 'OMBROS', 38);

INSERT INTO planos_academia (nome, descricao, preco, academia_id) VALUES
-- Academia 81 (Alpha)
('Plano Básico', 'Acesso à academia durante horário comercial, uso das máquinas e vestiário.', 99.90, 81),
('Plano Premium', 'Inclui aulas em grupo, avaliação física e personal trainer opcional.', 179.90, 81),
('Plano VIP', 'Acesso 24h, personal trainer incluso, sauna e área exclusiva.', 299.90, 81),

-- Academia 82 (CrossFit Power)
('Plano Mensal', 'Acesso à academia e aulas coletivas ilimitadas.', 109.90, 82),
('Plano Trimestral', 'Desconto especial para planos trimestrais, acesso completo.', 289.90, 82),
('Plano Anual', 'Melhor preço anual, acesso total e benefícios exclusivos.', 999.90, 82),

-- Academia 83 (Fitness Total)
('Plano Econômico', 'Acesso restrito de segunda a sexta, máquinas e pesos livres.', 79.90, 83),
('Plano Completo', 'Acesso livre, aulas e avaliação física sem custo adicional.', 159.90, 83),
('Plano Executivo', 'Acesso VIP com personal trainer e consultoria nutricional.', 279.90, 83),

-- Academia 84 (Beta)
('Plano Light', 'Ideal para quem quer começar, acesso limitado e aulas básicas.', 89.90, 84),
('Plano Standard', 'Acesso completo e participação em aulas especiais.', 169.90, 84),
('Plano Elite', 'Personal trainer dedicado e área exclusiva para treinos.', 319.90, 84),

-- Academia 85 (CrossFit Elite)
('Plano Bronze', 'Acesso em horário comercial e uso das máquinas.', 95.90, 85),
('Plano Prata', 'Inclui aulas de dança, yoga e avaliação física mensal.', 185.90, 85),
('Plano Ouro', 'Acesso 24h, personal trainer e massagem terapêutica.', 329.90, 85),

-- Academia 86 (Gym Central)
('Plano Starter', 'Para iniciantes, acesso durante a semana e aula de funcional.', 85.90, 86),
('Plano Plus', 'Inclui todas as aulas, sauna e avaliação periódica.', 175.90, 86),
('Plano Pro', 'Acesso VIP com personal trainer e benefícios exclusivos.', 305.90, 86),

-- Academia 87 (Delta)
('Plano Básico', 'Acesso e uso das máquinas durante horário comercial.', 99.90, 87),
('Plano Fitness', 'Aulas coletivas, avaliação física e acesso total.', 180.90, 87),
('Plano Master', 'Personal trainer incluso, acesso VIP e spa.', 320.90, 87),

-- Academia 88 (CrossFit Max)
('Plano Mensal', 'Acesso à academia e aulas ilimitadas.', 110.90, 88),
('Plano Trimestral', 'Melhor preço com acesso total.', 295.90, 88),
('Plano Anual', 'Acesso completo e consultoria especializada.', 1020.90, 88),

-- Academia 89 (Fitness Pro)
('Plano Econômico', 'Acesso restrito, ideal para iniciantes.', 75.90, 89),
('Plano Completo', 'Acesso livre e aulas sem custo adicional.', 165.90, 89),
('Plano Executivo', 'Personal trainer e área VIP.', 280.90, 89),

-- Academia 90 (Omega)
('Plano Light', 'Acesso limitado e aulas básicas.', 90.90, 90),
('Plano Standard', 'Acesso completo e aulas especiais.', 170.90, 90),
('Plano Elite', 'Personal trainer e área exclusiva.', 315.90, 90);
