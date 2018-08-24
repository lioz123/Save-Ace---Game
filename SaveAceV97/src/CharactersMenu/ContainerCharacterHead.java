package CharactersMenu;

import java.awt.Color;
import java.awt.Graphics2D;

import MoiveEditor.ContainerHead;
import MoiveEditor.ContainerSelectedSelectorBody;
import MoiveEditor.Selector;

public class ContainerCharacterHead extends ContainerHead{
	private Color background=Color.gray;
	public ContainerCharacterHead(int x, int y, int width, int height, Selector selector, String name) {
		super(x, y, width, height, selector, name);
		container = new  ContainerCharacterBody(x,y+height,width,300);
		 container.setShow(false);
		 containers.add(container);
	}
	
	
	public void render(Graphics2D g2d){
		g2d.setColor(background);

		super.render(g2d);
	
		if(container instanceof ContainerCharacterBody){
			((ContainerCharacterBody)container).render(g2d);
		}
	}
	
	public void tick(){
		super.tick();
	}
	

}
