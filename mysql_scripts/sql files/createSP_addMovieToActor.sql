DELIMITER $$
DROP PROCEDURE IF EXISTS sp_addMovieToActor;

create procedure sp_addMovieToActor( IN actor_name varchar(50), IN movie_title varchar(50) )
BEGIN

insert into movie_actor (movie_id, actor_id)
select movie.movie_id, actor.actor_id 
from movie 
join actor where actor.name=actor_name and movie.title=movie_title;

END $$

DELIMITER ;