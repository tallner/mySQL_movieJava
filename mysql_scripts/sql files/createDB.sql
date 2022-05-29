drop database if exists movies;
create database if not exists movies;
use movies;

drop table if exists actor;
create table if not exists actor(
	actor_id int auto_increment,
    name varchar(50) not null UNIQUE,
    age varchar(50),
    skill varchar(50),
    primary key (actor_id)
);

drop table if exists director;
create table if not exists director(
	director_id int auto_increment,
    name varchar(50) not null UNIQUE,
    city varchar(50),
    primary key (director_id)
);

drop table if exists genre;
create table if not exists genre(
	genre_id int auto_increment,
    genre varchar(50) not null UNIQUE,
    primary key (genre_id)
);


drop table if exists movie;
create table if not exists movie(
	movie_id int auto_increment,
    director_id int not null,
    title varchar(50) not null,
    release_year int not null,
    length_minutes int not null,
    primary key (movie_id),
    -- foreign key (actor_id) references actor (actor_id),
    foreign key (director_id) references director (director_id)
    -- foreign key (genre_id) references genre (genre_id)
);

drop table if exists movie_actor;
create table if not exists movie_actor(
    character_name varchar(50),
    movie_id int not null,
    actor_id int not null,
    foreign key (movie_id) references movie (movie_id),
    foreign key (actor_id) references actor (actor_id)
);

drop table if exists movie_genre;
create table if not exists movie_genre(
    movie_id int not null,
    genre_id int not null,
    foreign key (movie_id) references movie (movie_id),
    foreign key (genre_id) references genre (genre_id)
);
