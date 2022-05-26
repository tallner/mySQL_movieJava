package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.movieactorBean;
import helpers.jsonHelper;

public class movieactor_relations {
	
	private Connection _connection;
	private ArrayList<movieactorBean> _movieactor_relations;
	
	public movieactor_relations(Connection cn) {
		this._connection = cn;
		this._movieactor_relations = new ArrayList<movieactorBean>();
		getMovieactor_relations();
	}
	
	public ArrayList<movieactorBean> getMovieactor_relations(){
		String qry = "select * from movie_actor";
		
		if (this._movieactor_relations.size() > 0)
			return this._movieactor_relations;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)) {
			runQuery(myQry);
			
		} catch (SQLException e) {
			System.out.println("getMovieactor_relations exception for statement");
			e.printStackTrace();
		}
		
		return this._movieactor_relations;
	}
	
	public String toJson() {
		String beansContent = "";
		for (movieactorBean ma : this._movieactor_relations) {
			beansContent += ma.toJson() + ",";
		}

		return jsonHelper
				.toJsonArray("MovieActors", beansContent);
	}
	
	private movieactorBean buildMovieactor(ResultSet rs) {
		movieactorBean ma = new movieactorBean();

		try {
			ma.set_actor_id(rs.getInt("actor_id"));
			ma.set_movie_id(rs.getInt("movie_id"));
			ma.set_characterName(rs.getString("character_name"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ma;
	}
	
 	private void buildMovieactors(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._movieactor_relations.add(buildMovieactor(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovieactors(rs);
		} catch (SQLException e) {
			System.out.println("getActors exception for result set");
			e.printStackTrace();
		}

 	}

}
