package MoiveEditor;

import java.awt.image.BufferedImage;

import Editors.Button;
import Editors.Type;

public class SaveMovie extends Button {
	private MovieEditor editor;
	public SaveMovie(BufferedImage img, int x, int y, int width, int height, Type type,MovieEditor editor) {
		super(img, x, y, width, height, type);
		// TODO Auto-generated constructor stub
		this.editor = editor;
	}

	@Override
	public boolean onClick(int x, int y) {
		 boolean isClicked =super.isClicked(x, y);
		if(isClicked){
			editor.save();
		}
		return isClicked;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}

