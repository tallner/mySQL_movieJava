package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import beans.movieBean;
import helpers.jsonHelper;

public class movies {
	private Connection _connection;
	private ArrayList<movieBean> _movies;
	
	//call sp_addMovie(movie_title, movie_release_year, movie_length, director_name, director_city);
	private String createMovie = "{call sp_addMovie(?, ?, ?, ?)}";
	private String readAllMovies = "SELECT * FROM movie";
	private String updateMovieLength = "UPDATE movie SET lenght_minutes=? WHERE title=?";
	private String deleteMovie = "DELETE FROM movie WHERE title=?";

	
	public movies(Connection cn) {
		this._connection = cn;
		this._movies = new ArrayList<movieBean>();
		getMovies();
		
	}
	

	public int createMovie(
			String movie_title,int movie_release_year,int movie_length,
			String director_name) {
		int result = -1;
		
		if (movie_title.isBlank() || 
			movie_release_year<1600 || 
			movie_length==0 || 
			director_name.isBlank()) return -1;
		
		try (CallableStatement cst = (CallableStatement) this._connection.prepareCall(createMovie)) {
			cst.setString(1, movie_title);
			cst.setInt(2, movie_release_year);
			cst.setInt(3, movie_length);
			cst.setString(4, director_name);
			result = cst.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("createMovie exception for statement");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<movieBean> getMovies() {
		if (this._movies.size() > 0) 
			return this._movies;
			
		this._movies = new ArrayList<movieBean>(); //@ct why initiate a new list again?
		try (PreparedStatement myQry = this._connection.prepareStatement(readAllMovies)) { //@ct why try inside ()?
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getMovies exception for statement");
			e.printStackTrace();
		}
		
		return this._movies;
	}
	
	public int updateMovieLength(String title, int newLength) {
		int count = -1;
		
		if (title.isBlank() || newLength==0) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(updateMovieLength)) {
			myQry.setInt(1, newLength);
			myQry.setString(2, title);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("updateMovieLength exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int deleteMovie(String title) {
		   
		int count = -1;
		try (PreparedStatement myQry = this._connection.prepareStatement(deleteMovie)) {
			myQry.setString(1, title);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("deleteMovie exception for statement");
			e.printStackTrace();
		}
		
		return count;
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
