


package MainMenu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Main.Game;

public class EscapeInput extends KeyAdapter{
	private Game game;
	public EscapeInput(Game game){
		this.game=game;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE){
			game.handleHoldMenu();
		}
	}
	
	
	
}
