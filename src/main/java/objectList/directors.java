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

	public directors(Connection cn) {
		this._connection = cn;
		this._directors = new ArrayList<directorBean>();
		getDirectors();
	}

	public ArrayList<directorBean> getDirectors() {
		String qry = "select * from director";

		if (this._directors.size() > 0) 
			return this._directors;
			
		this._directors = new ArrayList<directorBean>();
		try {
			PreparedStatement myQry = this._connection.prepareStatement(qry);//@ct skillnad mot att göra det i parantes?
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
