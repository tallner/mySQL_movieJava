DROP VIEW if exists vw_movieInfo;
create or replace view vw_movieInfo as
select movie.title, genre.genre, director.name as "director_name", movie.release_year
from movie
join movie_genre on movie_genre.movie_id = movie.movie_id
join genre on movie_genre.genre_id = genre.genre_id
join director on director.director_id=movie.director_id;