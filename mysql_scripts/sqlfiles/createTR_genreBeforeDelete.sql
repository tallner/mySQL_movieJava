DELIMITER $$
CREATE TRIGGER genre_before_delete
BEFORE DELETE
ON genre
FOR EACH ROW
BEGIN
DELETE FROM movie_genre WHERE genre_id=OLD.genre_id;
END $$ 
DELIMITER ;