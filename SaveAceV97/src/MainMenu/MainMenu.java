package MainMenu;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Main.Game;
import Main.GameState;
import MapTrazlation.SpriteSheetList;
import Views.Container;
import Editors.Button;
import General.Film;
import General.ImageData;
import General.SpriteSheet;
import General.Utilities;
public class MainMenu extends MouseAdapter implements MouseMotionListener {
	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private Film backGround;
	private ImageData imgData = new ImageData(0,0,419,419);
	private SpriteSheet ss;
	private Image icon;
	private  ButtonMiddleText startGame,mapEditor,movieEditor,exit,charactersMenu;
	private Game game;
	public MainMenu(Game game){
		URL url = this.getClass().getResource("/castleAtNight.gif");
		 icon = new ImageIcon(url).getImage();
		 startGame = new ButtonMiddleText(null, (380-200)/2,150, 200,50,"New Game");
		 startGame.setButtonColor(new Color(173, 197, 237));
	
		 
		 
		 
		 mapEditor = new ButtonMiddleText(null, (380-200)/2,150+55, 200,50,"Map Editor");
		 mapEditor.setButtonColor(new Color(173, 197, 237));
	
		 
		 movieEditor = new ButtonMiddleText(null, (380-200)/2,150+55+55, 200,50,"Movie Editor");
		 movieEditor.setButtonColor(new Color(173, 197, 237));
		
		 charactersMenu = new ButtonMiddleText(null, (380-200)/2,150+55+55, 200,50,"Movie Editor");
		 charactersMenu.setButtonColor(new Color(173, 197, 237));
		 
		
		 
		 
		 charactersMenu = new ButtonMiddleText(null, (380-200)/2,150+55+55+55, 200,50,"Character Editor");
		 charactersMenu.setButtonColor(new Color(173, 197, 237));
		 
		 
		 exit = new ButtonMiddleText(null, (380-200)/2,150+55+55+55+55, 200,50,"Exit");
		 exit.setButtonColor(new Color(173, 197, 237));
		
		// startGame.setShowShell(false);
		// startGame.setHoverColor(new Color(171, 184, 204));
		 
		 
		
		 
		 buttons.add(charactersMenu);
		 buttons.add(exit);
		 buttons.add(movieEditor);
		 buttons.add(mapEditor);
		 buttons.add(startGame);
		 
		 for(Button button:buttons){
			 button.setHoverColor((new Color(170, 227, 237)));
		 }
		this.game=game;
		 
		 
		
/*		ss= SpriteSheetList.backGround;
		backGround = new Film();
		backGround.setFps(60);
		int bx=0,by=0,bwidth=420,bheight=428;
		while(by<ss.getHeight()-428){
			while(bx<ss.getWidth()){
				
				bx+=bwidth;
				if(bx>=ss.getWidth()){
					break;
				}
				backGround.addImage(new ImageData(bx,by,bwidth,bheight));
			}
			
			by+=bheight;
			bx=0;
		}
//		backGround.buildFilm(0, 430*2,419, 419, 5, 419, 0);
	//	backGround.buildFilm(0,  430*3,419, 419, 5, 419, 0);
		//backGround.buildFilm(0, 430*4,419, 419, 5, 419, 0);
		backGround.setFps(20);
		*/
	}
	
	public void mousePressed(MouseEvent e){
		if(startGame.isClicked(e.getX(), e.getY())){
		
			game.setGameState(GameState.playing);
			
		}
		
		if(mapEditor.isClicked(e.getX(),e.getY())){
			game.setGameState(GameState.mapEditor);
		}
		
		if(movieEditor.isClicked(e.getX(), e.getY())){
			game.setGameState(GameState.movieEditor);
		}
		if(exit.isClicked(e.getX(),e.getY())){
			game.exit();
		}
		if(charactersMenu.isClicked(e)){
			game.setGameState(GameState.charactersMenu);
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	public void mouseMoved(MouseEvent e){
		for(Button button : buttons){
			if(button.onMouseOver(e.getX(), e.getY()));
		
		}
	}
	
	public void tick(){
//		imgData =backGround.run();
	}

	public void render(Graphics2D g2d) {
		Rectangle titleLine;
		//	g2d.drawImage(ss.grabImage(imgData.getFrame()),0,0,Game.WIDTH,Game.HEIGHT,null);
		// TODO Auto-generated method stub
	g2d.drawImage(icon,0,0,Game.WIDTH,Game.HEIGHT,null);
	//g2d.drawImage(icon, 0,0,((int)(icon.getWidth(null)*1.5)),Game.HEIGHT,null);
	
		g2d.setColor(Color.black);
		//g2d.drawRect(0, 0, 350, Game.HEIGHT);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
		g2d.setColor(new Color(152, 175, 211));
		g2d.fillRect(0, 0, 380, Game.HEIGHT);
		titleLine = new Rectangle((380-300)/2,100 ,300,600);
		
		g2d.fill(titleLine);
		g2d.setColor(Color.gray);
		g2d.draw(titleLine);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));

		String content = "Main menu";
		g2d.setFont(new Font("Tahoma",Font.BOLD,20));
		g2d.setColor(new Color(173, 197, 237));
		Rectangle bounds  = Utilities.getStringBounds(g2d,content, 100,50);
		double dx,dy;
		dx = (380-bounds.getWidth())/2;
		dy = (Game.HEIGHT-bounds.getHeight())/2 + bounds.getHeight()-350;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.9));	
		g2d.drawString(content,((int)dx),((int)dy));
		//g2d.setColor(Color.black);


		
		
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.4));	
		
		for(Button button:buttons){
			button.render(g2d);
		}
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
	}
	
	
	

	

	
	


}
