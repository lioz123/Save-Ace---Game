package CharactersMenu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import Editors.Button;
import Editors.CounterButton;
import FilesUtils.FileEditor;
import Main.Game;
import Main.PhiyscalObject;
import MapTrazlation.CharacterKeysInfo;
import MapTrazlation.Characters;
import MapTrazlation.SpriteSheetList;
import MoiveEditor.ButtonChangeMode;
import MoiveEditor.ButtonText;
import Views.Container;

public class CharactersMenu extends MouseAdapter implements MouseMotionListener , KeyInterface{
	ArrayList<Container> containers=new ArrayList<Container>();
	private StringDisplay title;
	private ArrayList<ArrayList<ButtonTextAdvanced>> charactersTittle = new ArrayList<ArrayList<ButtonTextAdvanced>>();
	private CounterButton listIndex;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private int panelIndex= 0;
	private Game game;
	private ArrayList<ContainerMovies>containerMovies = new ArrayList<ContainerMovies>(); 
	private KeyInputer keyInputer;
	private ArrayList<PhiyscalObject> objects = new ArrayList<PhiyscalObject>();
	public CharactersMenu(Game game){
		keyInputer = new KeyInputer(this);
		this.game=game;
		int charHeight = 20;

		title = new StringDisplay("Key Menu",0,50,game);
		title.setFont(new Font("Tahoma", Font.BOLD,25));
		title.CalculateBounds();
		title.setMiddleX(0, Game.WIDTH);
		ArrayList<String>characters = Characters.getCharactersList();
	
		int i =0;
		int j = 0;
		int k =0;
		charactersTittle.add(new ArrayList<ButtonTextAdvanced>());
		for(String name : characters){
		//ContainerCharacterHead head = new ContainerCharacterHead(100,150+i*35,200,30,null,name);
			ArrayList<ButtonTextAdvanced> tempList = charactersTittle.get(j);
			ButtonTextAdvanced button =null;
			if(k!=0){
				button	= new ButtonTextAdvanced(name,tempList.get(k-1).getWidth()+20+tempList.get(k-1).getX(),70,game);
				button.setHeight(charHeight);
				button.setStringToCenter();
				button.setValue(i);
				button.setHoverColor(new Color(164, 168, 173));
				button.setButtonColor(new Color(149, 152, 153));
				
				if(button.getX()+button.getWidth()>=Game.WIDTH){
				charactersTittle.add(new ArrayList<ButtonTextAdvanced>());
				j++;
				k=0;
				
			}else{
				ContainerMovies container = new ContainerMovies(game, name,this);
				if(i==0){
					container.setShow(true);
				}
				containerMovies .add(container);
				tempList.add(button);
				k++;
			}
			}else{
				ContainerMovies container = new ContainerMovies(game, name,this);
				
				containerMovies .add(container);
			button = new ButtonTextAdvanced(name,20,70,game);
			button.setButtonColor(new Color(149, 152, 153));
			button.setHeight(charHeight);
			button.setStringToCenter();
			button.setValue(i);
			button.setHoverColor(new Color(164, 168, 173));
			
			tempList.add(button);
			k++;
			if(i==0){
				container.setShow(true);
				button.setButtonColor(new Color(176, 178, 178));
				button.setHoverColor(new Color(176, 178, 178));
			}
			}
			
		
		
//
			i++;
		}
		this.containers.addAll(containerMovies );
		listIndex = new CounterButton(SpriteSheetList.rightLeftArraws.getImage(),Game.WIDTH-100,25,40,40,Editors.Type.counterRightLeft,0,1,j,0);

		buttons.add(listIndex);
	}
	
	public void mousePressed(MouseEvent e){

		for(Container container:containers){
			if(container.isShow()){
				container.onClick(e.getX(), e.getY());
			}
			

		}
			
		for(Button button:buttons){
			button.onClick(e.getX(), e.getY());
		}
		
	
		ButtonTextAdvanced buttonClicked=null;
		for(ButtonTextAdvanced button:charactersTittle.get(listIndex.getValue())){
			if(button.isClicked(e)){
				buttonClicked=button;
				button.setButtonColor(new Color(176, 178, 178));
				button.setHoverColor(new Color(176, 178, 178));
				for(ContainerMovies container:containerMovies){
					if(container.getCharacterName().equals(button.getString())){
						container.setShow(true);
					}else{
						container.setShow(false);
					}
					
				}
			}
		}
		
	
		
		
		if(buttonClicked!=null){
			for(ButtonTextAdvanced button:charactersTittle.get(listIndex.getValue())){
				if(button!=buttonClicked){
					button.setButtonColor(new Color(149, 152, 153));
					button.setHoverColor(new Color(164, 168, 173));
				}
			}
		}
		
		
	}
	
