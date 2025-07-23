CREATE TABLE IF NOT EXISTS universidad(
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(100),
  ciudad VARCHAR(100)
);

INSERT INTO universidad (nombre, ciudad) VALUES
  ('Universidad de Cuenca', 'Cuenca'),
  ('Escuela Politécnica Nacional', 'Quito'),
  ('Universidad Técnica de Ambato', 'Ambato'),
  ('Universidad de las Fuerzas Armadas ESPE', 'Sangolquí'),
  ('Universidad del Azuay', 'Cuenca');
