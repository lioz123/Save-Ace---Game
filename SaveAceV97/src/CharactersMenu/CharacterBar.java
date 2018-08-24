package CharactersMenu;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Editors.ClickAble;
import FilesUtils.FileEditor;
import Main.Game;
import Main.PhiyscalObject;
import QestionMenu.QeustionAble;

public class CharacterBar implements PhiyscalObject , QeustionAble ,ClickAble{
	private Game game;
	private CharactersMenu menu;
	private String character;
	private StringDisplay title;
	private ArrayList<PhiyscalObject> objects = new ArrayList<PhiyscalObject>(); 
	private ButtonTextAdvanced delte;
	private ArrayList<ClickAble> clicks = new ArrayList<ClickAble>();
	private ContainerMovies containerMovies;
	public CharacterBar(int x, int y,Game game , ContainerMovies containerMovies, CharactersMenu menu,String character){
	this.containerMovies=containerMovies;

		this.menu=menu;
		this.game=game;
		this.character=character;
		title=  new StringDisplay("Character: " + character, x, y, game);
		title.CalculateBounds();
		delte = new ButtonTextAdvanced("Delete", x+title.getWidth()+5, y, game);
		delte.setStringToCenter();
		title.setMiddleY(y, delte.getHeight());;
		title.CalculateBounds();
		objects.add(delte);
		objects.add(title);
		clicks.add(delte);
	}
	
	@Override
	public void render(Graphics2D g2d) {
		for(PhiyscalObject object: objects){
			object.render(g2d);
		}
		
		
	}
	


	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reciveQeustion(boolean b) {
		if(b){
			this.menu.removeCharacter(containerMovies);

			FileEditor file = new FileEditor();
			file.setPath("Levels/Movies.txt");
			file.EditFileFrom("Character: " + character, new ArrayList<String>(), "Character-end");
			file.close();
		}
		
	}
	
	
	@Override
	public boolean isClicked(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onClick(int x, int y) {
		if(delte.isClicked(x, y)){
			game.OpenQuestion("Are You Sure You Want to delete Character: " + character+"?",this);
		}
		return false;
	}

}
