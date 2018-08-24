package Editors;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import FilesUtils.FileEditor;
import GameObjects.Map;
import General.ImageLoader;
import General.SpriteSheet;
import Main.Game;
import MapTrazlation.SpriteSheetList;

public class EditComponetsHandler {
	
	private ArrayList<ArrayList<Slot>> senders= new ArrayList<ArrayList<Slot>>();
	private ArrayList<ArrayList<Slot>>emptyObjectSenders=new ArrayList<ArrayList<Slot>>();
	private ArrayList<ArrayList<ArrayList<Slot>>> recievers=new ArrayList<ArrayList<ArrayList<Slot>>>();
	private ArrayList<Slot> specialSlots=new ArrayList<Slot>();
	private ArrayList<Button> buttons= new ArrayList<Button>();
	private CounterButton senderIndex,recieverIndexCol,recieverIndexRow,emptyObjectSendersIndex;
	private RoomsMap map;
	private SaveButton saveButton;
	private int width,height;// num of rooms call and rows
	
	
	public EditComponetsHandler(int width , int height){
	this.width=width;
	this.height=height;
	this.map=new RoomsMap(Map.MAPWIDTH,Map.MAPHEIGHT,this);
	this.map.createMap();
	SpriteSheet upAndDownArraws=SpriteSheetList.upDownArras;
	SpriteSheet leftAndRightArraws=SpriteSheetList.rightLeftArraws;
	SpriteSheet specialSprite = new SpriteSheet(new ImageLoader().loadImage("/sepcialObject.png"));
	SpriteSheet saveSprite = new SpriteSheet(new ImageLoader().loadImage("/SaveButton.png"));
	Slot ereaser = new Slot(new Rectangle(725,685,40,40),0,0,Type.earaser);
	ereaser.setFloorImage(specialSprite.grabImage(40, 0, 27, 40));
	Slot fillCol = new Slot(new Rectangle(500,750,60,40),0,0,Type.fillCol);
	Slot fillRow = new Slot(new Rectangle(570,740,40,60),0,0,Type.fillRow);
	specialSlots.add(fillCol);
	specialSlots.add(fillRow);
	senderIndex=new CounterButton(upAndDownArraws.getImage(),710,600,30,40,Type.counterUpDown,0,1,-1,0);
	emptyObjectSendersIndex=new CounterButton(leftAndRightArraws.getImage(),650+80,5,40,30,Type.counterRightLeft,0,1,-1,0);
	recieverIndexCol=new CounterButton(leftAndRightArraws.getImage(),650,0,40,30,Type.counterRightLeft,0,1,9,0);
	recieverIndexRow=new CounterButton(upAndDownArraws.getImage(),690,0,30,40,Type.counterUpDown,0,1,9,0);
	
	buttons.add(senderIndex);
	buttons.add(emptyObjectSendersIndex);
	
	buttons.add(recieverIndexCol);
	buttons.add(recieverIndexRow);
	saveButton=new SaveButton(saveSprite.getImage(),760,600,60,40,Type.counterUpDown,"Levels/level1/room2.txt",this);
	buttons.add(saveButton);
	
	specialSlots.add(ereaser);
	
	
	
	}
	
	public void addButton(CounterButton button){
		buttons.add(button);
	}
	
	public void addEmptyObjectSenders(ArrayList<Slot> senders){
		this.emptyObjectSenders.add(senders);
		this.emptyObjectSendersIndex.setMaxValue(this.emptyObjectSendersIndex.getMaxValue()+1);
	}
	
	public ArrayList<Slot> getcurrentEmptySenders(){
		return this.emptyObjectSenders.get(emptyObjectSendersIndex.getValue());
	}
	
	public void addSenders(ArrayList<Slot> senders){
		this.senders.add(senders);
		this.senderIndex.setMaxValue(this.senderIndex.getMaxValue()+1);
	}
	public void addRecivers(ArrayList<Slot> recivers){
		ArrayList<ArrayList<Slot>> temp = new ArrayList<ArrayList<Slot>>();
		temp.add(recivers);
		this.recievers.add(temp);
		this.recieverIndexCol.setMaxValue(this.recieverIndexCol.getMaxValue()+1);
	}
	public  ArrayList<Slot> getRecivers(){
		return this.map.getRoom(recieverIndexCol.getValue(),recieverIndexRow.getValue()).getSlot();
	}
	
	public ArrayList<ArrayList<ArrayList<Slot>>> getAllRecivers(){
		return this.recievers;
	}
	