	public void render(Graphics2D g2d){
		
		
		g2d.setColor(Color.black);
			g2d.drawRect(0,0,Game.WIDTH,Game.HEIGHT);
		
		g2d.setColor(new Color(192, 219, 229));
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)0.4));
	//	g2d.fillRect(0,0,Game.WIDTH,Game.HEIGHT);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)0.9));
		g2d.setColor(new Color(183, 183, 183));
		
		g2d.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
		
		g2d.setColor(new Color(166, 171, 173));
		g2d.fillRect(15,67,Game.WIDTH-40,25);
		g2d.setColor(Color.black);
		g2d.drawRect(15,67,Game.WIDTH-40,25);
		
		for(Container container:containers){
			container.render(g2d);
		}
		
		

		title.render(g2d);
		
		for(ButtonTextAdvanced button: charactersTittle.get(listIndex.getValue())){
			button.render(g2d);
		}
		
		for(Button button:buttons){
			button.render(g2d);
		}
		
	}
	
	public void tick(){
		int i =0;
	for(Container container:containers){
	
		container.tick();
		if(i!=0){
			container.setY(containers.get(i-1).getTotalMaxY()+5);
		}
		i++;
	}
	
	}
	
	public void mouseMoved(MouseEvent e){
		for(ButtonTextAdvanced button:charactersTittle.get(listIndex.getValue())){
			button.onMouseOver(e.getX(), e.getY());
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		for(Container container:containers){
			container.keyPressed(e);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		for(Container container:containers){
			container.keyReleased(e);
		}
	}
	
	public KeyInputer getKeyInputer(){
		return this.keyInputer;
	}

	public void removeCharacter(ContainerMovies containerMovies) {
		System.out.println(this.getClass().toString() + "Character name in characterMovies "+ containerMovies.getCharacterName());

		containers.remove(containerMovies);
		ButtonTextAdvanced bestButton = null;
		for(ButtonTextAdvanced button:charactersTittle.get(listIndex.getValue())){
		
				String character = ((ButtonTextAdvanced) button).getStringDisplay().getString();
				System.out.println(this.getClass().toString() + "Character name in button is " + character);
				if(character.equals(containerMovies.getCharacterName())){
					bestButton = button;
				break;
				
			}
		}
		charactersTittle.get(listIndex.getValue()).remove(bestButton);
		if(containers.size()>0&&charactersTittle.get(listIndex.getValue()).size()>0){
			containers.get(0).setShow(true);
			ButtonTextAdvanced button = charactersTittle.get(listIndex.getValue()).get(0);
			button.setButtonColor(new Color(176, 178, 178));
			button.setHoverColor(new Color(176, 178, 178));
		}
		
		 
	}

	public void save() {
		FileEditor file= new FileEditor();
		file.setPath("Levels/movies.txt");
		file.creatLines();
		for(CharacterKeysInfo info : Characters.getkeysInfo()){
			String content;
			content=("Key: " + info.getKey() +" ;");
			file.editLineFromBlockName("Character: " +info.getName(),"Character-end",info.getMovieName(),"end","Key:",content);
			content=("SpecialKey: "+info.getSpecialKey()+" ;");
			file.editLineFromBlockName("Character: " +info.getName(),"Character-end",info.getMovieName(),"end","SpecialKey:",content);
			int fps = Characters.getCharacter(info.getName()).getFilmByName(info.getMovieName()).getFilmLeft().getFpsOposite();
			content = "fps: " +fps + " ;";  
			file.editLineFromBlockName("Character: " +info.getName(),"Character-end",info.getMovieName(),"end","fps:",content);
		}
	file.Update();
	
	}
	
}
