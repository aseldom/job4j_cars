create table photo(
    id serial primary key,
    name varchar not null,
    path varchar not null unique,
    auto_post_id int not null references auto_post(id)
);