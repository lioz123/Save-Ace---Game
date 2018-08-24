package General;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageData {
	
	private int x,y,width,height,dx,dy,attack=0 , defence=0;
	private boolean solid=false;
	private ArrayList<ImagesGroup> groups = new ArrayList<ImagesGroup>();
	public ImageData(int x,int y,int width,int height){
		
		this.x=x;
		this.width=width;
		this.height=height;
		this.y=y;
		dx=0;
		dy=0;
	}
	
	public ImageData(Rectangle rect){
		if(rect!=null){
			x=(int)rect.getX();
			y=(int)rect.getY();
			width=(int)rect.getWidth();
			height=(int)rect.getHeight();
		dx =0;
		dy=0;
		}

	}
	
	public ArrayList<ImagesGroup> getGroup(){
		return this.groups;
	}
	
	
	public ImageData(ImageData data){
		this.x=data.getImageX();
		this.y=data.getImageY();
		this.width=data.getImageWidth();
		this.height=data.getImageHeight();
		this.defence=data.getDefence();
		this.solid=data.isSolid();
		this.attack=data.getAttack();
		this.dx=data.getDeltaX();
		this.dy=data.getDeltaY();
		this.attack=data.getAttack();
		this.solid=data.isSolid();
		this.defence=data.defence;
	}
	
	public void setDefence(int defence){
		this.defence=defence;
	}
	
	public int getDefence(){
		return this.defence;
	}
	
	public int getAttack(){
		return this.attack;
	}
	
	public void setAttack(int attack){
		this.attack=attack;
	}
	
	public int getDeltaX(){
		return this.dx;
	}
	
	public int getDeltaY(){
		return this.dy;
	}
	
	public void setDeltaX(int dx){
		this.dx=dx;
	}
	
	public void setDeltaY(int dy){
		this.dy=dy;
	}
	
	

	public int getImageX(){
		return x;
	}
	
	public int getImageY(){
		return y;
	}
	public int getImageWidth(){
		return width;
	}
	public int getImageHeight(){
		return height;
	}

	public void setBounds(Rectangle frame){
		if(frame==null){
			return;
		}
		this.x=(int)frame.getX();
		this.y=(int)frame.getY();
		this.width=(int)frame.getWidth();
		this.height=(int)frame.getHeight();
	}
	
	public void setSolid(boolean b){
		this.solid=b;
	}
	
	public boolean isSolid(){
		return this.solid;
	}

	public Rectangle getFrame() {
	 return new Rectangle(x,y,width,height);
		
	}
	

	

	
	
	
	
}
