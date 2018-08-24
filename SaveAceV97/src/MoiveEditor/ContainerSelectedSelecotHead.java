package MoiveEditor;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class ContainerSelectedSelecotHead extends ContainerHead {
	private MovieEditor editor;
	public ContainerSelectedSelecotHead(int x, int y, int width, int height, Selector selector, String name,MovieEditor editor) {
		super(x, y, width, height, selector, name);
		
///		this.containers.get(0).addButton(button);
		this.editor=editor;
		 container = new  ContainerSelectedSelectorBody(x,y+height,width,300,editor);
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
		