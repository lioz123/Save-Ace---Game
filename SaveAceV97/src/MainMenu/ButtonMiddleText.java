package MainMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Editors.Button;
import Editors.Type;
import General.Utilities;

public class ButtonMiddleText extends Button {
	private Font contentFont=new Font("Tahoma",Font.PLAIN,12);
	private String content;
	
	public  ButtonMiddleText(BufferedImage img,int x, int y, int width, int height) {
	super(img, x,y,width,height, Type.noraml);
	}
	
	public  ButtonMiddleText(BufferedImage img,int x, int y, int width, int height,String content) {
	super(img, x,y,width,height, Type.noraml);
	this.content=content;
	}
	
	@Override
	public boolean onClick(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setContent(String content){
		this.content=content;
	}

	public void render(Graphics2D g2d){
	super.render(g2d);
	g2d.setFont(contentFont);
	g2d.setColor(Color.black);
	Rectangle bounds  = Utilities.getStringBounds(g2d,content, x, y);
	double dx,dy;
	dx = (this.width-bounds.getWidth())/2;
	dy = (this.height-bounds.getHeight())/2 + bounds.getHeight();
	g2d.drawString(content,((int)dx+x),((int)dy+y));
	
	  
	}

	public boolean isClicked(MouseEvent e) {
		return isClicked(e.getX(),e.getY());
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
