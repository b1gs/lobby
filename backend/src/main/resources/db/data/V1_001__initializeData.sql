INSERT INTO player(
            id, username, email, rank, password, creation_date, last_login)
    VALUES (nextval('id_seq'), 'admin','admin@b1gs.com', 0, '123', current_date, current_date);

INSERT INTO player(
            id, username, email, rank, password, creation_date, last_login)
    VALUES (nextval('id_seq'), 'b1gs','b1gsmen@b1gs', 0, '123', current_date, current_date);

INSERT INTO player(
            id, username, email, rank, password, creation_date, last_login)
    VALUES (nextval('id_seq'), 'toxic','toxic@b1gs', 0, '123', current_date, current_date);

INSERT INTO player(
            id, username, email, rank, password, creation_date, last_login)
    VALUES (nextval('id_seq'), 'fred','fred@b1gs', 0, '123', current_date, current_date);

INSERT INTO player(
            id, username, email, rank, password, creation_date, last_login)
    VALUES (nextval('id_seq'), 'albert','albert@b1gs', 0, '123', current_date, current_date);

INSERT INTO player(
            id, username, email, rank, password, creation_date, last_login)
    VALUES (nextval('id_seq'), 'eddy','eddy@b1gs', 0, '123', current_date, current_date);


INSERT INTO room(
            id, room_name, capacity)
    VALUES (nextval('id_seq'), 'DefaultRoom1' ,4);

INSERT INTO room(
            id, room_name, capacity)
    VALUES (nextval('id_seq'), 'DefaultRoom2', 4);

INSERT INTO room(
            id, room_name, capacity)
    VALUES (nextval('id_seq'), 'DefaultRoom2', 4);

-- CARD CONSTANTS CLUBS
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'TWO', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'THREE', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'FOUR', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'FIVE', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'SIX', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'SEVEN', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'EIGHT', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'NINE', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'TEN', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'JACK', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'QUEEN', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'KING', 'CLUBS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'ACE', 'CLUBS' );

-- CARD CONSTANTS DIAMONDS
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'TWO', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'THREE', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'FOUR', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'FIVE', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'SIX', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'SEVEN', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'EIGHT', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'NINE', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'TEN', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'JACK', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'QUEEN', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'KING', 'DIAMONDS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'ACE', 'DIAMONDS' );


-- CARD CONSTANTS HEARTS
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'TWO', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'THREE', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'FOUR', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'FIVE', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'SIX', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'SEVEN', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'EIGHT', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'NINE', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'TEN', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'JACK', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'QUEEN', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'KING', 'HEARTS' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'ACE', 'HEARTS' );


-- CARD CONSTANTS SPADES
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'TWO', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'THREE', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'FOUR', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'FIVE', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'SIX', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'SEVEN', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'EIGHT', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'NINE', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'TEN', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'JACK', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'QUEEN', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'KING', 'SPADES' );
INSERT INTO card(id, rank, suit)
    VALUES (nextval('id_seq'), 'ACE', 'SPADES' );