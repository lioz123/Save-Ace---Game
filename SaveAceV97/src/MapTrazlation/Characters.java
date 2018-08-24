package MapTrazlation;

import java.util.ArrayList;

import General.FilmMannager;

public class Characters {
	public static ArrayList<CharacterMovments> characters = new ArrayList<CharacterMovments>();
	public static void setCharacters(ArrayList<CharacterMovments> characters){
		Characters.characters=characters;
	}
	
	public static CharacterMovments getCharacter(String name){
		for(CharacterMovments character : characters){
			if(character.getName().equals(name)){
				return character;
			}
		}
		return null;
	}
	
	
	public static ArrayList<String> getCharactersList(){
		ArrayList<String>names = new ArrayList<String>();
		for(CharacterMovments character:characters){
			
			names.add(character.getName());
		}
		return names;
	}
	
	public static ArrayList<CharacterKeysInfo> getkeysInfo(){
		ArrayList<CharacterKeysInfo> keys = new ArrayList<CharacterKeysInfo>();
		for(CharacterMovments character : characters){
		for(FilmMannager film : character.getFilms()){
			keys.add(new CharacterKeysInfo(character.getName(),film.getName(), film.getKey(), film.getSpecialKey()));
		}
		}
		return keys;
		
	}
	
	
}
