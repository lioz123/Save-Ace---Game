package Editors;

import java.util.concurrent.SynchronousQueue;

public class RoomsMap {
	private Room[][] rooms;
	private int width,height;
	private EditComponetsHandler handler;
	public RoomsMap(int width,int height,EditComponetsHandler handler){
		rooms = new Room[width][height];
		this.width = width;
		this.height =height;
		this.handler=handler;
	}
	
	public void createMap(){
		
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
		
			rooms[j][i]=new Room(handler);;
			
			}
		}
	}
	
	public Room getRoom(int x, int y){
		if(x<width&&y<height)
		return rooms[x][y];
		else{
			return null;
		}
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}

	public Room[][]getRooms() {
		// TODO Auto-generated method stub
		return rooms;
	}
	
}
