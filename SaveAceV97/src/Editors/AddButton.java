package Editors;

import java.awt.image.BufferedImage;

public class AddButton extends Button{
	int value = 0;
	int delta = 1;
	public AddButton(BufferedImage img, int x, int y, int width, int height, Type type,Slot slot,EditComponetsHandler handler) {
		super(img, x, y, width, height, type);
	}

	@Override
	public boolean onClick(int x, int y) {
		if(isClicked(x, y)){
			return true;
		}
		return false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}


}
