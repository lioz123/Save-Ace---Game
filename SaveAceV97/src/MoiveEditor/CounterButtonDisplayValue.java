package MoiveEditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Editors.CounterButton;
import Editors.Type;
import java.awt.Rectangle;
public class CounterButtonDisplayValue extends CounterButton{
	private Rectangle rect ;
	private int deltaDisplayX=0,deltaDisplayY=0;
	public CounterButtonDisplayValue(BufferedImage img, int x, int y, int width, int height, Type type, int value,int delta, int maxValue, int minValue) {
		super(img, x, y, width, height, type, value, delta, maxValue, minValue);
	}
	
	public void render(Graphics2D g2d){
		Rectangle rect = this.rect;
		super.render(g2d);
		if(rect!=null){
			rect = new Rectangle((int)(rect.getX()+x-maxX),(int)(rect.getY()+y-maxY),(int)(rect.getWidth()),(int)(rect.getHeight()));
			g2d.setColor(Color.GRAY);
			g2d.fill(rect);
			g2d.setColor(Color.black);
			g2d.draw(rect);
			g2d.setColor(Color.BLACK);
			g2d.drawString(value+"",(int)(rect.getX()+4+deltaDisplayX),(int)(rect.getY()+height/2+5+deltaDisplayY));
		}
	
	
	}
	
	public void setDeltaDisplayX(int x){
		this.deltaDisplayX=x;
	}
	
	public void setDeltaDisplayY(int y){
		this.deltaDisplayY=y;
	}
	
	public void setRect(Rectangle rect){
		this.rect=rect;
	}

	
	

	
	

}
