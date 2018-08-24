package Views;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import GameObjects.Item;

public class Slot {
	private BufferedImage img;
	private Item item;

	private Screen screen;
	public Slot(Screen screen){
	this.screen=screen;
	}

	public Slot(Slot slot,int x, int y){
	this.screen=new Screen(slot.getScreen(),x,y,slot.getWidth(),slot.getHeight());
	Screen temp=slot.getScreen().getBorder();
	Screen myScreen=screen.getBorder();
	boolean first=true;
	while(temp!=null){
		
		if(temp.getBorder()!=null){
			myScreen.setBorder(temp.getBorder().getColor(), temp.getBorderPx());
		}
		
		temp=temp.getBorder();
		myScreen=myScreen.getBorder();
		
	}
	}
	

	public Screen getScreen(){
		return screen;
	}
	
	public BufferedImage getImage(){
		return this.img;
	}
	
	public void setItem(Item item){
		this.item=item;
	}
	
	public void render(Graphics2D g2d){
		screen.render(g2d);
		if(item!=null){
			item.render(g2d);
		}
	}
	

	
	public void setX(int x){
		screen.setX(x);
	}
	
	public void setY(int x){
		screen.setY(x);
	}
	
	public int getWidth(){
		return screen.getWidth();
	}
	
	public int getHeight(){
		return screen.getHeight();
	}
	
	
	

}
