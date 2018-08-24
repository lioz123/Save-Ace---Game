package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import GameObjects.ID;
import General.Utilities;

public class Bar {

	private int x,y,width,height,capasity,textX,textY,fill,tempfill;
	private Color color,backGround;
	private String name;
	public ID id;
	
	
	public Bar(int x,int y,int width, int height,int capasity,Color color, String name,ID id){
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;
		this.capasity = capasity;
		this.color=color;
		this.name=name;
		this.backGround=Color.gray;
		this.textX =x+ width/2-10;
		this.textY=y+ height/2+6;
		this.id=id;
		fill =width;
		
	}
	
	public Bar(int x,int y,int width, int height,int capasity,Color color,Color backGround ,String name,ID id){
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;
		this.capasity = capasity;
		this.color=color;
		this.name=name;
		this.backGround=backGround;
		this.textX =x+ width/2-10;
		this.textY=y + height/2+6;
		this.id=id;
		fill=width;
	}
	public Bar(int x,int y,int width, int height,int capasity,int textSize,Color color,Color backGround ,String name,ID id){
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;
		this.capasity = capasity;
		this.color=color;
		this.name=name;
		this.backGround=backGround;
		this.textX =x+ width/2-10;
		this.textY=y + height/2+6;
		this.id=id;
		fill=width;
	}
	
	public Bar(int x,int y,int width, int height,int capasity,int textSize,int textX,int textY,Color color,Color backGround ,String name,ID id){
		this.x=x;
		this.y=y;
		this.width= width;
		this.height=height;
		this.capasity = capasity;
		this.color=color;
		this.name=name;
		this.backGround=backGround;
		this.textX=textX;
		this.textY=textY;
		this.id=id;
		fill = width;
	}
	public Bar(int x, int y, int width, int height,int capasity) {
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.capasity=capasity;
		this.textX=0;
		this.textY=0;
		this.name="";
		this.backGround=Color.darkGray;
		this.color=new Color(179, 36, 0);
		fill=width;
	}

	public void render(Graphics2D g2d){
	
   
	g2d.setColor(backGround);
	g2d.fillRect(x,y,width,height);
	g2d.setColor(this.color);
	g2d.fillRect(x, y, fill, height);
	g2d.setColor(Color.black);
	g2d.drawRect(x,y,width,height);
	 g2d.setFont(new Font("Courier News", Font.PLAIN, 15));
	    g2d.setColor(Color.black);
		g2d.drawString(name, textX ,textY);
	}
	

    public void tick(int capasity){
    	this.capasity=capasity;
    	
    }

	public void setCapasity(int capasity) {
		this.capasity=capasity;
		
	}

	
	public void updateFill(int x){
			this.tempfill-=x;
		
			this.fill=this.tempfill/this.capasity*width;
			if(fill<0){
			fill=0;
			}else if(fill>width){
			fill=width;
			}
			}
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
}
