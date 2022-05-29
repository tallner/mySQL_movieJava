package java_sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import helpers.databaseHelper;
import helpers.jsonHelper;
import objectList.actormovies;
import objectList.actors;
import objectList.directors;
import objectList.genres;
import objectList.movieactor_relations;
import objectList.movieactors;
import objectList.moviegenre_relations;
import objectList.movieinfo;
import objectList.movies;

public class main_class {
	public static void main(String[] args) throws SQLException {
		Connection conn = databaseHelper.DbConnect("movies");
		
		
		//ShowAllTables(conn);
		
		// updateActor(conn, "Hedvig", "Dancing", 45);
		
		//ShowAllTables(conn);
		
		//createActor(conn,"Nisse",67,"driving");
		
		//ShowAllTables(conn);
		
		//deleteActor(conn, "Sean");
		
		//ShowAllTables(conn);
		
		//readActors(conn);
		
		// updateActorsSkill(conn, "Nisse", "boxing");
		
		// updateActorsAge(conn, "Nisse", 0);
		
		// createMovie(conn, "testFilm", 1919, 56, "Christian", "Fredrik");
		// createMovie(conn, "testFilm", 1919, 56, "Christian", "Cecilia");
		

		//simple UI to test the methods
		
		userUI(conn);
		
		conn.close();
        
		
	}
	
	private static void userUI(Connection conn) {
		
		int sel = 666;
        Scanner userSel = new Scanner(System.in);
		
		while(true)
        {
            System.out.println(" |**********************| ");
            System.out.println(" |****     MENU     ****| ");
            System.out.println(" |**********************| ");
            System.out.println(" 0. Show all tables"); // all tables in database
            
            System.out.println(" 1. Create new actor "); //
            System.out.println(" 2. Read all actors"); //
            System.out.println(" 3. Update an actor"); //change skill and age
            System.out.println(" 4. Update an actors age"); //
            System.out.println(" 5. Update an actors skill"); //
            System.out.println(" 6. Delete an actor"); //

            System.out.println(" 7. Create movie"); //
            System.out.println(" 8. Delete movie");//
            System.out.println(" 9. Read movies");//
            
            System.out.println(" 10. Show all actors and their movies ");//
            System.out.println(" 11. Show all movies and their actors ");//
            System.out.println(" 0. Show movie information"); // general movieinformation
            System.out.println(" 0. Show movie information"); // general movieinformation
            

            System.out.println(" 100. Exit ");
            System.out.println("....................");
            System.out.println("Make your choice!");



            //make sure the input is correct
            try {
                sel = userSel.nextInt();
            } catch (Exception e) {
                System.out.println("bad input");
                userSel.next();
            }

            //exit and close connection
            if(sel == 100) {
            	System.out.println("-Program ended----------");
            	break;
            }


            switch (sel) {
                case 0 -> {
                    //System.out.println("All tables:");
                    ShowAllTables(conn);
                }

                //
                case 1 -> {
                	System.out.println("-Add new actor to database-");
                	System.out.println("Name of actor: ");
                    String name = userSel.next();
                    System.out.println("Actors age: ");
                    int age = userSel.nextInt();
                    System.out.println("Actors skill: ");
                    String skill = userSel.next();

                    createActor(conn,name,age,skill);
                }

                //
                case 2 -> {
                	System.out.println("-List all actors-");
                	readActors(conn);
                }
                //
                case 3 -> {
                	System.out.println("-Update an actors age and skill-");
                	System.out.println("Name of actor: ");
                    String name = userSel.next();
                    System.out.println("Actors new age: ");
                    int age = userSel.nextInt();
                    System.out.println("Actors new skill: ");
                    String skill = userSel.next();
                    
                    updateActor(conn, name, skill, age);
                }

                //
                case 4 -> {
                	System.out.println("-Update an actors age-");
                	System.out.println("Name of actor: ");
                    String name = userSel.next();
                    System.out.println("Actors new age: ");
                    int age = userSel.nextInt();
                   
                    updateActorsAge(conn, name, age);
                }

                //
                case 5 -> {
                	System.out.println("-Update an actors skill-");
                	System.out.println("Name of actor: ");
                    String name = userSel.next();
                    System.out.println("Actors new skill: ");
                    String skill = userSel.next();
                    
                    updateActorsSkill(conn, name, skill);
                }

                //
                case 6 -> {
                	System.out.println("-Delete an actor-");
                	System.out.println("Name of actor: ");
                    String name = userSel.next();
                                        
                    deleteActor(conn, name);
                }

                //
                case 7 -> {
                	System.out.println("-Create a movie-");
                	System.out.println("Movie title: ");String title = userSel.next();
                	System.out.println("Release year: ");int release_year = userSel.nextInt();
                	System.out.println("Length minutes: ");int length_minutes = userSel.nextInt();
                	System.out.println("Director: ");String director = userSel.next();
                	System.out.println("Actor: ");String actor = userSel.next();
                	System.out.println("Genre: ");String genre = userSel.next();
                    
                	createMovie(conn, title, release_year, length_minutes, director, actor, genre);
                }

                //
                case 8 -> {
                	System.out.println("-Delete movie-");
                	System.out.println("Movie title: ");String title = userSel.next();
                	
              
                    
                	deleteMovie(conn, title);
                    
                }

                //
                case 9 -> {
                	System.out.println("-Read all movies-");
                	      
                	readMovies(conn);
                   
                }

                //
                case 10 -> {
                	System.out.println("-Show actors and their movies-");
                	
                	showActorsMovies(conn);

                }

                //
                case 11 -> {
                	System.out.println("-Show movies and their actors-");
                	
                	showMoviesActors(conn);

                }

                default -> System.out.println("0");
            }
            }
		
	}
	
