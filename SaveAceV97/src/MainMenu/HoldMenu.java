package MainMenu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import Editors.Button;
import General.Utilities;
import Main.Game;
import Main.GameState;
public class HoldMenu extends MouseAdapter implements MouseMotionListener {
	
	private ArrayList<Button> buttons=new ArrayList<Button>();
	private ButtonMiddleText resume,save,saveAndExit,exit;
	private Game game;
	public HoldMenu(Game game){
		 resume = new ButtonMiddleText(null, (Game.WIDTH-200)/2,150+55, 200,50,"Resume");
		 save = new ButtonMiddleText(null, (Game.WIDTH-200)/2,150+55+55, 200,50,"Save");
		 saveAndExit= new ButtonMiddleText(null, (Game.WIDTH-200)/2,150+55+55+55, 200,50,"Save&Exit");
		 exit =  new ButtonMiddleText(null, (Game.WIDTH-200)/2,150+55+55+55+55, 200,50,"Exit");
		 buttons.add(resume);
		 buttons.add(save);
		 buttons.add(saveAndExit);
		 buttons.add(exit);
		
	
		
		 int i =0;
		 for(Button button:buttons){
			 button.setY(190+55*i);
			 button.setHoverColor(new Color(142, 142, 142));
			 button.setButtonColor(new Color(178, 176, 176));
			 i++;
		 }
		 this.game= game;
	}
	
	
	public void mousePressed(MouseEvent e){
		if(resume.isClicked(e.getX(), e.getY())){
			game.handleHoldMenu();
		}
		
		if(exit.isClicked(e)){
			game.setGameState(GameState.mainMenu);
		}
		if(save.isClicked(e)){
			
			game.save();
			game.handleHoldMenu();
		}
		if(saveAndExit.isClicked(e)){
			game.save();
			game.setGameState(GameState.mainMenu);
		}

	}
	
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public void mouseMoved(MouseEvent e){
		for(Button button:buttons){
			button.onMouseOver(e.getX(), e.getY());
		}
	}

	
	
	 public void render(Graphics2D g2d){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float) 0.5));
		
		 g2d.setColor(new Color(165, 165, 165));
		 g2d.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)1));
		
		 g2d.setColor(new Color(153, 151, 151));
	
		Rectangle rect = new Rectangle((Game.WIDTH-300)/2,150,300,400);	 
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)0.8));
		
		g2d.fill(rect);
		 
		 g2d.setColor(Color.black);
		 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)1));
		 g2d.draw(rect);
		 
		 
		 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)0.8));
	
		 for(Button button:buttons){
			 button.render(g2d);
		 }
		 
		 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)1));
		 Font font = new Font("Tahoma", Font.BOLD,25);
		 g2d.setFont(font);
		 Rectangle menuBounds =Utilities.getStringBounds(g2d,"Menu", 100,50);
		 double dx = rect.getX()+(rect.getWidth()-menuBounds.getWidth())/2;
		 g2d.drawString("Menu", (int) dx, 180);
	 }
	 
	 
	
}
