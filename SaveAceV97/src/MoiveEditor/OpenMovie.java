package MoiveEditor;

import java.awt.image.BufferedImage;

import Editors.Button;
import Editors.Type;
import MapTrazlation.TranzlateMovie;

public class OpenMovie extends Button{
	private MovieEditor editor;
	public OpenMovie(BufferedImage img, int x, int y, int width, int height, Type type,MovieEditor editor) {
		super(img, x, y, width, height, type);
		// TODO Auto-generated constructor stub
	this.editor=editor;
	}
	
	

	@Override
	public boolean onClick(int x, int y) {
		if(isClicked(x, y)){
		editor.loadMovie();
		}
		return false;
	}



	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
