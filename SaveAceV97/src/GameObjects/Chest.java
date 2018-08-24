package GameObjects;
import  General.SpriteSheet;
import Main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Chest extends Solid{
	private SpriteSheet ss;
	public boolean isActivate = false;
	public Chest(int x, int y, int width, int height, ID id, SpriteSheet ss) {
		super(x, y, width, height, id, null);
		super.items= new ArrayList<Item>();
	
		img = ss.grabImage(96,18,33,12);
		this.ss =ss;
	}
	
	
	
	public void Activation(){
		img = ss.grabImage(96+31,18+100,33,12-3);
		isActivate=true;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.drawImage(img, x, y,width,height,null);
		if(!isActivate){
			g2d.setColor(Color.black);
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
			g2d.drawString("E",x + width/2-6, y - 4);
			g2d.setColor(Color.lightGray);
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 15));
			g2d.drawString("E",x + width/2-5, y - 5);
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
