
CREATE TABLE user {

  id bigint NOT NULL,
  username character varying(255) NOT NULL ,
  email character varying(255),
  rank integer,
  creation_date timestamp with time zone,
  last_login timestamp with time zone,
  CONSTRAINT "user_pk" PRIMARY KEY (id)

}


CREATE TABLE room {

  id bigint NOT NULL,
  room_name character varying(255) NOT NULL,
  CONSTRAINT "room_pk" PRIMARY KEY (id)

}