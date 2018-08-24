package GameObjects;

import General.ImageLoader;
import General.SpriteSheet;

public class WallComponent {
private SpriteSheet sprite;
public static int blockWidth=33,blockHeight=33;
public WallComponent(){
	sprite = new SpriteSheet(new ImageLoader().loadImage("/warcraft.png"));
}

public Solid getWallComponentByType(int imageCol,int imageRow){
	// there are 15 types of wall componet
return new Solid(0,0,Map.BLOCKSIZE,Map.BLOCKSIZE,ID.solid,sprite.grabImage(imageCol*blockWidth,imageRow*blockHeight,blockWidth,blockHeight));
}

}





