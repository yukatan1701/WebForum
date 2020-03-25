INSERT INTO "user"(login, "password", date_of_registration, permissions) VALUES('admin', md5('moder'), '2015-01-01', 0);
INSERT INTO "user"(login, "password", date_of_registration, permissions) VALUES('vasya777', md5('vasya'), '2015-01-01', 1);
INSERT INTO "user"(login, "password", date_of_registration, permissions) VALUES('medved', md5('preved'), '2015-01-10', 1);
INSERT INTO "user"(login, "password", date_of_registration, permissions) VALUES('moder', md5('moder123'), '2015-02-26', 0);
INSERT INTO "user"(login, "password", date_of_registration, permissions, status) VALUES('belarus', md5('traktor'), '2016-03-18', 1, 1);
INSERT INTO "user"(login, "password", date_of_registration, permissions) VALUES('finland', md5('helsinki'), '2017-04-24', 1);

INSERT INTO "section"(title) VALUES('Музыка');
INSERT INTO "section"(title) VALUES('Фильмы');
INSERT INTO "section"(title) VALUES('Книги');
INSERT INTO "section"(title) VALUES('Игры');
INSERT INTO "section"(title) VALUES('Кухня');

INSERT INTO topic(section_id, title) VALUES(1, 'Рок, метал и смежное');
INSERT INTO topic(section_id, title) VALUES(1, 'Отечественный шансон');
INSERT INTO topic(section_id, title) VALUES(2, 'Зарубежное кино');
INSERT INTO topic(section_id, title) VALUES(2, 'Российское кино');
INSERT INTO topic(section_id, title) VALUES(3, 'Фантастика');
INSERT INTO topic(section_id, title) VALUES(3, 'Классика');
INSERT INTO topic(section_id, title) VALUES(4, 'Шутеры');
INSERT INTO topic(section_id, title) VALUES(4, 'Action-adventure');
INSERT INTO topic(section_id, title) VALUES(5, 'Кухня народов мира');
INSERT INTO topic(section_id, title) VALUES(5, 'Русская кухня');

INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(1, 1, 'Открыта тема по року, металлу и смежным направлениям.', '2015-05-05 00:05:04');
INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(1, 2, 'Давно ждал чего-то подобного. Рэп - это кал, слушай металл! Хэви метал в массы!', '2015-05-05 00:05:04');
INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(1, 3, 'О, митолизды, вы еще живы? 2015 год на дворе.', '2015-05-06 00:10:01');
	
INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(2, 1, 'Открыта тема русскому шансону.', '2015-06-01 00:12:54');
INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(2, 3, 'Владимирский сервак, вечер в серверной. Нас ДДОС-ят из Твери, пинг немеренный.', '2015-06-02 00:13:00');
INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(2, 4, 'Опять прописан чей-то шлюз.', '2015-05-05 00:05:04');
	
INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(8, 1, 'Обсуждаем жанр Action-Adventure.', '2015-06-01 00:12:54');
INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(8, 5, 'Assassins Creed III отстой. Без Эцио уже не торт.', '2015-06-02 00:13:00');
INSERT INTO post(topic_id, user_id, "text", datetime)
	VALUES(8, 6, 'Имхо, Tomb Raider Legend топ игра в своем жанре.', '2015-05-05 00:05:04');
	
INSERT INTO attachment(post_id, file_link) VALUES(6, 'krug.jpg');
INSERT INTO attachment(post_id, file_link) VALUES(8, 'assassin.jpg');
INSERT INTO attachment(post_id, file_link) VALUES(2, 'metallica.mp3');

