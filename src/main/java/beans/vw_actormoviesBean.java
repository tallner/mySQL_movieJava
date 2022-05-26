package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class vw_actormoviesBean {
	private String _name;
	private String _movies;
	
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String get_movies() {
		return _movies;
	}
	public void set_movies(String _movies) {
		this._movies = _movies;
	}
	
	
	public String toString() {
		String pattern = "Namn = %s, Film = %s";
		String text = String.format(pattern, this._name, this._movies);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Name", this._name));
		datalist.add(new keyvaluepair("Movies", this._movies));
		
		

		return jsonHelper.toJsonObject(datalist);
	}
	
	

}
