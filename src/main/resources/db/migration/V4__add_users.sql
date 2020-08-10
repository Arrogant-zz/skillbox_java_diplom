insert into users
(id, is_moderator, reg_time, name, email, password, code, photo)
values(1, 0, now(), "User1", "user1@mail.ru", "ABC", NULL, NULL);

insert into posts
(id, is_active, moderation_status, moderator_id, user_id, time, title, text, view_count)
values (1, 1, "ACCEPTED", NULL, 1, now(), "Пост 1", "Очень интересный пост", 10);

insert into posts
(id, is_active, moderation_status, moderator_id, user_id, time, title, text, view_count)
values (2, 1, "ACCEPTED", NULL, 1, now(), "Пост 2", "Очень интересный пост", 10);