	public ArrayList<Button> getButtons(){
		return this.buttons;
	}
	
	
	public ArrayList<Slot> buildSlotsList(Slot slot,int numOfCol,int numOfRows,SpriteSheet sprite,int imageWidth,int imageHeight,int imageCol,int imageRow){
		ArrayList<Slot> tempList=new ArrayList<Slot>();
		for(int row=0;row<numOfRows;row++){
			for(int col=0;col<numOfCol;col++ ){
				Rectangle rec= new Rectangle(slot.getX()+slot.getWidth()*col,slot.getY()+slot.getHeight()*row,slot.getWidth(),slot.getHeight());
				Slot temp = new Slot(rec,slot.getCol()+col,slot.getRow()+row,slot.getType());
				temp.getSlotData().setObjectName(slot.getSlotData().getObjectName());
				if(sprite!=null){
					if(temp.getSlotData().getObjectName().equals("null")){
						temp.getSlotData().setFloorDeltaX(slot.getSlotData().getFloorDeltaX());
						temp.getSlotData().setFloorDeltaY(slot.getSlotData().getFloorDeltaY());
						temp.setFloorImage(sprite.grabImage((imageCol+col)*imageWidth+temp.getSlotData().getFloorDeltaX(), (imageRow+row)*imageHeight+temp.getSlotData().getFloorDeltaY(), imageWidth, imageHeight));
						temp.setFloorImageX((imageCol+col)*imageWidth+temp.getSlotData().getFloorDeltaX()); 
						temp.setFloorImageY((imageRow+row)*imageHeight+temp.getSlotData().getFloorDeltaY());
					
						
					}else{
						temp.setObjectName(slot.getObjectName());
						temp.setBufferedImage(sprite.grabImage((imageCol+col)*imageWidth+temp.getSlotData().getObjectDeltaX(), (imageRow+row)*imageHeight+temp.getSlotData().getObjectDeltaY(), imageWidth, imageHeight));
						temp.setObjectImageX((imageCol+col)*imageWidth+temp.getSlotData().getObjectDeltaX());
						temp.setObjectImageY((imageRow+row)*imageHeight+temp.getSlotData().getObjectDeltaY());
					
					}
				}
				else{
			
				}
				tempList.add(temp);
			}
			
		}
		return tempList;
	}
	
	public ArrayList<Slot> getSenders(){
		return this.senders.get(this.senderIndex.getValue());
	}
	
	
	
	
	
	public ArrayList<Slot> getNewRoom(){
		Slot emptySlot = new Slot(new Rectangle(0,0,40,40),0,0,Type.recieve);
		return this.buildSlotsList(emptySlot, Game.WIDTH/Map.BLOCKSIZE,Game.HEIGHT/Map.BLOCKSIZE, null, 0, 0, 0,0);
	}
	
	public RoomsMap getMap(){
		return this.map;
	}
	
	
	public void render(Graphics2D g2d){
	
		for(Slot slot: senders.get(senderIndex.getValue())){
			slot.render(g2d);
		}
		for(Button button:buttons){
			button.render(g2d);
		}
		if(map.getRoom(0, 0)==null){
		}
		for(Slot slot:map.getRoom(recieverIndexCol.getValue(), recieverIndexRow.getValue()).getSlot()){
			
			slot.render(g2d);
			
		}
		for(Slot slot:emptyObjectSenders.get(emptyObjectSendersIndex.getValue())){
			slot.render(g2d);
		}
		for(Slot slot:specialSlots){
			slot.render(g2d);
			
		}
		
	}
	

	
	public void SaveData(String path){
		String Filepath = "Levels/room2"+".txt";
		FileEditor write = new FileEditor();
		write.Openfile(path);
		for(int i =0; i<Map.MAPHEIGHT;i++){
			for(int j=0;j<Map.MAPWIDTH;j++){
				for(Slot slot :map.getRoom(j,i).getSlot()){
					write.editFile(slot.toString());
				}
			}
		}
		
		write.close();
		
	}
	public ArrayList<Slot> getSpecialSlot() {
		// TODO Auto-generated method stub
		return this.specialSlots;
	}
	
	public RoomsMap getRoomsMap(){
		return this.map;
	}

	public ArrayList<Slot> getAllSenders() {
		ArrayList<Slot> slots= new ArrayList<Slot>();
		for(ArrayList<Slot> tempSlots : senders){
			slots.addAll(tempSlots);
		}
		return slots;
	}

}
