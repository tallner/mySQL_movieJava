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
		
		updateActor(conn, "Hedvig", "Dancing", 45);
		
		//ShowAllTables(conn);
		
		//createActor(conn,"Nisse",67,"driving");
		
		//ShowAllTables(conn);
		
		//deleteActor(conn, "Sean");
		
		//ShowAllTables(conn);
		
		//readActors(conn);
		
		// updateActorsSkill(conn, "Nisse", "boxing");
		
		// updateActorsAge(conn, "Nisse", 0);
		

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

            System.out.println(" 5. "); //
            System.out.println(" 6. "); //
            System.out.println(" 7. ");//

            System.out.println(" 8. ");//
            System.out.println(" 9. ");//
            System.out.println(" 10. ");//
            System.out.println(" 11. ");//

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
            //	conn.close();
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

                }

                //
                case 8 -> {
                    
                }

                //
                case 9 -> {
                   
                }

                //
                case 10 -> {

                }

                //
                case 11 -> {

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
	
}
