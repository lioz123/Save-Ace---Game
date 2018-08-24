package GameObjects;

import java.awt.Graphics2D;

import General.ImageLoader;
import General.SpriteSheet;

public class ImageLocationTester extends GameObject{

	public ImageLocationTester(int x, int y, int width, int height, ID id) {
		super(x, y, width, height, id);
		SpriteSheet marinOffiserSprite = new SpriteSheet(new ImageLoader().loadImage("/marinOffiserSprite.png"));
		ss=marinOffiserSprite;
		 this.img=ss.grabImage(8+330-45,39+5,30+5,39);

	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(img,x,y,width,height,null);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	

}
