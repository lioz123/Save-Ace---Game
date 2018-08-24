package MoiveEditor;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.SpringLayout;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import Editors.Button;
import Editors.Type;
import General.ImageLoader;
import General.SpriteSheet;
import General.Utilities;
import Main.Game;
import MapTrazlation.SpriteSheetList;
import Views.Container;

public class Settings extends Container {
	private boolean show =false,close=false, animating =false;
	private ArrayList<Button> buttons; 
	private ArrayList<TextArea> texts;
	private boolean selected=false;
	private int x=0,y=0,width=0,height=0,maxWidth=400,maxHeight=Game.HEIGHT;
	private double cx=0.1,cy=0;
	private MovieEditor editor;
	private ContainerSelectorHead selectedSetting;
	private ContainerFramesHeader framesHeader ;
	private ContainerMovieHeader movieHeader;
	private ContainerSelectorSettingsHead selectorHeader;
	private ArrayList<Container> containers;
	private TextArea name,path,character;
	private ContainerBonesSettingsHead bonesHead;
	private ButtonChangeMode lowRender,windowSize;
	private CounterButtonDisplayValue sensitivity;
	private ContainerAramtureHead armature;
	private MultyModeButton keyMode ;
	private ContainerSelectedSelecotHead selectedSelector;
	private TextArea openKey;
	private ButtonText loadImage;
	public Settings(MovieEditor editor){
		containers = new ArrayList<Container>();
		this.editor=editor;
		buttons = new ArrayList<Button>();
		texts = new ArrayList<TextArea>();
		
		
		ArrayList<String> modes = new ArrayList<String>(2);
		modes.add("None");
		modes.add("Control");
		modes.add("Shift");
		modes.add("Alt");
		modes.add("Space");
		keyMode= new MultyModeButton(null,130, 150, 45, 20, Editors.Type.noraml, modes);
		buttons.add(keyMode);
		openKey = new TextArea(80, 150, 40, 20, "openKey");
		openKey.setMaxChars(1);
		openKey.setNameDeltaWidth(-10);
		texts.add(openKey);
		SpriteSheet saveButton = new SpriteSheet(new ImageLoader().loadImage("/SaveButton.png"));
		SaveMovie save  = new SaveMovie(saveButton.getImage(),10,Game.HEIGHT-50,60,40,Type.send,editor);
		save.setEditableY(false);;
		lowRender  = new ButtonChangeMode(null,80,Game.HEIGHT-30,60,20,Type.send,"Yes","No");
		 lowRender.setFont(new Font("Tahoma",Font.PLAIN,10) );
		 lowRender.setName("Save RamMode");
		 lowRender.setDeltaNameY(-2);
		 lowRender.setModeFont(new Font("Tahoma",Font.PLAIN,15) );
		 lowRender.setEditableY(false);
			windowSize  = new ButtonChangeMode(null,150,Game.HEIGHT-30,60,20,Type.send,"Normal","Picture");
			windowSize.setFont(new Font("Tahoma",Font.PLAIN,10) );
			windowSize.setName("Window Size");
			windowSize.setDeltaNameY(-2);
			windowSize.setModeFont(new Font("Tahoma",Font.PLAIN,15) );
			windowSize.setEditableY(false);
			sensitivity = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),205,Game.HEIGHT-35,30,30, Type.counterUpDown, 5, 1, 30, 1);
			sensitivity.setRect(new Rectangle(sensitivity.getX()+sensitivity.getWidth()-8,sensitivity.getY()+5 ,30,20));
			sensitivity.setDeltaDisplayY(-3);
				
			sensitivity.setEditableY(false);
				
			buttons.add(sensitivity);
		 buttons.add(windowSize);
		 buttons.add(lowRender);
		buttons.add(save);
		 name = new TextArea(100,120,100,20, "movie Name :");
		name.setString(editor.getName());
		path = new TextArea(54,90,100,20, "path :");
		path.setString(editor.getPath());
		loadImage = new ButtonText(path.getX()+path.getWidth()+2,path.getY(),"Load");
		buttons.add(loadImage);
		character = new TextArea(85,60,100,20, "Character :" );
		character.setString(editor.getCharacter());
		texts.add(character);
		texts.add(path);
		texts.add(name);
		for(TextArea text:texts){
			text.setX(-maxHeight);
		}
		
		for(Button button:buttons){
			button.setX(-maxWidth);
		}
		selectedSetting = new ContainerSelectorHead(20,180,200,30,null,"selector setting",editor);
		 framesHeader = new ContainerFramesHeader(20,150+40,200,30,null,"Frames Order",editor);
		 movieHeader = new ContainerMovieHeader(20,150+40+40,300,30,null,"movie settings",editor);
		 selectorHeader = new ContainerSelectorSettingsHead(20,150+40+40+40,250,30,null,"Selector Propeties",editor);
		 bonesHead = new ContainerBonesSettingsHead(20,150+40+40+40,250,30,null,"Bones Settings",editor);
		 armature = new ContainerAramtureHead(20,150+40+40+40+40,250,30,null,"Bones Settings",editor);
		 selectedSelector = new  ContainerSelectedSelecotHead(20,150+40+40+40+40+40,250,30,null,"Bones Settings",editor);
		 
		 containers.add(selectedSelector);
		 containers.add(armature);
		 containers.add(bonesHead);
		containers.add(selectedSetting);
		containers.add(framesHeader);
		containers.add(movieHeader);
		containers.add(selectorHeader);
		for(Container container:containers){
			container.setX(-container.getMaxX());
		}
	//	containers.add(selectorsProperties);
		

	}
	
	public void tick(){
		if(show){
			if(!close){
				if(width<maxWidth){
					if(cx*1.1<1){
						cx*=1.015;
					}
						for(Button button:buttons){
							button.setX(button.getMaxX()-maxWidth+width);
						}
						for(TextArea text:texts){
							text.setX(text.getMaxX()-maxWidth+width);
						}
						
						for(Container container : containers){
							container.setX(container.getMaxX()-maxWidth+width);
							
						}
					
					cy = 1/cx;
					width +=cy;
				}else{
					width = maxWidth;
				animating=false;
				}
				if(height<maxHeight){
					height=maxHeight;
				}
			}		
		else{
			if(width>5){
				
					cx/=0.99;
					for(Button button:buttons){
						
							button.setX(button.getMaxX()-maxWidth+width);
						
					}
					for(TextArea text:texts){		
							text.setX(text.getMaxX()-maxWidth+width);
					
					}
					
					for(Container container : containers){
						container.setX(container.getMaxX()-maxWidth+width);
					}
					
					
				cy = 1/cx;
				width -=cy;
			}else{
				
			
				width = 0;
				cx= 0.1;
				cy=0;
				x=0;
				
				this.show=false;
				close=false;
				animating =false;
			}
			if(height<maxHeight){
				height=maxHeight;
			}
			
		}
		
		for(TextArea text:texts){
			text.tick();
		}
		for(Container container:containers){
			if(container==framesHeader){
				framesHeader.setY(selectedSetting.getTotalMaxY()+10);
			}
			if(container==movieHeader){
				movieHeader.setY(framesHeader.getTotalMaxY()+10);
			}
			
			if(container==selectorHeader){
				container.setY(movieHeader.getTotalMaxY()+10);
			}
			
			if(container==bonesHead){
				container.setY(selectorHeader.getTotalMaxY()+10);
			}
			
			if(container==armature){
				container.setY(bonesHead.getTotalMaxY()+10);
			}
			
			if(container==selectedSelector){
				container.setY(armature.getTotalMaxY()+10);
			}
			container.tick();
		}
		}
		editor.setName(name.getString());
		editor.setCharacter(character.toString());
	}
	
	public boolean Hide(){
		if(!animating){
			close=true;
			cx=0.1;
			cy=0;
			animating =true;
			return true;
		}
		return false;

	}
	
	public boolean Show(){
			if(!animating){
				this.show=true;
				animating =true;
				return true;
			}
			return false;
		
	}
	
	public void render(Graphics2D g2d){
		if(show){
			g2d.setColor(Color.black);
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
			g2d.fillRect(x,y,width,height);

			for(Button button:buttons){
				button.render(g2d);
			}
			
			for(TextArea text :texts){
				text.render(g2d);
				
			}
			for(Container container : containers){
				
				container.render(g2d);
			}
			
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		}
	
	}
	
	public boolean onMousehover(int x, int y){ 
		for(Button button:buttons){
			button.onMouseOver(x, y);
		}
		
		for(Container container:containers){
			container.onMouseHover(x,y);
		}
		return false;
	}
	
	public boolean onClick(int x, int y){
		if(loadImage.isClicked(x, y)){
			editor.loadImage();
		}
		
		for(Container container : containers){
			container.onClick(x,y);
		
		}
		
		
		
		if(x>this.x&&x<this.x+width&&y>this.y&&y<this.y+height){
			selected =true;
			for(Button button:buttons){
				button.onClick(x, y);
				if(button.equals(this.lowRender)){
					if(button.isClicked(x, y)){
						editor.setSaveRam(!lowRender.isModeChanged());
					}
				}
				if(button.equals(keyMode)){
					if(button.isClicked(x,y)){
						editor.setSpecialKey(keyMode.getMode());
					}
				}
			}
			
			if(windowSize.isClicked(x, y)){
				if(windowSize.isModeChanged()){
					if(editor!=null){
						editor.setWindowSizeReltiveToImage();
					}
				}else{
					if(editor!=null){
						editor.setWindowSizeToNormal();
					}
				}
			}
			
			if(sensitivity.isClicked(x, y)){
				editor.setSensitivity(sensitivity.getValue());
			}
			
			for(TextArea text : texts){
				text.onClick(x, y);
			}
			
			
		
		
			

			return true;
					}
		selected =false;
		return false;
	}
	
	public void KeyPreesed(KeyEvent e){
	
		if(selected){
		
			for(TextArea text:texts){
				if(text.isSelected()){
					text.KeyPressed(e);
					if(text==name){
						editor.setName(name.getString());
					}
					
					if(text==path){
						editor.setPath(path.toString());
					}
					
					if(text==openKey){
						if(openKey.toString().toCharArray().length>0)
						editor.setOpenKey(openKey.toString().toCharArray()[0]);
					}
					
					
					return; 
				}
			}
			
					
			
			for(Container container:containers){
				container.keyPressed(e);
			}
			
			
			
		}
	}
	
	public TextArea getOpenKeyButton(){
		return this.openKey;
	}
	
	public void MouseWheelMoved(MouseWheelEvent arg0){
		if(selected){
		int delta =10*arg0.getWheelRotation();
		for(Button button: buttons){
			button.setY(button.getY()+delta);
		}
		
		for(TextArea text: texts){
			text.setY(text.getY()+delta);
		}
		
		for(Container container: containers){
			container.setY(container.getY()+delta);
		}
		}
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isClicked(int x,int y) {
	if(Utilities.isClicked(x, y, new Rectangle(this.x,this.y,this.width,this.height))){
		return true;
	}
		return false;
	}
	
	public MultyModeButton getSpecialKey(){
		return keyMode;
	}

	public MultyModeButton getKeyMode() {
		// TODO Auto-generated method stub
	return keyMode;
	}


	
}
