@ECHO OFF

SET mySQLpath="C:\Program Files\MySQL\MySQL Workbench 8.0\mysql.exe"
SET mySQLfilePath=C:\ProgrammingCourses\grit\databas\mySQL_movieJava\mysql_scripts\sqlfiles
SET myPW="tallner"
SET myUS="root"
SET myDB="movies"

SET mySQLfileName_0=createDB.sql
SET mySQLfileName_1=createTR_actorAge.sql
SET mySQLfileName_2=createTR_genreBeforeDelete.sql
SET mySQLfileName_3=createTR_actorBeforeDelete.sql
SET mySQLfileName_4=createTR_movieBeforeDelete.sql
SET mySQLfileName_5=createSP_addMovie.sql
SET mySQLfileName_6=createSP_addGenreToMovie.sql
SET mySQLfileName_7=createSP_addMovieToActor.sql
SET mySQLfileName_8=populateDB.sql
SET mySQLfileName_9=createVW_movieInfo.sql
SET mySQLfileName_10=createVW_actorMovies.sql
SET mySQLfileName_11=createVW_movieActors.sql

%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_0%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_1%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_2%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_3%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_4%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_5%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_6%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_7%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_8%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_9%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_10%"
%mySQLpath% --user=%myUS% --database=%myDB% --password=%myPW% < "%mySQLfilePath%\%mySQLfileName_11%"



