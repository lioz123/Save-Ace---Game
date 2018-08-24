
package MapTrazlation;

import java.util.ArrayList;

import FilesUtils.ReadFile;
import General.FilmMannager;
import MoiveEditor.StringReader;

public class TranzlateCharacters {
	private  ArrayList<CharacterMovments> characters= new ArrayList<CharacterMovments>();
	private ArrayList<String> lines =  new ArrayList<String>();
	public TranzlateCharacters(){	
	}
	
	
	
	public void generateLines(){
		
	}
	
	
	public void GetFilmByBlock(String BlockName,String endBlock , String name ,  String path){
		
	}
	
	
	public void GenerateCharactersMovment(String path){
		ArrayList<String> lines = new ArrayList<String>();
		ReadFile fileReader = new ReadFile();
		fileReader.openFile(path);
		lines = fileReader.getLines();
		fileReader.close();
		fileReader.openFile(path);
	//	StringReader reader = new StringReader(fileReader.getFileText());
		StringReader reader;
		fileReader.close();
		
		int i = 0;
		while(i<lines.size()){
			String line = lines.get(i);
			reader = new StringReader(line);
			while(reader.hasNext()){
				
				String str = reader.getNext();
				if(str.equals("Character:")){
					CharacterMovments character = new CharacterMovments(reader.getNext());
					boolean firstTime = true;
					while(!line.equals("Character-end")){
						i++;
						
					
						line = lines.get(i);
					if(!line.equals("Character-end")){
						
					
						ArrayList<String> tempLines = new ArrayList<String>();
						while(!line.equals("end")){
							
							line = lines.get(i);
							tempLines.add(line);
							i++;
						}
				
			
						if(tempLines.size()>0){
						//	tempLines.add("end");
							TranzlateMovie tm = new TranzlateMovie(path,tempLines.get(0));
							tm.setlines(tempLines);
							tm.TranzlateToObjectNoLineTranzlate();
						;
							character.addFilm(new FilmMannager(tm.getFilm(),tm.getFilm()));
							character.getFilms().get(character.getFilms().size()-1).setName(tempLines.get(0));
					
							i--;
						}
					
					}
					}
					this.characters.add(character);
				}
			}
			i++;
		}
		
		
		
		
	
		
	/*	boolean foundCharacter = false;
		while(reader.hasNext()){
			String str = reader.getNext();
			switch(str){
			case "Character:":
				foundCharacter=true;
		     	CharacterMovments character = new CharacterMovments(reader.getNext());
		     	while(!str.equals("Character-end:")&&reader.hasNext()){
					
					str = reader.getNext();
					TranzlateMovie mt = new TranzlateMovie(path,str);
					mt.TranzlateObject();
					FilmMannager film = new FilmMannager(mt.getFilm(),mt.getFilm());
					character.addFilm(film);
					while(!str.equals("end")&&reader.hasNext()){
						str = reader.getNext();
					}
				}
				this.characters.add(character);
				
				
				
				break;
				
				default:
				
					break;
			}
			
			
		}
		*/
	}
	
	public  CharacterMovments getCharacterMovments(String name){
		for(CharacterMovments character : characters){
			if(character.getName().equals(name)){
				return character;
			}
		}
		return null;
	}
	
	public FilmMannager getFilmByCharacterName(String characterName, String filmName){
		for(CharacterMovments character : characters){
			if(character.getName().equals(characterName)){
				for(FilmMannager film: character.getFilms()){
					if(film.getName().equals(filmName)){
						return film;
					}
				}
			}
		}
		return null;
	}



	public ArrayList<CharacterMovments> getCharacterMovments() {
		// TODO Auto-generated method stub
		 return this.characters;
	}


}
