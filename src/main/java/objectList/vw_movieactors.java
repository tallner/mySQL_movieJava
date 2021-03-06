package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.vw_movieactorsBean;
import helpers.jsonHelper;

public class vw_movieactors {
	private Connection _connection;
	private ArrayList<vw_movieactorsBean> _movieactors;
	
	private String readView = "SELECT * FROM vw_movieActors";
	
	public vw_movieactors(Connection cn) {
		this._connection = cn;
		this._movieactors = new ArrayList<vw_movieactorsBean>();
		getActorMovies();
	}
	
	public ArrayList<vw_movieactorsBean> getActorMovies() {

		if (this._movieactors.size() > 0) 
			return this._movieactors;
			
		try (PreparedStatement myQry = this._connection.prepareStatement(readView)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getMovieActors exception for statement");
			e.printStackTrace();
		}
		
		return this._movieactors;
	}
	
	public String toJson() {
		String beansContent = "";
		for (vw_movieactorsBean ma : this._movieactors) {
			beansContent += ma.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("MovieActors", beansContent);
	}

 	private vw_movieactorsBean buildActor(ResultSet rs) {
 		vw_movieactorsBean ma = new vw_movieactorsBean();

		try {
			ma.set_title(rs.getString("title"));
			ma.set_actors(rs.getString("actors"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ma;
	}
	
 	private void buildActors(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._movieactors.add(buildActor(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildActors(rs);
		} catch (SQLException e) {
			System.out.println("getMovieActors exception for result set");
			e.printStackTrace();
		}

 	}

}
