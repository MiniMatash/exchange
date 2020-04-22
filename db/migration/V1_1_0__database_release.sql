DROP TABLE IF EXISTS users;

CREATE TABLE users (

    id SERIAL PRIMARY KEY,
    username varchar(20),
    token varchar(50)

);

INSERT INTO users (username, token) VALUES ('admin', 'Basic YWRtaW46YWRtaW4=');
INSERT INTO users (username, token) VALUES ('user1', 'user1');
INSERT INTO users (username, token) VALUES ('user2', 'user2');

CREATE TABLE commission (

    id SERIAL PRIMARY KEY,
    from_currency varchar (20),
    to_currency varchar (20),
    commission_pt FLOAT

);

CREATE TABLE rates (

    id SERIAL PRIMARY KEY,
    from_currency varchar (20),
    to_currency varchar (20),
    rate FLOAT

);

