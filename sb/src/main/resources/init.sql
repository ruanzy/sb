create table if not exists users(
  id integer GENERATED BY DEFAULT AS IDENTITY(START WITH 1),
  name varchar(40) NOT NULL,
  age integer NOT NULL
);
INSERT INTO users VALUES (1, 'linsen', 18);  
INSERT INTO users VALUES (2, 'sam', 19);  