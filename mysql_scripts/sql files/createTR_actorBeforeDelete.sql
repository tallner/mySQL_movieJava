DELIMITER $$
CREATE TRIGGER actor_before_delete
BEFORE DELETE
ON actor
FOR EACH ROW
BEGIN
DELETE FROM movie_actor WHERE actor_id=OLD.actor_id;
END $$ 
DELIMITER ;