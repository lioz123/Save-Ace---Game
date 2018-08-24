package MoiveEditor;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import General.Utilities;

public class Camera{
	private Rectangle rect;
	private boolean selected = false;
	private Color bg = null;
	public Camera(){
		this.setRect(new Rectangle());
	}
	public Rectangle getRect() {
		return rect;
	}
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}
	
	public void chnageBounds(PointInt start, PointInt end){
		int x, y ,width ,height;
		if(start.getX()>end.getX()){
			x=end.getX();
		}else{
			x=start.getX();
		}
		if(start.getY()>end.getY()){
			y=end.getY();
		}else{
			y= start.getY();
		}
		width = Math.abs(start.getX()-end.getX());
		height = Math.abs(start.getY()-end.getY());
		rect = new Rectangle(x,y,width,height);
	}
	
	
	public boolean onClick(int x,int y){
	if(Utilities.isClicked(x, y, rect)){
		this.selected=!selected;
		return true;
	}
	return false;
	}
	
	
	
	
	public void render(Graphics2D g2d){
		g2d.setColor(Color.black);
		
		g2d.draw(rect);
		if(selected){
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.1));
			g2d.fill(rect);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		}
		if(bg!=null){
			g2d.setColor(Color.gray);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
			g2d.fill(rect);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		}
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public void setColor(Color color) {
	this.bg=color;
		
	}
	
	
	
	

}
