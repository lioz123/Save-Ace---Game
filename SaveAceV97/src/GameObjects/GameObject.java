package GameObjects;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SizeRequirements;

import AI.Movment;
import General.ImageData;
import General.SpriteSheet;
import Main.PhiyscalObject;
import Views.ItemsInventory;

public abstract class GameObject  implements PhiyscalObject{
	public int x,y,width,height,speedX=0,speedY=0,imageX,imageY,imageWidth, imageHeight, medKitCounter=0,syringeCounter;
	public ID id;
	public BufferedImage img;
    protected SpriteSheet ss;
    protected List<Item> items;
    protected AttackID attack=AttackID.none;
	protected boolean isAttacking=false;
	private boolean attacskLock=false;
	protected boolean startAttackCounter =false;
	protected int defultImageWidth,defultImageHeight,defultRImageX,defultRImageY,defultWidth,defultLImageX,difultLImageY;
	protected int attackDeley=0,attackTimCtn=0;
	protected int defultLImageY;
	protected Side side;
	protected int attackImageRX=0, attackImageLX=0,attackImageLY=0;
	protected ItemsInventory inventory;
	protected KeyEvent key;
	
	
	public void KeyInput(KeyEvent key){
		this.key= key;
	}

	protected ArrayList<MovementData> attacksData =new ArrayList<MovementData>();
	protected boolean attackLock=false;
	
	public ItemsInventory getInventory(){
		return this.inventory;
	}
	
	public void setSide(Side side){
		this.side=side;
	}
	
	public void setImage(ImageData img){
		imageX=img.getImageX();
		imageY=img.getImageY();
		imageWidth=img.getImageWidth();
		imageHeight=img.getImageHeight();
	}
	
	public Side getSide(){
		return this.side;
	}
	public GameObject(int x, int y, int width, int height, ID id)
	{
		this.x=x; 
		this.y=y;
		this.width=width;
		this.height=height;
		this.id=id;
		
	}
	
	public int distance(GameObject object){
		int middleX=(int)(this.getBounds().getMaxX()+this.getBounds().getMinX()/2);
		int middleY=(int)(this.getBounds().getMaxY()+this.getBounds().getMinY()/2);
		int objectMiddleX = (int)(object.getBounds().getMinX()+object.getBounds().getMaxX()/2);
		int objectMiddleY = (int)(object.getBounds().getMinY()+object.getBounds().getMaxY()/2);
		int d =(int) Math.sqrt(Math.pow(middleX-objectMiddleX, 2)+Math.pow(middleY-objectMiddleY, 2));
		return d;
	}
	
	public boolean isAttack(){
		return this.isAttacking;
	}
	
	public void setAttack(AttackID attack){
		if(attack == this.attack)
			return;
		
		this.attack=attack;
		if(attack==AttackID.none){
			this.isAttacking=false;
			setAttackLock(false);
		}
		else{        
			this.isAttacking=true;
			setAttackLock(true);
		}
	}
	
	public BufferedImage getImage(){
		
		
		return this.img;
	}
	

	public GameObject(GameObject object)
	{
		this.x=object.x; 
		this.y=object.y;
		this.width=object.width;
		this.height=object.height;
		this.id=object.id;
		this.ss=object.ss;
		
	}
	

