package Editors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import GameObjects.GameObject;
import General.SpriteSheet;
import MapTrazlation.SpriteSheetList;

public  class Slot {
	protected Type type;
	protected Rectangle rec;
	protected int col,row,x,y,width,height,objectImageX=0,floorImageX=-1,floorImageY=-1,objectImageY=0;
	protected String objectName;
	protected BufferedImage img = null;
	protected BufferedImage FloorImg=null;
	protected SlotData slotData;
	protected SpriteSheet ss;
	public Slot(Rectangle rec,int col,int row,Type type){
		this.rec=rec;
		this.row=row;
		this.col=col;
		this.objectName="null";
		this.x=(int)rec.getX();
		this.y=(int)rec.getY();
		this.width=(int)rec.getWidth();
		this.height=(int)rec.getHeight();
		this.type=type;
		slotData = new SlotData();
		slotData.setCol(col);
		slotData.setRow(row);
	
	}
	public void setFloorImage(BufferedImage img){
		this.FloorImg=img;
	}
	
	public void setFloorImageX(int imageX){
		this.floorImageX=imageX;
	}
	
	public void setFloorImageY(int imageY){
		this.floorImageY=imageY;
	}
	
	public void setObjectImageX(int x){
		this.objectImageX=x;
	}
	public void setObjectImageY(int y){
		this.objectImageY=y;
	}
	
	public void setFloorImageBounds(int x , int y,int width,int height){
		if(x>-1&&y>-1){
			ss = SpriteSheetList.tiles;
			this.floorImageX=x;
			this.floorImageY=y;
			this.FloorImg=ss.grabImage(x, y,width,height);
		}
	}
	
	public int getObjectImageX(){
		return this.objectImageX;
	}
	
	public int getObjectImageY(){	
		return this.objectImageY;
	}
	
	public int getFloorImageX(){
		return this.floorImageX;
	}
	
	public int getFloorImageY(){
		return this.floorImageY;
	}
	public BufferedImage getFloorImage(){
		return this.FloorImg;
	}
	public SlotData getSlotData(){
		return this.slotData;
	}
	
	public void setSlot(SlotData slot){
		this.slotData=slot;
	}
	public String getObjectName(){
		return this.objectName;
	}
	
	public BufferedImage getImage(){
		return this.img;
	}
	
	public Type getType(){
		return this.type;
	}
	
	public void setBufferedImage(BufferedImage img){
		this.img=img;
		
	}
	public void setObjectName(String object){
		this.objectName=object;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public String toString(){
		String str=objectName + " "+ objectImageX +" "+ objectImageY + " " + floorImageX+ " " + floorImageY + " " +col+ " " + row;
		return str;
	}
	
	public void render(Graphics2D g2d){
		
		
		if(this.img!=null){
			if(this.FloorImg!=null){
				g2d.drawImage(FloorImg, x, y, width, height,null);	
			}
			g2d.drawImage(img,x,y,width,height,null);
		}else if(this.FloorImg!=null){
			g2d.drawImage(FloorImg, x, y, width, height,null);
			
		}
		else{
			g2d.setColor(new Color(179, 179, 179));
			g2d.fillRect(x, y, width, height);
			g2d.setColor(new Color(77, 77, 77));
			g2d.drawRect(x, y, width, height);
		}
	}

	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	


}
