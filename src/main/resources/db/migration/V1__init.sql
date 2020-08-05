drop table if exists captcha_codes;
drop table if exists global_settings;
drop table if exists post_comments;
drop table if exists post_votes;
drop table if exists posts;
drop table if exists tag2post;
drop table if exists tags;
drop table if exists users;

create table captcha_codes (
    id integer not null auto_increment,
    code tinytext not null,
    secret_code tinytext not null,
    time datetime not null,
    primary key (id)
);

create table global_settings (
    id integer not null auto_increment,
    code varchar(255),
    name varchar(255),
    value varchar(255),
    primary key (id)
);

create table post_comments (
    id integer not null auto_increment,
    text text not null,
    time datetime not null,
    parent_id integer,
    post_id integer not null,
    user_id integer not null,
    primary key (id)
);

create table post_votes (
    id integer not null auto_increment,
    time datetime not null,
    value TINYINT not null,
    post_id integer not null,
    user_id integer not null,
    primary key (id)
);

create table posts (
    id integer not null auto_increment,
    is_active TINYINT not null,
    moderation_status enum('NEW', 'ACCEPTED', 'DECLINED') not null,
    text text not null,
    time datetime not null,
    title varchar(255) not null,
    view_count integer not null,
    user_id integer not null,
    moderator_id integer,
    primary key (id)
);

create table tag2post (
    id integer not null auto_increment,
    post_id integer not null,
    tag_id integer not null,
    primary key (id)
);

create table tags (
    id integer not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

create table users (
    id integer not null auto_increment,
    code varchar(255),
    email varchar(255) not null,
    is_moderator TINYINT not null,
    name varchar(255) not null,
    password varchar(255) not null,
    photo text,
    reg_time datetime not null,
    primary key (id)
);

alter table post_comments add constraint fk_post_comments_parent_id foreign key (parent_id) references post_comments (id);
alter table post_comments add constraint fk_post_comments_post_id foreign key (post_id) references posts (id);
alter table post_comments add constraint fk_post_comments_user_id foreign key (user_id) references users (id);
alter table post_votes add constraint fk_post_votes_post_id foreign key (post_id) references posts (id);
alter table post_votes add constraint fk_post_votes_user_id foreign key (user_id) references users (id);
alter table posts add constraint fk_posts_user_id foreign key (user_id) references users (id);
alter table posts add constraint fk_posts_moderator_id foreign key (moderator_id) references users (id);
alter table tag2post add constraint fk_tag2post_post_id foreign key (post_id) references posts (id);
alter table tag2post add constraint fk_tag2post_tag_id foreign key (tag_id) references tags (id);