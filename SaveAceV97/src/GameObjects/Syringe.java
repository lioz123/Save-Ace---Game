package GameObjects;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import General.SpriteSheet;

public class Syringe extends Item {
     
	

	public boolean hasSyringe=false;
	public boolean insideInventory = false;
	public Syringe(int x, int y, int width, int height, int imageX, int imageY, ID id, SpriteSheet ss) {
		super(x, y, width, height, imageX, imageY, id, ss);
		img = ss.grabImage(35,3,30,10);
	} 
	
	
	public Syringe(ID id){
		super(id);
	}
	public void setInsideInvetory(boolean b){
		this.insideInventory=b;
	}
   
	@Override
	public void Activation(GameObject object) {
		object.updateStamina(10);
	}

	@Override
	public void render(Graphics2D g2d) {
		  if(!hasSyringe&&insideInventory){ 
			   g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.1));
		   }
		g2d.drawImage(img, x, y, width, height,null);
		
	}

}
