package Views;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseConnector extends MouseAdapter {
	
	private Container container;
	public MouseConnector(Container container){
		this.container=container;
	
	}
	
	public void mousePressed(MouseEvent e){
		container.mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e){
		container.mosueReleased(e);;
	}
	
	public void mouseClicked(MouseEvent e){
		container.mouseClicked(e);
	}
	
	public void mouseDragged(MouseEvent e){
		container.mouseDragged(e);
	}
}
