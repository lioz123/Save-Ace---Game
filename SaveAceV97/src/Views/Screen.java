package Views;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Editors.Button;
import MoiveEditor.TextArea;

public class Screen {
	
	private Screen border;
	private int x,y,width,height,px=0;
	private double transperent=1.0;
	private Color color;
	private ArrayList<Button> buttons;
	private ArrayList<TextArea> texts;
	
	BufferedImage image;
	public Screen(Color color, int x, int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.color=color;
		buttons = new ArrayList<Button>();
		texts = new ArrayList<TextArea>();
	}
	
	public void addTextArea(TextArea text){
		this.texts.add(text);
	}
	
	public void addButton(){
		
	}
	
	
	public Screen(Screen screen, int x, int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.color=screen.getColor();
		
		this.image=screen.getImage();
		this.transperent=screen.getTransparent();
		if(screen.getBorder()!=null){
			this.setBorder(screen.getBorder().getColor(),screen.getBorderPx());
			this.setBorderTransperent(screen.getBorder().getTransparent());
		}
		
		buttons = new ArrayList<Button>();
		texts = new ArrayList<TextArea>();
	}
	

	
	public Screen getBorder() {
		// TODO Auto-generated method stub
		return this.border;
	}

	private double getTransparent() {
		// TODO Auto-generated method stub
		return this.transperent;
	}

	public Screen(BufferedImage image, int x,int y,int width,int height){
		this.image=image;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;

	}
	public int  getBorderPx(){
		return this.px;
	}
	public void setBorder(Color color, int px){
		this.px=px;
		border= new Screen(color,x-px/2,y-px/2,width+px,height+px);
	}
	
	public void render(Graphics2D g2d){
		if(border!=null)
		 border.render(g2d);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) transperent));
		 if(this.color!=null){
			 g2d.setColor(color);
			 g2d.fillRect(x, y, width, height);	 
			 
			
		 }
		 else{
			
			 g2d.drawImage(image, x,y,width,height,null);
		 }
	}
	
	public void setTransperent(double d){
	this.transperent=d;
	}
	
	public void setBorderTransperent(double d){
		this.border.setTransperent(d);
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
	
	public void setX(int x){
		this.x=x;
		if(border!=null)
		border.setX(x-px/2);
	}
	
	public void setY(int y){
		this.y=y;
		if(border!=null)
		border.setY(y-px/2);
	}
	
	
	public Color getColor() {
		// TODO Auto-generated method stub
	return this.color;
	}
	
	public BufferedImage getImage(){
		return this.image;
	}

	
	
	
	

}
