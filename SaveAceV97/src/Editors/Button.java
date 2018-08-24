package Editors;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Main.PhiyscalObject;

public abstract class Button implements PhiyscalObject,ClickAble {
	protected int x,y,width,height,maxX,maxY,deltaNameX=0,deltaNameY=0;
	protected BufferedImage img;
	protected Type type;
	protected String name = null;
	protected Font font;
	protected Color nameColor = Color.black;
	protected Color buttonColor = Color.gray;
	protected boolean editable =true,editableY=true,editableX=true;
	protected boolean mouseHover =false;
	protected Color hoverColor=Color.black;
	protected double alpha =1, textAlpha=1;
	protected boolean showShell = true;
	protected int value=0;
	public Button(BufferedImage img,int x, int y ,int width , int height,Type type){
		this.type=type;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.img=img;
		this.maxX=x;
		this.maxY=y;
		 font = new Font("Tahoma", Font.PLAIN, 10);
	}
	
	public int getvalue(){
		return this.value;
	}
	
	public void setValue(int value){
		this.value=value;
	}
	
	public void setShowShell(boolean t){
		showShell = t;
	}
	
	public void setHoverColor(Color color){
	this.hoverColor=color;	
	}
	
	
	public void setAlpha(double alpha){
		this.alpha=alpha;
	}
	
	public void setTextAlpha(double alpha){
		this.textAlpha=alpha;
	}
	
	
	public void setEditable(boolean b){
		this.editable=b;
	}
	
	public  Button(){
		
	}
	
	public boolean onMouseOver(int x, int y){
	 mouseHover = isClicked(x,y);
	 return mouseHover;
	}
	
	public void setButtonColor(Color color){
		this.buttonColor=color;
	}
	
	public void setDeltaNameX(int x){
		this.deltaNameX=x;
	}
	
	public void setDeltaNameY(int y){
		this.deltaNameY=y;
	}
	
	public void setNameColor(Color color){
		this.nameColor=color;
	}
	
	
	public void setName(String name){
		this.name=name;
	}
	
	
	
	public int getMaxX(){
		return this.maxX;
	}
	
	public int getMaxY(){
		return this.maxY;
	}
	public abstract boolean onClick(int x, int y);
	public boolean isClicked(int x, int y){
		if(x>this.x&&x<this.x+width){
			if(y>this.y&&y<this.y+height){
				return true;
			}
		}
		return false;
	}
	public Type getType(){
		return this.type;
	}
	public int getWidth(){
		return this.width;
	}
	public int getHeight(){
		return this.height;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public void render(Graphics2D g2d){
		Color buttonColor=Color.black;
		if(mouseHover){

	
			 buttonColor = this.hoverColor;
	
		}else{
		
			buttonColor = this.buttonColor;
		}
		
	
		if(img!=null){
			
			if(name!=null){
				if(font!=null){
					g2d.setFont(font);
				}
				g2d.setColor(nameColor);
			g2d.drawString(name,x+deltaNameX ,y+deltaNameY);
			}
			g2d.drawImage(img, x,y,width,height,null);
		
		}else{
			
			g2d.setColor(buttonColor);
			
			g2d.fillRect(x, y, width, height);
			if(showShell){
			g2d.setColor(Color.black);
			g2d.drawRect(x,y,width,height);
			}
			if(name!=null){
				if(font!=null){
					g2d.setFont(font);
				}
				g2d.setColor(nameColor);
				g2d.drawString(name,x+deltaNameX,y+deltaNameY);
			}
	
		}
	
	}

	public void setX(int x) {
		if(editable&&editableX){
			this.x=x;
		}
		
		
	}
	
	public void setFont(Font font){
	this.font=font;	
	}
	
	public void setY(int y){
		if(editable&&editableY){
			this.y=y;
		}
	}
	public void moveXwithLimit(int delta){
		if(this.x+delta>=maxX){
			this.x+=delta;
		}else{
			x=maxX;
		}
	}
	
	public void moveYWithLimit(int delta){
		if(y+delta>=maxY){
			y+=delta;
		}else{
			y=maxY;
		}
	}


	public void setEditableY(boolean b){
		this.editableY=b;
	}
	
	public void setEditableX(boolean b){
		this.editableX=b;
	}

	public void setYWithLimit(int y) {
		if(y<maxY){
			this.y=y;
		}else{
			this.y=maxY;
		}
		
		
	}

	public boolean isClicked(MouseEvent e) {
		return isClicked(e.getX(),e.getY());
	}
}
