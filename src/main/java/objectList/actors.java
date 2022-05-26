package objectList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import beans.actorBean;
import helpers.jsonHelper;

public class actors {
	private Connection _connection;
	private ArrayList<actorBean> _actors;
	
	public actors(Connection cn) {
		this._connection = cn;
		this._actors = new ArrayList<actorBean>();
		getActors();
	}
	
	public ArrayList<actorBean> getActors() {
		String qry = "select * from actor";

		if (this._actors.size() > 0) 
			return this._actors;
			
		this._actors = new ArrayList<actorBean>(); //@ct why initiate a new list again?
		try (PreparedStatement myQry = this._connection.prepareStatement(qry)) { //@ct why try inside ()?
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return this._actors;
	}
	
	public String toJson() {
		String beansContent = "";
		for (actorBean ab : this._actors) {
			beansContent += ab.toJson() + ",";
		}
		
		return jsonHelper.toJsonArray("Actors", beansContent);
	}

 	private actorBean buildActor(ResultSet rs) {
		actorBean ab = new actorBean();

		try {
			ab.setId(rs.getInt("actor_id"));
			ab.setAge(rs.getInt("age"));
			ab.setName(rs.getString("name"));
			ab.setSkill(rs.getString("skill"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ab;
	}
	
 	private void buildActors(ResultSet rs) throws SQLException {
 		while(rs.next()) {  // rows
			this._actors.add(buildActor(rs));
		}
 	}
 	
 	private void runQuery(PreparedStatement query) {
		try (ResultSet rs = query.executeQuery()) {
			buildActors(rs);
		} catch (SQLException e) {
			System.out.println("getActors exception for result set");
			e.printStackTrace();
		}

 	}
}
