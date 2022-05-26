package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class moviegenreBean {
	private int _movie_id;
	private int _genre_id;
	
	public int get_movie_id() {
		return _movie_id;
	}
	public void set_movie_id(int _movie_id) {
		this._movie_id = _movie_id;
	}
	public int get_genre_id() {
		return _genre_id;
	}
	public void set_genre_id(int _genre_id) {
		this._genre_id = _genre_id;
	}
	
	public String toString() {
		String pattern = "MovieId = %d, GenreId = %d, Egenskap = %s";
		String text = String.format(pattern, this._movie_id, this._genre_id);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair(Integer.toString(this._movie_id), Integer.toString(this._genre_id)));
		
		

		return jsonHelper.toJsonObject(datalist);
	}
	
	

}
