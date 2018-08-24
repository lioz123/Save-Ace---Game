package CharactersMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import General.ImageData;
import General.Utilities;
import MapTrazlation.SpriteSheetList;
import MoiveEditor.BonesGroup;
import MoiveEditor.ButtonText;
import MoiveEditor.ContainerBody;
import MoiveEditor.CounterButtonDisplayValue;
import MoiveEditor.Frame;
import MoiveEditor.MultyModeButton;
import MoiveEditor.Puzzle;
import MoiveEditor.Selector;

public class ContainerCharacterBody extends ContainerBody {
	private Color backGround=Color.gray;
	public ContainerCharacterBody(int x, int y, int width, int height) {
		super(x, y, width, height);

		
	}
	


	
	public void tick(){

		super.tick();


	}
	

	
	public void render(Graphics2D g2d){
		
		super.render(g2d);
	}
	
	public boolean onClick(int x,int y){
	
	
		return false;
		
		
	}
	
	
	
}
