package java_sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import helpers.databaseHelper;
import helpers.jsonHelper;
import objectList.vw_actormovies;
import objectList.actors;
import objectList.directors;
import objectList.genres;
import objectList.relation_movieactor;
import objectList.vw_movieactors;
import objectList.relation_moviegenre;
import objectList.vw_movieinfo;
import objectList.movies;

public class main_class {
	public static void main(String[] args) throws SQLException {
		Connection conn = databaseHelper.DbConnect("movies");
		
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
            
            
            System.out.println("-The story-------------------------------"); //
            System.out.println(" 99. Run the story"); // all tables in database
            
            
            
            System.out.println("-Actors-------------------------------"); //
            
            //objects
            System.out.println(" 1. Create new actor "); //
            System.out.println(" 2. Read all actors"); //
            System.out.println(" 3. Update an actor"); //change skill and age
            System.out.println(" 4. Update an actors age"); //
            System.out.println(" 5. Update an actors skill"); //
            System.out.println(" 6. Delete an actor"); //
            
            System.out.println("-Directors-------------------------------"); //
            
            System.out.println(" 7. Create new director "); //
            System.out.println(" 8. Read all director"); //
            System.out.println(" 9. Update a directors city"); //
            System.out.println(" 10. Delete a director"); //
            
            System.out.println("-Genres-------------------------------"); //
            
            System.out.println(" 11. Create new genre "); //
            System.out.println(" 12. Read all genre"); //
            System.out.println(" 13. Update a genre"); //
            System.out.println(" 14. Delete a genre"); //
            
            System.out.println("-Movies-------------------------------"); //

            System.out.println(" 15. Create movie"); //
            System.out.println(" 16. Read movies");//
            System.out.println(" 17. Update a movies length"); //
            System.out.println(" 18. Delete movie");//
            
            System.out.println("-Relations-------------------------------"); //
            
            //relations
            System.out.println(" 19. Create actor to movie relation");//
            System.out.println(" 20. Read actor to movie relation");//
            
            System.out.println(" 21. Create genre to movie relation");//
            System.out.println(" 22. Read genre to movie relation");//
            
            System.out.println("-Views-------------------------------"); //
            
            //views
            System.out.println(" 23. Read movie information ");//
            System.out.println(" 24. Read actors and their movies"); //
            System.out.println(" 25. Read movies and their actors"); //
            
            System.out.println("--------------------------------"); //
            

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

            //exit
            if(sel == 100) {
            	System.out.println("-Program ended----------");
            	break;
            }

            switch (sel) {
                case 0 -> {
                    //System.out.println("All tables:");
                    ShowAllTables(conn);
                }

                //actors
                case 1 -> {
                	System.out.println("-Add new actor to database-");
                	System.out.println("Name of actor: "); String name = userSel.next();
                    System.out.println("Actors age: "); int age = userSel.nextInt();
                    System.out.println("Actors skill: "); String skill = userSel.next();

                    createActor(conn,name,age,skill);
                }
                case 2 -> {
                	System.out.println("-List all actors-");
                	readActors(conn);
                }
                case 3 -> {
                	System.out.println("-Update an actors age and skill-");
                	System.out.println("Name of actor: "); String name = userSel.next();
                    System.out.println("Actors new age: "); int age = userSel.nextInt();
                    System.out.println("Actors new skill: ");String skill = userSel.next();
                    
                    updateActor(conn, name, skill, age);
                }
                case 4 -> {
                	System.out.println("-Update an actors age-");
                	System.out.println("Name of actor: ");String name = userSel.next();
                    System.out.println("Actors new age: ");int age = userSel.nextInt();
                   
                    updateActorsAge(conn, name, age);
                }
                case 5 -> {
                	System.out.println("-Update an actors skill-");
                	System.out.println("Name of actor: ");String name = userSel.next();
                    System.out.println("Actors new skill: ");String skill = userSel.next();
                    
                    updateActorsSkill(conn, name, skill);
                }
                case 6 -> {
                	System.out.println("-Delete an actor-");
                	System.out.println("Name of actor: ");
                    String name = userSel.next();
                                        
                    deleteActor(conn, name);
                }
                
                
                //directors
                case 7 -> {
                	System.out.println("-Add new director to database-");
                	System.out.println("Name of director: "); String name = userSel.next();
                    System.out.println("director city: "); String city = userSel.next();

                    createDirector(conn, name, city);
                }
                case 8 -> {
                	System.out.println("-List all director-");
                	
                	readDirectors(conn);
                }
                case 9 -> {
                	System.out.println("-Update a directors city-");
                	System.out.println("Name of director: "); String name = userSel.next();
                    System.out.println("Directors new city: "); String city = userSel.next();
                    
                    updateDirectorCity(conn, name, city);
                }
                case 10 -> {
                	System.out.println("-Delete director-");
                	System.out.println("Name of director: ");String name = userSel.next();
                   
                    deleteDirector(conn, name);
                }
                
                
                //genres
                case 11 -> {
                	System.out.println("-Add new genre to database-");
                	System.out.println("Name of genre: "); String genre = userSel.next();

                    createGenre(conn, genre);
                }
                case 12 -> {
                	System.out.println("-List all genres-");
                	
                	readGenres(conn);
                }
                case 13 -> {
                	System.out.println("-Update a genre -");
                	System.out.println("Name of genre: "); String genre = userSel.next();
                    System.out.println("New genre: "); String newGenre = userSel.next();
                    
                    updateGenre(conn, genre, newGenre);
                }
                case 14 -> {
                	System.out.println("-Delete genre-");
                	System.out.println("Name of genre: ");String genre = userSel.next();
                   
                    deleteGenre(conn, genre);
                }

              
                //movies
                case 15 -> {
                	System.out.println("-Create a movie-");
                	System.out.println("Movie title: ");String title = userSel.next();
                	System.out.println("Release year: ");int release_year = userSel.nextInt();
                	System.out.println("Length minutes: ");int length_minutes = userSel.nextInt();
                	System.out.println("Director: ");String director = userSel.next();
                	System.out.println("Actor: ");String actor = userSel.next();
                	System.out.println("Genre: ");String genre = userSel.next();
                    
                	createMovie(conn, title, release_year, length_minutes, director, actor, genre);
                }
                case 16 -> {
                	System.out.println("-Read all movies-");
                	      
                	readMovies(conn);
                }
                case 17 -> {
                	System.out.println("-Update movie lenght-");
                	System.out.println("Movie title: ");String title = userSel.next();
                    System.out.println("New length: ");int length = userSel.nextInt();
                    
                    updateMovieLength(conn, title, length);
                }
                case 18 -> {
                	System.out.println("-Delete movie-");
                	System.out.println("Movie title: ");String title = userSel.next();
                	
                	deleteMovie(conn, title);
                }
                
                
              //relations
                case 19 -> {
                	System.out.println("-Create actor to movie relation-");
                	System.out.println("Actor name: ");String actor_name = userSel.next();
                	System.out.println("Movie title: ");String movie_title = userSel.next();
                    
                	createActorToMovieRelation(conn, actor_name, movie_title);
                }
                case 20 -> {
                	System.out.println("-Read actor to movie relations-");
                	
                	readActorToMovieRelations(conn);
                }
                

                case 21 -> {
                	System.out.println("-Create genre to movie relation-");
                	System.out.println("Genre name: ");String genre = userSel.next();
                	System.out.println("Movie title: ");String movie_title = userSel.next();
                    
                	createGenreToMovieRelation(conn, genre, movie_title);
                }
                case 22 -> {
                	System.out.println("-Read genre to movie relations-");
                	
                	readGenreToMovieRelations(conn);
                }
                
                
                //views
                case 23 -> {
                	System.out.println("-Read movie information-");
                	
                	readMovieInfo(conn);
                }
                case 24 -> {
                	System.out.println("-Read actors and their movies-");
                	
                	readActorsMovies(conn);
                }
                case 25 -> {
                	System.out.println("-Read movies and their actors-");
                	
                	readMoviesActors(conn);
                }
                
                case 99 -> {
                	System.out.println("--This story creates a new movie for the database "
                			+ "and makes some corrections to errors that later pops up--" + "/r/n");
                	
                	story(conn);
                		
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
		relation_movieactor myMovieactor_relations = new relation_movieactor(conn);
		relation_moviegenre myMoviegenres_relations = new relation_moviegenre(conn);
		
		// views
		vw_actormovies myActormovies = new vw_actormovies(conn);
		vw_movieactors myMovieactors = new vw_movieactors(conn);
		vw_movieinfo myMovieinfoView = new vw_movieinfo(conn);

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
	
	//Story
	private static void story(Connection conn) {
		
		//this story creates a new movie for the database and makes some corrections to errors that later pops up
		
		//check if the movie exists in the current list
		System.out.println("A person checks if the NewMovie is in the database by reading all titles and info.");
		readMovies(conn);
		
		System.out.println();
		
		//no it was not --> create the movie
		//(title,year,length,director,actor,genre)
		System.out.println("The person could not find it and tries to create it ");
		createMovie(conn, "myNewMovie", 2022, 125, "JoeTheDirector", "JaneTheActor", "scifi");
		//if the director, actor and genre does not exist it will be automatically created
		//Details about director and actor is currently secret and will later be changed
		
		System.out.println();
		
		//the person reads the movie to the website with a view to see if all correct
		System.out.println("The person reads the movie to the website with a view to see if all correct");
		readMovieInfo(conn);

		System.out.println();
		
		//when it came to website the length is wrong and needs update
		System.out.println("Length was reported wrong from a website user and needs an update");
		updateMovieLength(conn, "myNewMovie", 152);
		
		System.out.println();
		
		System.out.println("The person reads the info again and checks the length");
		readMovies(conn);
		
		System.out.println();
		
		//someone reads more about the actor and notice both age and skill is wrong
		System.out.println("A user reads more about the actor and notice both age and skill is wrong");
		updateActor(conn, "JanesTheActor", "guitarr", 28);

		System.out.println();
		
		System.out.println("Later on the director moves to a new city and the location is no longer secret");
		updateDirectorCity(conn, "JoeTheDirector", "Flyinge");
		
		System.out.println();
		
		System.out.println("There is a scandal in the movieindustry and the actor is not the real actor");
		System.out.println("Actor needs to be deleted from the database");
		deleteActor(conn, "JaneTheActor");
		
		System.out.println();
		
		System.out.println("The real actor is now added and connected to the movie");
		createActor(conn, "JanneTheActor", 82, "drums");
		createActorToMovieRelation(conn, "JanneTheActor", "myNewMovie");
		
		System.out.println("--END OF STORY--");
		
		

		
	}
	
	// -- actors object --
	private static void createActor(Connection conn, String name, int age, String skill) {
		actors myActor = new actors(conn);
		
		int nrCreated = myActor.createActor(name, age, skill);
		if (nrCreated==-1) System.out.println("Result: "+" Actor not created, check inputs");
		else	System.out.println("Result: "+"Actor: "+name+" succesfully created with age="+age+" and skill="+skill);
		
	}
	
	private static void readActors(Connection conn) {
		actors myActors = new actors(conn);
		String jsonDoc = "{" + myActors.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	private static void updateActor(Connection conn, String name, String newSkill, int newAge) {
		actors myActors = new actors(conn);

		int nrUpdates = myActors.updateActor(name, newSkill, newAge, "all");
		if (nrUpdates==-1) System.out.println("Result: "+" Actor not updated, check inputs");
		else	System.out.println("Result: "+"Actor: "+name+" succesfully updated age="+newAge+" and skill="+newSkill);
				
	}
	
	private static void updateActorsSkill(Connection conn, String name, String newSkill) {
		actors myActors = new actors(conn);

		int nrUpdates = myActors.updateActor(name, newSkill, 0, "skills");
				
		if (nrUpdates==-1) System.out.println("Result: "+"Actor not updated, check inputs");
		else	System.out.println("Result: "+"Actor: "+name+" new skill="+newSkill);
		
				
	}
	
	private static void updateActorsAge(Connection conn, String name, int newAge) {
		actors myActors = new actors(conn);

		int nrUpdates = myActors.updateActor(name, "", newAge, "age");
		if (nrUpdates==-1) System.out.println("Result: "+"Actor not updated, check inputs");
		else	System.out.println("Result: "+"Actor: "+name+" new age="+newAge);
				
	}
	
	private static void deleteActor(Connection conn, String name) {
		actors myActor = new actors(conn);
		
		int nrDeleted = myActor.deleteActor(name);
		if (nrDeleted==-1) System.out.println("Result: "+"Actor not deleted, check inputs");
		else	System.out.println("Result: "+"Actor: "+name+" succesfully deleted");
		
	}
	
	
	// -- directors object --
	private static void createDirector(Connection conn, String name, String city) {
		directors myDirector = new directors(conn);
		
		int nrCreated = myDirector.createDirector(name, city);
		if (nrCreated==-1) System.out.println("Result: "+"Director not created, check inputs");
		else	System.out.println("Result: "+"Director: "+name+" created");
		
	}
	
	private static void readDirectors(Connection conn) {
		directors myDirectors = new directors(conn);
		String jsonDoc = "{" + myDirectors.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	private static void updateDirectorCity(Connection conn, String name, String newCity) {
		directors myDirector = new directors(conn);

		int nrUpdates = myDirector.updateDirector(name, newCity);
		if (nrUpdates==-1) System.out.println("Result: "+"Director not updated, check inputs");
		else	System.out.println("Result: "+"Director: "+name+" new city="+newCity);
				
	}
	
	private static void deleteDirector(Connection conn, String name) {
		directors myDirector = new directors(conn);
		
		int nrDeleted = myDirector.deleteDirector(name);
		if (nrDeleted==-1) System.out.println("Result: "+"Director not deleted, check inputs");
		else	System.out.println("Result: "+"Director: "+name+" deleted");
		
	}
	
	
	// -- genres object --
	private static void createGenre(Connection conn, String genre) {
		genres myGenre = new genres(conn);
		
		int nrCreated = myGenre.createGenre(genre);
		if (nrCreated==-1) System.out.println("Result: "+"Genre not created, check inputs");
		else	System.out.println("Result: "+"Genre: "+genre+" created");
		
	}
	
	private static void readGenres(Connection conn) {
		genres myGenres = new genres(conn);
		String jsonDoc = "{" + myGenres.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	private static void updateGenre(Connection conn, String genre, String newGenre) {
		genres myGenre = new genres(conn);

		int nrUpdates = myGenre.updateGenre(genre, newGenre);
		if (nrUpdates==-1) System.out.println("Result: "+"Genre not updated, check inputs");
		else	System.out.println("Result: "+"Genre: "+genre+" updated to "+newGenre);
				
	}
	
	private static void deleteGenre(Connection conn, String genre) {
		genres myGenre = new genres(conn);
		
		int nrDeleted = myGenre.deleteGenre(genre);
		if (nrDeleted==-1) System.out.println("Result: "+"Genre not deleted, check inputs");
		else	System.out.println("Result: "+"Genre: "+genre+" deleted");
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
		relation_movieactor ma_rel = new relation_movieactor(conn);
		relation_moviegenre mg_rel = new relation_moviegenre(conn);
		
		myActor.createActor(actor_name, 99, "noskilldefined"); //create actor and default age and skill if actor does not exist
		myDirector.createDirector(director_name, "nocitydefined"); //create director and default city if director does not exist
		myGenres.createGenre(genre); //create genre if genre does not exist
		
		int nrCreated = myMovie.createMovie(movie_title,movie_release_year,movie_length,director_name);
		if (nrCreated==-1) System.out.println("Result: "+"Movie not created, check inputs");
		else if (nrCreated == -2) System.out.println("Result: "+"Movie title already exists!");
		else {
			System.out.println("Movie: "+movie_title+" created");
			
			ma_rel.createActorToMovieRelation(actor_name, movie_title); // bind actor to movie
			mg_rel.createGenreToMovieRelation(genre, movie_title); // bind genre to movie
			
			
		}
		
		
		
		
	}
	
	private static void readMovies(Connection conn) {
		movies myMovies = new movies(conn);
		String jsonDoc = "{" + myMovies.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	private static void updateMovieLength(Connection conn, String movie_title, int length_minutes) {
		movies myMovie = new movies(conn);
		
		int nrUpdates = myMovie.updateMovieLength(movie_title, length_minutes);
		if (nrUpdates==-1) System.out.println("Result: "+"Movie not updated, check inputs");
		else	System.out.println("Result: "+"Movie: "+movie_title+" length updated to "+length_minutes);
	}
	
	private static void deleteMovie(Connection conn, String movie_title) {
		movies myMovie = new movies(conn);
		
		int nrDeleted = myMovie.deleteMovie(movie_title);
		if (nrDeleted==-1) System.out.println("Result: "+"Movie not deleted, check inputs");
		else	System.out.println("Result: "+"Movie: "+movie_title+" deleted");		
	}
	
	
	// -- movieactor_relations object --
	private static void createActorToMovieRelation(Connection conn, String actor_name, String movie_title) {
		relation_movieactor ma_rel = new relation_movieactor(conn);
		
		int nrCreated = ma_rel.createActorToMovieRelation(actor_name, movie_title);
	}
	
	private static void readActorToMovieRelations(Connection conn) {
		relation_movieactor ma_rel = new relation_movieactor(conn);
		String jsonDoc = "{" + ma_rel.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	
	// -- moviegenre_relations object --
	private static void createGenreToMovieRelation(Connection conn, String genre, String movie_title) {
		relation_moviegenre mg_rel = new relation_moviegenre(conn);
		
		int nrCreated = mg_rel.createGenreToMovieRelation(genre , movie_title);
		
		
	}
	
	private static void readGenreToMovieRelations(Connection conn) {
		relation_moviegenre ma_rel = new relation_moviegenre(conn);
		String jsonDoc = "{" + ma_rel.toJson() + "}";
		
		System.out.println(jsonDoc);
		
	}
	
	
	// -- vw_movieinfo object --
	private static void readMovieInfo(Connection conn) {
			vw_movieinfo myMovies = new vw_movieinfo(conn);
			String jsonDoc = "{" + myMovies.toJson() + "}";
			
			System.out.println(jsonDoc);
			
		}
		
	// -- vw_actormovies object --
	private static void readActorsMovies(Connection conn) {
		vw_actormovies myActorMovies = new vw_actormovies(conn);
		String jsonDoc = "{" + myActorMovies.toJson() + "}";
		
		System.out.println(jsonDoc);
	}
	
	// -- vw_movieactors object --
	private static void readMoviesActors(Connection conn) {
		vw_movieactors myMovieActors = new vw_movieactors(conn);
		String jsonDoc = "{" + myMovieActors.toJson() + "}";
		
		System.out.println(jsonDoc);
	}
	
}
