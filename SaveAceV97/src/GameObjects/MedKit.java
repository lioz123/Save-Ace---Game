package GameObjects;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import General.SpriteSheet;

public class MedKit extends Item {

	public boolean hasMedcits=false;
	public boolean insideInventory = false;
	public MedKit(int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight, ID id,SpriteSheet ss) {
		super(x, y, width, height, imageX, imageY, imageWidth, imageHeight, id, ss);
		this.img = ss.grabImage(imageX, imageY, imageWidth, imageHeight);
	}

	public MedKit(int x, int y, int width, int height, int imageX, int imageY, ID id,SpriteSheet ss,boolean insideInventory) {
		super(x, y, width, height, imageX, imageY, id, ss);
		this.img = ss.grabImage(imageX, imageY, 30, 22);
		this.insideInventory=insideInventory;
	}
	
	public MedKit(ID id) {
		super(id);
	}
	
	public MedKit(int x, int y, int width, int height, int imageX, int imageY, int imageWidth, int imageHeight, ID id,SpriteSheet ss,boolean insideInventory) {
		super(x, y, width, height, imageX, imageY, imageWidth, imageHeight, id, ss);
		this.img = ss.grabImage(imageX, imageY, imageWidth, imageHeight);
		this.insideInventory=insideInventory;
	}
	
	
	

	@Override
	public void Activation(GameObject object) {
		object.updateHp(10);
		
	}

	@Override
	public void render(Graphics2D g2d) {
		
		   if(!hasMedcits&&insideInventory){ 
			   g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.1));
		   }
		g2d.drawImage(img,x, y, width, height, null);
	

		
	}


	

}
