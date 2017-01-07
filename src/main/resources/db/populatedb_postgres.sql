DELETE FROM books;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, salt, role)
VALUES ('Reader', 'reader@onlinelibrary.ru', 'cb6e5c605babf1dbe9dff5b2bff4ae945c1b4dc3fb080b97b8736125b54789a3', 'da8f288c848f5f000e98bc6b2b0e4f6b', 'ROLE_READER');

INSERT INTO users (name, email, password, salt, role)
VALUES ('Librarian', 'librarian@onlinelibrary.ru', 'd18aca8b42c65fe39edc3c376a97e7baccc62bfe418fc63fb2b0dac9c54c01fe', 'f1ad36d5cc7e905f4c1329adecd5b3bc', 'ROLE_LIBRARIAN');

INSERT INTO books (author, title, year, text) VALUES
  ('Шарль Перро', 'Кот в сапогах', 2014, 'Было у мельника три сына, и оставил он им, умирая, всего только мельницу, осла и кота...'),
  ('Шарль Перро', 'Золушка', 2015, 'Жил-был один почтенный и знатный человек. Первая жена его умерла, и он женился во второй раз...'),
  ('Шарль Перро', 'Красная Шапочка', 2016, 'Жила-была маленькая девочка. Была она скромная и добрая, послушная и работящая...'),
  ('Ганс Христиан Андерсен', 'Гадкий утёнок', 2014, 'Хорошо было за городом! Стояло лето. На полях уже золотилась рожь, овес зеленел, сено было смётано в стога...'),
  ('Ганс Христиан Андерсен', 'Дикие лебеди', 2015, 'Далеко-далеко, в той стране, куда улетают от нас на зиму ласточки, жил король...'),
  ('Ганс Христиан Андерсен', 'Дюймовочка', 2016, 'Жила-была женщина; очень ей хотелось иметь ребёнка...'),
  ('Гримм, братья Вильгем и Якоб','Белоснежка и семь гномов', 2014, 'Было то в середине зимы, падали снежинки, точно пух с неба, и сидела королева у окошка..'),
  ('Гримм, братья Вильгем и Якоб','Горшочек каши', 2015, 'Жила-была одна девочка. Пошла девочка в лес за ягодами и встретила там старушку...'),
  ('Гримм, братья Вильгем и Якоб','Бременские музыканты', 2016, 'Много лет тому назад жил на свете мельник. И был у мельника осел...');
