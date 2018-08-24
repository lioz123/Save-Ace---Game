package Views;

public class AnimationFullSize {

	
	private int x,y,width,height,maxX,maxY,maxWidth,maxHeight;
	private double xkey=0.1, ykey=0.1 ,widthkey=0.1,hegihtkey=0.1;
	private double delta,deltaLocation,deltaSize;
	private boolean animate = false;
	
	
	public AnimationFullSize(int x , int y, int width,int height,int maxX,int maxY,int maxWidth,int maxHeight,double delta){
		this.x=x;
		this.y=y;
		this.maxWidth=maxWidth;
		this.width=width;
		this.height=height;
		this.maxHeight=maxHeight;
		this.maxX=maxX;
		this.maxY=maxY;
		this.setDelta(delta);
		this.deltaLocation=delta;
		this.deltaSize=delta;
	}
	
	

	
	
	public void animate(){
		if(x<maxX){
			double t=deltaLocation/xkey;
			x+=(int)t;
		}else{
			x=maxX;
		}
		
		if(y<maxY){
		double t=deltaLocation/ykey;
		y+=t;
		}else{
			y=maxY;
		}
		
		if(width<maxWidth){
			double t=deltaSize/widthkey;
			width+=t;
		}else{
			width=maxWidth;
		}
		if(height<maxHeight){
			double t =deltaSize/hegihtkey;
			height+=t;
		}else{
			height = maxHeight;
		}
		
	
		
	}
	
	
	public void setAnimate(boolean b){
		this.animate=b;
	}
	
	




	public int getX() {
		return x;
	}




	public void setX(int x) {
		this.x = x;
	}




	public int getY() {
		return y;
	}




	public void setY(int y) {
		this.y = y;
	}




	public int getWidth() {
		return width;
	}




	public void setWidth(int width) {
		this.width = width;
	}




	public int getHeight() {
		return height;
	}




	public void setHeight(int height) {
		this.height = height;
	}




	public int getMaxX() {
		return maxX;
	}




	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}




	public int getMaxY() {
		return maxY;
	}




	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}




	public int getMaxWidth() {
		return maxWidth;
	}




	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
	}




	public int getMaxHeight() {
		return maxHeight;
	}




	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}
	
	public void tick(){
		if(animate){
			animate();
		}
	}









	public double getDeltaLocation() {
		return deltaLocation;
	}









	public void setDeltaLocation(double deltaLocation) {
		this.deltaLocation = deltaLocation;
	}









	public double getDeltaSize() {
		return deltaSize;
	}









	public void setDeltaSize(double deltaSize) {
		this.deltaSize = deltaSize;
	}









	public double getDelta() {
		return delta;
	}









	public void setDelta(double delta) {
		this.delta = delta;
		this.deltaSize=delta;
		this.deltaLocation=delta;
	}




}


