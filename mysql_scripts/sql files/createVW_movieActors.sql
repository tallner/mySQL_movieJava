DROP VIEW if exists vw_movieActors;
create or replace view vw_movieActors as
select movie.title, group_concat(actor.name separator ' * ') as "actors"
from movie
join movie_actor on movie.movie_id = movie_actor.movie_id
join actor on movie_actor.actor_id = actor.actor_id
group by movie.title;