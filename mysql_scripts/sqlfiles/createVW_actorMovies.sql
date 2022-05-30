DROP VIEW if exists vw_actorMovies;
create or replace view vw_actorMovies as
select actor.name, group_concat(movie.title separator ' * ') as "movies"
from actor
join movie_actor on actor.actor_id = movie_actor.actor_id
join movie on movie_actor.movie_id = movie.movie_id
group by actor.name;