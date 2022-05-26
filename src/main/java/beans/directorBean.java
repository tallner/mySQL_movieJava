package beans;

public class directorBean {
	private int _id;
	private String _name;
	private String _city;
	
	public int getId() {
		return _id;
	}
	
	public void setId(int id) {
		this._id = id;
	}
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		this._city = city;
	}

	public String toString() {
		String pattern = "Namn = %s, Stad = %s";
		String returnString = String.format(pattern, this._name, this._city);	

		return returnString;
	}
	
	public String toJson() {
		String pattern = "{ \"Name\": \"%s\", \"City\": \"%s\"}";
		String returnString = String.format(pattern, this._name, this._city);	
		
		return returnString;
	}
}

