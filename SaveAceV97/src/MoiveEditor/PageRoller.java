package MoiveEditor;

import java.awt.Color;
import java.awt.Graphics2D;

import GameObjects.Side;

public class PageRoller {
	
	private int x,y,width,height,speedX,speedY;
	private Side side;
	
	public PageRoller(int x, int y, int width , int height, Side side){
	this.setX(x);
	this.setY(y);
	this.setWidth(width);
	this.setHeight(height);
	this.setSide(side);
	speedX =0;
	speedY=0;
	}
	
	
	
	public void render(Graphics2D g2d){
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(x,y,width,height);
	}
	
	public void tick(){
		this.x+=speedX;
		this.y+=speedY;
	}
	
	
	public void setSpeedX(int speedX){
		this.speedX=speedX;
	}
	
	public void setSpeedY(int speedY){
		this.speedY=speedY;
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



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	public Side getSide() {
		return side;
	}



	public void setSide(Side side) {
		this.side = side;
	}





}


