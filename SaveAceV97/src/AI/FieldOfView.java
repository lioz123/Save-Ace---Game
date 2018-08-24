package AI;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import GameObjects.GameObject;
import GameObjects.ID;
import GameObjects.ObjectHandler;
import GameObjects.Side;

public class FieldOfView {
	public Point start;
	ArrayList<Rectangle> fieldOfView = new ArrayList<Rectangle>();
	public FieldOfView(Point start,ObjectHandler objectHandler,Side side){
		int resulution =21;
		int middle = resulution/2;
		
		this.start=start;
		int width=400,height=16;
		ArrayList<GameObject> objects = (ArrayList<GameObject>) objectHandler.getObjects();
		if(side==Side.right)
		start.setLocation(start.getX()+20, start.getY()-height*(middle-2));
		else{
			start.setLocation(start.getX()-width+35, start.getY()-height*(middle-2));
		}
		for(int i=0; i<resulution;i++)
		fieldOfView.add(new Rectangle(((int)start.getX()),((int) start.getY()+i*height),width,height));
		
		for(GameObject object:objects){			
			boolean collision= false;
			if(object.id==ID.solid||ID.chest==object.id){
				for(Rectangle rec:fieldOfView){
				if(rec.intersects(object.getBounds()))
				collision=true;			
			while(collision){	
				if(side==Side.right)
				rec.setBounds(new Rectangle((int)rec.getX(),(int) rec.getY(),(int) rec.getWidth()-1,(int)rec.getHeight()));
				else
					rec.setBounds(new Rectangle((int)rec.getX()+1,(int) rec.getY(),(int) rec.getWidth()-1,(int)rec.getHeight()));
				if(!rec.intersects(object.getBounds())){
					collision=false;
				}
			}
		}
			}
		}
		int counter =middle-2;
		while(counter>=0){
			if(fieldOfView.get(counter+1).getWidth()<=100&&fieldOfView.get(counter).getWidth()>fieldOfView.get(counter+1).getWidth()&&fieldOfView.get(counter).getHeight()>0){
				if(side == Side.left)
				fieldOfView.get(counter).setBounds((int)start.getX() +width -(int)fieldOfView.get(counter+1).getWidth(), (int)fieldOfView.get(counter).getY(),(int) fieldOfView.get(counter+1).getWidth(),(int)fieldOfView.get(counter+1).getHeight());
				else{
					fieldOfView.get(counter).setBounds((int)start.getX(), (int)fieldOfView.get(counter).getY(),(int) fieldOfView.get(counter+1).getWidth(),(int)fieldOfView.get(counter+1).getHeight());
				}
			}
			
			counter--;
		}
		for(int i=middle;i<fieldOfView.size();i++){
			if(fieldOfView.get(i-1).getWidth()<=100&&fieldOfView.get(i).getWidth()>fieldOfView.get(i-1).getWidth()&&fieldOfView.get(i).getHeight()>0){
				if(side==Side.left)
				fieldOfView.get(i).setBounds((int)start.getX()+width-(int) fieldOfView.get(i-1).width, (int)fieldOfView.get(i).getY(), (int) fieldOfView.get(i-1).getWidth(),(int)fieldOfView.get(i-1).getHeight());
				else{
					fieldOfView.get(i).setBounds((int)start.getX(), (int)fieldOfView.get(i).getY(), (int) fieldOfView.get(i-1).getWidth(),(int)fieldOfView.get(i-1).getHeight());
				}
			}
			
		}
		
	}
	
	public ArrayList<GameObject> getTargetsOnSight(ArrayList<GameObject> targets){
		ArrayList<GameObject> targetsOnSight = new ArrayList<GameObject>();
		
		for(GameObject object:targets){
			for(Rectangle rec:fieldOfView){
				if(rec.intersects(object.getBounds())){
					targetsOnSight.add(object);
					break;
				}
			}
		}
		return targetsOnSight;
	}
	
	public ArrayList<Rectangle> getFieldOfView(){
		return fieldOfView;
	}
}
