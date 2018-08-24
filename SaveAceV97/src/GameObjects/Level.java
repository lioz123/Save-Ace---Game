package GameObjects;

import java.util.ArrayList;

public class Level{
private ArrayList<ArrayList<ObjectHandler>> rooms;
private int width,height=0;


public Level(int width){
	rooms = new ArrayList<ArrayList<ObjectHandler>>();
	 rooms.add(new ArrayList<ObjectHandler>());
	 this.width=width;
}

public void addRoom(ObjectHandler handler){

 if(rooms.get(height).size()>=Map.MAPWIDTH){
	 height++;

	 rooms.add(new ArrayList<ObjectHandler>());
 }
 rooms.get(height).add(handler);
}

public ObjectHandler getRoom(int x,int y){
	if(x>=width||y>=height||x<0||y<0){
		return null;
	}
	return rooms.get(y).get(x);
}
}
