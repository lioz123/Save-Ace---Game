package Editors;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import FilesUtils.FileEditor;
import GameObjects.Block;
import GameObjects.Map;
import GameObjects.WallComponent;
import General.ImageLoader;
import General.SpriteSheet;
import Main.Game;
import MapTrazlation.EditTranzlation;
import MapTrazlation.MapTranzlation;


public class EditMap {
	private Slot soldier = new Slot(new Rectangle(0,600,40,40),0,0,Type.send);
	private  Slot crocodile = new Slot(new Rectangle(40,600,40,40),0,0,Type.send);
	private	Slot boa = new Slot(new Rectangle(80,600,40,40),0,0,Type.send);
	private 	Slot buggy = new Slot(new Rectangle(120,600,40,40),0,0,Type.send);
	private 	Slot mr1 = new Slot(new Rectangle(200,600,40,40),0,0,Type.send);
	private	Slot garila = new Slot(new Rectangle(240,600,40,40),0,0,Type.send);
	private	Slot mr2 = new Slot(new Rectangle(280,600,40,40),0,0,Type.send);
	private	Slot mr3 = new Slot(new Rectangle(160,600,40,40),0,0,Type.send);
	private  Slot jinbe = new Slot(new Rectangle(320,600,40,40),0,0,Type.send);
	private	Slot magglen = new Slot(new Rectangle(360,600,40,40),0,0,Type.send);
	private 	Slot chest = new Slot(new Rectangle(400,600,40,40),0,0,Type.send);
	private 	Slot deathChest = new Slot(new Rectangle(440,600,40,40),0,0,Type.send);
	private 	Slot floor = new Slot(new Rectangle(650,40,40,40),0,0,Type.sendEmpty);
	private 	Slot wall = new Slot(new Rectangle(0,605,40,40),0,0,Type.send);
	private  EditComponetsHandler handler=new EditComponetsHandler(Game.WIDTH/Map.BLOCKSIZE,Game.HEIGHT/Map.BLOCKSIZE);
	private MouseListener mouselisenter;
	public EditMap(){
		ArrayList<ArrayList<Slot>> senders= new ArrayList<ArrayList<Slot>>();
		ArrayList<ArrayList<Slot>> recievers= new ArrayList<ArrayList<Slot>>();
		ArrayList<Slot> specialObject = new ArrayList<Slot>();
		ArrayList<Button> buttons = new ArrayList<Button>();
		SpriteSheet warcraft = new SpriteSheet(new ImageLoader().loadImage("/warcraft2.png"));
		SpriteSheet upAndDownArraws=new SpriteSheet(new ImageLoader().loadImage("/upAndDownArrows.png"));
		SpriteSheet tiles = new SpriteSheet(new ImageLoader().loadImage("/tiles.jpg"));
		SpriteSheet specialSprite = new SpriteSheet(new ImageLoader().loadImage("/sepcialObject.png"));	
		
		
		
		soldier.setBufferedImage(specialSprite.grabImage(0,0,27,40));
		soldier.getSlotData().setObjectName("soldier");
		soldier.setObjectName("soldier");
		specialObject.add(soldier);		
		
		
		crocodile.setBufferedImage(specialSprite.grabImage(65+33,0,33,39));
		crocodile.getSlotData().setObjectName("crocodile");
		crocodile.setObjectName("crocodile");
		specialObject.add(crocodile);
		
	
		boa.setBufferedImage(specialSprite.grabImage(65+33+33+33,0,33,70));
		boa.getSlotData().setObjectName("boa");
		boa.setObjectName("boa");
		specialObject.add(boa);
		
	
		buggy.setBufferedImage(specialSprite.grabImage(65+33+33+33+33,0,50,57));
		buggy.getSlotData().setObjectName("buggy");
		buggy.setObjectName("buggy");
		specialObject.add(buggy);
		
	
		mr1.setBufferedImage(specialSprite.grabImage(65+33+33+33+33+50+50,0,28,39));
		mr1.getSlotData().setObjectName("mr1");
		mr1.setObjectName("mr1");
		specialObject.add(mr1);
		
	
		garila.setBufferedImage(specialSprite.grabImage(65+33+33+33+33+50+50+28,0,35,40));
		garila.getSlotData().setObjectName("garila");
		garila.setObjectName("garila");
		specialObject.add(garila);
	
		mr2.setBufferedImage(specialSprite.grabImage(0,40,40,40));
		mr2.getSlotData().setObjectName("mr2");
		mr2.setObjectName("mr3");
		specialObject.add(mr2);
		
		
		mr3.setBufferedImage(specialSprite.grabImage(65+33+33+33+33+54,0,40,60));
		mr3.getSlotData().setObjectName("mr3");
		mr3.setObjectName("mr3");
		specialObject.add(mr3);
		
		
		jinbe.setBufferedImage(specialSprite.grabImage(45,40,68,85));
		jinbe.getSlotData().setObjectName("jinbe");
		jinbe.setObjectName("jinbe");
		specialObject.add(jinbe);
		
		
		magglen.setBufferedImage(specialSprite.grabImage(0,132,65,85));
		magglen.getSlotData().setObjectName("magglen");
		magglen.setObjectName("magglen");
		specialObject.add(magglen);
		
	
		chest.setBufferedImage(specialSprite.grabImage(65,0,33,20));
		chest.getSlotData().setObjectName("chest");
		chest.setObjectName("chest");
		specialObject.add(chest);
		
		
		deathChest.setBufferedImage(specialSprite.grabImage(65+33+30,0,37,25));
		deathChest.getSlotData().setObjectName("deathChest");
		deathChest.setObjectName("deathChest");
		specialObject.add(deathChest);
		
		
		Slot emptySlot = new Slot(new Rectangle(0,0,40,40),0,0,Type.recieve);
		
		wall.getSlotData().setObjectName("wall");
		wall.setObjectName("wall");
		floor.getSlotData().setFloorDeltaX(3);
		floor.getSlotData().setFloorDeltaY(2);
		senders.add(handler.buildSlotsList(wall,18, 3, warcraft, WallComponent.blockWidth, WallComponent.blockHeight,0, 1));
		senders.add(handler.buildSlotsList(wall,18, 3, warcraft, WallComponent.blockWidth, WallComponent.blockHeight,0, 4));
		recievers.add(handler.buildSlotsList(emptySlot, Game.WIDTH/Map.BLOCKSIZE,Game.HEIGHT/Map.BLOCKSIZE, null, 0, 0, 0,0));
		//handler.addRecivers(handler.buildSlotsList(emptySlot, Game.WIDTH/Map.BLOCKSIZE,Game.HEIGHT/Map.BLOCKSIZE, null, 0, 0, 0,0));
		handler.addSenders((senders.get(0)));
		handler.addSenders((senders.get(1)));
		handler.addSenders(handler.buildSlotsList(wall,18, 3, warcraft, WallComponent.blockWidth, WallComponent.blockHeight,0, 7));
		handler.addSenders(handler.buildSlotsList(wall,18, 3, warcraft, WallComponent.blockWidth, WallComponent.blockHeight,0, 10));
		//handler.addRecivers(recievers.get(0));
		handler.addEmptyObjectSenders(handler.buildSlotsList(floor, 5,13,tiles , Block.getBlockImageSize(),Block.getBlockImageSize(), 0,0));
		handler.addEmptyObjectSenders(handler.buildSlotsList(floor, 5,13,tiles , Block.getBlockImageSize(), Block.getBlockImageSize(), 5,0));
		handler.addEmptyObjectSenders(handler.buildSlotsList(floor, 5,13,tiles , Block.getBlockImageSize(), Block.getBlockImageSize(), 10,0));
		handler.addEmptyObjectSenders(handler.buildSlotsList(floor, 5,13,tiles ,Block.getBlockImageSize(), Block.getBlockImageSize(), 15,0));
		handler.addSenders(specialObject);
		mouselisenter = new MouseListener(handler);
		/*FileEditor editor = new FileEditor();
		editor.Openfile("Levels/level1.txt");
		editor.editFile(handler.reciversToStrings());
		editor.close();
		*/
		EditTranzlation et = new EditTranzlation();
		et.TranzlatMap(this, "Levels/level"+1+"/room"+2+ ".txt");
	  	
	}
	
	public void render(Graphics2D g2d){
		handler.render(g2d);
	}
	public MouseListener getMouseListener(){
		return this.mouselisenter;
	}
	

	public EditComponetsHandler getComponentHandler(){
		return this.handler;
	}
	public  Slot getSlot(String name){
		for(Slot slot : handler.getAllSenders()){
			if(slot.getObjectName().equals(name))
				return slot;
		}
		return null;
	}

	public void save() {
	handler.SaveData("Levels/level"+1+"/room"+2+ ".txt");
		
	}
	
}
