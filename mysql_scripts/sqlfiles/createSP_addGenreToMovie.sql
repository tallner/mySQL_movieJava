DELIMITER $$

DROP PROCEDURE IF EXISTS sp_addGenreToMovie;
create procedure sp_addGenreToMovie( IN genre varchar(50), IN movie_title varchar(50) )
BEGIN

insert into movie_genre (movie_id, genre_id)
select movie.movie_id, genre.genre_id
from movie 
join genre where genre.genre=genre and movie.title=movie_title;



END $$

DELIMITER ;