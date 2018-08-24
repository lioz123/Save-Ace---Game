package CharactersMenu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInputer extends KeyAdapter {
	KeyInterface object;
	public KeyInputer(KeyInterface object){
		this.object=object;
	}
	
	public void keyPressed(KeyEvent e){
		object.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		object.keyReleased(e);
	}
	
}
