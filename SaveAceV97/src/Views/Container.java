package Views;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import CharactersMenu.StringDisplay;
import Editors.Button;
import Editors.ClickAble;
import General.Utilities;
import Main.Game;
import Main.PhiyscalObject;
import MoiveEditor.Frame;
import MoiveEditor.MovieEditor;
import MoiveEditor.Selector;
import MoiveEditor.TextArea;

public abstract class Container implements PhiyscalObject{
	protected KeyConnector keyInput;
	protected MouseConnector mouseInput;
	protected boolean show =false,opening =false , closing =false;
	protected int x,y,width,height;
	protected ArrayList<Button> buttons;
	protected ArrayList<TextArea> texts;
	protected ArrayList<Container> containers;
	protected int maxX,maxY,maxWidth,maxHeight;
	protected boolean open =false, close =true;
	protected ArrayList<Frame> frames;
	protected int totalHeight,totalWidth;
	protected MovieEditor editor;
	protected ArrayList<Selector> selectors;
	protected ArrayList<StringDisplay> stringsDisplay = new ArrayList<StringDisplay>();
	protected ArrayList<PhiyscalObject> objects = new ArrayList<PhiyscalObject>();
	protected ArrayList<ClickAble> clickAbles = new ArrayList<ClickAble>();
	public Container(int x,int y,int width ,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.maxX=x;
		this.maxY=y;
		this.maxWidth=width;
		
		this.maxHeight=height;
		buttons = new ArrayList<Button>();
		texts = new ArrayList<TextArea>();
		containers = new ArrayList<Container>();
		frames = new ArrayList<Frame>();
		totalWidth = width;
		totalHeight= height;
	}
	

	

	
	public Container(){
		this.x=0;
		this.y=0;
		this.width=Game.WIDTH;
		this.height=Game.HEIGHT;
		this.maxX=x;
		this.maxY=y;
		this.maxWidth=width;
		
		this.maxHeight=height;
		buttons = new ArrayList<Button>();
		texts = new ArrayList<TextArea>();
		containers = new ArrayList<Container>();
		frames = new ArrayList<Frame>();
		totalWidth = width;
		totalHeight= height;
	}
	
	public void addFrame(Frame frame){
		this.frames.add(frame);
	}

	public boolean isOpen(){
		return this.open;
	}
	public void startAnimate(){
		
		if(!opening&&!closing){
			if(close){
				opening =true;
			}else if(open){
				closing = true;
			}
		}
	}
	public void addContainer(Container container){
		containers.add(container);
	}
	
	public int getTotalWidth(){
		Container minContainer=this;
		Container maxContainer = this;
	 for(Container container:containers){
		if(container.getX()<minContainer.getX()){
			minContainer = container;
		}
		if(container.getX()+container.getWidth()>maxContainer.getX()+maxContainer.getWidth()){
			maxContainer = container;
		}
	 }
	 return maxContainer.getX()+maxContainer.getWidth()-minContainer.getX();
	}
	
	public int getTotalHeight(){
		Container minContainer=this;
		Container maxContainer = this;
	 for(Container container:containers){
		if(container.getY()<minContainer.getY()){
			minContainer = container;
		}
		if(container.getY()+container.getHeight()>maxContainer.getY()+maxContainer.getHeight()){
			maxContainer = container;
		}
	 }
	 return maxContainer.getX()+maxContainer.getWidth()-minContainer.getX();
	}
	
	public int getTotalMaxY(){
		Container max = this;
		for(Container container:containers){
			if(container.isShow()){
				if(container.getY()+container.getHeight()>max.getX()+max.getHeight()){
					max=container;
				}
			}
			
		}
	
		return max.getY()+max.getHeight();
	}
	
	public int getTotalX(){
		Container max = this;
		for(Container container:containers){
			
			if(container.getX()+container.getWidth()>max.getX()+max.getWidth()){
				max=container;
			}
		}
		return max.getX()+max.getWidth();
	}
	
	
	
