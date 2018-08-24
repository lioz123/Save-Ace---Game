package Editors;

import java.awt.image.BufferedImage;

import General.SpriteSheet;

public class SlotData {
	private SpriteSheet floorSprite=null,objectSprite=null;
	private BufferedImage floorImg=null,objectImg=null;
	private String floorPath="",objectPath="",object="null"; // name of spriteSheet
	private int col=0,row=0,objectCol=0,objectRow=0,floorCol=0,floorRow=0,floorSize=0,objectSize=0;
	private int objectDeltaX=0,objectDeltaY=0,floorDeltaX=0,floorDeltaY=0;
	public SlotData(){
		
	}
	public void setFloorDeltaX(int delta){
		this.floorDeltaX=delta;
	}
	public void setFloorDeltaY(int delta){
		this.floorDeltaY=delta;
	}
	
	public void setObjectDeltaX(int delta){
		this.objectDeltaX=delta;
	}
	
	public void setObjectDeltaY(int delta){
		this.objectDeltaY=delta;
	}
	
	
	public int getFloorDeltaX(){
		return this.floorDeltaX;
	}
	public int getFloorDeltaY(){
		return this.floorDeltaY;
	}
	public int getObjectDeltaX(){
		return this.objectDeltaX;
	}
	public int getObjectDeltaY(){
		return this.objectDeltaY;
	}
	
	public void setObjectName(String object){
		this.object=object;
	}
	public void setCol(int col){
		this.col=col;
	}

	
	public void setRow(int col){
		this.row=col;
	}
	
	public void setObjectCol(int col){
		this.objectCol=col;
	}
	
	public void setObjectRow(int col){
		this.objectRow=col;
	}
	
	public void setFloorCol(int col){
		this.floorCol=col;
	}
	
	public void setFloorRow(int col){
		this.floorRow=col;
	}
	public void setFloorSize(int col){
		this.floorCol=col;
	}
	public void setObjectSize(int col){
		this.objectSize=col;
	}
	
	public int getFloorRow(){
		return this.floorRow;
	}
	
	public int getFloorCol(){
		return this.floorCol;
	}
	
	public int getObjectRow(){
		return this.objectRow;
	}
	
	public int getObectCol(){
		return this.objectCol;
	}
	
	public void setFloorSpriteSheetPath(String path){
		this.floorPath=path;
	}
	public void setObjectSpriteSheetPath(String path){
		this.objectPath=path;
	}
	public String toString(){
		return object+" "+objectCol+ " " + objectRow + " " + floorCol +" " + floorRow +" " +col + " " + row ;
						
	}

	public String getObjectName() {
		// TODO Auto-generated method stub
		return this.object;
	}
	
	
	

}
