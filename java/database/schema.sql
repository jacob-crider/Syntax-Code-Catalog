BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS example_tag;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS examples;
DROP TABLE IF EXISTS languages;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE languages (
      id SERIAL PRIMARY KEY,
      type VARCHAR(32) NOT NULL,
      is_deleted BOOLEAN DEFAULT false
);

CREATE TABLE examples (
        example_id SERIAL PRIMARY KEY,
        title VARCHAR(80) NOT NULL,
        snippet VARCHAR(1000) NOT NULL,
        language_id bigint,
        description VARCHAR(1000),
        username VARCHAR(64),
        is_public BOOLEAN DEFAULT FALSE,
        image_url VARCHAR(150),

        constraint fk_language_id foreign key (language_id) references languages(id)
);

CREATE TABLE tags (
      id SERIAL PRIMARY KEY,
      name VARCHAR(120) NOT NULL
);

CREATE TABLE example_tag (
        example_id bigint,
        tag_id bigint,
        
        constraint fk_example_id foreign key (example_id) references examples(example_id),
        constraint fk_tag_id foreign key (tag_id) references tags(id),
        constraint pk_example_tag primary key (example_id, tag_id)
);

INSERT INTO languages (id, type) VALUES (DEFAULT, 'JavaScript');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Java');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'SQL');

INSERT INTO tags (id, name) VALUES (DEFAULT, 'Loops');
INSERT INTO tags (id, name) VALUES (DEFAULT, 'Branches');
INSERT INTO tags (id, name) VALUES (DEFAULT, 'Arrays');
INSERT INTO tags (id, name) VALUES (DEFAULT, 'Other');

INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'Hello World', 'console.log("hello world");', 1);
INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'if Block', 'if (x < 10) {
    x += 1;
}', 1);
INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'for Loop', 'for (let i = 0; i < array.length; i++) {
    array[i] = i * 2;
}', 1);
INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'Array Initialization', 'const array = [1, 2, 3, 4];', 1);
INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'Hello World', 'System.out.println("hello world");', 2);
INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'if Block', 'if (str.equals("bird") {
    bird.tweet();
}', 2);
INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'forEach Loop', 'for(int num : array) {
    System.out.println(num);
}', 2);
INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'SELECT', 'SELECT * FROM users;', 3);
INSERT INTO examples (example_id, title, snippet, language_id) VALUES (DEFAULT, 'INSERT', 'INSERT INTO users (id, name) 
VALUES (1, ''John Doe'');', 3);

INSERT INTO example_tag (example_id, tag_id) VALUES (1, 4);
INSERT INTO example_tag (example_id, tag_id) VALUES (2, 2);
INSERT INTO example_tag (example_id, tag_id) VALUES (3, 3);
INSERT INTO example_tag (example_id, tag_id) VALUES (3, 1);
INSERT INTO example_tag (example_id, tag_id) VALUES (4, 3);
INSERT INTO example_tag (example_id, tag_id) VALUES (5, 4);
INSERT INTO example_tag (example_id, tag_id) VALUES (6, 2);
INSERT INTO example_tag (example_id, tag_id) VALUES (7, 1);
INSERT INTO example_tag (example_id, tag_id) VALUES (8, 4);
INSERT INTO example_tag (example_id, tag_id) VALUES (9, 4);

COMMIT TRANSACTION;
