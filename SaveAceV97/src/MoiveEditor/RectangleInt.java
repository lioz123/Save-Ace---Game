package MoiveEditor;

import java.awt.Point;
import java.awt.Rectangle;

public class RectangleInt {
	private Rectangle rec;
	public RectangleInt(Rectangle rec){
		this.setRec(rec);
	}
	
	public int getX(){
		return (int) rec.getX();
		
	}
	
	public int getY(){
		return (int) rec.getY();
	}
	
	public int getWidth(){
		return (int) rec.getWidth();
	}
	
	public int getHeight(){
		return (int) rec.getHeight();
	}
	
	public void setLocation(int x, int y){
		rec.setLocation(new Point(x,y));
	}
	
	
	public Rectangle getRec() {
		return rec;
	}
	public void setRec(Rectangle rec) {
		this.rec = rec;
	}

}
