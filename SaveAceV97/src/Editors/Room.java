package Editors;

import java.awt.Rectangle;
import java.util.ArrayList;

import GameObjects.Map;
import Main.Game;

public class Room {
private ArrayList<Slot> slots;

public Room(EditComponetsHandler handler){
	Slot slot= new Slot(new Rectangle(0,0,40,40),0,0,Type.recieve);
	slots= handler.buildSlotsList(slot, Game.WIDTH/Map.BLOCKSIZE,Game.HEIGHT/Map.BLOCKSIZE, null, 0, 0, 0,0);
}

public ArrayList<Slot> getSlot(){
	return this.slots;
}

public String toString(){
	String data="";
	for(Slot slot : slots){
		data+=slot.toString();
	}
	return  data;
}


}
