package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import General.Utilities;

public class TextArea{
protected boolean selected =false;
	protected boolean isNumber=false;
	protected int x, y, width,height,maxX,maxY,defualtWidth,maxWidth,maxChars,stringWidth;
	protected String string,name;
	protected boolean characterDelete =false,editable=true;
	protected Font font;
	protected int nameDeltaWidth=0;
	
	
 public TextArea(int x, int y, int width ,int height,String name){
	this.x=x;
	this.y=y;
	this.width=width;
	this.height=height;
	string = "";
	this.name = name;
	this.maxX=x;
	this.maxY=y;
	defualtWidth = width;
	this.maxWidth=400;
	this.maxChars =400;
	 font = new Font("Tahoma", Font.PLAIN, 14);
	 
	
 }
 
 	public void setIsNumber(boolean b){
 	isNumber =b;
 	}
 	
 	public boolean getisNumber(){
 		return this.isNumber;
 	}
 
 	public void setMaxWidth(int width){
 		this.maxWidth=width;
 	}
 	
 	public int getlMaxWidth(){
 		return this.maxWidth;
 	}
 	
 	public void setSelected(boolean selected){
 		this.selected=selected;
 	}
 	
 	public boolean onClick(int x, int y){
 		if(editable){
 			if(Utilities.isClicked(x, y, new Rectangle(this.x,this.y,width,height))){
 		 		this.selected=true;
 		 		return true;
 		 	}else{
 		 		this.selected =false;
 		 	}
 		}
 	return false;	
 	}
 	
 	public int getMaxX(){
 		return this.maxX;
 	}
 	
 	public int getMaxY(){
 		return this.maxY;
 	}
 	
 	public void KeyPressed(KeyEvent e){
 		if(selected&&editable){
 			
 			int key = e.getKeyCode();
 			
 			if(key!=8/*8 = the left arrow delete button*/&&key!=KeyEvent.VK_CONTROL&&key!=KeyEvent.VK_SHIFT&&key!=KeyEvent.VK_ESCAPE){
 				if(width!=maxWidth){
 				if(string.toCharArray().length<maxChars){
 					if(isNumber){
 						String tempStr = "" + e.getKeyChar();
 						if(!Utilities.isNumeber(tempStr)){
 							return;
 						}
 					}
 			
 					string+=e.getKeyChar();
 	 			/*	if(string.toCharArray().length*10>width){
 	 					width+=10;
 	 					
 	 				}
 	 				*/
 				}
 			}
 			}else if(key==8/*the left arrow for delting charactes in any program*/){	
 					string = removeChar(string);
 				/*	if(string.toCharArray().length*10<width&&width>defualtWidth){
 	 					width-=10;
 	 				}
 	 				*/
 			}
 		
 		}
 	}
 	
 	public void setMaxChars(int maxChars){
 		this.maxChars=maxChars;
 	}
 	
 	public int getMaxChars(){
 		return this.maxChars;
 	}
 	
 	
 	public String toString(){
 		return string;
 	}
 	
 	public void tick(){

 	}
 	
 	public void setFont(Font font){
	this.font=font;
		
 	}
	
	public void render(Graphics2D g2d){
		if(font!=null){
			g2d.setFont(font);
		}
		stringWidth = g2d.getFontMetrics().stringWidth(string);
		int nameWidth = g2d.getFontMetrics().stringWidth(name);
		
		if(stringWidth>=width){
			width=stringWidth+10;
		}
		
		if(stringWidth<width&&width>defualtWidth){
			width = stringWidth+10;
		}
		
		
		if(width>maxWidth){
			width =maxWidth;
		}
		
		g2d.drawString(name, x-nameWidth+5+nameDeltaWidth, y+height/2+5);
		g2d.setColor(Color.darkGray);
		g2d.drawRect(x, y, width, height);
		if(selected){
			g2d.setColor(Color.gray);
			g2d.fillRect(x,y, width, height);
		}
		g2d.setColor(Color.black);
		g2d.drawString(string, x+2, y+height/2+5);
	
	}
	
	public void setNameDeltaWidth(int delta){
		this.nameDeltaWidth = delta;
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSelected() {
		return selected;
	}
	
	public String removeChar(String str){
		char[] chars = str.toCharArray();
			String temp = "";
			for(int i = 0; i<chars.length-1;i++){
				temp+=chars[i];
			}
			return temp;
	}

	public void setString(String string) {
		this.string=string;
		if(string.toCharArray().length*7>width){
				width=string.toCharArray().length*7;
			}
	}

	public String getString() {
		// TODO Auto-generated method stub
		return this.string;
	}
	
	public int getNubmer(){
	
		if(isNumber()){
			if(Utilities.isNumeber(string)){
				return Integer.parseInt(string);
			}

		}
		
		
		return -999999;
	}
	
	public boolean isNumber(){
		if(Utilities.isNumeber(string)){
			return true;
		}
		return false;
	}

	public void seEditable(boolean b) {
		this.editable = b;
		
	}
}
