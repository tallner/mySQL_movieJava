DELIMITER $$
CREATE TRIGGER genre_before_insert
BEFORE INSERT
ON genre
FOR EACH ROW
BEGIN
IF 
(new.genre != 'comedy') AND
(new.genre != 'horror') AND
(new.genre != 'action') AND
(new.genre != 'drama') AND
(new.genre != 'splatter') AND
(new.genre != 'fantasy') AND
(new.genre != 'sci-fi') AND
(new.genre != 'romance') AND
(new.genre != 'thriller') AND
(new.genre != 'western') AND
(new.genre != 'horror')
THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Variable "genre" out of range.';
END IF;
END $$ 
DELIMITER ;