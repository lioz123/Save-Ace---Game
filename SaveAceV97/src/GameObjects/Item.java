package GameObjects;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import General.SpriteSheet;

public abstract class Item {
	
	public int x,y,width,height,imageX,imageY,imageWidth,ImageHeight,capasity;
	protected BufferedImage img;
	protected SpriteSheet ss;
	public ID id;
	public Item(int x,int y ,int width,int height,int imageX,int imageY,int imageWidth,int imageHeight,ID id,SpriteSheet ss)
	{
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.imageX=imageX;
		this.imageY=imageY;
		this.imageWidth = imageWidth;
		this.ImageHeight= imageHeight;
		this.id=id;
	}
	public Item(int x,int y ,int width,int height,int imageX,int imageY,ID id,SpriteSheet ss)
	{
		this.x=x;
		this.y=y;
		
		this.width=width;
		this.height=height;
		this.imageX=imageX;
		this.imageY=imageY;
		
		this.id=id;
	}
	public Item(ID id){
		this.id=id;
	}
	public abstract void Activation(GameObject object);
	public abstract void render(Graphics2D g2d);

	
	
	
	

}
