package MoiveEditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.SynchronousQueue;

import Editors.NormalButton;
import Editors.Type;
import MapTrazlation.SpriteSheetList;

public class ContainerSelectorHead extends ContainerHead {
	private MovieEditor editor;
	private Frame frame=null;
	private Selector selector;
	
	public ContainerSelectorHead(int x, int y, int width, int height, Selector selector, String name,MovieEditor editor) {
		super(x, y, width, height, selector, name);
	
///		this.containers.get(0).addButton(button);
		this.editor=editor;
		 container = new  ContainerSelectorBody(x,y+height,width,250,editor);
		 container.setShow(false);
		 
		 containers.add(container);
		 
		
		}
	
		public void tick(){
			super.tick();
			if(container == null){
				
				return ;
			}
			if(frame!=null){
				frame.setX(container.getX());
				frame.setY(container.getY());
			}
			
			
		}
	
	
		public void render(Graphics2D g2d){
			super.render(g2d);
		}
		
		public boolean onClick(int x,int y){
			 ContainerSelectorBody containerBody=null;
			 if(this.container instanceof ContainerSelectorBody){
				containerBody = (ContainerSelectorBody)(this.container);
			 }
			 
			 
				if(super.onClick(x, y)){
					
				
					return true;
				}
				
				 Selector selector = editor.getSelectedSelector();
				 if(selector !=this.selector){
					
					 this.selector=selector;
					 containerBody.setSelector(selector);
					 if(selector!=null){
						 frame = new Frame(container.getX(),container.getY(),100,100,selector.getFrame(),editor.getSpriteSheet());
						 containerBody.setFrame(frame);
				 }else{
					 frame =null;
					 containerBody.setFrame(null);
				 }
				
				 }
			return false;
			
		}
		
	

}
