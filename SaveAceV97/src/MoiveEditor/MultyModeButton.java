package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Editors.Button;
import General.Utilities;

public class MultyModeButton extends Button {
	private int value;
	private ArrayList<String> modes;
	private Font contentFont = new Font("Tahoma" , Font.PLAIN,12);
	public MultyModeButton(BufferedImage img , int x, int y, int width ,int height,Editors.Type type, ArrayList<String> modes){
		super(img, x, y, width, height, type);
		this.modes=modes;
		this.value=0;
	}
	
	public void setContentFont(Font font){
		this.font=font;
	}
	
	public void render(Graphics2D g2d){
		super.render(g2d);
		g2d.setFont(contentFont);
		g2d.setColor(Color.black);
		Rectangle bounds  = Utilities.getStringBounds(g2d, modes.get(value), x, y);
		double dx,dy;
		dx = (this.width-bounds.getWidth())/2;
		dy = (this.height-bounds.getHeight())/2 + bounds.getHeight();
		g2d.drawString(modes.get(value),((int)dx+x),((int)dy+y));
	}
	
	@Override
	public boolean onClick(int x, int y) {
		// TODO Auto-generated method stub
		if(isClicked(x,y)){
			value++;
			if(value>modes.size()-1){
				value = 0;
			}else if(value<0){
				value=modes.size()-1;
			}
		}
		return false;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public String getMode(){
	if(modes.size()==0){
		return null;
	}
	return modes.get(value);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public void setMode(String specialKey) {
		for(int i = 0; i< modes.size();i++ ){
			if(modes.get(i).equals(specialKey)){
				value = i;
				break;
			}
		}
		
	}
	

}
