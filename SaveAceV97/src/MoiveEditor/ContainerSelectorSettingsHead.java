package MoiveEditor;

import java.awt.Graphics2D;

public class ContainerSelectorSettingsHead extends ContainerHead {
	private MovieEditor editor;
	

	public ContainerSelectorSettingsHead(int x, int y, int width, int height, Selector selector, String name,MovieEditor editor) {
		super(x, y, width, height, selector, name);
	
///		this.containers.get(0).addButton(button);
		this.editor=editor;
		 container = new  ContainerSelectorSettingsBody(x,y+height,width,270,editor);
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
		