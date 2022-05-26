package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class actorBean {
	
	private int _id;
	private String _name;
//	private String _hometown;
	private int _age;
//	private int _addressId;
	private String _skill;

	public void setId(int id) {
		this._id = id;
	}

	public int getId() {
		return this._id;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getName() {
		return this._name;
	}

	/** /
	public void setHometown(String hometown) {
		this._hometown = hometown;
	}

	public String getHometown() {
		return this._hometown;
	}
	/**/

	public void setAge(int age) {
		this._age = age;
	}

	public int getAge() {
		return this._age;
	}

	/** /
	public void setAddressId(int adressId) {
		this._addressId = adressId;
	}

	public int getAddressId() {
		return this._addressId;
	}
	/**/
	
	public void setSkill(String skill) {
		this._skill = skill;
	}

	public String getSkill() {
		return this._skill;
	}

	public String toString() {
		String pattern = "Namn = %s, Ålder = %d, Egenskap = %s";
		String text = String.format(pattern, this._name, this._age, this._skill);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Name", this._name));
		datalist.add(new keyvaluepair("Age", Integer.toString(this._age)));
		datalist.add(new keyvaluepair("Skill", this._skill));
		
		

		return jsonHelper.toJsonObject(datalist);
	}

}
