package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import beans.moviegenreBean;
import helpers.jsonHelper;

public class relation_moviegenre {
	
	private Connection _connection;
	private ArrayList<moviegenreBean> _moviegenre_relations;
	
	private String createGenreToMovieRelation = "call sp_addGenreToMovie(?, ?)";
	private String readRelations = "SELECT * FROM movie_genre";
	
	public relation_moviegenre(Connection cn) {
		this._connection = cn;
		this._moviegenre_relations = new ArrayList<moviegenreBean>();
		getMoviegenre_relations();
	}
	
	public ArrayList<moviegenreBean> getMoviegenre_relations(){
		
		if (this._moviegenre_relations.size() > 0)
			return this._moviegenre_relations;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(readRelations)) {
			runQuery(myQry);
			
		} catch (SQLException e) {
			System.out.println("getMovieactor_relations exception for statement");
			e.printStackTrace();
		}
		
		return this._moviegenre_relations;
	}
	
	public int createGenreToMovieRelation(String genre, String movie_title) {
		int result = -1;
		
		if (genre.isBlank() || movie_title.isBlank()) return -1;
		
		try (CallableStatement cst = (CallableStatement) this._connection.prepareCall(createGenreToMovieRelation)) {
			cst.setString(1, genre);
			cst.setString(2, movie_title);
			result = cst.executeUpdate();
			
			
		}catch (SQLIntegrityConstraintViolationException e) {
			result = -2;
			
		}catch (SQLException e) {
			System.out.println("createGenreToMovieRelation exception for statement");
			e.printStackTrace();
		}
		
		return result;
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
