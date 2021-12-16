BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS example_tag;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS comments;
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

CREATE TABLE comments (
        id SERIAL PRIMARY KEY,
        description VARCHAR(1500),
        example_comment_id BIGINT,
        username VARCHAR(50),
        
        constraint fk_example_comment_id FOREIGN KEY (example_comment_id) REFERENCES examples(example_id) 

);

INSERT INTO languages (id, type) VALUES (DEFAULT, 'JavaScript');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Java');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'SQL');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'C');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'C++');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'PHP');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Perl');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Python 2');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Ruby');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'GO Lang');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Scala');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Bash Shell');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Pascal');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'C#');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Objective C');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Swift');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Groovy');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Fortran');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Hack');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'RUST');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'R Language');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'FREE BASIC');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'YaBasic');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Clojure');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'NodeJS');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Scheme');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'coffeeScript');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Assembler - NASM');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Assembler - GCC');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Rhino JS');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Elixir');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Kotlin');
INSERT INTO languages (id, type) VALUES (DEFAULT, 'Whitespace');

INSERT INTO tags (id, name) VALUES (DEFAULT, 'Loops');
INSERT INTO tags (id, name) VALUES (DEFAULT, 'Branches');
INSERT INTO tags (id, name) VALUES (DEFAULT, 'Arrays');
INSERT INTO tags (id, name) VALUES (DEFAULT, 'Other');

INSERT INTO examples (example_id, title, snippet, language_id, description, image_url) VALUES (DEFAULT, 'Hello World', 'console.log("hello world");', 1, 'A "Hello, World!" is a simple program that prints Hello, World! on the screen. Since it is a very simple program, this program is often used to introduce a new programming language to beginners.', 'https://res.cloudinary.com/syntax-image/image/upload/v1639498307/pzw4aaaxusfthqr5awiv.jpg');
INSERT INTO examples (example_id, title, snippet, language_id, description) VALUES (DEFAULT, 'if Block', 'if (x < 10) {
    x += 1;
}', 1, 'If statement is used to specify a block of JavaScript code to be executed if a condition is true.');
INSERT INTO examples (example_id, title, snippet, language_id, description, image_url) VALUES (DEFAULT, 'for Loop', 'for (let i = 0; i < array.length; i++) {
    array[i] = i * 2;
}', 1, 'Loops can execute a block of code a number of times. Loops are handy, if you want to run the same code over and over again, each time with a different value.', 'https://res.cloudinary.com/syntax-image/image/upload/v1639498639/hq2wc45yzdl9b6uy11sf.png');
INSERT INTO examples (example_id, title, snippet, language_id, description, image_url) VALUES (DEFAULT, 'Array Initialization', 'const array = [1, 2, 3, 4];', 1, 'Arrays are list-like objects whose prototype has methods to perform traversal and mutation operations. Neither the length of a JavaScript array nor the types of its elements are fixed.', 'https://res.cloudinary.com/syntax-image/image/upload/v1639498520/au5kv9c60vu12z6umhod.jpg');
INSERT INTO examples (example_id, title, snippet, language_id, description) VALUES (DEFAULT, 'Hello World', 'System.out.println("hello world");', 2, 'In Java, System.out.println() is a statement which prints the argument passed to it. The println() method display results on the monitor. Usually, a method is invoked by objectname.methodname().');
INSERT INTO examples (example_id, title, snippet, language_id, description) VALUES (DEFAULT, 'if Block', 'if (str.equals("bird")) {
    bird.tweet();
}', 2, 'Use the if statement to specify a block of Java code to be executed if a condition is true.');
INSERT INTO examples (example_id, title, snippet, language_id, description) VALUES (DEFAULT, 'for each Loop', 'for(int num : array) {
    System.out.println(num);
}', 2, 'For-each is another array traversing technique like for loop, while loop, do-while loop introduced in Java5.');
INSERT INTO examples (example_id, title, snippet, language_id, description) VALUES (DEFAULT, 'SELECT', 'SELECT * FROM users;', 3, 'The SELECT statement is used to select data from a database. The data returned is stored in a result table, called the result-set.');
INSERT INTO examples (example_id, title, snippet, language_id, description) VALUES (DEFAULT, 'INSERT', 'INSERT INTO users (id, name) 
VALUES (1, ''John Doe'');', 3, 'The INSERT INTO statement is used to insert new records in a table.');

INSERT INTO example_tag (example_id, tag_id) VALUES (1, 4);
INSERT INTO example_tag (example_id, tag_id) VALUES (2, 2);
INSERT INTO example_tag (example_id, tag_id) VALUES (3, 3);
INSERT INTO example_tag (example_id, tag_id) VALUES (3, 1);
INSERT INTO example_tag (example_id, tag_id) VALUES (4, 3);
INSERT INTO example_tag (example_id, tag_id) VALUES (5, 4);
INSERT INTO example_tag (example_id, tag_id) VALUES (6, 2);
INSERT INTO example_tag (example_id, tag_id) VALUES (7, 1);
INSERT INTO example_tag (example_id, tag_id) VALUES (8, 4);
INSERT INTO example_tag (example_id, tag_id) VALUES (9, 4);\

INSERT INTO users (user_id, username, password_hash, role) VALUES (DEFAULT, Linus Torvalds, $2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC, ROLE_USER);
INSERT INTO users (user_id, username, password_hash, role) VALUES (DEFAULT, Ada Lovelace, $2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC, ROLE_USER);
INSERT INTO users (user_id, username, password_hash, role) VALUES (DEFAULT, Alan Turing, $2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC, ROLE_USER);
INSERT INTO users (user_id, username, password_hash, role) VALUES (DEFAULT, Larry Page, $2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC, ROLE_USER);

INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'Hello Syntax!', 1,'Linus Torvalds');
INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'Hey Linus, how is the Kernal?', 1, 'Ada Lovelace');
INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'This was my first program!', 1, 'Alan Turing');

INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'IF I had a dollar... lol', 2,'Linus Torvalds');
INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'Ha! That was a good one, Linus!', 2, 'Ada Lovelace');
INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'OMG very clever, kudos to you Linus. Clever indeed!', 2, 'Alan Turing');

INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'Oh, I know what these are FOR', 3,'Larry Page');
INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'You are such a nerd, Larry.', 3, 'Ada Lovelace');

INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'You will be surprised at what you can do with a simple array!', 4,'Larry Page');

INSERT INTO comments (id, description, example_comment_id, username) VALUES (DEFAULT, 'A little bird told me...', 6,'Linus Torvalds');


COMMIT TRANSACTION;

