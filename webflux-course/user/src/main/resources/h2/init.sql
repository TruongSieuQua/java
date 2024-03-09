create table users (
    id bigint auto_increment,
    name varchar(50),
    balance int,
    primary key (id)
);

create table user_transaction(
    id bigint auto_increment,
    userId bigint,
    amount int,
    transaction_date timestamp,
    foreign key (userId) references users (id) on delete cascade,
    primary key (id)
);

insert into users
    (name, balance)
    values
    ('sam', 1000),
    ('mike', 1200),
    ('jake', 800),
    ('marshal', 2000);