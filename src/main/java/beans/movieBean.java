package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class movieBean {
	private int _movie_id;
	private int _director_id;
	private String _title;
	private int _release_year;
	private int _length_minutes;
	
	public int getMovie_id() {
		return _movie_id;
	}
	public void setMovie_id(int movie_id) {
		this._movie_id = movie_id;
	}
	public int getDirector_id() {
		return _director_id;
	}
	public void setDirector_id(int director_id) {
		this._director_id = director_id;
	}
	public String getTitle() {
		return _title;
	}
	public void setTitle(String title) {
		this._title = title;
	}
	public int getRelease_year() {
		return _release_year;
	}
	public void setRelease_year(int release_year) {
		this._release_year = release_year;
	}
	public int getLength_minutes() {
		return _length_minutes;
	}
	public void setLength_minutes(int length_minutes) {
		this._length_minutes = length_minutes;
	}
	
	public String toString() {
		String pattern = "Titel = %s, Release≈r = %d, L‰ngdMinuter = %d";
		String text = String.format(pattern, this._title, this._release_year, this._length_minutes);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Title", this._title));
		datalist.add(new keyvaluepair("ReleaseYear", Integer.toString(this._release_year)));
		datalist.add(new keyvaluepair("LengthMinutes", Integer.toString(this._length_minutes)));
		
		

		return jsonHelper.toJsonObject(datalist);
	}
	
	

}
