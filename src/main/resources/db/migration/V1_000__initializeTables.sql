CREATE SCHEMA IF NOT EXISTS lobby;

CREATE SEQUENCE id_seq START 1 INCREMENT 50;

CREATE TABLE player (
  id bigint NOT NULL,
  username CHARACTER VARYING (255) NOT NULL,
  email CHARACTER VARYING(255),
  rank INTEGER,
  room_id INTEGER,
  password CHARACTER VARYING(32) NOT NULL,
  is_ready boolean NOT NULL DEFAULT false,
  creation_date TIMESTAMP WITH TIME ZONE,
  last_login TIMESTAMP WITH TIME ZONE,
  CONSTRAINT "player_pk" PRIMARY KEY (id)
);

CREATE TABLE room (
  id bigint NOT NULL,
  room_name CHARACTER VARYING(255) NOT NULL,
  capacity INTEGER NOT NULL,
  CONSTRAINT "room_pk" PRIMARY KEY (id)
);

CREATE TABLE game (
  id bigint NOT NULL,
  creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
  game_status CHARACTER VARYING(255),
  game_type CHARACTER VARYING(255),
  first_turn_player_id char(1),
  room_id bigint NOT NULL,
  CONSTRAINT "game_pk" PRIMARY KEY (id)
)

CREATE TABLE turn {
  id bigint NOT NULL,
  rank CHARACTER VARYING(255) NOT NULL ,
  suit CHARACTER VARYING(255) NOT NULL,
  game_id bigint NOT NULL,
  player_id bigint NOT NULL,
  CONSTRAINT "turn_pk" PRIMARY KEY (id)
}