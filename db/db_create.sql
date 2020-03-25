--CREATE TYPE permissions_type AS ENUM('moderator', 'user');
--CREATE TYPE status_type AS ENUM('normal', 'blocked');

CREATE TABLE "user" (
	user_id SERIAL PRIMARY KEY,
	login VARCHAR(32) NOT NULL UNIQUE,
	"password" VARCHAR(512) NOT NULL,
	date_of_registration DATE NOT NULL,
	permissions SMALLINT NOT NULL,
	status SMALLINT NOT NULL DEFAULT 0
);

CREATE TABLE "section" (
	section_id SERIAL PRIMARY KEY,
	title VARCHAR(256) NOT NULL UNIQUE
);

CREATE TABLE topic (
	topic_id SERIAL PRIMARY KEY,
	section_id INTEGER NOT NULL,
	title VARCHAR(256) NOT NULL,
	FOREIGN KEY (section_id) REFERENCES "section" ON DELETE CASCADE
);

CREATE TABLE post (
	post_id SERIAL PRIMARY KEY,
	topic_id INTEGER NOT NULL,
	user_id INTEGER,
	"text" VARCHAR(4096) NOT NULL,
	datetime TIMESTAMP NOT NULL,
	FOREIGN KEY (topic_id) REFERENCES topic ON DELETE CASCADE,
	FOREIGN KEY (user_id) REFERENCES "user" ON DELETE SET NULL
);

CREATE TABLE attachment (
	attachment_id SERIAL PRIMARY KEY,
	post_id INTEGER NOT NULL,
	file_link VARCHAR(4096) NOT NULL,
	FOREIGN KEY (post_id) REFERENCES post ON DELETE CASCADE
);

