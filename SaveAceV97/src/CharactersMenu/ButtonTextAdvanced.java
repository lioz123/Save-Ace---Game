package CharactersMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Editors.Button;
import General.Utilities;
import Main.Game;

public class ButtonTextAdvanced extends Button{
	
	private int value;
	private Game game;

	private StringDisplay string;
	public ButtonTextAdvanced(String string,int x, int y,Game game) {
		this.x=x;
		this.y=y;
		this.game=game;
		this.string=new StringDisplay(string, x, y, game);
	//	Graphics2D g2d = game.getIntimadatedGraphics();
//		Rectangle stringBounds = Utilities.getStringBounds(g2d, string, x, y);
	
		
	//	g2d.dispose();
		this.string.CalculateBounds();
		this.width=this.string.getWidth()+10;
		this.height=this.string.getHeight()+5;
	
		
	}
	
	public void setStringToCenter(){
		string.CalculateBounds();
		string.setX(this.x+(width-string.getWidth())/2);
		string.setY(y+(height-string.getHeight())/2+string.getHeight());
	}

	
	@Override
	public boolean onClick(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void render(Graphics2D g2d){
		super.render(g2d);
		string.render(g2d);		
	}
	
	public StringDisplay getStringDisplay(){
		return string;
	}

	public void setHeight(int height) {
		this.height=height;
		// TODO Auto-generated method stub
		
	}
	
	public void setFont(Font font){
		string.setFont(font);
	}
	
	public void setX(int x){
		int delta = this.x-string.getX();
		this.x=x;
		string.setX(x+delta);
	}
	
	
	public void setY(int y){
		int delta = this.y-string.getY();
		this.y=y;
		string.setY(y+delta);
	}
	
	public void setStringColor(Color color){
		string.setColor(color);
	}
	
	public void SetBoundsToStringBounds(){
		string.CalculateBounds();
		this.width=this.string.getWidth()+10;
		this.height=this.string.getHeight()+5;
		setStringToCenter();
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public String getString(){
		return this.string.getString();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
