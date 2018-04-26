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