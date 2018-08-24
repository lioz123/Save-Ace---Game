package Main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Window extends Canvas {
private  JFrame frame;
private Game game;
private Loading loading;
 public Window(int width, int height, String title, Game game, Loading loadingGif){

		JPanel panel = new JPanel();
	  frame = new JFrame(title);
	  frame.add(panel);
	
	 frame.setMinimumSize(new Dimension(width, height));
	 frame.setMaximumSize(new Dimension(width, height));
	 frame.setPreferredSize(new Dimension(width, height));
	 frame.setResizable(false);
	 frame.setLocationRelativeTo(null);
//	 frame.setUndecorated(true);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.loading=loadingGif;
	this.game=game;
	


	//frame.getContentPane().add(loadingGif);
//	frame.getContentPane().add(game);


	
	
	
	//frame.add(lable);
//	frame.add(loadingGif);
	 
	 frame.add(game);
	 frame.setVisible(true);
	 
	 

	 game.start();
	 
	 
 }
 
 public JFrame getFrame(){
	 return this.frame;
 }

public void resize(int width,int height){
	frame.setMinimumSize(new Dimension(width, height));
	 frame.setMaximumSize(new Dimension(width, height));
	 frame.setPreferredSize(new Dimension(width, height));
	 frame.setSize(width, height);
	 frame.setResizable(false);
	 frame.setLocationRelativeTo(null);
	
}

public void startLoading(){

	frame.add(loading);
	loading.start();

}
	
}
