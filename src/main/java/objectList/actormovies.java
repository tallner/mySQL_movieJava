package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.vw_actormoviesBean;
import helpers.jsonHelper;

public class actormovies {
	
	private Connection _connection;
	private ArrayList<vw_actormoviesBean> _actormovies;
	
	public actormovies(Connection cn) {
		this._connection = cn;
		this._actormovies = new ArrayList<vw_actormoviesBean>();
		getActorMovies();
	}
	
	public ArrayList<vw_actormoviesBean> getActorMovies() {
		String qry = "select * from vw_actorMovies";

		if (this._actormovies.size() > 0) 
			return this._actormovies;
			
		this._actormovies = new ArrayList<vw_actormoviesBean>(); //@ct why initiate a new list again?
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)) { //@ct why try inside ()?
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActorMovies exception for statement");
			e.printStackTrace();
		}
		
		return this._actormovies;
	}
	
	public String toJson() {
		String beansContent = "";
		for (vw_actormoviesBean am : this._actormovies) {
			beansContent += am.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("ActorMovies", beansContent);
	}

 	private vw_actormoviesBean buildActorMovie(ResultSet rs) {
 		vw_actormoviesBean am = new vw_actormoviesBean();

		try {
			am.set_movies(rs.getString("movies"));
			am.set_name(rs.getString("name"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return am;
	}
	
 	private void buildActorMovies(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._actormovies.add(buildActorMovie(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildActorMovies(rs);
		} catch (SQLException e) {
			System.out.println("getActorMovies exception for result set");
			e.printStackTrace();
		}

 	}

}
