package MoiveEditor;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import General.SpriteSheet;
import General.Utilities;

public class Frame {
	private Rectangle rect,deltaFrameBounds;
	private SpriteSheet ss;
	
	private int x,y,width ,height,maxX,maxY,maxWidth,maxHeight;
	public Frame(int x, int y, int width ,int height, Rectangle rect,SpriteSheet ss){
		this.rect=rect;
		this.setSs(ss);
		this.setX(x);
		this.setY(y);
		this.width=width;
		this.height=height;
		this.maxX=x;
		this.maxY=y;
		this.maxWidth=width;
		this.maxHeight=height;
		this.deltaFrameBounds = new Rectangle(0,0,0,0);
	}
	
	public Frame(int x, int y, int width ,int height, Rectangle rect,Rectangle deltaFrameBounds,SpriteSheet ss){
		this.rect=rect;
		this.setSs(ss);
		this.setX(x);
		this.setY(y);
		this.width=width;
		this.height=height;
		this.maxX=x;
		this.maxY=y;
		this.maxWidth=width;
		this.maxHeight=height;
		this.deltaFrameBounds = deltaFrameBounds;
	}
	
	public void setRect(Rectangle rect){
		this.rect=rect;
	}
	
	public Rectangle getRect(){
		return this.rect;
	}
	
	
	public int getMaxX(){
		return this.maxX;
	}
	
	public int getMaxY(){
		return this.maxY;
	}
	
	public int getMaxWidth(){
		return this.maxWidth;
	}
	
	public int getMaxHeight(){
		return this.maxHeight;
	}
	


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
			if(height<maxHeight){
				this.height=height;
			}else{
				this.height=maxHeight;
			}
		
		
		
	}

	public SpriteSheet getSs() {
		return ss;
	}

	public void setSs(SpriteSheet ss) {
		this.ss = ss;
	}
	public void render(Graphics2D g2d){
		if(rect== null){
			return;
		}
			if(rect.getX()>0&&rect.getY()>0&&rect.getWidth()>0&&rect.getHeight()>0){
				BufferedImage img = ss.grabImage((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth(),(int)rect.getHeight());
				g2d.drawImage(img,x,y,width,height,null);
			}
			
		
	}

	public boolean onClicked(int x, int y) {
		if(Utilities.isClicked(x, y, new Rectangle(this.x,this.y,width,height))){
			return true;
		}
		return false;
	}

	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return ss.grabImage(x,y,width,height);
	}
}
