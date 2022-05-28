use movies;

-- adding actors
insert into actor (name, age, skill) values ('Hedvig', '78', 'dancing');
insert into actor (name, age, skill) values ('Johan', '43', 'singing');
insert into actor (name, age, skill) values ('Kim', '25', 'climbing');
insert into actor (name, age, skill) values ('Bertil', '105', 'languanges');
insert into actor (name, age, skill) values ('Sean', '9', 'animals');
insert into actor (name, age, skill) values ('Tina', '43', 'strong');
insert into actor (name, age, skill) values ('Xiao', '53', 'fighting');
insert into actor (name, age, skill) values ('Liu', '43', 'smart');
insert into actor (name, age, skill) values ('Anders', '51', 'carpenting');
insert into actor (name, age, skill) values ('Linnea', '15', 'flying');

-- adding directors
insert into director (name, city) values ('JohnC', 'LA');
insert into director (name, city) values ('Sylvester', 'Mexico City');
insert into director (name, city) values ('Mike','NY');
insert into director (name, city) values ('Christian', 'Flyinge');
insert into director (name, city) values ('Adam', 'Hell');
insert into director (name, city) values ('Belinda','Heaven');
insert into director (name, city) values ('Alberto', 'LA');
insert into director (name, city) values ('Ingmar', 'Mora');
insert into director (name, city) values ('Ronaldo','NY');
insert into director (name, city) values ('Tieru', 'Sao Paolo');

-- adding genres
insert into genre (genre) values ('comedy');
insert into genre (genre) values ('horror');
insert into genre (genre) values ('action');
insert into genre (genre) values ('drama');
insert into genre (genre) values ('splatter');
insert into genre (genre) values ('fantasy');
insert into genre (genre) values ('sci-fi');
insert into genre (genre) values ('romance');
insert into genre (genre) values ('thriller');
insert into genre (genre) values ('western');

-- adding movie
-- sp_addMovie( IN movie_title varchar(50), IN movie_release_year int, IN movie_length int, IN director_name varchar(50), IN director_city varchar(50))
call sp_addMovie('title1', 1979, 158, 'JohnC', 'LA');
call sp_addMovie('title2', 1963, 102, 'Sylvester', null);
call sp_addMovie('title3', 1971, 98, 'Mike', 'NY');
call sp_addMovie('title4', 2009, 260, 'Adam', 'Hell');
call sp_addMovie('title5', 1897, 45, 'Belinda', 'Heaven');
call sp_addMovie('title6', 1979, 89, 'Christian', null);
call sp_addMovie('title7', 1963, 102, 'Sylvester', null);
call sp_addMovie('title8', 1963, 102, 'Sylvester', null);
call sp_addMovie('title9', 1979, 158, 'JohnC', 'LA');
call sp_addMovie('title10', 1963, 102, 'Sylvester', null);

-- adding actors to movie
-- create procedure sp_addMovieToActor( IN actor_name varchar(50), IN movie_title varchar(50) )
call sp_addMovieToActor('Hedvig', 'title1');
call sp_addMovieToActor('Hedvig', 'title10');
call sp_addMovieToActor('Hedvig', 'title5');

call sp_addMovieToActor('Johan', 'title2');
call sp_addMovieToActor('Johan', 'title9');
call sp_addMovieToActor('Johan', 'title4');

call sp_addMovieToActor('Kim', 'title3');
call sp_addMovieToActor('Kim', 'title8');
call sp_addMovieToActor('Kim', 'title6');

call sp_addMovieToActor('Bertil', 'title4');
call sp_addMovieToActor('Bertil', 'title7');
call sp_addMovieToActor('Bertil', 'title3');

call sp_addMovieToActor('Sean', 'title5');
call sp_addMovieToActor('Sean', 'title6');
call sp_addMovieToActor('Sean', 'title7');

call sp_addMovieToActor('Tina', 'title6');
call sp_addMovieToActor('Tina', 'title5');
call sp_addMovieToActor('Tina', 'title2');

call sp_addMovieToActor('Xiao', 'title7');
call sp_addMovieToActor('Xiao', 'title4');
call sp_addMovieToActor('Xiao', 'title8');

call sp_addMovieToActor('Liu', 'title8');
call sp_addMovieToActor('Liu', 'title3');
call sp_addMovieToActor('Liu', 'title1');

call sp_addMovieToActor('Anders', 'title9');
call sp_addMovieToActor('Anders', 'title2');
call sp_addMovieToActor('Anders', 'title10');

call sp_addMovieToActor('Linnea', 'title10');
call sp_addMovieToActor('Linnea', 'title1');
call sp_addMovieToActor('Linnea', 'title9');

-- adding genres to movie
-- create procedure sp_addGenreToMovie( IN genre varchar(50), IN movie_title varchar(50) )
call sp_addGenreToMovie('comedy', 'title1');
call sp_addGenreToMovie('horror', 'title2');
call sp_addGenreToMovie('action', 'title3');
call sp_addGenreToMovie('drama', 'title4');
call sp_addGenreToMovie('splatter', 'title5');
call sp_addGenreToMovie('fantasy', 'title6');
call sp_addGenreToMovie('sci-fi', 'title7');
call sp_addGenreToMovie('romance', 'title8');
call sp_addGenreToMovie('thriller', 'title9');
call sp_addGenreToMovie('western', 'title10');

call sp_addGenreToMovie('western', 'title1');
call sp_addGenreToMovie('comedy', 'title2');
call sp_addGenreToMovie('horror', 'title3');
call sp_addGenreToMovie('action', 'title4');
call sp_addGenreToMovie('drama', 'title5');
call sp_addGenreToMovie('splatter', 'title6');
call sp_addGenreToMovie('fantasy', 'title7');
call sp_addGenreToMovie('sci-fi', 'title8');
call sp_addGenreToMovie('romance', 'title9');
call sp_addGenreToMovie('thriller', 'title10');