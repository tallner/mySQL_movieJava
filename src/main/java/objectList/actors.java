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
	
	private String createActor = "INSERT INTO actor (name,age,skill) VALUES (?,?,?)";
	private String readAllActors = "SELECT * FROM actor";
	private String updateActors = "UPDATE actor SET age=?, skill=? WHERE name=?";
	private String updateActorsSkill = "UPDATE actor SET skill=? WHERE skill=?";
	private String deleteActors = "DELETE FROM actor WHERE name=?";
	
	public actors(Connection cn) {
		this._connection = cn;
		this._actors = new ArrayList<actorBean>();
		getActors();
	}
	
	public ArrayList<actorBean> getActors() {
		if (this._actors.size() > 0) 
			return this._actors;
			
		this._actors = new ArrayList<actorBean>(); //@ct why initiate a new list again?
		try (PreparedStatement myQry = this._connection.prepareStatement(readAllActors)) { //@ct why try inside ()?
			runQuery(myQry);
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return this._actors;
	}
	
	public int createActor(String name, int age, String skill) {
		   
		int count = -1;
		try (PreparedStatement myQry = this._connection.prepareStatement(createActor)) {
			myQry.setString(1, name);
			myQry.setInt(2, age);
			myQry.setString(3, skill);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int updateActors(String name, String newSkill, int newAge) {
		   
		int count = -1;
		try (PreparedStatement myQry = this._connection.prepareStatement(updateActors)) {
			myQry.setInt(1, newAge);
			myQry.setString(2, newSkill);
			myQry.setString(3, name);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int updateActorsSkill(String oldSkill, String newSkill) {
		   
		int count = -1;
		try (PreparedStatement myQry = this._connection.prepareStatement(updateActorsSkill)) {
			myQry.setString(1, newSkill);
			myQry.setString(2, oldSkill);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int deleteActor(String name) {
		   
		int count = -1;
		try (PreparedStatement myQry = this._connection.prepareStatement(deleteActors)) {
			myQry.setString(1, name);
			count = myQry.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("getActors exception for statement");
			e.printStackTrace();
		}
		
		return count;
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
