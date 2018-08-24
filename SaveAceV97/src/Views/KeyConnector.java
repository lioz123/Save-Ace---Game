package Views;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyConnector extends KeyAdapter {
	
	private Container container;
	
	public KeyConnector(Container container){
		this.container=container;
	}
	
	public void keyPressed(KeyEvent e){
		container.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		container.keyReleased(e);
	}
	
	
	
}
