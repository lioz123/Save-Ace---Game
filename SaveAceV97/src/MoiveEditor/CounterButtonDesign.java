package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Editors.CounterButton;
import Editors.Type;
import General.Utilities;

public class CounterButtonDesign extends CounterButton{
	
	private Rectangle rect;
	private Color rectColor = Color.gray;
	private Font contentFont = new Font("Tahoma",Font.PLAIN,11);
	private boolean middleX=false,middleY=true;
	private int deltaContentX = 5,deltaContentY=5,deltaX,deltaY;
	public CounterButtonDesign(BufferedImage img, int x, int y, int width, int height, Type type, int value,
			int delta, int maxValue, int minValue) {
		super(img, x, y, width, height, type, value, delta, maxValue, minValue);
		deltaX =0;
		deltaY = 0;
		if(type==Editors.Type.counterUpDown){
			deltaX=  width;
			rect = new Rectangle(x+deltaX,y,width*2,height);
		}else{
			
			deltaY=height;
			rect =new Rectangle(x-20,y+height+20,width*2,height);
		}
	}
	
	public void setRectBounds(int width ,int height){
		rect.setSize(width, height);
	}
	
	public void render(Graphics2D g2d){
	
		rect.setLocation(x+deltaX, y+deltaY);
		super.render(g2d);
		g2d.setColor(rectColor);
		g2d.fill(rect);
		g2d.setColor(Color.black);
		g2d.draw(rect);
		g2d.setFont(contentFont);
		Rectangle strBounds = Utilities.getStringBounds(g2d, value+"" , (int)rect.getX(),(int)rect.getY());
		double tempHeight = strBounds.getHeight();
		strBounds = Utilities.getMiddleBounds(rect, strBounds);
		int strY, strX;
		strY = (int)(rect.getY()+deltaContentY);
		strX = (int)(rect.getX()+deltaContentX);
		if(middleY){
			strY= (int)(strBounds.getY()+tempHeight);
		}
		if(middleX){
			strX = (int)strBounds.getX();
		}
		
		
		
		g2d.drawString(value+"",strX,strY);
		
	}
	

	
	public int getDeltaContentX(){
		return this.deltaContentX;
	}
	
	public void setDeltaContentX(int deltaContentY){
		this.deltaContentY=deltaContentY;;
	}

	public boolean isMiddleY() {
		return middleY;
	}

	public void setMiddleY(boolean middleY) {
		this.middleY = middleY;
	}

	public boolean isMiddleX() {
		return middleX;
	}

	public void setMiddleX(boolean middleX) {
		this.middleX = middleX;
	}

	public int getDeltaContentY() {
		return deltaContentY;
	}

	public void setDeltaContentY(int deltaContentY) {
		this.deltaContentY = deltaContentY;
	}
	

	

	
	

}
