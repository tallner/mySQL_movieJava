DELIMITER $$
CREATE TRIGGER actor_before_delete
BEFORE DELETE
ON actor
FOR EACH ROW
BEGIN
DELETE FROM movie_actor WHERE movie_id=OLD.movie_id;
DELETE FROM movie_genre WHERE movie_id=OLD.movie_id;
END $$ 
DELIMITER ;