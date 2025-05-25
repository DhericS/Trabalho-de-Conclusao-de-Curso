INSERT INTO user_academia (name, email, senha, telefone, imagem_url, role) VALUES
('Ana Paula', 'ana.paula1@example.com', '123456', '11988880001', NULL, 'USERACAD'),
('Bruno Oliveira', 'bruno.oliveira2@example.com', '123456', '11988880002', NULL, 'USERACAD'),
('Camila Ferreira', 'camila.ferreira3@example.com', '123456', '11988880003', NULL, 'USERACAD'),
('Daniel Souza', 'daniel.souza4@example.com', '123456', '11988880004', NULL, 'USERACAD'),
('Elisa Martins', 'elisa.martins5@example.com', '123456', '11988880005', NULL, 'USERACAD'),
('Fábio Santos', 'fabio.santos6@example.com', '123456', '11988880006', NULL, 'USERACAD'),
('Gabriela Lima', 'gabriela.lima7@example.com', '123456', '11988880007', NULL, 'USERACAD'),
('Henrique Rocha', 'henrique.rocha8@example.com', '123456', '11988880008', NULL, 'USERACAD'),
('Isabela Costa', 'isabela.costa9@example.com', '123456', '11988880009', NULL, 'USERACAD'),
('João Pedro', 'joao.pedro10@example.com', '123456', '11988880010', NULL, 'USERACAD');


INSERT INTO user_admin (name, email, senha, telefone, role) VALUES
('Admin Carlos', 'admin.carlos1@example.com', '123456', '11990000001', 'USERADMIN'),
('Admin Fernanda', 'admin.fernanda2@example.com', '123456', '11990000002', 'USERADMIN'),
('Admin João', 'admin.joao3@example.com', '123456', '11990000003', 'USERADMIN'),
('Admin Mariana', 'admin.mariana4@example.com', '123456', '11990000004', 'USERADMIN'),
('Admin Ricardo', 'admin.ricardo5@example.com', '123456', '11990000005', 'USERADMIN'),
('Admin Patrícia', 'admin.patricia6@example.com', '123456', '11990000006', 'USERADMIN'),
('Admin Eduardo', 'admin.eduardo7@example.com', '123456', '11990000007', 'USERADMIN'),
('Admin Aline', 'admin.aline8@example.com', '123456', '11990000008', 'USERADMIN'),
('Admin Marcelo', 'admin.marcelo9@example.com', '123456', '11990000009', 'USERADMIN'),
('Admin Larissa', 'admin.larissa10@example.com', '123456', '11990000010', 'USERADMIN');

INSERT INTO academias (nome, endereco, telefone, tipo_acad, imagem_url) VALUES
('Academia Alpha', 'Rua A, 100', '11999990001', 'CONVENCIONAL', NULL),
('CrossFit Power', 'Av. B, 200', '11999990002', 'CROSSFIT', NULL),
('Fitness Total', 'Rua C, 300', '11999990003', 'CONVENCIONAL', NULL),
('Academia Beta', 'Av. D, 400', '11999990004', 'CONVENCIONAL', NULL),
('CrossFit Elite', 'Rua E, 500', '11999990005', 'CROSSFIT', NULL),
('Gym Central', 'Av. F, 600', '11999990006', 'CONVENCIONAL', NULL),
('Academia Delta', 'Rua G, 700', '11999990007', 'CONVENCIONAL', NULL),
('CrossFit Max', 'Av. H, 800', '11999990008', 'CROSSFIT', NULL),
('Fitness Pro', 'Rua I, 900', '11999990009', 'CONVENCIONAL', NULL),
('Academia Omega', 'Av. J, 1000', '11999990010', 'CONVENCIONAL', NULL);




INSERT INTO dieta (titulo, descricao, calorias, objetivo, user_acad_id, personal_id, tipo_dieta) VALUES
('Dieta de Crescimento Muscular Avançada',
 'Refeições diárias:\n1) Café da manhã: Omelete com 4 ovos, aveia e banana.\n2) Almoço: Peito de frango grelhado, arroz integral, brócolis e azeite.\n3) Lanche da tarde: Iogurte natural com amêndoas e mel.\n4) Jantar: Salmão grelhado, quinoa e legumes cozidos.',
 3200, 'BULKING', NULL, 51, 'BULKING'),

('Plano de Corte para Definição Rápida',
 'Refeições diárias:\n1) Café da manhã: Shake de proteína com morangos e chia.\n2) Almoço: Filé de peixe, salada verde com azeite e batata doce.\n3) Lanche da tarde: Maçã com pasta de amendoim natural.\n4) Jantar: Frango ao vapor com abobrinha e cenoura.',
 1800, 'CUTTING', NULL, 52, 'CUTTING'),

