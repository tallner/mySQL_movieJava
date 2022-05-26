package beans;

import java.util.ArrayList;

import helpers.jsonHelper;
import helpers.keyvaluepair;

public class movieactorBean {
	private String _characterName;
	private int _movie_id;
	private int _actor_id;
	
	public String get_characterName() {
		return _characterName;
	}
	public void set_characterName(String _characterName) {
		this._characterName = _characterName;
	}
	public int get_movie_id() {
		return _movie_id;
	}
	public void set_movie_id(int _movie_id) {
		this._movie_id = _movie_id;
	}
	public int get_actor_id() {
		return _actor_id;
	}
	public void set_actor_id(int _actor_id) {
		this._actor_id = _actor_id;
	}
	
	public String toString() {
		String pattern = "Rollfigur = %s, MovieId = %d, ActorId = %d";
		String text = String.format(pattern, this._characterName, this._movie_id, this._actor_id);	

		return text;
	}

	public String toJson() {
		ArrayList<keyvaluepair> datalist = new ArrayList<keyvaluepair>();
		datalist.add(new keyvaluepair("Character", this._characterName));
		datalist.add(new keyvaluepair(Integer.toString(_movie_id), Integer.toString(_actor_id)));
		

		return jsonHelper.toJsonObject(datalist);
	}

}
