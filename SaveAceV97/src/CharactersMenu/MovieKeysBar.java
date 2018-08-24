package CharactersMenu;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Editors.ClickAble;
import Editors.NormalButton;
import Editors.Type;
import FilesUtils.FileEditor;
import Main.Game;
import MapTrazlation.Characters;
import MapTrazlation.SpriteSheetList;
import MoiveEditor.CounterButtonDisplayValue;
import MoiveEditor.MultyModeButton;
import MoiveEditor.TextArea;
import QestionMenu.QeustionAble;

public class MovieKeysBar implements QeustionAble,ClickAble {
	private Color color=Color.gray;
	private StringDisplay string;
	private MultyModeButton specialKeys;
	private CharArea keyDisplay;
	private ButtonTextAdvanced loadMovie,delte;
	private Game game;
	private String character,movie,path;
	private boolean answer=false;
	private boolean answerRecieved=false;
	private ContainerMovies containerMovie;
	private CounterButtonDisplayValue fps;
	
	public MovieKeysBar(String movie,int x,int y , Game game,String character,ContainerMovies containerMovie){
		this.containerMovie=containerMovie;
		this.game=game;
		String keyContent = movie;
		this.movie=movie;
		this.character=character;
		path = Characters.getCharacter(character).getFilmByName(movie).getFilmRight().getPath();
	//	defenceCounter.setRect(new Rectangle(defenceCounter.getX()+defenceCounter.getWidth()-10,defenceCounter.getY(),35,35));
		fps  = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+375, y,30,30,Type.counterUpDown,Characters.getCharacter(character).getFilmByName(movie).getFilmLeft().getFpsOposite(),1,100,0);
		fps.setRect(new Rectangle(fps.getX()+fps.getWidth(),fps.getY(),35,30));
		
		loadMovie = new ButtonTextAdvanced("Load", fps.getWidth()+fps.getX()+45, y, game);
		loadMovie.setHeight(30);
		loadMovie.setStringToCenter();
		
		
		
		

		
		delte = new ButtonTextAdvanced("Delte", loadMovie.getWidth()+loadMovie.getX()+10, y, game);
		delte .setHeight(30);
		delte .setStringToCenter();
	
		
		//l	string.setMiddle(0,0,Game.WIDTH,Game.HEIGHT);
		keyDisplay= new  CharArea(x+250, y, 50, 30,"");
		ArrayList<String> modes = new ArrayList<String>();
		modes.add("None");
		modes.add("Control");
		modes.add("Shift");
		modes.add("Alt");
		modes.add("Space");
		specialKeys = new MultyModeButton(null, x+320,y,60,30, Editors.Type.noraml, modes);
		
		
		string = new StringDisplay(movie,x,y,game);
		string.CalculateBounds();
		string.setMiddleY(y, 30);
		if(Characters.getCharacter(character).getFilmByName(movie).getFilmRight().getKey()!=null){
			keyDisplay.setString(Characters.getCharacter(character).getFilmByName(movie).getFilmRight().getKey());

		}
		if(Characters.getCharacter(character).getFilmByName(movie).getFilmRight().getSpecialKey()!=null){
			specialKeys.setMode(Characters.getCharacter(character).getFilmByName(movie).getFilmRight().getSpecialKey());
		}
	}
	
	public void render(Graphics2D g2d){
		string.render(g2d);
		keyDisplay.render(g2d);
		specialKeys.render(g2d);
		loadMovie.render(g2d);
		delte.render(g2d);
		fps.render(g2d);
	}

	public boolean onClick(int x, int y) {
		keyDisplay.onClick(x, y);
		if(fps.onClick(x, y)){
			Characters.getCharacter(character).getFilmByName(movie).getFilmLeft().setFpsOposite(fps.getValue());
			Characters.getCharacter(character).getFilmByName(movie).getFilmRight().setFpsOposite(fps.getValue());

		}
		
		
		if(specialKeys.isClicked(x,y)){
			specialKeys.onClick(x, y);
			System.out.println(this.getClass() + " special key have been set " + specialKeys.getMode());
			Characters.getCharacter(character).getFilmByName(movie).setSpecialKey(specialKeys.getMode());
		}
		
		if(loadMovie.isClicked(x,y)){
			
			game.loadMovie(character,movie,path);
		}
		
		if(delte.isClicked(x,y)){
			
			game.OpenQuestion("Are You Sure You Want to delete Movie: " + movie +"?",this);
		}
		return false;
	}
	
	public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();
		if(key!=KeyEvent.VK_ESCAPE){
			keyDisplay.KeyPressed(e);
			if(keyDisplay.isSelected()){
				Characters.getCharacter(character).getFilmByName(movie).setOpenKey(e.getKeyChar()+"");

			}
			System.out.println(this.getClass() + "open key is " + Characters.getCharacter(character).getFilmByName(movie).getKey());
		}

	}

	@Override
	public void reciveQeustion(boolean b) {
		answer=b;
		answerRecieved=true;
		if(b){
			System.out.println(this.getClass().toString() + " delted movie " + character+" " + movie );
			FileEditor file = new FileEditor();
			
			file.setPath("Levels/movies.txt");
			ArrayList<String> strings = new ArrayList<String>();
			file.editFromBlockName("Character: " +character,"Character-end",movie,strings, "end");	
			containerMovie.removeMovie(this);	
			
		}
		
		
	}

	@Override
	public boolean isClicked(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
