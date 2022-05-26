package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.movieBean;
import helpers.jsonHelper;

public class movies {
	private Connection _connection;
	private ArrayList<movieBean> _movies;
	
	public movies(Connection cn) {
		this._connection = cn;
		this._movies = new ArrayList<movieBean>();
		getMovies();
		
	}
	
	public ArrayList<movieBean> getMovies() {
		String qry = "select * from movie";

		if (this._movies.size() > 0) 
			return this._movies;
			
		this._movies = new ArrayList<movieBean>(); //@ct why initiate a new list again?
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)) { //@ct why try inside ()?
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return this._movies;
	}
	
	public String toJson() {
		String beansContent = "";
		for (movieBean ab : this._movies) {
			beansContent += ab.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Movies", beansContent);
	}

 	private movieBean buildMovie(ResultSet rs) {
 		movieBean mb = new movieBean();

		try {
			mb.setMovie_id(rs.getInt("movie_id"));
			mb.setDirector_id(rs.getInt("director_id"));
			mb.setTitle(rs.getString("title"));
			mb.setRelease_year(rs.getInt("release_year"));
			mb.setLength_minutes(rs.getInt("length_minutes"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mb;
	}
	
 	private void buildMovies(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._movies.add(buildMovie(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildMovies(rs);
		} catch (SQLException e) {
			System.out.println("getMovies exception for result set");
			e.printStackTrace();
		}

 	}
	
	


}
