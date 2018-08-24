package Main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Loading extends Canvas implements Runnable {
	private Thread thread ;
	private boolean running =false;
	private Image loadingBackground;
	private boolean b = true;
	private Game game ;
	public Loading(Game game){
this.game = game;
	URL url = this.getClass().getResource("/loading2.gif");
	loadingBackground= new ImageIcon(url).getImage();
	


	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running =true;
	}
	
	public synchronized void stop(){
		try {
			running =false;
			thread.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		
		
		while(running){
		
			game.LoadImage();
			render();
		}
		
	}
	
	public void setRunning(boolean b){
		this.running=b;
	}


	private void render() {

	}

}
