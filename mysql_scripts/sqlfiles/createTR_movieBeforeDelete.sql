DELIMITER $$
CREATE TRIGGER movie_before_delete
BEFORE DELETE
ON movie
FOR EACH ROW
BEGIN
DELETE FROM movie_actor WHERE movie_id=OLD.movie_id;
DELETE FROM movie_genre WHERE movie_id=OLD.movie_id;
END $$ 
DELIMITER ;