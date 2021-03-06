package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import beans.movieactorBean;
import helpers.jsonHelper;

public class relation_movieactor {
	
	private Connection _connection;
	private ArrayList<movieactorBean> _movieactor_relations;
	
	private String createActorToMovieRelation = "call sp_addMovieToActor(?, ?)";
	private String readRelations = "SELECT * FROM movie_actor";
	
	public relation_movieactor(Connection cn) {
		this._connection = cn;
		this._movieactor_relations = new ArrayList<movieactorBean>();
		getMovieactor_relations();
	}
	
	public ArrayList<movieactorBean> getMovieactor_relations(){
		
		if (this._movieactor_relations.size() > 0)
			return this._movieactor_relations;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(readRelations)) {
			runQuery(myQry);
			
		} 
		
		
		
		catch (SQLException e) {
			System.out.println("getMovieactor_relations exception for statement");
			e.printStackTrace();
		}
		
		return this._movieactor_relations;
	}
	
	public int createActorToMovieRelation(String actor_name, String movie_title) {
		int result = -1;
		
		if (actor_name.isBlank() || movie_title.isBlank()) return -1;
		
		try (CallableStatement cst = (CallableStatement) this._connection.prepareCall(createActorToMovieRelation)) {
			cst.setString(1, actor_name);
			cst.setString(2, movie_title);
			result = cst.executeUpdate();
			
			
		}catch (SQLIntegrityConstraintViolationException e) {
			result = -2;
			
		} catch (SQLException e) {
			System.out.println("createActorToMovieRelation exception for statement");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String toJson() {
		String beansContent = "";
		for (movieactorBean ma : this._movieactor_relations) {
			beansContent += ma.toJson() + ",";
		}

		return jsonHelper
				.toJsonArray("MovieActorRelations", beansContent);
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
			System.out.println("getMovieActorRelations exception for result set");
			e.printStackTrace();
		}

 	}

}