	public void useItem(ID item){
		  for(Item checkItem:items){
			  if(checkItem.id==item){
				  if(item==ID.medkit) medKitCounter--;
				  if(item==ID.syring) syringeCounter--;
				  checkItem.Activation(this);
				  items.remove(checkItem);
				 return;
			  }
		  }
		}
	
	
	public abstract void render(Graphics2D g2d);
	public abstract void tick();

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,width-1,height-1);
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
	this.y=y;	
	}
	
	public void setSpeedX(int speedX){
		this.speedX=speedX;
	}
	public void setSpeedY(int speedY){
		this.speedY=speedY;
	}
	
	public void setImageX(int x){
		this.imageX=x;
	}
	
	public void setImageY(int y){
		this.imageY=y;
	}
	
	public int distance(int x, int y){
	 int distance = (int) Math.sqrt(Math.pow(this.x-x,2) + Math.pow(this.y-y,2));
	 return distance;
	}
	
	public List<Item> getItems(){
		return this.items;
	}
	
	public void ItemTransfer(GameObject object){
		
		for(Item item: items){
			object.addItem(item);			
			switch (item.id) {
			case medkit:medKitCounter--;break;
			case syring:syringeCounter--; break;
			}
			
		}
		items = new ArrayList<Item>();
	}
	
	public void addItem(Item item){
	 items.add(item);
	 switch(item.id){
	 case medkit: medKitCounter++; break;
	 case syring: syringeCounter++; break;
	 }
	}
	
	public Rectangle getRecSide(Side side){
		int minX=x;
		int minY=y;
		int maxX=x+width;
		int maxY=y+height;
		switch(side){
		case left:return new Rectangle(minX,minY,1,height);
		case right: return new Rectangle(maxX,minY,1,height);
		case down: return new Rectangle(minX,minY,width,1);
		case up: return new Rectangle(minX,maxY,width,1);
		}
		return getBounds();
	}
	
	public Rectangle getRecSide(Side side, Rectangle rec){
		int minX=(int)rec.getX();
		int minY=(int)rec.getY();
		int maxX=(int) (rec.getX()+rec.getWidth());
		int maxY=(int) (rec.getY()+rec.getWidth());
		switch(side){
		case left:return new Rectangle(minX,minY,1,height);
		case right: return new Rectangle(maxX,minY,1,height);
		case down: return new Rectangle(minX,minY,width,1);
		case up: return new Rectangle(minX,maxY,width,1);
		}
		return getBounds();
	}

	public void updateHp(int i) {
		// TODO Auto-generated method stub
		
	}


	public void updateStamina(int i) {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle getRec(int x, int y){
	return new Rectangle(x,y,width,height);
	}
	

	public Side getCollisionSide(GameObject object,Rectangle rec){
		ArrayList<Rectangle> sides = new ArrayList<Rectangle>();
		// supposed to be only in this order : up,down,right,left
		sides.add(getRecSide(Side.up));
		sides.add(getRecSide(Side.down));
		sides.add(getRecSide(Side.right));
		sides.add(getRecSide(Side.left));
		
		for(int i=0; i<sides.size();i++){
			if(sides.get(i).intersects(object.getBounds())){
			switch(i){
			case 0:return Side.up ;
			case 1:return Side.down;
			case 2:return Side.right;
			case 3: return Side.left;
			}
			}
		}
		return Side.none;		
	}
	
	public Side getCollisionRecSide(GameObject object,Rectangle rec){
		ArrayList<Rectangle> sides = new ArrayList<Rectangle>();
		// supposed to be only in this order : up,down,right,left
		sides.add(getRecSide(Side.up));
		sides.add(getRecSide(Side.down));
		sides.add(getRecSide(Side.right));
		sides.add(getRecSide(Side.left));
		
		for(int i=0; i<sides.size();i++){
			if(sides.get(i).intersects(object.getBounds())){
			switch(i){
			case 0:return Side.up ;
			case 1:return Side.down;
			case 2:return Side.right;
			case 3: return Side.left;
			}
			}
		}
		return null;		
	}

	public boolean isAttackLock() {
		return attackLock;
	}

	public void setAttackLock(boolean attackLock) {
		this.attackLock = attackLock;
	}
	

	
	public MovementData getAttackDataByAttackID(AttackID id){
		for(MovementData attack:attacksData){
			if(attack.getAttackID()==id){
				return attack;
			}
		}
		return null;
	}
	
	public void resetAttacksFilms(){
		for(MovementData data:attacksData){
			if(data.getAttackID()!=attack){
				data.resetFilms();
				
			}else{
				data.resetOneFilm(side);
			}
		}
	}

	public void setRoom(ObjectHandler room) {
		
		
	}
	

	
	
	
	
	

}
