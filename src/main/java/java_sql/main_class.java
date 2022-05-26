package java_sql;

import java.sql.Connection;
import java.sql.SQLException;

import helpers.databaseHelper;
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
		

//		String jsonDoc = "{" + myActors.toJson() + ", " + myAddresses.toJson() + "}";
//		String jsonDoc = "{" + myActors.toJson() + "}";
//		String jsonDoc = "{" + myDirectors.toJson() + "}";
//		String jsonDoc = "{" + myGenres.toJson() + "}";
//		String jsonDoc = "{" + myMovies.toJson() + "}";
//		String jsonDoc = "{" + myMovieactor_relations.toJson() + "}";
//		String jsonDoc = "{" + myMoviegenres_relations.toJson() + "}";
//		String jsonDoc = "{" + myActormovies.toJson() + "}";
//		String jsonDoc = "{" + myMovieactors.toJson() + "}";
		String jsonDoc = "{" + myMovieinfoView.toJson() + "}";

		System.out.println(jsonDoc);

		conn.close();
	}
}
