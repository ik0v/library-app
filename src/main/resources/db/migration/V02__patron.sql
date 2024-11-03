create table if not exists patron(
    patron_id bigint primary key,
    first_name varchar(50),
    last_name varchar(50),
    join_date timestamp
);

create sequence patron_seq;