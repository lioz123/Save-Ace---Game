package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import Editors.Button;
import Editors.NormalButton;
import Editors.Type;
import General.Utilities;
import MapTrazlation.SpriteSheetList;
import Views.Animation;
import Views.Container;

public class ContainerHead extends Container {
	protected Animation animateOpen ;
	protected boolean opening= false;
	protected String name;
	protected ContainerBody container;
	protected NormalButton openButton;
	protected Font font ;
	protected Color background = null;
//	Font font = new Font("Tahoma", Font.PLAIN, 10);
	
	public ContainerHead(int x,int y , int width, int height,Selector selector,String name){
		super(x,y,width,height);

		font = new Font("Tahoma", Font.PLAIN, 14);
		 animateOpen = new Animation(0,height,1);
		 BufferedImage img = SpriteSheetList.DownArraw.getImage();
		 openButton = new NormalButton(img,x+width-30-5,y+2,30,height-5,Type.noraml);
		 buttons.add(openButton);
		 this.show=true;
	
		 container = new  ContainerBody(x,y+height,width,200);
		 container.setShow(false);
	//	 containers.add(container);
		 this.name = name;
		 
	}
	
	public void setBackground(Color color){
		this.background=color;
	}
	
	public void tick(){
		
		if(opening){
			
		animateOpen.tick();
		if(!animateOpen.isAnimate()){
			opening=false;
		}
		}else if(super.closing){
			
		}
		
		for(Button  button:buttons){
			button.setX(button.getMaxX()+x-maxX);
			
		}
		for(TextArea text:texts){
			text.setX(text.getMaxX()+x-maxX);
		}
		
		for(Frame frame:frames){
			
		}
		
		for(Container container : containers){
			container.setX(container.getMaxX()+x-maxX);
			container.setY(container.getMaxY()+y-maxY);
			container.tick();
		}
		
	}
	
	
	public void startOpening(){
		opening = true;
		animateOpen.start();
	
		for(Container container : containers){
			container.setWidth(0);
		}
	}
	
	public void startClosing(){
		
	}
	
	
	
	public void render(Graphics2D g2d){
		g2d.setFont(font);
		if(this.show){
		g2d.setColor(Color.black);	
		g2d.drawRect(x,y,width,height);
		if(this.background!=null){
			g2d.setColor(background);
			g2d.fillRect(x, y, width, height);
		}
		g2d.drawString(name, x+5,y+height/2 );
		for(Button button:buttons){
			button.setX(button.getMaxX()+x-maxX+width-maxWidth);
			button.setY(button.getMaxY()+y-maxY+height-maxHeight);
			if(button.getX()>=x&&button.getY()>=y){
				button.render(g2d);
			}
			
			
			
		}
		}
		
	
		
		for(Container container: containers){
			container.render(g2d);
		}	
		
	}
	public boolean onClick(int x, int y){
		super.onClick(x, y);
		if(Utilities.isClicked(x, y, new Rectangle(this.x,this.y,width,height))){
		if(openButton.isClicked(x, y)){
			container.startAnimate();
		}
			
			return true;
		}
		return false;
	}
}
