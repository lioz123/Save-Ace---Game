package GameObjects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import General.ImageLoader;
import General.SpriteSheet;
import Main.PhiyscalObject;
import MapTrazlation.SpriteSheetList;

public class Block implements PhiyscalObject {
	private BufferedImage img; 
	private Solid object;
	private boolean isBlocked=false;
	private boolean[]openSides = new boolean[4];
	private static int blockimageSize = 31;
	private int col=0,row=0;
	

	private Rectangle block;
	private int divWeight =5,width,height,x,y;
	private SpriteSheet ss;
	
	public Block(Rectangle block,BufferedImage img){
	ss= SpriteSheetList.tiles;
	this.block=block;
	this.img=img;
	x=(int) block.getX() ;
	y=(int) block.getY();
	width=(int) block.getWidth();
	height= (int) block.getHeight();
	for(int i=0;i<openSides.length;i++){
		openSides[i]=true;
	}
	}
	public static int getBlockImageSize(){
	
		return blockimageSize;
	}
	public void setImage(int x, int y,int width, int height){
		this.img=ss.grabImage(x, y, width, height);
	}
	public BufferedImage getImage(){
		return this.img;
	}
	
	
	public void addObject(Solid object){
		int x= (this.x+this.width-object.width)/2;
		int y= (this.y+this.height-object.height)/2;
		this.object=new Solid(x,y,object.getWidth(),object.getHeight(),ID.solid,object.getImage());
		this.isBlocked=true;
	}
	

	public void render(Graphics2D g2d){
		g2d.drawImage(img,x,y,width,height,null);
		
		if(object!=null){
			object.render(g2d);

		}
		
	}
	
	public Rectangle getRectangle(){
		return this.block;
	}
	
	public void setSolid(Solid solid){
		int x= this.x+(this.width-solid.width)/2;
		int y= this.y+(this.height-solid.height)/2;
		this.object=solid;
		object.setX(x);
		object.setY(y);
	}
	
	public void tick(){
	
		if(object!=null){
			object.tick();

		}
	}
	
	public void setCol(int col){
		this.col=col;
	}
	public void setRow(int row){
		this.row=row;
	}
	public int getRow(){
		return this.row;
	}
	public int getCol(){
		return this.col;
	}
	public Solid getObject() {
	
		return this.object;
	}
	
	public boolean IsBlocked(){
		return this.isBlocked;
	}
	
	

}
