package MapTrazlation;

import java.awt.image.BufferedImage;


import GameObjects.Chest;

import GameObjects.GameObject;
import GameObjects.ID;
import GameObjects.Map;
import GameObjects.Soldier;
import GameObjects.Solid;
import General.ImageLoader;
import General.SpriteSheet;

public class ObjectList {
	
	
	public static GameObject getObjectByName(String objectName){
		
		//BufferedImage sewerPng = new ImageLoader().loadImage("/sewer.png");
		//BufferedImage tilesetformattedupdate1Png= new ImageLoader().loadImage("/tilesetformattedupdate1.png");
		//SpriteSheet sewer = new SpriteSheet(sewerPng);
		//SpriteSheet tilesetformattedupdate1 = new SpriteSheet(tilesetformattedupdate1Png);
		
		//SpriteSheet MedSpriteSheet = new SpriteSheet(new ImageLoader().loadImage("/MedSpriteSheet.png"));
		
	//	SpriteSheet warcraft = new SpriteSheet(new ImageLoader().loadImage("/warcraft2.png"));
		switch(objectName){
		case "null": return null;
		case "wall": return new Solid(0,0,Map.BLOCKSIZE,Map.BLOCKSIZE,ID.solid,null);
		case "soldier":return new Soldier(120,120,50,50,ID.soldier,null,SpriteSheetList.marinOffiserSprite);
		case "chest": return  new Chest(303,303,50,30,ID.chest,SpriteSheetList.Dungeon_Crawler_Sheet);
		default:
			break;
		}
		return null;
	}
	
	
	
}
