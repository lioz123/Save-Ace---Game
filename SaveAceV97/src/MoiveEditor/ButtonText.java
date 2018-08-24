package MoiveEditor;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Editors.Button;
import Editors.Type;
import General.Utilities;
import Main.Game;

public class ButtonText extends Button{
	private String content;
	private Font contentFont = new Font("Tahoma", Font.PLAIN,12);
	public ButtonText(int x, int y, String content){
		this.x=x;
		this.y=y;
		this.maxX=x;
		this.maxY=y;
		this.content=content;
		this.img=null;
	
	}
	
	
	public void render(Graphics2D g2d){
	g2d.setFont(contentFont);
	Rectangle bounds =Utilities.getStringBounds(g2d, content, x, y);
	this.width=(int)bounds.getWidth() + 10;
	this.height=(int)bounds.getHeight() + 10;
	g2d.drawString(content,x+5,y+5+height/2);
	
	super.render(g2d);
	g2d.setFont(contentFont);
	g2d.drawString(content,x+5,y+5+height/2);
	
	}
	
	
	public void setContentFont(Font font){
		this.contentFont=font;
	}
	


	@Override
	public boolean onClick(int x, int y) {
	if(isClicked(x, y)){
		return true;
	}
		return false;
	}
	
	public void calculateBounds(Game game){
		Graphics2D g2d = game.getIntimadatedGraphics();
		g2d.setFont(contentFont);
		Rectangle bounds = Utilities.getStringBounds(g2d, content, 150,150);
		this.width=(int)bounds.getWidth();
		this.height=(int)bounds.getHeight();
		g2d.dispose();
	}


	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
