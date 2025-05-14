ALTER TABLE dieta ADD COLUMN tipo_dieta VARCHAR(50);

INSERT INTO dieta (titulo, descricao, calorias, objetivo, user_acad_id, tipo_dieta)
VALUES
('Dieta Proteica', 'Foco em alto consumo de proteínas para hipertrofia', 3200, 'BULKING', 1, 'BULKING'),
('Dieta Low Carb', 'Redução de carboidratos para perda de gordura', 1800, 'CUTTING', 1, 'CUTTING'),
('Dieta Cetogênica', 'Dieta com foco em gordura como fonte principal de energia', 2000, 'CUTTING', 1, 'CUTTING'),
('Dieta Mediterrânea', 'Equilíbrio de alimentos naturais e saudáveis', 2500, 'BULKING', 1, 'BULKING'),
('Dieta Vegana', 'Foco em alimentos de origem vegetal', 2200, 'BULKING', 1, 'BULKING');
