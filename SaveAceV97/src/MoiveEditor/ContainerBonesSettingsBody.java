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
import General.ImageData;
import General.Utilities;
import MapTrazlation.SpriteSheetList;

public class ContainerBonesSettingsBody extends ContainerBody{
	private ArrayList<Frame> frames;
	private MovieEditor editor;
	private Selector selector;
	private ArrayList<BonesGroup> groups= new ArrayList<BonesGroup>();;
	private CounterButtonDisplayValue groupIndex,activateRender,solid,attack,defence;
	private int formerGroupIndexValue = 0;
	private double frameWidth=180 , frameHeight = 200;
	private Frame frame;
	private Rectangle normalBounds;
	private Puzzle puzzle;
	private ArrayList<Color> colors;
	private ButtonText subdivide,clean;
	private MultyModeButton armatureGroup;
	private ButtonText asignArmature,creatShell,asign;
	public ContainerBonesSettingsBody(int x, int y, int width, int height,MovieEditor editor) {
		super(x, y, width, height);
		this.editor = editor;

		Font px12= new Font("Tahoma", Font.PLAIN,12);
		groupIndex = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-62,y+15,30,30,Editors.Type.counterUpDown,0,1,0,0);
		groupIndex.setRect(new Rectangle(groupIndex.getX()+25,groupIndex.getY()+5,30,20));
		groupIndex.setFont(px12);
		groupIndex.setName("Group");
		groupIndex.setDeltaNameX(25);
		groupIndex.setDeltaNameY(-1);
		groupIndex.setDeltaDisplayY(-4);
		groupIndex.setMaxValue(0);
	
		
	
		
		
		ArrayList<String> modes =new ArrayList<String>();
		modes.add("head");
		modes.add("Chest");
		modes.add("Hand");
		modes.add("Leg");
		armatureGroup= new MultyModeButton(null, x+width-41,y+45,35,20,Editors.Type.noraml, modes);
		asignArmature = new ButtonText( x+width-41,y+70, "Asign");
		asignArmature.setContentFont(new Font ("Tahoma",Font.PLAIN,11));
		
		
		
		subdivide= new ButtonText(x+width-31,y+95,"div");
		clean = new ButtonText(x+width-43,y+120, "clean");
		
