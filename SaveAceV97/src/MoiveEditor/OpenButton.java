package MoiveEditor;

import java.awt.image.BufferedImage;

import Editors.Button;
import Editors.Type;
import Views.Container;

public class OpenButton extends Button {
	private Container container;
	
	public OpenButton(BufferedImage img, int x, int y, int width, int height, Type type,Container container) {
		super(img, x, y, width, height, type);
		// TODO Auto-generated constructor stub
		this.container = container;
	}

	@Override
	public boolean onClick(int x, int y) {
		if(isClicked(x, y)){
			if(container.isShow()){
			 container.startAnimate();
			return true;
		}
	}
		return false;
}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
