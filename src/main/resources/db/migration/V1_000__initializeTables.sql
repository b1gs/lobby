CREATE SCHEMA IF NOT EXISTS lobby;

CREATE SEQUENCE id_seq START 1 INCREMENT 50;

CREATE TABLE player (
  id bigint NOT NULL,
  username character varying(255) NOT NULL,
  email character varying(255),
  rank INTEGER,
  room_id INTEGER,
  creation_date timestamp with time zone,
  last_login timestamp with time zone,
  CONSTRAINT "player_pk" PRIMARY KEY (id)
);

CREATE TABLE room (
  id bigint NOT NULL,
  room_name character varying(255) NOT NULL,
  capacity INTEGER NOT NULL,
  CONSTRAINT "room_pk" PRIMARY KEY (id)
);