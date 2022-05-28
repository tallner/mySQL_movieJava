package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.moviegenreBean;
import helpers.jsonHelper;

public class moviegenre_relations {
	
	private Connection _connection;
	private ArrayList<moviegenreBean> _moviegenre_relations;
	
	public moviegenre_relations(Connection cn) {
		this._connection = cn;
		this._moviegenre_relations = new ArrayList<moviegenreBean>();
		getMoviegenre_relations();
	}
	
	public ArrayList<moviegenreBean> getMoviegenre_relations(){
		String qry = "select * from movie_genre";
		
		if (this._moviegenre_relations.size() > 0)
			return this._moviegenre_relations;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)) {
			runQuery(myQry);
			
		} catch (SQLException e) {
			System.out.println("getMovieactor_relations exception for statement");
			e.printStackTrace();
		}
		
		return this._moviegenre_relations;
	}
	
	public String toJson() {
		String beansContent = "";
		for (moviegenreBean mg : this._moviegenre_relations) {
			beansContent += mg.toJson() + ",";
		}

		return jsonHelper
				.toJsonArray("MovieGenreRelations", beansContent);
	}
	
	private moviegenreBean buildMoviegenre(ResultSet rs) {
		moviegenreBean mg = new moviegenreBean();

		try {
			mg.set_genre_id(rs.getInt("genre_id"));
			mg.set_movie_id(rs.getInt("movie_id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mg;
	}
	
 	private void buildMoviegenres(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._moviegenre_relations.add(buildMoviegenre(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMoviegenres(rs);
		} catch (SQLException e) {
			System.out.println("getMoviegenre_relations exception for result set");
			e.printStackTrace();
		}

 	}

}
