package java_sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
		ShowAllTables(conn);
		
		updateActors(conn, "Hedvig", "Dancing", 45);
		
		ShowAllTables(conn);
		
		createActor(conn,"Goran",67,"driving");
		
		ShowAllTables(conn);
		
		deleteActor(conn, "Sean");
		
		ShowAllTables(conn);
		

//		String jsonDoc = "{" + myActors.toJson() + ", " + myAddresses.toJson() + "}";
//		String jsonDoc = "{" + myActors.toJson() + "}";
//		String jsonDoc = "{" + myDirectors.toJson() + "}";
//		String jsonDoc = "{" + myGenres.toJson() + "}";
//		String jsonDoc = "{" + myMovies.toJson() + "}";
//		String jsonDoc = "{" + myMovieactor_relations.toJson() + "}";
//		String jsonDoc = "{" + myMoviegenres_relations.toJson() + "}";
//		String jsonDoc = "{" + myActormovies.toJson() + "}";
//		String jsonDoc = "{" + myMovieactors.toJson() + "}";
//		String jsonDoc = "{" + myMovieinfoView.toJson() + "}";
		
	

		conn.close();
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
	
	private static void updateActors(Connection conn, String name, String newSkill, int newAge) {
		actors myActors = new actors(conn);

		int nrUpdates = myActors.updateActors(name, newSkill, newAge);
		System.out.println("Nr of updated actors : " + nrUpdates);
		
//		antal = myActors.updateActorsSkill("s%", "växjö");
//		System.out.println("uppdaterat : " + antal);		
	}
	
	private static void createActor(Connection conn, String name, int age, String skill) {
		actors myActor = new actors(conn);
		
		int nrCreated = myActor.createActor(name, age, skill);
		System.out.println("Nr of created actors : " + nrCreated);
		
	}
	

	private static void deleteActor(Connection conn, String name) {
		actors myActor = new actors(conn);
		
		int nrDeleted = myActor.deleteActor(name);
		System.out.println("Nr of deleted actors : " + nrDeleted);
		
	}
	
}
