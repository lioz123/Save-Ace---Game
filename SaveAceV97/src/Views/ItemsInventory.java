package Views;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameObjects.Side;

public class ItemsInventory {
private int x,y,width,height;
private BufferedImage backGround;
private ArrayList<Slot> slots = new ArrayList<Slot>();
private boolean show = false;
private boolean color=true;
private Screen screen;
private ArrayList<Screen> screens = new ArrayList<Screen>();

	public ItemsInventory(BufferedImage backGround,int x, int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.backGround=backGround;
		screen = new Screen(backGround,x,y,width,height);

		
	}
	public ItemsInventory(Color color,int x, int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		screen = new Screen(color,x,y,width,height);		
		
		
	}
	
	public void addScreen(Screen screen){
		screens.add(screen);
	}
	
	public void addScreenWithMargin(Screen screen,int marginX,int marginY){
		screen.setX(marginX+x);
		screen.setY(marginY+y);
		screens.add(screen);
	}
	
	public void addScreenWithMarginPrecent(Screen screen,int marginX,int marginY){
		screen.setX(marginX*width/100+x);
		screen.setY(marginY*height/100+y);
		screens.add(screen);
	}
	
	public void slotsArea(Slot slot,int startX,int startY,int rows,int cols,int deltaX,int deltaY){
		int x= startX;
		int y= startY;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				
				Slot temp = new Slot(slot,x,y);
				slots.add(temp);
				x+= slot.getScreen().getWidth()+deltaX;
			
				
			}
			x=startX;
			y+=slot.getHeight()+deltaY;
			
		}
		for(Slot temp:slots){
			
		}

	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	
	
	public void slotsAreaWithMargin(Slot slot,int marginX,int marginY,int rows,int cols,int deltaX,int deltaY){
		int x= this.x+marginX;
		int y= this.y+marginY;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				
				Slot temp = new Slot(slot,x,y);
				slots.add(temp);
				x+= slot.getScreen().getWidth()+deltaX;
			
				
			}
			x=marginX+this.x;
			y+=slot.getHeight()+deltaY;
			
		}
	

	}
	public void slotsAreaWithMarginPrecent(Slot slot,int marginX,int marginY,int rows,int cols,int deltaX,int deltaY){
		int x= this.x+marginX*width/100;
		int y= this.y+marginY*height/100;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				
				Slot temp = new Slot(slot,x,y);
				slots.add(temp);
				x+= slot.getScreen().getWidth()+deltaX;
			
				
			}
			x=marginX*width/100+this.x;
			y+=slot.getHeight()+deltaY;
		
		}


	}
	
	public void slotsAreaWithMarginPrecent(ArrayList<Slot> slots,double marginX,double marginY,int delta,Side side){
		
		int x= (int) (this.x+marginX*width/100);
		int y= (int) (this.y+marginY*height/100);
		for(Slot slot:slots){
			this.slots.add(new Slot(slot,x,y));
			if(side==Side.horizontal){
				x+=slot.getWidth()+delta;
			}
			else{
				y+=slot.getHeight()+delta;
			}
			
		}
		


	}
	
	

	public void fillWithSlots(Slot slot){
		int rows=height/slot.getHeight()-1;
		int cols=width/slot.getWidth()-2;
		int tempX=x+15,tempY=y+20;
		int x= this.x; 
		int y=this.y;
		x=tempX; y= tempY;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
			slots.add(new Slot(slot,x,y));
			x+=slot.getWidth()+7;
			}
			y+=slot.getHeight()+7;
			x=tempX;
			
		}
	}
	public void show(boolean b){
		show=b;
	}
	public void render(Graphics2D g2d){
		if(show){
			screen.render(g2d);		
			for(Slot slot:slots){
			slot.render(g2d);
			}
			
			for(Screen screen:screens){
			screen.render(g2d);	
			}
		}
}
	
	public void setBackGround(Color color,int px){
		screen.setBorder(color, px);
	}

	public boolean isShow() {
		// TODO Auto-generated method stub
		return show;
	}
	
	public Screen getScreen(){
		return this.screen;
	}

}
