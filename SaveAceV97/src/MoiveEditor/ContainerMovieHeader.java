package MoiveEditor;

import java.awt.Graphics2D;

import Views.Container;

public class ContainerMovieHeader extends ContainerHead {

	public ContainerMovieHeader(int x, int y, int width, int height, Selector selector, String name,MovieEditor editor) {
		super(x, y, width, height, selector, name);
		 container = new  ContainerMovieBody(x,y+height,width,300,editor);
		 container.setShow(false);
		 containers.add(container);
		
	}
	
	
	public void tick(){
		super.tick();
		if(container instanceof ContainerMovieBody){
			ContainerMovieBody body = (ContainerMovieBody)(container);
			body.tick();
		}
	
	}
	
	public void render(Graphics2D g2d){
		super.render(g2d);
		if(container instanceof ContainerMovieBody){
			ContainerMovieBody body = (ContainerMovieBody)(container);
			body.render(g2d);
		}
	}

	

}
