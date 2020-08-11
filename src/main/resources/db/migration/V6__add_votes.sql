insert into users
(id, is_moderator, reg_time, name, email, password, code, photo)
values(2, 0, now(), "User2", "user2mail.ru", "ABC", NULL, NULL);

insert into post_votes
(id, time, value, post_id, user_id)
values (1, now(), 1, 1, 1);

insert into post_votes
(id, time, value, post_id, user_id)
values (2, now(), 0, 1, 2);

insert into post_votes
(id, time, value, post_id, user_id)
values (3, now(), 1, 2, 2);

insert into post_votes
(id, time, value, post_id, user_id)
values (4, now(), 1, 2, 1);