package MapTrazlation;

import GameObjects.ObjectHandler;
import GameObjects.Solid;
import General.ImageLoader;
import General.SpriteSheet;
import Main.Game;

import java.awt.Rectangle;
import java.util.ArrayList;

import FilesUtils.ReadFile;
import GameObjects.Block;
import GameObjects.GameObject;
import GameObjects.Map;
import GameObjects.Mob;
public class MapTranzlation {
	public static int BLOCKSIZE=Map.BLOCKSIZE;
public void TranzlatMap(Map map, String path){
	
	Block defualtblock = new Block(new Rectangle(0,0,BLOCKSIZE,BLOCKSIZE),SpriteSheetList.singleBlockImg);
	ReadFile reader = new ReadFile();
	reader.openFile(path);
	int counter =0;
	int blockCounter = 0;
	while(reader.hasNext()){
	if(!reader.hasNext()){
		reader.close();
		return;
	}
	ObjectHandler room= new ObjectHandler();
 	for(int i=0;i<Game.HEIGHT/BLOCKSIZE;i++){
  		for(int j=0;j<Game.WIDTH/BLOCKSIZE;j++){
  			Rectangle rec = new Rectangle(j*55,i*55,BLOCKSIZE,BLOCKSIZE);
  			Block temp = new Block(rec,defualtblock.getImage());
  			temp.setRow(i);
  			temp.setCol(j);
  			room.addBlock(temp);
  		}
  	}
	for(Block block:room.getBlockList()){
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
			block.setImage(floorImageX,floorImageY, Block.getBlockImageSize(),Block.getBlockImageSize());
			GameObject object = ObjectList.getObjectByName(objectName);
			if(object!=null)

				if(object instanceof Solid){
					if(objectName.equals("wall"))
					((Solid)object).setImage(objectImageX,objectImageY);
					room.addObjectoOnBlock(block.getCol(),block.getRow(),((Solid)object));
				}
				
			else{
			
			object.setX(block.getCol()*Map.BLOCKSIZE);
			object.setY(block.getRow()*Map.BLOCKSIZE);
			if(object instanceof Mob){
				((Mob)object).setRoom(room);
			}
			room.add(object);
			
			}
			blockCounter++;
		}
	
	}
	counter++;

	map.addRoom(room);
	}
	reader.close();
}
}
