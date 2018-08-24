package CharactersMenu;

import java.awt.event.KeyEvent;

import MoiveEditor.TextArea;

public class CharArea  extends TextArea{
	public boolean isNumber=false;
	public CharArea(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
		// TODO Auto-generated constructor stub
	}
	
	public void KeyPressed(KeyEvent e){
		if(selected&&super.editable){
			System.out.println("key pressed " + e.getKeyChar());
		this.setString("" +e.getKeyChar());
		}
	}

}
