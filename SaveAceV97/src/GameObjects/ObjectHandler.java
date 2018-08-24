package GameObjects;




import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import AI.RectangleNode;
import Main.Game;
import Views.Bar;
import Views.Inventory;

public class ObjectHandler {
public List<GameObject> objects = new ArrayList<GameObject>();
public List<Bar> bars = new ArrayList<Bar>();
public List<Item> items=new ArrayList<Item>();

private ArrayList<Block> blocks = new ArrayList<Block>();
private ArrayList<RectangleNode> path=new ArrayList<RectangleNode>();
private boolean  running=true;
private Point up,down,left,right;
List<Inventory> inventories = new ArrayList<Inventory>();
public ObjectHandler(){
int middleX,middleY;
middleX = Game.WIDTH/2;
middleY=Game.HEIGHT/2;

up=new Point(middleX,0);
down=new Point(middleX,Game.HEIGHT-Map.BLOCKSIZE);
right= new Point(Game.WIDTH-Map.BLOCKSIZE,middleY);
left=new Point(0,middleY);
}

public void setUp(Point point){
	up=point;
}
public void setDown(Point point){
	down=point;
}

public void setRight(Point point ){
	right=point;
}

public void setLeft(Point point){
	down=point;
}

public Point getUp(){
	return this.up;
}
public Point getDown(){
	return this.down;
}

public Point getRight(){
	return this.right;
}

public Point getLeft(){
	return this.left;
}
public void add(GameObject object){
	
	objects.add(object);
	if(object instanceof Mob){
		((Mob)(object)).setRoom(this);
	}
  
	
}
public void addBlock(Block block){
	blocks.add(block);
	if(block.getObject()!=null){
		objects.add(block.getObject());
	}

}

public void addBlockList(ArrayList<Block>blocks){
	this.blocks.addAll(blocks);
	for(Block block:blocks){
		if(block.getObject()!=null){
			objects.add(block.getObject());
	
		}
		
	}
}

public ArrayList<Block> getMap(){
	return this.blocks;
}

public void addInventory(Inventory inventory){
	inventories.add(inventory);
}
public void addItem(Item item){
	items.add(item);
}

public ArrayList<Block> getBlockList(){
	return this.blocks;
}

public Block getBlock(int col, int row){
	for(Block block:blocks){
		if(block.getRow()==row&&block.getCol()==col){
			return block;
		}
	}
	return null;
}

public void addBar(Bar bar){
	bars.add(bar);
}

public void addObjectArrayList(ArrayList<GameObject> objects){
	this.objects.addAll(objects);
}
public void remove(GameObject object){
 objects.remove(object);
}

public void tick(){
	if(running){
		for(GameObject object:objects){
			object.tick();
			}
		for(Block block:blocks){
			block.tick();
		}
			for(Inventory inventory: inventories){
				inventory.tick();
			}
			
	}

}


public void render(Graphics2D g2d){

	
	for(Block block:blocks){
		block.render(g2d);
	}
	if(path!=null)
	for(RectangleNode node:path){
		g2d.setColor(Color.red);
		g2d.drawRect(node.getX(),node.getY(),Map.BLOCKSIZE,Map.BLOCKSIZE);
	}
	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
	for(GameObject object:objects){
		object.render(g2d);
		}
	g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
	for(Inventory inventory: inventories){
		inventory.render(g2d);
	}
	 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
	if(getPlayer()!=null){
		if(getPlayer().getInventory()!=null){
		if(getPlayer().getInventory().isShow()){
			g2d.setColor(Color.black);
			 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));

			g2d.fillRect(0, Game.PLAYBOUNDRY, Game.WIDTH, Game.HEIGHT);
			 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));


		}
	
			
			getPlayer().getInventory().render(g2d);
		}
		
		
	}
	
	
}

public void addPath(ArrayList<RectangleNode> path){
	this.path=path;
}

public Object getObjects(){
	return objects;
	}
public GameObject getPlayer() {
 for(GameObject object:objects){
	 if(object.id==ID.player) return object;
 }
	return null;
}

public boolean intesectWithSolid(Rectangle rec){
	for(GameObject object:objects){
		if(object.id==ID.solid||object.id==ID.chest){
			if(object.getBounds().intersects(rec))return true;
			
		}
	}

	return false;
	
}

public void addObjectoOnBlock(int col, int row, Solid object){
	objects.add(object);
	getBlock(col, row).setSolid(object);
}

public ArrayList<GameObject> intesectWithMPC(Rectangle rec){
	ArrayList<GameObject> mpcs = new ArrayList<GameObject>();
	for(GameObject object:objects){
		if(object.id==ID.soldier&&object.getBounds().intersects(rec)){
			mpcs.add(object);
		}
	}
	return  mpcs;
}
public void run(boolean b) {
	this.running=b;
	
}
public Block getBlockByObject(GameObject object){
	for(Block block:blocks){
		if(block.getObject()==null)
		if(block.getRectangle().intersects(object.getBounds())){
			return block;
		}
	}
	return null;
}
}
