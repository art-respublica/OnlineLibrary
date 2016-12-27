DELETE FROM books;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, role)
VALUES ('Reader', 'reader@onlinelibrary.ru', 'password', 'ROLE_READER');

INSERT INTO users (name, email, password, role)
VALUES ('Librarian', 'librarian@onlinelibrary.ru', 'admin', 'ROLE_LIBRARIAN');

INSERT INTO books (author, title, year) VALUES
  ('Шарль Перро', 'Кот в сапогах', 2014),
  ('Шарль Перро', 'Золушка', 2015),
  ('Шарль Перро', 'Красная Шапочка', 2016),
  ('Ганс Христиан Андерсен', 'Гадкий утёнок', 2014),
  ('Ганс Христиан Андерсен', 'Дикие лебеди', 2015),
  ('Ганс Христиан Андерсен', 'Дюймовочка', 2016),
  ('Гримм, Вильгем и Якоб, братья','Белоснежка и семь гномов', 2014),
  ('Гримм, Вильгем и Якоб, братья','Горшочек каши', 2015),
  ('Гримм, Вильгем и Якоб, братья','Бременские музыканты', 2016);
