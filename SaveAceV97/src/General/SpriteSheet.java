package General;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	public BufferedImage img;

	public SpriteSheet(BufferedImage img)
	{
		this.img = img;
	}
	
	public BufferedImage grabImage(int x, int y, int width, int height){
	
		return img.getSubimage(x, y, width,height);
	}
	
	public BufferedImage getImage(){
		return this.img;
	}
	
	public int getWidth(){
		return img.getWidth();
	}
	
	public int getHeight(){
		return img.getHeight();
	}

	public BufferedImage grabImage(Rectangle frame) {
		// TODO Auto-generated method stub
		return grabImage((int)frame.getX(),(int)frame.getY(),(int)frame.getWidth(),(int)frame.getHeight());
	}
	
	

}
