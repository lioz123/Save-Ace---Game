package GameObjects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageTester {
private int x,y,width,height;
private BufferedImage img;
	public ImageTester (int x,int y, int width, int height,BufferedImage img){
	this.x=x;
	this.height=height;
	this.width=width;
	this.y=y;
	this.img=img;
}
	
	public void render(Graphics2D g2d){
		g2d.drawImage(img,x,y,width,height,null);
	}
}
