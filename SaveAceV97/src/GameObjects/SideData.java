package GameObjects;

public class SideData {

	private Side side;
	int x,y,width,height;
	public SideData(Side side,int x,int y, int width,int height){
		this.y=y;
		this.x=x;
		this.width=width;
		this.height=height;
		this.side=side;
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
	
	public int getHeight(){
		return this.height;
	}
	
	public Side getSide(){
		return this.side;
	}
	
}