	private static void ShowAllTables(Connection conn) {
		// tables
		actors myActors = new actors(conn);
		directors myDirectors = new directors(conn);
		genres myGenres = new genres(conn);
		movies myMovies = new movies(conn);
		
		
		// table connections
		movieactor_relations myMovieactor_relations = new movieactor_relations(conn);
		moviegenre_relations myMoviegenres_relations = new moviegenre_relations(conn);
		
		// views
		actormovies myActormovies = new actormovies(conn);
		movieactors myMovieactors = new movieactors(conn);
		movieinfo myMovieinfoView = new movieinfo(conn);

		ArrayList<String> document = new ArrayList<String>();
		document.add(myActors.toJson());
		document.add(myDirectors.toJson());
		document.add(myGenres.toJson());
		document.add(myMovies.toJson());
		document.add(myMovieactor_relations.toJson());
		document.add(myMoviegenres_relations.toJson());
		document.add(myActormovies.toJson());
		document.add(myMovieactors.toJson());
		document.add(myMovieinfoView.toJson());

		String jsonDoc = jsonHelper.toJsonObjectFromStrings(document);

		System.out.println(jsonDoc);
		
	}
	
	// -- actors object KLAR--
	private static void createActor(Connection conn, String name, int age, String skill) {
		actors myActor = new actors(conn);
		
		int nrCreated = myActor.createActor(name, age, skill);
		System.out.println("Nr of created actors : " + nrCreated);
		
	}
	