	public void startClosing(){
		if(!opening&&!closing){
			closing=true;
		}
	}
	
	public void startOpening(){
		if(!opening&&!closing){
			opening=true;
		}
	}
	
	public int getMaxX(){
		return this.maxX;
	}
	
	public int getMaxY(){
		return this.maxY;
	}
	
	public int getMaxWidth(){
		return this.maxWidth;
	}
	
	public int getMaxHeight(){
		return this.maxHeight;
	}
	
	public void setMaxHegiht(int maxHeight){
		this.maxHeight=maxHeight;
	}
	
	public void setMaxWidth(int maxWidth){
		this.maxWidth=maxWidth;
	}
	
	public void setMaxX(int x){
		this.maxX=x;
	}
	
	public void setMaxY(int y){
		this.maxY=y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.y=y;
	}
	
	public void setWidth(int width){
		this.width=width;
	}
	
	public void setHeight(int height){
		this.height=height;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	
	public int getWidth(){
		return this.width;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	
	
	public void addMouseInput(){
		

	}
	
	public void addKeyInput(){
		
	}

	public void mousePressed(MouseEvent e){
	
		for(Button button : buttons){
			button.onClick(e.getX(), e.getY());
			if(button.isClicked(e)){
				System.out.println("button clicked");
			}
			System.out.println("there is atleast one button");
		}

	for(TextArea text:texts){
		text.onClick(e.getX(), e.getY());
		
		
	}
	for(Container container:containers){
		container.onClick(e.getX(),e.getY());
	
	}
	}
	
	public void mosueReleased(MouseEvent e){
		
	}
	
	public void mouseClicked(MouseEvent e){
		
	}
	
	public void mouseDragged(MouseEvent e){
		
	}
	
	public void keyPressed(KeyEvent e){
		for(TextArea text: texts){
			text.KeyPressed(e);
		}
		for(Container container:containers){
			container.keyPressed(e);
		}
	}
	
	public void keyReleased(KeyEvent e){
		
	}
	
	public void setShow(boolean show){
		this.show=show;
	}
	
	public boolean isShow(){
		return this.show;
	}
	
	public void tick(){
		if(show){
			for(PhiyscalObject object:objects){
				object.tick();
			}
		}
	}
	
	public void render(Graphics2D g2d){
		if(show){
			for(Container container : containers){
				container.render(g2d);
			}
			for(Button button : buttons){
				button.render(g2d);
			}
			for(TextArea text : texts){
			}
			for(Frame frame: frames){
				frame.render(g2d);
			}
			
			for(StringDisplay string:stringsDisplay){
				string.render(g2d);
			}
			
			for(PhiyscalObject object:objects){
			object.render(g2d);
			}
		}
	
	}
	
	
	
	public boolean onClick(int x, int y) {
		System.out.println(this.getClass().toString() + " show = "  +  show);
		if(show){
			
	
		for(Container container: containers){
			container.onClick(x, y);		
		}
		
		for(ClickAble click: clickAbles){
			click.onClick(x, y);
		}
		
		if(Utilities.isClicked(x, y, new Rectangle(this.x,this.y,width,height))){
			for(Button button: buttons){
				button.onClick(x, y);
				if(button.isClicked(x,y)){
			
				}
			}
			
			for(TextArea text : texts){
				text.onClick(x, y);
			}
			return true;
		}
		}
		return false;
		
	}

	public void addButton(Button button) {
		this.buttons.add(button);
		
	}
	
	public int getDeltaX(){
		return x-maxX;
	}
	
	public int getDeltaY(){
		return y-maxY;
	}
	
	public int getDeltaWidth(){
		return width-maxWidth;
	}
	
	public int getDeltaHeight(){
		return height-maxHeight;
	}


	public void onMouseHover(int x, int y) {
		if(buttons!=null)
		for(Button button:buttons){
			button.onMouseOver(x, y);
		}
		if(containers!=null)
		for(Container container:containers){
			container.onMouseHover(x, y);
		}
		
	}


	
	
	
	
	
	


}
