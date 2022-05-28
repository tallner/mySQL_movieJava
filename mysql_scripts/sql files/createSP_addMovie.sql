DELIMITER $$

DROP PROCEDURE IF EXISTS sp_addMovie;
create procedure sp_addMovie( IN movie_title varchar(50), IN movie_release_year int, IN movie_length int, IN director_name varchar(50), IN director_city varchar(50))
BEGIN

	IF director_city IS NULL THEN 
		insert into movie (title, release_year, length_minutes, director_id)
		select movie_title, movie_release_year, movie_length, 
		director_id from director where name=director_name;
	ELSE
		insert into movie (title, release_year, length_minutes, director_id)
		select movie_title, movie_release_year, movie_length, 
		director_id from director where (name=director_name and city=director_city);
	END IF;



END $$

DELIMITER ;