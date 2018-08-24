package MoiveEditor;

import java.awt.Graphics2D;

import Views.Container;

public class ContainerAramtureHead extends ContainerHead {

	public ContainerAramtureHead(int x, int y, int width, int height, Selector selector, String name,MovieEditor editor) {
		super(x, y, width, height, selector, name);
		 container = new   ContainerArmatureBody(x,y+height,width,320,editor);
		 container.setShow(false);
		 containers.add(container);
		
	}
	
	
	public void tick(){
		super.tick();
		if(container instanceof  ContainerArmatureBody){
			 ContainerArmatureBody body = (ContainerArmatureBody)(container);
			body.tick();
		}
	
	}
	
	public void render(Graphics2D g2d){
		super.render(g2d);
		if(container instanceof  ContainerArmatureBody){
			 ContainerArmatureBody body = ( ContainerArmatureBody)(container);
			body.render(g2d);
		}
	}
	
	

	

}
