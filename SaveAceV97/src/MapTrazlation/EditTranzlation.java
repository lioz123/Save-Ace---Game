package MapTrazlation;

import java.awt.Rectangle;

import Editors.Slot;
import Editors.EditMap;
import Editors.RoomsMap;
import FilesUtils.ReadFile;
import GameObjects.Block;
import GameObjects.GameObject;
import GameObjects.Map;
import GameObjects.Mob;
import GameObjects.ObjectHandler;
import GameObjects.Solid;
import General.ImageLoader;
import General.SpriteSheet;
import Main.Game;

public class EditTranzlation {
	public static int BLOCKSIZE=Map.BLOCKSIZE;
public void TranzlatMap(EditMap editMap, String path){
	int countRoomX=0,countRoomY=0;
	RoomsMap map = editMap.getComponentHandler().getRoomsMap();

	Block defualtblock = new Block(new Rectangle(0,0,BLOCKSIZE,BLOCKSIZE),SpriteSheetList.singleBlockImg);
	ReadFile reader = new ReadFile();
	reader.openFile(path);
	int counter =0;
	
	while(reader.hasNext()){
	if(!reader.hasNext()){
		reader.close();
		return;
	}
	
	for(Slot slot:map.getRoom(countRoomX,countRoomY).getSlot()){
		if(reader.hasNext()){
			String objectName = reader.getNext();
			String strobjectImageX=reader.getNext();
			String strobjectImageY=reader.getNext();
			String strfloorImageX=reader.getNext();
			String strfloorImageY=reader.getNext();
			String strcol = reader.getNext();
			String strrow= reader.getNext();
		
		
			
			int objectImageX = Integer.parseInt(strobjectImageX);
			int objectImageY =Integer.parseInt(strobjectImageY);
			int floorImageX= Integer.parseInt(strfloorImageX);

			int floorImageY= Integer.parseInt(strfloorImageY);
			if(floorImageX!=-1)
			slot.setFloorImageBounds(floorImageX,floorImageY, Block.getBlockImageSize(),Block.getBlockImageSize());
			Slot data = editMap.getSlot(objectName);
		
			
			GameObject object = ObjectList.getObjectByName(objectName);
		
					if(objectName.equals("wall")){
						((Solid)object).setImage(objectImageX,objectImageY);
						slot.setBufferedImage(object.getImage());
						slot.setObjectName(objectName);
						slot.setObjectImageX(objectImageX);
						slot.setObjectImageY(objectImageY);
					
					}
					else if(data!=null){
						
						slot.setBufferedImage(data.getImage());
						slot.setObjectName(data.getObjectName());
						System.out.println("slot objectName is " + data.getObjectName());
						slot.setObjectImageX(data.getObjectImageX());
						slot.setObjectImageY(data.getObjectImageY());
					}
				
				
				
				
				
			
			
				
			
		}
	
	}
	if(countRoomY==map.getHeight()-1){
		break;
	}
	if(countRoomX==map.getWidth()-1){
	countRoomY++;
	countRoomX=0;
	}else{
		countRoomX++;
	}
	
	counter++;
	
	}
	System.out.println("rooms added: "+counter );
	reader.close();
}
}
