package MapTrazlation;

import java.util.ArrayList;

import General.Film;
import General.FilmMannager;

public class CharacterMovments {
private ArrayList<FilmMannager> films;
private String name;

	

	public CharacterMovments(String name){
		this.name = name;
		films = new ArrayList<FilmMannager>();
	}
	
	public FilmMannager getFlimByKey(String key){
		for(FilmMannager film : films){
			if(film.getKey().equals(key)){
				return film;
			}
		}
		
		return null;
	}
	
	
	public FilmMannager getFilmByName(String name){
		for(FilmMannager film:films){
			if(film.getName().equals(name)){
				return film;
			}
		}
		return null;
	}
	
	public void setName(String name){
		this.name= name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<FilmMannager> getFilms(){
		return this.films;
	}
	
	public void addFilm(FilmMannager film){
		this.films.add(film);
	}

	public ArrayList<String> getMoviesName() {
		ArrayList<String> names = new ArrayList<String>();
		for(FilmMannager film: films){
			names.add(film.getName());
		}
		return names;
	}
	
}
