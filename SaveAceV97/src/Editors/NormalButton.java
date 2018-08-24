package Editors;

import java.awt.image.BufferedImage;

public class NormalButton extends Button {

	public NormalButton(BufferedImage img, int x, int y, int width, int height, Type type) {
		super(img, x, y, width, height, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onClick(int x, int y) {
		
		return (isClicked(x, y));
	
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}


	


}
