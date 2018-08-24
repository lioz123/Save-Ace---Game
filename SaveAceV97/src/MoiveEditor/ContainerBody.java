package MoiveEditor;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Currency;

import javax.rmi.CORBA.Util;

import Editors.Button;
import General.Utilities;
import Views.Animation;
import Views.Container;

public class ContainerBody extends Container {
	protected Animation openAnimate,closeAnimate;
	protected Animation animate =null;
	public ContainerBody(int x,int y ,int width , int height){
		super(x,y,width,height);
		openAnimate= new Animation(0,height,5);
		closeAnimate = new Animation (height,0,-5);
	
	}

	@Override
	public void tick() {
		if(opening){
			show =true;
			if(close){
				closeAnimate.rest();
				close =false;
				openAnimate.start();
			}
			
			openAnimate.tick();
			animate = openAnimate;
			if(!animate.isAnimate()){
				opening=false;
				super.open= true;
				
			}
		}else if(super.closing){
			if(open){
				openAnimate.rest();
				closeAnimate.start();
				open =false;
			}
			
			closeAnimate.tick();
			animate = closeAnimate;
			if(!animate.isAnimate()){
			show=false;
			closing =false;
				super.close=true;
			}
		
		}else{
			
		}
		
	
		
		
	}
	
	

	@Override
	public void render(Graphics2D g2d) {
	
		
		
		if(isShow()){
			
		
			int height = 0 ;
			if(animate !=null){
				
				height= (int) animate.getX();
				
			}
			
			
			this.height=height;
			g2d.setColor(Color.gray);
			 g2d.fillRect(x,y,width,height);
			 g2d.setColor(Color.black);
			 g2d.drawRect(x,y,width,height);
			
			 
			for(TextArea text:texts){
				text.setY(text.getMaxY()+height-maxHeight-maxY+y);
				text.setX(text.getMaxX()-maxX+x+width-maxWidth);
				text.render(g2d);
			}
			
			
			
			
			
			for(Button button : buttons){
			
					button.setX(button.getMaxX()-maxX+x+width-maxWidth);
					button.setY(button.getMaxY()+height-maxHeight-maxY+y);
					if(button.getX()>=x&&button.getY()>=y){
						button.render(g2d);
					}
				
			}
			
			for(Frame frame:frames){
				frame.setX(frame.getX()+getDeltaX()+getDeltaWidth());
				frame.setY(frame.getY()+getDeltaY()+getDeltaHeight());
				frame.render(g2d);
			}
			
			for(Container container:containers){
				container.render(g2d);
			}
		
		 
		 
		}
		
		
	}
	
	
	
	


}
