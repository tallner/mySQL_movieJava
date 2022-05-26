package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class vw_movieactorsBean {
	private String _title;
	private String _actors;
	
	public String get_title() {
		return _title;
	}
	public void set_title(String _title) {
		this._title = _title;
	}
	public String get_actors() {
		return _actors;
	}
	public void set_actors(String _actors) {
		this._actors = _actors;
	}
	
	public String toString() {
		String pattern = "Titel = %s, Skådespelare = %s";
		String text = String.format(pattern, this._title, this._actors);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Title", this._title));
		datalist.add(new keyvaluepair("Actors", this._actors));
		
		return jsonHelper.toJsonObject(datalist);
	}

}
