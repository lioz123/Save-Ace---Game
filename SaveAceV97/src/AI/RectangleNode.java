package AI;

import java.awt.Rectangle;

import GameObjects.Block;

public class RectangleNode {
	private boolean isClosed=false;
	private boolean isBlocked = false;
	private boolean isOpenedNow=false;
	private int x,y,col,row;
	private double h=0; // distance between the node to the goal node
	private double g=0; // movment cost
	private double f=0; //f+g
	private Rectangle rec;
	private RectangleNode prev;
	public RectangleNode(Block block){
		this.rec=block.getRectangle();
		this.x=(int)rec.getX();
		this.y=(int)rec.getY();
		this.col=block.getCol();
		this.row=block.getRow();
		if(block.getObject()!=null)
		this.isBlocked=true;
	}	
	
	public RectangleNode getPrevRec(){
		return this.prev;
	}
	
	public void setPrevRec(RectangleNode prev){
		this.prev=prev;
	}
	
	public void SetDistance(RectangleNode node){
		double x=node.getRow()-row;
		double y = node.getCol()-col;
		x=x*x;
		y=y*y;
		this.h=x+y;
		
		this.h=Math.sqrt(h);
		
	
	}
	
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public double getG(){
		return this.g;
	}
	public double getH(){
		return this.h;
	}
	
	public double getF(){
		return this.f;
	}
	public int getCol(){
		return this.col;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public boolean IsBlocked(){
		return this.isBlocked;
	}
	
	public boolean IsClosed(){
		return this.isClosed;
	}
	
	public void setClose(boolean b){
		this.isClosed=b;
	}
	
	public void calculateF(){
		f=g+h;
	}

	public void setG(double g) {
		this.g=g;
		
	}

	public boolean IsOpenNow() {
		// TODO Auto-generated method stub
		return this.isOpenedNow;
	}

	public void setIsOpenNow(boolean b) {
	this.isOpenedNow=b;
		
	}
	
}
