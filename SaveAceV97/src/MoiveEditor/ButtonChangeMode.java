package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Editors.Button;
import Editors.Type;
import General.Utilities;

public class ButtonChangeMode extends Button {

	private String mode1,mode2;
	private String currentMode;
	private boolean modeChanged= false;
	private Font modeFont = new Font("Tahoma" ,Font.PLAIN,12);
	public ButtonChangeMode(BufferedImage img, int x, int y, int width, int height, Type type,String mode1,String mode2) {
		super(img, x, y, width, height, type);
		// TODO Auto-generated constructor stub
		this.mode1=mode1;
		this.mode2=mode2;
		currentMode = mode1;
	}
	
	public void setModeFont(Font font){
		this.modeFont = font;
	}

	@Override
	public boolean onClick(int x, int y) {
		if(isClicked(x, y)){
			modeChanged=!modeChanged;
			if(currentMode.equals(mode1)){
				currentMode=mode2;
			}else{
				currentMode = mode1;
			}
			return true;
		}
		return false;
	}
	
	public void render(Graphics2D g2d){
		super.render(g2d);
		g2d.setFont(this.modeFont);
		Rectangle strBounds = Utilities.getStringBounds(g2d, currentMode,x, y);
		int strX=x, strY=y;
		strX += (int)((width-strBounds.getWidth())/2);
		strY += (int)((height-strBounds.getHeight())/2)+(int)strBounds.getHeight();
		g2d.drawString(currentMode, strX, strY);
		
	}

	public boolean isModeChanged() {
		// TODO Auto-generated method stub
		return modeChanged;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	

}
