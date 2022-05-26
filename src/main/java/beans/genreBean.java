package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class genreBean {
	private int _id;
	private String _genre;
	
	public int setId(int i) {
		return _id;
	}
	public void getId(int id) {
		this._id = id;
	}
	public String getGenre() {
		return this._genre;
	}
	public void setGenre(String genre) {
		this._genre = genre;
	}
	
	public String toString() {
		String pattern = "Genre = %s";
		String text = String.format(pattern, this._genre);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Genre", this._genre));
		

		return jsonHelper.toJsonObject(datalist);
	}
	
	

}