	private static void readActors(Connection conn) {
		actors myActors = new actors(conn);
		String jsonDoc = "{" + myActors.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	private static void updateActor(Connection conn, String name, String newSkill, int newAge) {
		actors myActors = new actors(conn);

		int nrUpdates = myActors.updateActor(name, newSkill, newAge, "all");
		System.out.println("Nr of updated actors : " + nrUpdates);
				
	}
	
	private static void updateActorsSkill(Connection conn, String name, String newSkill) {
		actors myActors = new actors(conn);

		int nrUpdates = myActors.updateActor(name, newSkill, 0, "skills");
		System.out.println("Nr of updated actors : " + nrUpdates);
				
	}
	
	private static void updateActorsAge(Connection conn, String name, int newAge) {
		actors myActors = new actors(conn);

		int nrUpdates = myActors.updateActor(name, "", newAge, "age");
		System.out.println("Nr of updated actors : " + nrUpdates);
				
	}
	
	private static void deleteActor(Connection conn, String name) {
		actors myActor = new actors(conn);
		
		int nrDeleted = myActor.deleteActor(name);
		System.out.println("Nr of deleted actors : " + nrDeleted);
		
	}
	
	// -- directors object --
	private static void createDirector(Connection conn, String name, String city) {
		directors myDirector = new directors(conn);
		
		int nrCreated = myDirector.createDirector(name, city);
		System.out.println("Nr of created directors : " + nrCreated);
		
	}
	
	private static void readDirectors(Connection conn) {
		directors myDirectors = new directors(conn);
		String jsonDoc = "{" + myDirectors.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	private static void updateDirectorCity(Connection conn, String name, String newCity) {
		directors myDirector = new directors(conn);

		int nrUpdates = myDirector.updateDirector(name, newCity);
		System.out.println("Nr of updated directors : " + nrUpdates);
				
	}
	
	private static void deleteDirector(Connection conn, String name) {
		directors myDirector = new directors(conn);
		
		int nrDeleted = myDirector.deleteDirector(name);
		System.out.println("Nr of deleted directors : " + nrDeleted);
		
	}
	
	// -- genres object --
	private static void createGenre(Connection conn, String genre) {
		genres myGenre = new genres(conn);
		
		int nrCreated = myGenre.createGenre(genre);
		System.out.println("Nr of created genres : " + nrCreated);
		
	}
	
	private static void readGenres(Connection conn) {
		genres myGenres = new genres(conn);
		String jsonDoc = "{" + myGenres.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	private static void updateGenre(Connection conn, String genre, String newGenre) {
		genres myGenre = new genres(conn);

		int nrUpdates = myGenre.updateGenre(genre, newGenre);
		System.out.println("Nr of updated genres : " + nrUpdates);
				
	}
	
	private static void deleteGenre(Connection conn, String name) {
		genres myGenre = new genres(conn);
		
		int nrDeleted = myGenre.deleteGenre(name);
		System.out.println("Nr of deleted genres : " + nrDeleted);
		
	}
		

	// -- movies object --
	private static void createMovie(
			Connection conn, 
			String movie_title,int movie_release_year,int movie_length,
			String director_name, String actor_name,
			String genre) {
		
		movies myMovie = new movies(conn);
		directors myDirector = new directors(conn);
		actors myActor = new actors(conn);
		genres myGenres = new genres(conn);
		movieactor_relations ma_rel = new movieactor_relations(conn);
		moviegenre_relations mg_rel = new moviegenre_relations(conn);
		
		System.out.println(myActor.createActor(actor_name, 99, "noskilldefined")); //create actor and default age and skill if actor does not exist
		System.out.println(myDirector.createDirector(director_name, "nocitydefined")); //create director and default city if director does not exist
		System.out.println(myGenres.createGenre(genre)); //create genre if genre does not exist
		System.out.println(myMovie.createMovie(movie_title,movie_release_year,movie_length,director_name));
		System.out.println(ma_rel.bindActorToMovie(actor_name, movie_title)); // bind actor to movie
		System.out.println(mg_rel.bindGenreToMovie(genre, movie_title)); // bind genre to movie
		
	}
	
	private static void readMovies(Connection conn) {
		movieinfo myMovies = new movieinfo(conn);
		String jsonDoc = "{" + myMovies.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	private static void updateMovieLength(Connection conn, String title, int length_minutes) {
		movies myMovie = new movies(conn);
		
		myMovie.updateMovieLength(title, length_minutes);
	}
	
	private static void deleteMovie(Connection conn, String movie_title) {
		movies myMovie = new movies(conn);
		
		int nrDeleted = myMovie.deleteMovie(movie_title);
		System.out.println("Nr of deleted movies : " + nrDeleted);
		
	}
	
	// -- movieinfo object --
	// -- movieactor_relations object --
	// -- moviegenre_relations object --
	
	// -- actormovies object --
	private static void showActorsMovies(Connection conn) {
		actormovies myActorMovies = new actormovies(conn);
		String jsonDoc = "{" + myActorMovies.toJson() + "}";
		
		System.out.println(jsonDoc);
	}
	
	// -- movieactors object --
	private static void showMoviesActors(Connection conn) {
		movieactors myMovieActors = new movieactors(conn);
		String jsonDoc = "{" + myMovieActors.toJson() + "}";
		
		System.out.println(jsonDoc);
	}
	
}
