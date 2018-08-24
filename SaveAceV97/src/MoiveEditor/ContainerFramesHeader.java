package MoiveEditor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.SynchronousQueue;

import Editors.NormalButton;
import Editors.Type;
import MapTrazlation.SpriteSheetList;

public class ContainerFramesHeader extends ContainerHead {
	private MovieEditor editor;

	public ContainerFramesHeader(int x, int y, int width, int height, Selector selector, String name,MovieEditor editor) {
		super(x, y, width, height, selector, name);
	
///		this.containers.get(0).addButton(button);
		this.editor=editor;
		 container = new  ContainerFramesBody(x,y+height,width,200,editor);
		 container.setShow(false);
		 containers.add(container);
		 
		
		}
	
		public void tick(){
			super.tick();
		
			
		}
	
	
		public void render(Graphics2D g2d){
			super.render(g2d);
		}
		

	

}
