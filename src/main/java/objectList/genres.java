package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.genreBean;
import helpers.jsonHelper;

public class genres {
	//connection
	//list of objects
	//get objects
	//to json
	//runquery
	//build resultset into json string
	private Connection _connection;
	private ArrayList<genreBean> _genres;
	
	public genres(Connection cn) {
		this._connection = cn;
		this._genres = new ArrayList<genreBean>();
		get_genres();
	}

	public ArrayList<genreBean> get_genres() {
		String qry = "select * from genre";
		
		
		//this._genres = new ArrayList<genreBean>();
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)) { //@ct why try inside ()?
			try (ResultSet rs = myQry.executeQuery()) {
				
				while(rs.next()) {  // rows
					genreBean gb = new genreBean();
					gb.setId(rs.getInt("genre_id"));
					gb.setGenre(rs.getString("genre"));
					
					this._genres.add(gb);
				}
				
			} catch (SQLException e) {
				System.out.println("getGenres exception for result set");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("getGenres exception for statement");
			e.printStackTrace();
		}
		
		return _genres;
	}
	
	public String toJson() {
		String beansContent = "";
		for (genreBean gb : this._genres) {
			beansContent += gb.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Genres", beansContent);
	}
	
	


}
