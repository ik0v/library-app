create table if not exists book_event(
    event_id bigint primary key,
    date timestamp,
    book_id bigint not null,
    patron_id bigint,
    type varchar(50)
);

create sequence event_seq;