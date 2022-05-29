package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.vw_movieinfoBean;
import helpers.jsonHelper;

public class vw_movieinfo {
	
	private Connection _connection;
	private ArrayList<vw_movieinfoBean> _movieinfo;
	
	private String readView = "SELECT * FROM vw_movieInfo";
	
	
	public vw_movieinfo(Connection cn) {
		this._connection = cn;
		this._movieinfo = new ArrayList<vw_movieinfoBean>();
		getActorMovies();
	}
	
	public ArrayList<vw_movieinfoBean> getActorMovies() {

		if (this._movieinfo.size() > 0) 
			return this._movieinfo;
			
		try (PreparedStatement myQry = this._connection.prepareStatement(readView)) {
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getMovieInfo exception for statement");
			e.printStackTrace();
		}
		
		return this._movieinfo;
	}
	
	public String toJson() {
		String beansContent = "";
		for (vw_movieinfoBean mi : this._movieinfo) {
			beansContent += mi.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("MovieInfo", beansContent);
	}

 	private vw_movieinfoBean buildActor(ResultSet rs) {
 		vw_movieinfoBean mi = new vw_movieinfoBean();

		try {
			mi.set_title(rs.getString("title"));
			mi.set_genre(rs.getString("genre"));
			mi.set_directorname(rs.getString("director_name"));
			mi.set_releaseyear(rs.getString("release_year"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mi;
	}
	
 	private void buildActors(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._movieinfo.add(buildActor(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildActors(rs);
		} catch (SQLException e) {
			System.out.println("getMovieInfo exception for result set");
			e.printStackTrace();
		}

 	}


}