('Dieta Equilibrada para Ganho de Massa',
 'Refeições diárias:\n1) Café da manhã: Panqueca de aveia com mel e frutas vermelhas.\n2) Almoço: Carne magra, arroz integral, feijão e salada mista.\n3) Lanche da tarde: Sanduíche de pão integral com queijo cottage.\n4) Jantar: Tilápia assada, batata doce e espinafre.',
 3000, 'BULKING', NULL, 53, 'BULKING'),

('Programa de Redução de Gordura Corporal',
 'Refeições diárias:\n1) Café da manhã: Chá verde, 2 fatias de pão integral com abacate.\n2) Almoço: Frango grelhado, salada de rúcula, tomate e pepino.\n3) Lanche da tarde: Mix de castanhas e frutas secas.\n4) Jantar: Omelete com legumes variados.',
 1700, 'CUTTING', NULL, 54, 'CUTTING'),

('Dieta Flexível para Hipertrofia',
 'Refeições diárias:\n1) Café da manhã: Iogurte grego, granola e frutas frescas.\n2) Almoço: Carne vermelha magra, arroz integral e legumes salteados.\n3) Lanche da tarde: Barra proteica caseira.\n4) Jantar: Peito de peru, purê de batata doce e brócolis.',
 3100, 'BULKING', NULL, 55, 'BULKING'),

('Plano Nutricional para Emagrecimento Saudável',
 'Refeições diárias:\n1) Café da manhã: Suco verde detox e 1 tapioca com chia.\n2) Almoço: Peixe assado, quinoa e salada colorida.\n3) Lanche da tarde: Iogurte natural com frutas vermelhas.\n4) Jantar: Sopa de legumes com frango desfiado.',
 1600, 'CUTTING', NULL, 56, 'CUTTING'),

('Dieta Alta em Proteínas para Ganho de Massa',
 'Refeições diárias:\n1) Café da manhã: Omelete com claras, espinafre e tomate.\n2) Almoço: Frango grelhado, arroz integral, feijão preto e salada.\n3) Lanche da tarde: Shake de whey protein com banana.\n4) Jantar: Tilápia assada, batata doce e brócolis.',
 3300, 'BULKING', NULL, 57, 'BULKING'),

('Plano Detox com Redução Calórica',
 'Refeições diárias:\n1) Café da manhã: Suco detox de couve, maçã e gengibre.\n2) Almoço: Salada de folhas verdes, tomate, pepino e peito de frango.\n3) Lanche da tarde: Maçã verde.\n4) Jantar: Caldo detox com legumes variados.',
 1500, 'CUTTING', NULL, 58, 'CUTTING'),

('Dieta de Manutenção com Ênfase em Energia',
 'Refeições diárias:\n1) Café da manhã: Pão integral, ovo mexido e café.\n2) Almoço: Carne magra, arroz integral, feijão e salada.\n3) Lanche da tarde: Mix de castanhas.\n4) Jantar: Frango assado, purê de batata e legumes.',
 2500, 'BULKING', NULL, 59, 'BULKING'),

('Programa Nutricional para Definição Muscular',
 'Refeições diárias:\n1) Café da manhã: Iogurte grego, granola sem açúcar e frutas.\n2) Almoço: Salmão grelhado, quinoa e salada verde.\n3) Lanche da tarde: Palitos de cenoura e homus.\n4) Jantar: Omelete com legumes variados.',
 1700, 'CUTTING', NULL, 60, 'CUTTING');


INSERT INTO treinos (nome, descricao, personal_id) VALUES
('Treino Full Body Intenso', 'Treino focado em todos os grupos musculares com alta intensidade.', 51),
('Treino Foco em Pernas', 'Exercícios para hipertrofia e resistência das pernas.', 52),
('Treino CrossFit Funcional', 'Treino funcional estilo CrossFit para condicionamento geral.', 53),
('Treino Resistência e Cardio', 'Treino com ênfase em resistência muscular e cardio.', 54),
('Treino HIIT para Definição', 'Treino intervalado para perda de gordura e definição.', 55),
('Treino de Força e Massa', 'Treino para ganho de força com foco em musculação.', 56),
('Treino Ombros e Braços', 'Exercícios voltados para membros superiores.', 57),
('Treino Calistenia Avançada', 'Treino com exercícios de peso corporal avançados.', 58),
('Treino Funcional Completo', 'Treino funcional para condicionamento total.', 59),
('Treino Cardiovascular e Core', 'Treino focado em cardio e fortalecimento do core.', 60);

