CREATE SCHEMA IF NOT EXISTS lobby;

CREATE SEQUENCE id_seq START 1 INCREMENT 50;

CREATE TABLE player (
  id bigint NOT NULL,
  username CHARACTER VARYING (255) NOT NULL,
  email CHARACTER VARYING(255),
  rank INTEGER,
  room_id INTEGER,
  password CHARACTER VARYING(32) NOT NULL,
  is_ready boolean NOT NULL DEFAULT TRUE,
  creation_date TIMESTAMP WITH TIME ZONE,
  last_login TIMESTAMP WITH TIME ZONE,
  CONSTRAINT "player_pk" PRIMARY KEY (id)
);

CREATE TABLE room (
  id bigint NOT NULL,
  room_name CHARACTER VARYING(255) NOT NULL,
  capacity INTEGER NOT NULL,
  owner bigint,
  CONSTRAINT "room_pk" PRIMARY KEY (id)
);

CREATE TABLE game (
  id bigint NOT NULL,
  creation_date TIMESTAMP WITH TIME ZONE NOT NULL,
  game_status CHARACTER VARYING(255),
  game_type CHARACTER VARYING(255),
  current_turn_number bigint,
  room_id bigint NOT NULL,
  CONSTRAINT "game_pk" PRIMARY KEY (id)
--   ,
--   CONSTRAINT fk_player FOREIGN KEY (first_turn_player_id)
--       REFERENCES lobby.player (id) MATCH SIMPLE
--       ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE move (
  id bigint NOT NULL,
  rank CHARACTER VARYING(255) NOT NULL ,
  suit CHARACTER VARYING(255) NOT NULL,
  game_id bigint NOT NULL,
  player_id bigint NOT NULL,
  CONSTRAINT "turn_pk" PRIMARY KEY (id),
  CONSTRAINT fk_game FOREIGN KEY (game_id)
      REFERENCES lobby.game (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_player FOREIGN KEY (player_id)
      REFERENCES lobby.player (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE card (
  id bigint NOT NULL,
  rank CHARACTER VARYING (255) NOT NULL,
  suit CHARACTER VARYING (255) NOT NULL,
  CONSTRAINT "card_pk" PRIMARY KEY (id)
);

CREATE TABLE player_card (
  player_id bigint NOT NULL ,
  card_id bigint NOT NULL ,
  PRIMARY KEY (player_id,card_id),
  CONSTRAINT fk_player FOREIGN KEY (player_id)
      REFERENCES lobby.player (id) MATCH SIMPLE,
  CONSTRAINT fk_card FOREIGN KEY (card_id)
      REFERENCES lobby.card (id) MATCH SIMPLE
);

CREATE TABLE game_player (
  player_id bigint NOT NULL,
  game_id bigint NOT NULL,
  player_turn_number bigint NOT NULL,
  PRIMARY KEY (player_id,game_id),
  CONSTRAINT fk_player FOREIGN KEY (player_id)
      REFERENCES lobby.player (id) MATCH SIMPLE,
  CONSTRAINT fk_game FOREIGN KEY (game_id)
      REFERENCES lobby.game (id) MATCH SIMPLE
);