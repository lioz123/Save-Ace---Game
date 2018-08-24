package AI;

import java.awt.Rectangle;
import java.util.concurrent.SynchronousQueue;

import GameObjects.ObjectHandler;

public class StraitPath {
	public Rectangle rec;
	
	public boolean hasStraitPath(Rectangle start,Rectangle end,ObjectHandler objectHandler){
		int counter=0;
		
		while(true){
			if(objectHandler.intesectWithSolid(start)){
		
				return false;
			}
			if(start.intersects(end)){
				
				return true;
			}
			
			double difX=start.x-end.x;
			double difY=start.y-end.y;
			double dis = Math.sqrt(Math.pow(start.x-end.x, 2)+Math.pow(start.y-end.y, 2));
			double valX=-1/dis*difX;
			double valY =-1/dis*difY;
			int x = (int) (valX+start.getX());
			int y = (int) (valY+start.getY());
			start.setLocation(x, y);
			counter++;
			if(counter>500){
				return false;
			}
		}
		
	}

}
