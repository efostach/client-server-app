INSERT INTO USERS VALUES (1, 'admin', 'admin');
INSERT INTO USERS VALUES (2, 'moderator', 'moderator');
INSERT INTO USERS VALUES (3, 'user', 'user');

INSERT INTO ROLES VALUES (1,'ROLE_ADMIN');
INSERT INTO ROLES VALUES (2,'ROLE_MODERATOR');
INSERT INTO ROLES VALUES (3,'ROLE_USER');

INSERT INTO USER_ROLES VALUES (1,1);
INSERT INTO USER_ROLES VALUES (2,2);
INSERT INTO USER_ROLES VALUES (3,3);