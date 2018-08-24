package MoiveEditor;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import General.SpriteSheet;
import General.Utilities;
public class Puzzle {
	
	private int x, y, width, height, maxWidth,maxHeight;
	private Rectangle normalImagebounds,normalBounds;
	private ArrayList<Rectangle> recs = new ArrayList<Rectangle>();
	private BonesGroup group;
	private SpriteSheet ss;
	private int divBound;
	public Puzzle(int x, int y, int width, int height,SpriteSheet ss){
	this.x=x;
	this.y=y;
	this.width=width;
	this.height=height;
	this.maxWidth=width;
	this.maxHeight=height;
	this.ss=ss;
	divBound = 1;
	}
	
	
	public void setBonesGroup(BonesGroup group){
		this.group=group;
		
	}
	
	public void setNormalImageBounds(Rectangle rect){
		this.normalImagebounds=rect;
	}
	
	public void setRecs(ArrayList<Rectangle> recs){
		this.recs=recs;
	}
	
	
	public void GenerateRecs(){
	
	}
	public void CalcualteRecsBounds(){
		recs = new ArrayList<Rectangle>();
	if(group!=null){
		int i = 0;
		 while(i<group.size()){
			 double vh , vw;
		
			 this.normalBounds= new Rectangle(x,y,width,height);
			 Rectangle bounds = Utilities.getBoundsFromImageToWindow(normalBounds, group.getBone(i).getFrame(), this.normalImagebounds);
		
			recs.add(bounds);
			 i++;
		 }
	}
	
	}
	
	
	
	
	
	public void render(Graphics2D g2d){
		CalcualteRecsBounds();	
		int i =0;
		while(i<recs.size()){
			Rectangle rec = recs.get(i);
			Selector bone = group.getBone(i);
			if(bone!=null){
				Rectangle frame = bone.getFrame();
				g2d.drawImage(ss.grabImage(frame),(int)rec.getX(),(int)rec.getY(),(int)rec.getWidth(),(int)rec.getHeight(),null);
				g2d.drawRect(x, y, width, height);
			}
			
			i++;
		}
	}


	public void setX(int x) {
		this.x=x;
		
	}
	
	public void setY(int y){
		this.y=y;
	}

}
