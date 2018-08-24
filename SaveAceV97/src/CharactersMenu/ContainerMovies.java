package CharactersMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Main.Game;
import MapTrazlation.Characters;
import Views.Container;

public class ContainerMovies extends Container{
	private StringDisplay title;
	private Game game;
	private ArrayList<MovieKeysBar> movies= new ArrayList<MovieKeysBar>();
	private String character;
	private CharacterBar bar;
	public ContainerMovies(Game game,String character,CharactersMenu menu){
		
		bar = new CharacterBar(20,120,game,this,menu,character);
		clickAbles.add(bar);
		this.objects.add(bar);
		this.character=character;
		this.game=game;
		stringsDisplay=new ArrayList<StringDisplay>();
		
		title = new StringDisplay("Movies",150,150,game);
		title.setFont(new Font("Tahoma",Font.BOLD,20));
		title.CalculateBounds();
		title.setMiddleX(0, Game.WIDTH);
		stringsDisplay.add(title);
		ArrayList<String>moviesNames= Characters.getCharacter(character).getMoviesName();
		int i =0;
		for(String name:moviesNames){
			MovieKeysBar bar = new MovieKeysBar(name,150,250+i*50,game,character,this);
			movies.add(bar);
			i++;
		}
		
		
		}
	
	
	public String getCharacterName(){
		return this.character;
	}
	
	@Override
	public void tick() {
	
		
	}
	
	public void removeMovie(MovieKeysBar movie){
		movies.remove(movie);
	}
	
	public void render(Graphics2D g2d){
		if(show){
			super.render(g2d);
			for(MovieKeysBar movie:movies){
				movie.render(g2d);
			}
			
			
			
		}
		
	}
	
	public boolean onClick(int x, int y){
		super.onClick(x, y);
		for(MovieKeysBar bar:movies){
			bar.onClick(x,y);
		}
		return true;
	}
	
	public void keyPressed(KeyEvent e){
		super.keyPressed(e);
		for(MovieKeysBar bar:movies){
			bar.keyPressed(e);
		}
	}

}
