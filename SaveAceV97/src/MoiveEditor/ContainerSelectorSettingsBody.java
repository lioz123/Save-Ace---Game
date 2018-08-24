package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.util.ArrayList;

import javax.print.attribute.TextSyntax;
import javax.xml.transform.Templates;

import Editors.CounterButton;
import Editors.NormalButton;
import Editors.SaveButton;
import General.Utilities;
import MapTrazlation.SpriteSheetList;

public class ContainerSelectorSettingsBody extends ContainerBody{
	private ArrayList<Frame> frames;
	private CounterButtonDisplayValue defenceCounter;
	private CounterButtonDisplayValue damagedCounter;
	private CounterButtonDisplayValue solidButton;
	private CounterButtonDisplayValue bonesAttackCounter;
	private CounterButtonDisplayValue bonesSolid;
	private CounterButtonDisplayValue bonesDefence;
	private Frame frame=null;
	private Selector selector;
	private NormalButton saveButton;
	private NormalButton bonesTitle;

	public ContainerSelectorSettingsBody(int x, int y, int width, int height,MovieEditor editor) {
		super(x, y, width, height);
		this.editor = editor;
		Font px12= new Font("Tahoma", Font.PLAIN,12);
		defenceCounter= new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-65,y+20,35,35, Editors.Type.counterUpDown, 0, 1, 10000, 0);
		defenceCounter.setRect(new Rectangle(defenceCounter.getX()+defenceCounter.getWidth()-10,defenceCounter.getY(),35,35));
		defenceCounter.setName("defence");
		defenceCounter.setFont(px12);
		defenceCounter.setDeltaNameX(20);
		defenceCounter.setDeltaNameY(-2);
		damagedCounter= new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-65,y+55+15,35,35, Editors.Type.counterUpDown, 0, 1, 10000, 0);
		damagedCounter.setRect(new Rectangle(damagedCounter.getX()+damagedCounter.getWidth()-10,damagedCounter.getY(),35,35));
		damagedCounter.setName("Attack");
		damagedCounter.setFont(px12);
		damagedCounter.setDeltaNameX(25);
		damagedCounter.setDeltaNameY(-2);
		solidButton= new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-65,y+105+15,35,35, Editors.Type.counterUpDown, 0, 1, 1, 0);
		solidButton.setRect(new Rectangle(solidButton.getX()+solidButton.getWidth()-10,solidButton.getY(),35,35));
		solidButton.setName("Solid");
		solidButton.setFont(px12);
		solidButton.setDeltaNameX(25);
		solidButton.setDeltaNameY(-2);
		saveButton = new NormalButton(null, x+width-50, y+height-35, 45,30, Editors.Type.noraml);
		saveButton.setName("Save");
		saveButton.setDeltaNameX(7);
		saveButton.setDeltaNameY(20);
		saveButton.setFont(new Font("Tahoma" ,Font.PLAIN,14));
		bonesAttackCounter= new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-120-30,y+height-35,35,30, Editors.Type.counterUpDown, 0, 1, 10000, 0);
		bonesAttackCounter.setRect(new Rectangle(bonesAttackCounter.getX()+bonesAttackCounter.getWidth()-10,bonesAttackCounter.getY(),35,30));
		bonesAttackCounter.setName("attack");
		bonesAttackCounter.setFont(px12);
		bonesAttackCounter.setDeltaNameX(25);
		bonesAttackCounter.setDeltaNameY(-2);
		bonesDefence= new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-170-30,y+height-35,35,30, Editors.Type.counterUpDown, 0, 1, 10000, 0);
		bonesDefence.setRect(new Rectangle(bonesDefence.getX()+bonesDefence.getWidth()-10,bonesDefence.getY(),35,30));
		bonesDefence.setName("defence");
		bonesDefence.setFont(px12);
		bonesDefence.setDeltaNameX(25);
		bonesDefence.setDeltaNameY(-2);
		bonesSolid= new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-220-30,y+height-35,35,30, Editors.Type.counterUpDown, 0, 1, 1, 0);
		bonesSolid.setRect(new Rectangle(bonesSolid.getX()+bonesSolid.getWidth()-10,bonesSolid.getY(),35,30));
		bonesSolid.setName("Solid");
		bonesSolid.setFont(px12);
		bonesSolid.setDeltaNameX(25);
		bonesSolid.setDeltaNameY(-2);
		bonesTitle = new NormalButton(null, x+5,y+height-55, width-7,52, Editors.Type.noraml);
		bonesTitle.setName("bones Settings");
		bonesTitle.setFont(new Font("Tahoma",Font.BOLD,14));
		bonesTitle.setDeltaNameX(5);
		bonesTitle.setDeltaNameY(-2);
		buttons.add(bonesTitle);
		this.buttons.add(bonesSolid);
		this.buttons.add(bonesAttackCounter);
		this.buttons.add(bonesDefence);
		this.buttons.add(saveButton);
		this.buttons.add(defenceCounter);
		this.buttons.add(damagedCounter);
		this.buttons.add(solidButton);
		
	
		frame = new Frame(x+200,y+40,175,190,new Rectangle(0,0,0,0),editor.getSpriteSheet());
	//	this.frames.add(frame);
	}
	


	
	public void tick(){
	Selector temp= editor.getSelectedSelector();
		if(temp!=null){
			if(temp!=selector){
				selector =temp;
				damagedCounter.setValue(selector.getImageData().getAttack());
				defenceCounter.setValue(selector.getImageData().getDefence());
				if(selector.getImageData().isSolid()){
					solidButton.setValue(1);
				}else{
					solidButton.setValue(0);
				}
				
				
				frame.setRect(selector.getFrame());
			}
		
		}else{
			selector = null;
			frame.setRect(new Rectangle(0,0,0,0));
		}
		
		frame.setY(y);
		frame.setHeight(height);
		frame.setX(x);
		super.tick();
	
	}
	

	
	public void render(Graphics2D g2d){
		super.render(g2d);
		frame.setY(y);
		if(height-65>=0){
			frame.setHeight(height-65);
		}else{
			frame.setHeight(0);
		}
		
		frame.setX(x);
	//	upDownFrameDisplay.render(g2d);
		if(!close){
			if(frame!=null){
				
				frame.render(g2d);
				
				g2d.drawRect(frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());
			
			}
			

			
			
		}
		
	}
	
	public boolean onClick(int x,int y){
		super.onClick(x, y);
		
		if(!opening&&show){
			
			boolean isClicked= Utilities.isClicked(x, y, new Rectangle(this.x,this.y,width,height));
			if(selector!=null){
			if(solidButton.isClicked(x, y)){
			if(solidButton.getValue()>0){
				selector.setSolid(true);
			}else{
				selector.setSolid(false);
			}
			
			}
			
			
			if(damagedCounter.isClicked(x, y)){
				selector.setAttack(damagedCounter.getValue());
			}
			
			if(defenceCounter.isClicked(x, y)){
				selector.setDefence(defenceCounter.getValue());
			}
			
			if(saveButton.isClicked(x, y)){
				selector.setBonesAttack(bonesAttackCounter.getValue());
				selector.setBonesDefence(bonesDefence.getValue());
				if(this.bonesSolid.getValue()>0){
					selector.setBonesSolid(true);
				}else{
					selector.setBonesSolid(false);
				}
			}
			
			
			}
			return isClicked;
		}
	
	
		return false;
		
		
	}
	

}
