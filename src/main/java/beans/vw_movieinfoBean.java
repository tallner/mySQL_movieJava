package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class vw_movieinfoBean {
	private String _title;
	private String _genre;
	private String _directorname;
	private String _releaseyear;
	
	public String get_title() {
		return _title;
	}
	public void set_title(String _title) {
		this._title = _title;
	}
	public String get_genre() {
		return _genre;
	}
	public void set_genre(String _genre) {
		this._genre = _genre;
	}
	public String get_directorname() {
		return _directorname;
	}
	public void set_directorname(String _directorname) {
		this._directorname = _directorname;
	}
	public String get_releaseyear() {
		return _releaseyear;
	}
	public void set_releaseyear(String _releaseyear) {
		this._releaseyear = _releaseyear;
	}
	
	public String toString() {
		String pattern = "Titel = %s, Genre = %s, Regissör = %s, Release = %s";
		String text = String.format(pattern, this._title, this._genre, this._directorname, this._releaseyear);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Title", this._title));
		datalist.add(new keyvaluepair("Genre", this._genre));
		datalist.add(new keyvaluepair("Director", this._directorname));
		datalist.add(new keyvaluepair("Release", this._releaseyear));
		
		

		return jsonHelper.toJsonObject(datalist);
	}

}
