package GameObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import General.ImageLoader;
import General.SpriteSheet;
import Main.PhiyscalObject;
import MapTrazlation.SpriteSheetList;

public class Solid extends GameObject implements PhiyscalObject {
	
	
	private int imageSize=33;
	public Solid(int x, int y, int width, int height, ID id,BufferedImage img) {
		super(x, y, width, height, id);
		this.img=img;
		ss= SpriteSheetList.warcraft;
		
	}
	
	public void setImage(int x, int y){
		img=ss.grabImage(x, y,imageSize,imageSize);
	}
	

	public Solid(Solid solid) {
		super(solid.x, solid.y, solid.width, solid.height, solid.id);
		super.ss=solid.ss;
		this.imageX=solid.imageX;
		this.imageY=solid.imageY;
		this.imageWidth=solid.imageWidth;
		this.imageHeight=solid.imageHeight;
		img = ss.grabImage(imageX,imageY,imageWidth,imageHeight);
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(img, x, y,width, height, null);
	}

	@Override
	public void tick() {
	
		
	}

}
