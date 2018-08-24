package CharactersMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import General.Utilities;
import Main.Game;
import Main.PhiyscalObject;

public class StringDisplay implements PhiyscalObject{
	private String string;
	private Font font;
	private Color color = Color.BLACK;
	private boolean changed =true;
	private int x,y,width,height;
	private Rectangle panel  = null;
	private  Game game;
	public StringDisplay(String string,int x, int y,Game game){
		this.game=game;
		this.setString(string);
		this.setFont(font);
		this.x=x;
		this.y=y;
		font = new Font("Tahoma",Font.PLAIN,12);
		CalculateBounds();
		
		
	}
	
	
	public void CalculateBounds(){
		Graphics2D g2d = game.getIntimadatedGraphics();
		g2d.setFont(font);
		Rectangle bounds = Utilities.getStringBounds(g2d, string, x, y);
		this.width =(int) bounds.getWidth();
		this.height= (int) bounds.getHeight();
		g2d.dispose();
	}
	
	


	public String getString() {
		return string;
	}
	public void setString(String string) {
		changed=true;
		this.string = string;
	}
	public Font getFont() {
		return font;
	}
	public void setFont(Font font) {
		changed =true;
		this.font = font;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void render(Graphics2D g2d){
		
		g2d.setFont(font);
		g2d.setColor(color);
		g2d.drawString(string, x, y);
		
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
	
	public void setMiddle(int x , int y, int width, int height){
		this.x= x+(width-this.width)/2;
		this.y=y+(height-this.height)/2+this.height;

	}





	public void setHeight(int height) {
		this.height = height;
	}

	public void setMiddleX(int x, int width){
		this.x= x+(width-this.width)/2;
	}
	
	public void setMiddleY(int y, int height){
		this.y = y+(height-this.height)/2+this.height;
	}


	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	

	
}
