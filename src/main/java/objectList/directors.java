package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.directorBean;
import helpers.jsonHelper;

public class directors {
	private Connection _connection;
	private ArrayList<directorBean> _directors;
	
	private String createDirector = "INSERT INTO director (name,city) VALUES (?,?) ON DUPLICATE KEY UPDATE director_id=director_id";
	private String readAllDirectors = "SELECT * FROM director";
	private String updateDirectorCity = "UPDATE director SET city=? WHERE name=?";
	private String deleteDirector = "DELETE FROM director WHERE name=?";

	public directors(Connection cn) {
		this._connection = cn;
		this._directors = new ArrayList<directorBean>();
		getDirectors();
	}

	public int createDirector(String name, String city) {
		int count = -1;
		
		if (name.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(createDirector)) {
			myQry.setString(1, name);
			myQry.setString(2, city);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("getActor exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public ArrayList<directorBean> getDirectors() {
		if (this._directors.size() > 0) 
			return this._directors;
			
		this._directors = new ArrayList<directorBean>();
		try {
			PreparedStatement myQry = this._connection.prepareStatement(readAllDirectors);//@ct skillnad mot att göra det i parantes?
			ResultSet rs = myQry.executeQuery();
			while(rs.next()) {  // rows
				this._directors.add(buildAddress(rs));
			}
			
			rs.close();
			myQry.close();			
		} catch (SQLException e) {
			System.out.println("getDirectors exception");
			e.printStackTrace();
		}

		return this._directors;
	}

	public int updateDirector(String name, String city) {
		int count = -1;
		
		if (name.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(updateDirectorCity)) {
			myQry.setString(1, name);
			myQry.setString(2, city);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("getActor exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}

	public int deleteDirector(String name) {
		int count = -1;
		
		if (name.isBlank()) return -1;
		
		try (PreparedStatement myQry = this._connection.prepareStatement(deleteDirector)) {
			myQry.setString(1, name);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("getActor exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}

	public String toJson() {
		String beansContent = "";
		for (directorBean db : this._directors) {
			beansContent += db.toJson() + ",";
		}

		return jsonHelper
				.toJsonArray("Directors", beansContent);
	}
	
 	private directorBean buildAddress(ResultSet rs) {
 		directorBean ab = new directorBean();

		try {
			ab.setId(rs.getInt("director_id"));
			ab.setName(rs.getString("name"));
			ab.setCity(rs.getString("city"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ab;
	}

 	
}
