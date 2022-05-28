DELIMITER $$
CREATE TRIGGER actor_age_before_insert
BEFORE INSERT
ON actor
FOR EACH ROW
BEGIN
IF (NEW.age NOT BETWEEN 0 AND 150) THEN
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error: Variable "age" out of range.';
END IF;
END $$ 
DELIMITER ;