package GameObjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Editors.Slot;
import General.Film;
import General.ImageData;
import General.ImageLoader;
import General.SpriteSheet;
import Main.Game;
import MapTrazlation.FilmList;
import MapTrazlation.MapTranzlation;
import MapTrazlation.SpriteSheetList;
import MapTrazlation.TranzlateMovie;
import Views.Bar;
import Views.Inventory;


public class Map {
	public static int ROOMSCOL=10,ROOMSROWS=10;

	private static List<ObjectHandler> rooms;
	public static int MAPWIDTH=10,MAPHEIGHT=10;
	public static int BLOCKSIZE =55;
	public Bar healthBar;
	public Bar stamina;
	public Bar level;
	public int gameBounderies=Game.WINDOWHEIGHT-Game.HEIGHT;
	public static int levelINdex=1,roomIndex=1;
	Player player;
	private  int roomX=0,roomY=0,levelIndex=0;
	private Level level1;
	private ArrayList<Level> levels = new ArrayList<Level>();
	private Inventory inventory;
	private Film filmteser;
	private ImageTester tester=null;
	private boolean loading = true;
 public Map(Player player,Inventory inventory){
	  
	//  	filmteser=new Film(FilmList.luffyRun);
	 // 	filmteser.setFps(6);
	  	
	this.inventory=inventory;
	BufferedImage sewerPng = new ImageLoader().loadImage("/sewer.png");
	tester = new ImageTester(0,0,1000,1000,SpriteSheetList.luffy.grabImage(623,135,50,70));
	SpriteSheet sewer = new SpriteSheet(sewerPng);
	SpriteSheet MedSpriteSheet = new SpriteSheet(new ImageLoader().loadImage("/MedSpriteSheet.png"));
	SpriteSheet blockImage = new SpriteSheet(new ImageLoader().loadImage("/SingleFloorTxture.jpg"));
	Block block = new Block(new Rectangle(0,0,BLOCKSIZE,BLOCKSIZE),blockImage.grabImage(0,0,400,400));

	healthBar = new Bar(5,5 ,80,15,80,new Color(0,160,0),new Color(200,0,0),"HP",ID.hp);
	stamina = new Bar(90,5 ,80,15,80,15,95,5+15/2+6,new Color(0, 204, 204),new Color(255, 255, 77),"Stamina",ID.stamina);
	level = new Bar(185,5,80,15,0,15,95,5+15/2+6,new Color(200,0,0),new Color(255,255,77),"Stamina",ID.level);
	MedKit medkit = new MedKit(5,25,30,20,0,0,ID.medkit,MedSpriteSheet,true);
	Syringe syringe = new Syringe(50,27,45,15,0,0,ID.syring,MedSpriteSheet);
	player.updateStamina(-50);
	syringe.setInsideInvetory(true);
	level1= new Level(Map.MAPWIDTH);
	levels.add(level1);
	

		MapTranzlation mt= new MapTranzlation();
	  	mt.TranzlatMap(this, "Levels/level"+1+"/room"+2+ ".txt");
	
		this.player=player;
	  	level1.getRoom(0,0).add(this.player);
	  	
	  	
		inventory.addbar(healthBar);
		inventory.addbar(stamina);
		inventory.addItem(medkit);
		inventory.addItem(syringe);
		inventory.addbar(level);
	
 }
 

 
 
 public  ObjectHandler getRoom(){
	 return levels.get(levelIndex).getRoom(roomX,roomY);
 }
 
 public void setRoom(int x, int y){
	roomX=x;
	roomY=y;
 }

public void addRoom(ObjectHandler handler) {
levels.get(levelIndex).addRoom(handler);
//rooms.add(handler);
}


public void tick(){
	ObjectHandler currentRoom =levels.get(levelIndex).getRoom(roomX, roomY);
	boolean [] roomChanged = new boolean[4];
	boolean isChanged =false;
	for(int i = 0 ; i<roomChanged.length;i++){
		roomChanged[i]=false;
	}
	
	if(currentRoom.getPlayer()!=null){
		int x= currentRoom.getPlayer().getX();
		int y= currentRoom.getPlayer().getY();
		if(x>Game.WIDTH){
			roomX++;
			isChanged = true;
			roomChanged[0]=true;
		}else if(x<0){
			isChanged = true;
			roomX--;
			roomChanged[1]=true;
		}else if(y>Game.HEIGHT){
		roomY--;
		isChanged = true;
		roomChanged[2]=true;
		}else if(y<0){
			isChanged = true;
			roomChanged[3]=true;
			roomY++;
		}
	}
	if(isChanged){
	
		levels.get(levelIndex).getRoom(roomX,roomY).add(currentRoom.getPlayer());
		currentRoom.getPlayer().setRoom(levels.get(levelIndex).getRoom(roomX,roomY));
		currentRoom.remove(currentRoom.getPlayer());
		currentRoom = levels.get(levelIndex).getRoom(roomX,roomY);
		int deltaX=BLOCKSIZE-currentRoom.getPlayer().getWidth();
		int deltaY=BLOCKSIZE-currentRoom.getPlayer().getHeight();
		for(int i = 0; i<4;i++){
			if(roomChanged[i]==true){
				switch (i) {
				case 0:
					currentRoom.getPlayer().setX(0);
					break;
				case 1:
					currentRoom.getPlayer().setX(Game.WIDTH-BLOCKSIZE+deltaX);
					break;
				case 2:
					currentRoom.getPlayer().setY(0);
					break;
				default:
					currentRoom.getPlayer().setY(Game.HEIGHT-BLOCKSIZE+deltaY);
					break;
				}
			}
		}
	}
	
	levels.get(levelIndex).getRoom(roomX,roomY).tick();
	
	//ImageData data = filmteser.run();
//	tester = new ImageTester(0, 0, 500, 500, SpriteSheetList.luffy.grabImage(data.getImageX(), data.getImageY(), data.getImageWidth(), data.getImageHeight()));

}

public void render(Graphics2D g2d){
	if(levels!=null&&levels.get(this.levelIndex).getRoom(roomX,roomY)!=null){
	levels.get(levelIndex).getRoom(roomX,roomY).render(g2d);
	}
	if(tester!=null){
	//	tester.render(g2d);
	}
	if(inventory!=null){
		inventory.render(g2d);
	}
	
	
	
}


}