		activateRender = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-60,y+180,30,30,Editors.Type.counterUpDown,0,1,1,0);
		activateRender .setRect(new Rectangle(activateRender .getX()+25,activateRender.getY()+5,30,20));
		activateRender .setFont(px12);
		activateRender .setName("render");
		activateRender .setDeltaNameX(20);
		activateRender .setDeltaNameY(-2);
		activateRender .setDeltaDisplayY(-4);
		
		
		
		
		asign= new ButtonText(x+width-40,y+240,"asign");
		asign.setContentFont(px12);
		
		creatShell = new ButtonText(x+width-65,y+250+20,"createShell");
		creatShell.setContentFont(new Font("Tahoma",Font.PLAIN,11));
		
		

	
		
		
		solid = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+5,y+220+15,30,30,Editors.Type.counterUpDown,0,1,1,0);
		solid.setRect(new Rectangle(solid.getX()+25,solid.getY()+5,30,20));
		solid.setFont(px12);
		solid.setName("solid");
		solid.setDeltaNameX(20);
		solid.setDeltaNameY(-2);
		solid.setDeltaDisplayY(-4);
		
		
		attack = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+55,y+220+15,30,30,Editors.Type.counterUpDown,0,1,1000,0);
		attack.setRect(new Rectangle(attack.getX()+25,attack.getY()+5,30,20));
		attack.setFont(px12);
		attack.setName("attack");
		attack.setDeltaNameX(20);
		attack.setDeltaNameY(-2);
		attack.setDeltaDisplayY(-4);
		
		
		
		defence = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+105,y+220+15,30,30,Editors.Type.counterUpDown,0,1,1000,0);
		defence .setRect(new Rectangle(defence.getX()+25,defence .getY()+5,30,20));
		defence .setFont(px12);
		defence .setName("defence");
		defence .setDeltaNameX(20);
		defence .setDeltaNameY(-2);
		defence .setDeltaDisplayY(-4);
		

		buttons.add(asignArmature);
		buttons.add(armatureGroup);
		buttons.add(defence);
		buttons.add(attack);
		buttons.add(solid);
		buttons.add(asign);
		buttons.add(creatShell);
		buttons.add(activateRender);
		buttons.add(clean);
		buttons.add(subdivide);
		frame = new Frame(x,y,(int)frameWidth,(int)frameHeight,new Rectangle(0,0,0,0),editor.getSpriteSheet());
		buttons.add(groupIndex);
		normalBounds = new Rectangle(0,0,0,0);
		
	}
	


	
	public void tick(){
		Selector temp = editor.getSelectedSelector();
		if(temp!=null){
			if(temp!=selector&&temp.getFrame()!=null){
				
				selector = temp;
				groups=selector.getGroups();
				normalBounds = new Rectangle(selector.getFrame());
				puzzle = new Puzzle(x, y, 200,200, editor.getSpriteSheet());
				puzzle.setNormalImageBounds(selector.getFrame());
				if(selector.getGroups().size()>0){
				groupIndex.setValue(0);
				attack.setValue(selector.getGroups().get(0).getAttack());
				defence.setValue(selector.getGroups().get(0).getDefence());
				if(selector.getGroups().get(0).isSolid()){
					solid.setValue(1);
				}else{
					solid.setValue(0);
				}
				}
				if(groups.size()>0){
					puzzle.setBonesGroup(groups.get(0));
				}
			}
		}else {
			this.selector=null;
			groupIndex.setMaxValue(0);
			groups=  new ArrayList<BonesGroup>();
			groupIndex.setValue(0);
			normalBounds=new Rectangle(0,0,0,0);
			puzzle = null;
			solid.setValue(0);
			attack.setValue(0);
			defence.setValue(0);
			
		}
		
		super.tick();
	
		
		if(selector!=null){
			groupIndex.setMaxValue(groups.size());
			
		}
		
		
	

	}
	

	
	public void render(Graphics2D g2d){
		
		if(puzzle!=null){
			if(activateRender.getValue()==1&&open){
				puzzle.setX(x);
				puzzle.setY(y);
				puzzle.render(g2d);
			}
			
		}
	
		super.render(g2d);
		if(!close){
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Tahoma",Font.BOLD,15));
			g2d.drawString("Others",x+width-creatShell.getWidth()+5, asign.getY()-8);
			g2d.drawRect(x+width-creatShell.getWidth()-5, asign.getY()-3, creatShell.getWidth()+5,height+y-asign.getY());
			
		}
		
	}
	
	public boolean onClick(int x,int y){
		
		
		if(!opening&&show&&selector!=null){
			super.onClick(x, y);
			boolean isClicked= Utilities.isClicked(x, y, new Rectangle(this.x,this.y,width,height));
			if(groupIndex.isClicked(x, y)){
				if(groupIndex.getValue()>groups.size()-1){
					groupIndex.setValue(groups.size()-1);				
				}
				if(groups!=null&&groups.size()>0){
					defence.setValue(groups.get(groupIndex.getValue()).getDefence());
					attack.setValue(groups.get(groupIndex.getValue()).getAttack());
					if(groups.get(groupIndex.getValue()).isSolid()){
						solid.setValue(1);
					}else{
						solid.setValue(0);
					}
				}
				
				puzzle.setBonesGroup(groups.get(groupIndex.getValue()));
			}
			
			if(subdivide.isClicked(x, y)){
				if(selector!=null){
					selector.Subdivide(groupIndex.getValue());
				}
				
			}
			if(clean.isClicked(x, y)){
				if(selector!=null){
					selector.getGroups().get(groupIndex.getValue()).clean();
				}
			}
			
			if(creatShell.isClicked(x, y)){
				if(selector!=null){
					selector.creatBoneShell();
				}
			}
			if(asign.isClicked(x, y)){
				if(selector!=null){
					selector.asignGroup(editor.getSelectedBones());
				}
				
			}
			
			if(attack.isClicked(x, y)){
			if(selector.getGroups().size()!=0){
				selector.getGroups().get(groupIndex.getValue()).setAttack(attack.getValue());
			}
			}
			
			if(defence.isClicked(x, y)){
				if(selector.getGroups().size()!=0){
					selector.getGroups().get(groupIndex.getValue()).setDefence(defence.getValue());
				}
				}
			
			if(solid.isClicked(x, y)){
				if(selector.getGroups().size()!=0){
					if(solid.getValue()==1){
						selector.getGroups().get(groupIndex.getValue()).setSolid(true);
					}else{
						selector.getGroups().get(groupIndex.getValue()).setSolid(false);
					}
					
				}
				}
			
			if(asignArmature.isClicked(x, y)){
				switch(armatureGroup.getValue()){
				case 0:
					if(selector.getGroups().size()>0){
						ImageData data = editor.getHead();
						defence.setValue(data.getDefence());
						attack.setValue(data.getAttack());
						BonesGroup group =selector.getGroups().get(groupIndex.getValue());
						group.setAttack(data.getAttack());
						group.setDefence(data.getDefence());
						group.setSolid(data.isSolid());
						if(data.isSolid()){
							solid.setValue(1);
						
						}else{
							solid.setValue(0);
						}
					}
				
					
					break;
				case 1:
					if(selector.getGroups().size()>0){
						ImageData data = editor.getChest();
						defence.setValue(data.getDefence());
						attack.setValue(data.getAttack());
						BonesGroup group =selector.getGroups().get(groupIndex.getValue());
						group.setAttack(data.getAttack());
						group.setDefence(data.getDefence());
						group.setSolid(data.isSolid());
						if(data.isSolid()){
							solid.setValue(1);
						
						}else{
							solid.setValue(0);
						}
					}
					break;
				case 2:
					if(selector.getGroups().size()>0){
						ImageData data = editor.getArm();
						defence.setValue(data.getDefence());
						attack.setValue(data.getAttack());
						BonesGroup group =selector.getGroups().get(groupIndex.getValue());
						group.setAttack(data.getAttack());
						group.setDefence(data.getDefence());
						group.setSolid(data.isSolid());
						if(data.isSolid()){
							solid.setValue(1);
						
						}else{
							solid.setValue(0);
						}
					}
					break;
				case 3:
					if(selector.getGroups().size()>0){
						ImageData data = editor.getLeg();
						defence.setValue(data.getDefence());
						attack.setValue(data.getAttack());
						BonesGroup group =selector.getGroups().get(groupIndex.getValue());
						group.setAttack(data.getAttack());
						group.setDefence(data.getDefence());
						group.setSolid(data.isSolid());
						if(data.isSolid()){
							solid.setValue(1);
						
						}else{
							solid.setValue(0);
						}
					}
					
					break;
					
					
				default:
					break;
				}
			
			}
		
			return isClicked;
		}
	
	
		return false;
		
		
	}
	

}
