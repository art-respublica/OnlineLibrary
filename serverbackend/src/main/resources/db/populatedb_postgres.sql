DELETE FROM books;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, salt, version)
VALUES ('Reader', 'reader@onlinelibrary.ru', '11f1373fb7075096ce945fe559ad9e9586c51fce572064a6a3d39ece049b7273', '796ebb52630c2b42ea3bbee29596a9c6', 0);

INSERT INTO users (name, email, password, salt, version)
VALUES ('Librarian', 'librarian@onlinelibrary.ru', 'd18aca8b42c65fe39edc3c376a97e7baccc62bfe418fc63fb2b0dac9c54c01fe', 'f1ad36d5cc7e905f4c1329adecd5b3bc', 0);

INSERT INTO user_roles (user_id, email, role)
VALUES (100000, 'reader@onlinelibrary.ru', 'ROLE_USER');
INSERT INTO user_roles (user_id, email, role)
VALUES (100001, 'librarian@onlinelibrary.ru', 'ROLE_USER');
INSERT INTO user_roles (user_id, email, role)
VALUES (100001, 'librarian@onlinelibrary.ru', 'ROLE_ADMIN');

INSERT INTO books (author, title, year, text, version) VALUES
  ('Шарль Перро', 'Кот в сапогах', 2014, 'Было у мельника три сына, и оставил он им, умирая, всего только мельницу, осла и кота...', 0),
  ('Шарль Перро', 'Золушка', 2015, 'Жил-был один почтенный и знатный человек. Первая жена его умерла, и он женился во второй раз...', 0),
  ('Шарль Перро', 'Красная Шапочка', 2016, 'Жила-была маленькая девочка. Была она скромная и добрая, послушная и работящая...', 0),
  ('Ганс Христиан Андерсен', 'Гадкий утёнок', 2014, 'Хорошо было за городом! Стояло лето. На полях уже золотилась рожь, овес зеленел, сено было смётано в стога...', 0),
  ('Ганс Христиан Андерсен', 'Дикие лебеди', 2015, 'Далеко-далеко, в той стране, куда улетают от нас на зиму ласточки, жил король...', 0),
  ('Ганс Христиан Андерсен', 'Дюймовочка', 2016, 'Жила-была женщина; очень ей хотелось иметь ребёнка...', 0),
  ('Гримм, братья Вильгем и Якоб','Белоснежка и семь гномов', 2014, 'Было то в середине зимы, падали снежинки, точно пух с неба, и сидела королева у окошка..', 0),
  ('Гримм, братья Вильгем и Якоб','Горшочек каши', 2015, 'Жила-была одна девочка. Пошла девочка в лес за ягодами и встретила там старушку...', 0),
  ('Гримм, братья Вильгем и Якоб','Бременские музыканты', 2016, 'Много лет тому назад жил на свете мельник. И был у мельника осел...', 0);
