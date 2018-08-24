package MoiveEditor;

import java.awt.AlphaComposite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.color.CMMException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.SynchronousQueue;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.management.ImmutableDescriptor;
import javax.swing.plaf.SliderUI;

import org.omg.Messaging.SyncScopeHelper;

import Editors.Button;
import Editors.Type;
import FilesUtils.FileEditor;
import GameObjects.ImageLocationTester;
import GameObjects.ImageTester;
import General.ImageData;
import General.ImageLoader;
import General.ImagesGroup;
import General.SpriteSheet;
import General.Utilities;
import Main.Game;
import MapTrazlation.TranzlateCharacters;
import MapTrazlation.TranzlateMovie;

public class MovieEditor extends MouseAdapter {
	private int swWidth=Game.WIDTH,swHeight=Game.HEIGHT;//start window width and height
	private SpriteSheet ss;
	private String name, path,characterName = "luffy";
	private BufferedImage img;
	private PointInt point1, dragPoint;
	private boolean mouseClicked = false, mousepressed,tselected=false,addingSelector=false,addingCamera=false,nPressed =false,xleft=true,yup=true;
	private WheelEvent weel;
	private KeyMovieInput keyInput;
	private boolean crtlPressed = false, mouseDrag = false, mouseweelMoved = false, formerMouseWheelMoved = false,vpressed=false,bpressed=false,shiftpressed = false,zpressed=false,hpressed=false;
	private int x, y;
	private ArrayList<Selector> selectors;
	private double sizeWidth = 1, sizeHeight = 1;
	private Selector normalBounds =null;
	private int imgDiv = 10;
	private Settings settings;
	private ArrayList<Button> buttons;
	private ArrayList<Camera> cameras;
	private Rectangle testRectangle;
	private ArrayList<Rectangle> frames = new ArrayList<Rectangle>();
	private int fps=5;
	private Camera selectSelectors=null;
	private boolean saveRam = true;
	private boolean hideBones =true;
	private int backGround;
	private int sensetivity = 5;
	private ImageData head ,leg, chest, arm;
	private char openKey='-';
	private String specialKey="None";;
	
	public MovieEditor(String name, String path) {
		head = new ImageData(0,0,0,0);
		leg = new ImageData(0,0,0,0);
		arm = new ImageData(0,0,0,0);
		chest= new ImageData(0,0,0,0);
		cameras = new ArrayList<Camera>();
		buttons = new ArrayList<Button>();
		this.path = path;
		this.name = name;
		SpriteSheet settiingsSprite = new SpriteSheet(new ImageLoader().loadImage("/settings_grey.png"));
		ss = new SpriteSheet(new ImageLoader().loadImage(path));
		img = ss.getImage();
		setWeel(new WheelEvent(this));
		setKeyInput(new KeyMovieInput(this));
		this.selectors = new ArrayList<Selector>();
		x = 0;
		y = 0;
		backGround=ss.grabImage(1,1,1,1).getRGB(0,0);
		settings = new Settings(this);
		SettingsButton sb = new SettingsButton(settiingsSprite.getImage(), 0, 0, 50, 50, Type.send, settings);
		buttons.add(sb);
	}
	
	public void setSpecialKey(String key){
		this.specialKey=key;
	}
	
	public MovieEditor(String character, String movie, String path) {

		head = new ImageData(0,0,0,0);
		leg = new ImageData(0,0,0,0);
		arm = new ImageData(0,0,0,0);
		chest= new ImageData(0,0,0,0);
		cameras = new ArrayList<Camera>();
		buttons = new ArrayList<Button>();
		this.path = path;
		this.name = movie;
		this.characterName=character;
		SpriteSheet settiingsSprite = new SpriteSheet(new ImageLoader().loadImage("/settings_grey.png"));
		ss = new SpriteSheet(new ImageLoader().loadImage(path));
		img = ss.getImage();
		setWeel(new WheelEvent(this));
		setKeyInput(new KeyMovieInput(this));
		this.selectors = new ArrayList<Selector>();
		x = 0;
		y = 0;
		backGround=ss.grabImage(1,1,1,1).getRGB(0,0);
		settings = new Settings(this);
		SettingsButton sb = new SettingsButton(settiingsSprite.getImage(), 0, 0, 50, 50, Type.send, settings);
		buttons.add(sb);
		loadMovie();
		System.out.println(this.getClass().toString()+" image path at constructor" +this.path);

	}

	public int getBackGround(){
		return this.backGround;
	}
	
	public int setBackGround(int backGround){
		return this.backGround;
	}
	public Rectangle getWindowBounds(){
		return new Rectangle(0,0,Game.WIDTH,Game.WINDOWHEIGHT);
	}
	
	public void mouseMoved(MouseEvent e){

		settings.onMouseHover(e.getX(),e.getY());
	}
	
	public void mousePressed(MouseEvent e) {
		
		int i = 0 ;
	
		
		for (Button button : buttons) {
			if(button.onClick(e.getX(), e.getY())){
			 return;
			}
		}
		if(!settings.isClicked(e.getX(),e.getY())&&!crtlPressed){
			if(normalBounds!=null){
				normalBounds.onClick(e.getX(),e.getY());
			}
			boolean clickedSelector = false;
			 i = 0 ; 
			if(!shiftpressed&&!zpressed){
				while(i<selectors.size()){
					if(selectors.get(i).onClick(e.getX(),e.getY())){
						if(!selectors.get(i).isSaved()){
							selectors.get(i).setSaved(true);
							Selector temp = demoSave(selectors.get(i));
							selectors.get(i).setFrame(temp.getFrame());
							
						}
					
				clickedSelector  =true;
					}
					i++;
				}
			}else if(shiftpressed){
				while(i<selectors.size()){
					Selector selector = selectors.get(i);
					if(!selector.isBone()){
						selector.onClick(e.getX(), e.getY());
					}else{
						if(selector.isClicked(e.getX(),e.getY())){
							if(selector.isChosen()&&selector.isEditable()){
								selector.setChosen(false);
								selector.setEditable(false);
							}else{
								selector.setChosen(true);
								selector.setEditable(true);
							}
							
							
						}
					}
					
					i++;
				}
			}else{
				i=0;
				while(i<selectors.size()){
					Selector selector = selectors.get(i);
					if(selector.isBone()){
						if(selector.onClick(e.getX(), e.getY())){
							selector.setSaved(true);
						}
						
					}
					i++;
				}
			}	
		}
		if(settings.onClick(e.getX(), e.getY())){
			return;
		}
		if(tselected){
			i=0;
			while(i<cameras.size()){
				if(cameras.get(i).onClick(e.getX(), e.getY())){
					
					return;
				}
				i++;
			}
		}
		
		if(vpressed==true){
			selectSelectors= new Camera();
		}
	
	
		if (!tselected&&!shiftpressed) {
	
			if (!mousepressed) {
				mousepressed = true;
				if(!crtlPressed){
					i = 0;
					while (i < selectors.size()) {
						Selector selector = selectors.get(i);
						
						if (!selectors.get(i).isSaved()) {
							selectors.remove(i);
							i--;
						}
						i++;
					}
				}
				selectors.add(new Selector());
				selectors.get(selectors.size()-1).setMovieEditor(this);
				if(bpressed){
					selectors.get(selectors.size()-1).setBone(true);
				}
				addingSelector=true;
			}
		
			double x = e.getX() / sizeWidth;
			double y = e.getY() / sizeWidth;
			point1 = new PointInt(e.getX(), e.getY());
			dragPoint = new PointInt(e.getX(), e.getY());
			// selectors.get(selectors.size()-1).SetStart(new
			// PointInt((int)x,(int)y));
			selectors.get(selectors.size() - 1).setSdeltaX(this.x);
			selectors.get(selectors.size() - 1).setSdeltaY(this.y);
			selectors.get(selectors.size() - 1).setDivHeight(sizeWidth);
			selectors.get(selectors.size() - 1).setDivWidth(sizeHeight);
			selectors.get(selectors.size() - 1).setDeltaX(this.x);
			selectors.get(selectors.size() - 1).setDeltaY(this.y);
		}else if(tselected){
			addingCamera=true;
		cameras.add(new Camera());
		point1 = new PointInt(e.getX(), e.getY());
		dragPoint = new PointInt(e.getX(), e.getY());
		}
	
	}

	public void mouseClicked(MouseEvent e) {
		point1 = null;
	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
		if (crtlPressed) {
			if (true) {
				int deltaX, deltaY;
				int arg = arg0.getWheelRotation();
				double delta = 1;

				if (arg > 0) {
					delta = 0.95;
				} else {
					delta = 1.05;
				}
				sizeHeight *= delta;
				sizeWidth *= delta;
				int i = 0;
				while (i < selectors.size()) {
					Selector selector = selectors.get(i);
					selector.setScaleHeight(selector.getScaleHeight() * delta);
					selector.setScaleWidth(selector.getScaleWidth() * delta);
					i++;
				}
				
				if(normalBounds!=null){
					normalBounds.setScaleHeight(normalBounds.getScaleHeight() * delta);
					normalBounds.setScaleWidth(normalBounds.getScaleWidth() * delta);
				}
			}

		} else {
			settings.MouseWheelMoved(arg0);
		}
	}
	
	public double getDivHeight(){
		return this.sizeHeight;
	}
	public double getDivWidth(){
		return this.sizeWidth;
	}

	public void keyPressed(KeyEvent e) {
		if (settings.isSelected()) {
			settings.KeyPreesed(e);
		} else {
			int key = e.getKeyCode();
			int i = 0;
			switch (key) {
			case KeyEvent.VK_Z:
				if(crtlPressed){
					if(selectors.size()>0){
						Selector selector = selectors.get(selectors.size()-1);
						selector.delete();
						selectors.remove(selector);
					}
				}else{
					zpressed=!zpressed;
				}
				
				break;
			
			case KeyEvent.VK_H:
				hpressed=!hpressed;
				break;
			case KeyEvent.VK_V:
				vpressed= true;
				break;
			case KeyEvent.VK_SHIFT:
			
				shiftpressed=true;
				break;
		
			case KeyEvent.VK_CONTROL:
				crtlPressed = true;
				break;
				
			case KeyEvent.VK_Q:
				setBoundsToNoraml();
				break;
			case KeyEvent.VK_ENTER:
				i = 0;
				while (i < selectors.size()) {
					if (!selectors.get(i).isSaved()) {
						selectors.get(i).setSaved(true);
						selectors.get(i).setEditable(false);
						if(!selectors.get(i).isLocked()){
							Selector temp  = demoSave(selectors.get(i));
							selectors.get(i).setFrame(temp.getFrame());
							
						}
					}
					i++;
				}

				break;
			case KeyEvent.VK_N:
				this.nPressed = true;
				setBoundsToFullPicture();
				break;
			case KeyEvent.VK_DELETE:
				while (i < selectors.size()) {
					Selector selector = selectors.get(i);
					if (selector.isChosen()) {
						selectors.remove(selector);
						selector.delete();
						
						
					}else{
						i++;
					}
					
				}
				i=0;
				while(i<cameras.size()){
					Camera camera =  cameras.get(i);
					if(camera.isSelected()){
						cameras.remove(camera);
					}else{
						i++;
					}
				}
				break;
			case KeyEvent.VK_B:
				this.bpressed=true;
				break;
			case KeyEvent.VK_S:
				//save();
				break;
			
			case KeyEvent.VK_T:
				tselected=true;
				break;
			case KeyEvent.VK_L:
				loadMovie();
				break;
				
			case KeyEvent.VK_C:
				i=0;
				ArrayList<Selector> selectedBones = new ArrayList<Selector>();
				Selector armature=null;
				while(i<selectors.size()){
					Selector selector = selectors.get(i);
					if(selector.isChosen()){
						if(selector.isBone()){
							selectedBones.add(selector);
						}else{
							armature = selector;
						}
					}
					i++;
				}
				
				i=0;
				if(armature!=null){
				while(i<selectedBones.size()){
					armature.addBone(selectedBones.get(i));
					selectedBones.get(i).setArmature(armature);
					i++;
				}
				}
				
				break;
			
			default:
				break;
			
			
			}
		}

	}
	
	public void setBoundsToFullPicture(){
		int i = 0;
		double div = this.imgDiv;
		div = 1.0 / div;
		setDivSize(div, div);
		setLocation(0, 0);
		i = 0;
		while (i < selectors.size()) {
			Selector selector = selectors.get(i);

			i++;
		}

	}
	
	public void setWindowLocationBounds(double zoom , Point location){
		zoom = 1.0/zoom;
		setDivSize(zoom, zoom);
		setLocation((int)location.getX(),(int)location.getY());
	}
	
	public void setBoundsToNoraml(){
		int i = 0;
		double div = this.imgDiv;
		div = 1.0 / div;
		setDivSize(1,1);
		setLocation(0, 0);
		i = 0;
		while (i < selectors.size()) {
			Selector selector = selectors.get(i);

			i++;
		}
		
	}
	
	
	public void mouseReleased(MouseEvent e) {
		addingSelector=false;
		addingCamera = false;
		selectSelectors=null;
		if (selectors.size() > 0) {
			if (!selectors.get(selectors.size() - 1).isSaved()) {
				selectors.get(selectors.size() - 1).setSelected(true);
			}
		}
		if (selectors.size() > 0) {
			if (!selectors.get(selectors.size() - 1).isSaved()) {
				selectors.get(selectors.size() - 1).setSelected(true);
			}
		}  
		int i = 0;
		if(!crtlPressed){
		
			while (i < selectors.size()) {
				Selector selector = selectors.get(i);
				if (selector.getWidth() == 0 && selector.getHeight() == 0) {
					selectors.remove(selector);
				}
				i++;
			}
		}
		
		vpressed=false;
		
		mousepressed = false;

	}

	public void mouseDragged(MouseEvent e) {

		if (!settings.isSelected()&&addingSelector&&!vpressed) {
			if (crtlPressed) {
				double deltaX = -(dragPoint.getX() - e.getX());
				double deltaY = -(dragPoint.getY() - e.getY());
				x += deltaX*sensetivity;
				y += deltaY*sensetivity;
				int i = 0;
				while (i < selectors.size()) {
					Selector selector = selectors.get(i);
					selector.setDeltaX(x);
					selector.setDeltaY(y);
					i++;
				}
				if(normalBounds!=null){
					normalBounds.setDeltaX(x);
					normalBounds.setDeltaY(y);
				}
			
				dragPoint = new PointInt(e.getX(), e.getY());
			}
			else {
				if (selectors.size() > 0) {
		
					selectors.get(selectors.size() - 1).tick(new PointInt((int) (point1.getX()), (int) (point1.getY())),
										new PointInt((int) (e.getX()), (int) (e.getY())));
				
				
				}
				
			}

		}else if(addingCamera){
			Camera camera = cameras.get(cameras.size()-1);
			camera.chnageBounds(point1,new PointInt(e.getX(),e.getY()));
		}else if(vpressed){
			if(selectSelectors!=null){
				selectSelectors.chnageBounds(point1, new PointInt(e.getX(),e.getY()));
				int i =0;
				while(i<selectors.size()){
					Selector selector = selectors.get(i);
					if(selector.getViewRect()!=null){
					if(selector.getViewRect().intersects(selectSelectors.getRect())){
						selector.setSelected(true);
						selector.setChosen(true);
						selector.setEditable(true);
					}else{
						selector.setSelected(false);
						selector.setChosen(false);
						selector.setEditable(false);
					}
					}
					i++;
					
				}
			}
		
		}

	}
	
	public Selector getSelectedSelectorNoBones(){
		int i = 0 ; 
		while(i < selectors.size()){
			Selector selector =selectors.get(i);
			if(!selector.isBone()&&selector.isChosen()){
				return selector;
			}
			
			i++;
		}
		return null;
	}

	public void render(Graphics2D g2d) {
		
		int i = 0;
		if(testRectangle !=null){
		g2d.setColor(Color.BLACK);
			g2d.fill(testRectangle);
			
		}
	
		if(normalBounds!=null){
			g2d.setColor(Color.cyan);
			g2d.fill(normalBounds.getViewRect());
			normalBounds.render(g2d);
		
		
		
			
		}
		i=0;
		while(i<cameras.size()){
			cameras.get(i).render(g2d);
			i++;
		}
		

	
		
		double vx = x;
		double vy = y;
		double width = Game.WIDTH;
		double height = Game.HEIGHT;
		width *= imgDiv;
		width *= sizeWidth;
		height *= imgDiv;
		height *= sizeHeight;
		vx *= sizeWidth;
		vy *= sizeHeight;
		g2d.drawImage(img, (int) vx, (int) vy, (int) width, (int) height, null);
		
		
		i=0;
		while (i < selectors.size()) {
			Selector selector = selectors.get(i);
			if(!selector.isBone()){
				selectors.get(i).setFullVizability(!zpressed);
			}
			if(selector.isBone()){
				if(!hpressed){
					selectors.get(i).render(g2d);
				}
			}else{
				selectors.get(i).render(g2d);
			}
		
				
				
			
			i++;
		}
		
		
		
		for (Button button : buttons) {
			button.render(g2d);
		}
		
		
		
		if (settings != null) {
			settings.render(g2d);
		}
		
		if(selectSelectors!=null){
			selectSelectors.setColor(Color.black);
			selectSelectors.render(g2d);
		}
	
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_CONTROL:
			this.crtlPressed = false;
		//	this.imageDeltaX = 0;
			//this.imageDeltaY = 0;
			break;
		case KeyEvent.VK_T:
			tselected = false;
			break;
		case KeyEvent.VK_N:
			nPressed =false;
			break;
		
		case KeyEvent.VK_B:
			this.bpressed=false;
			break;
		case KeyEvent.VK_SHIFT:
			shiftpressed=false;
			break;
		case KeyEvent.VK_V:
			if(!mousepressed){
				vpressed=false;
			}
			
			
			break;
		default:
			break;
			
		
		
		}
	}

	public WheelEvent getWeelInput() {
		return weel;
	}

	public String getPath() {
		return this.path;
	}

	public void setWeel(WheelEvent weel) {
		this.weel = weel;
	}

	public KeyMovieInput getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyMovieInput keyInput) {
		this.keyInput = keyInput;
	}

	public void setDivSize(double sw, double sh) {
		int i = 0;
		while (i < selectors.size()) {
			Selector selector = selectors.get(i);
			selector.setScaleHeight(sh / selector.getDivHeight());
			selector.setScaleWidth(sw / selector.getDivWidth());

			i++;
		}
		this.sizeHeight = sh;
		this.sizeWidth = sw;

	}

	public String getName() {
		return this.name;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		int i = 0;
		while (i < selectors.size()) {
			Selector selector = selectors.get(i);
			selector.setDeltaX(x);
			selector.setDeltaY(y);
			i++;
		}

	}

	public void tick() {
		settings.tick();
	}
	


	public void save() {
		
		if(openKey==' '){
			openKey='-';
		}
		
		int i = 0;
		ArrayList<Selector> tempArr = demoSave();
		ArrayList<Selector> tempSelectors = new ArrayList<Selector>();
		while(i<tempArr.size()){
			if(!tempArr.get(i).isBone()){
				tempSelectors.add(tempArr.get(i));
			}
			
			i++;
		}
		
		
		FileEditor file = new FileEditor();
		///file.Openfile("Levels/movies.txt");
			file.setPath("Levels/movies.txt");
		ArrayList<String> content = new ArrayList<String>();
		content.add(name);
		content.add("<head>");
	
		content.add("path:  " + path +" ;");
		content.add("Key: " + openKey + " ;");
		content.add("SpecialKey: " + specialKey + " ;");
		content.add("arm: " + arm.isSolid() +" " + arm.getAttack() + " " + arm.getDefence()+ " ;");
		content.add("leg: " + leg.isSolid() +" " + leg.getAttack() + " " + leg.getDefence()+" ;");
		content.add("chest: " + chest.isSolid() +" " + chest.getAttack() + " " + chest.getDefence()+" ;");
		content.add("head-armature: " + head.isSolid() +" " + head.getAttack() + " " + head.getDefence()+" ;");
		 if(normalBounds!=null){
			Rectangle frame = normalBounds.getFrame();
			content.add("normalBounds: "+(int)frame.getWidth()+" " +(int)frame.getHeight()+" ;");
		}
		content.add("fps: "  + fps+" ;");
		content.add("</head>");
		content.add("<body>");

		i = 0;
		while (i < tempSelectors.size()) {
			Selector selector = tempSelectors.get(i);
	
			//content.add(selector.toString(Game.WIDTH, Game.HEIGHT, img.getWidth() / imgDiv, img.getHeight() / imgDiv));
		Rectangle frame = selector.getFrame();
		if(frame!=null){
			String bonesConent= "";
			int j = 0;
			
		//	content.add((int)frame.getX()+" " +(int)frame.getY()+" "+(int)frame.getWidth()+" "+(int)frame.getHeight()+" " +(int)selector.getFrameDeltaBounds().getWidth()+" " +  (int)selector.getFrameDeltaBounds().getHeight());
			
			content.add(selector.getContent());
		}
	
			i++;
		}
		content.add("</body>");

		content.add("end");

		file.editFromBlockName("Character: " +characterName,"Character-end",name, content, "end");
		
	}

	public ArrayList<Selector> demoSave() {
	
		ArrayList<Selector>  tempSelectors = new ArrayList<Selector>();
		int i = 0;
		while (i < selectors.size()) {
			if(selectors.get(i)==null){
			}
			Selector selector = selectors.get(i);
			selector.setLocked(selectors.get(i).isLocked());
			selector.setFrame(selectors.get(i).getFrame());
			tempSelectors.add(new Selector(selector));
			tempSelectors.get(tempSelectors.size()-1).setMovieEditor(this);
		
			i++;
		}

		double div = this.imgDiv;
		div = 1.0 / div;
		i = 0;
		while (i < tempSelectors.size()) {
			Selector selector = tempSelectors.get(i);
			selector.setScaleHeight(div / selector.getDivHeight());
			selector.setScaleWidth(div / selector.getDivWidth());
			selector.setDeltaX(-selector.getDeltaX());
			selector.setDeltaY(-selector.getDeltaY());
			i++;
		}

		return tempSelectors;
	}
	
	public Selector demoSave(Selector selected){
		double div = 0.1/this.imgDiv;
		Selector selector = new Selector(selected);
		selector.setScaleHeight(div / selector.getDivHeight());
		selector.setScaleWidth(div / selector.getDivWidth());
		selector.setDeltaX(-selector.getDeltaX());
		selector.setDeltaY(-selector.getDeltaY());
		selector.setMovieEditor(this);
		selector.generateFrame(Game.WIDTH, Game.HEIGHT, img.getWidth() / imgDiv, img.getHeight() / imgDiv);
		return selector;
	}

	public void loadMovie() {
		Point start = new Point(x,y);
		double zoom = 1.0/this.sizeHeight;
	selectors = new ArrayList<Selector>();
//	ArrayList<Rectangle> frames = new ArrayList<Rectangle>();
	
	
	TranzlateCharacters tc = new TranzlateCharacters();
	tc.GenerateCharactersMovment("Levels/movies.txt");
	
	TranzlateMovie tranzlate = new TranzlateMovie("Levels/movies.txt",name);
//	tranzlate.TranzlateObject();	
//	frames = tranzlate.getFrames();
//	this.frames=frames;

	ArrayList<ImageData> imagesData = new ArrayList<ImageData>();
	if(tc.getFilmByCharacterName(characterName, name)==null){
		return;
	}
	
	this.path=tc.getFilmByCharacterName(characterName, name).getFilmLeft().getPath();
	
	ss = new SpriteSheet(new ImageLoader().loadImage(path));
	this.img=ss.getImage();
	
	setBoundsToNoraml();	
	imagesData = tc.getFilmByCharacterName(characterName, name).getFilmRight().getImagesData();
	
	this.fps=tranzlate.getFps();
	for(ImageData data : imagesData){
		double x,y,width,height,windW,windH,imgW,imgH,deltaX,deltaY;
	windH=Game.HEIGHT;
	windW=Game.WIDTH;
	imgH=this.img.getHeight();
	imgW=this.img.getWidth();
	width =data.getImageWidth();
	height=  data.getImageHeight();
	x= data.getImageX();
	y= data.getImageY();
	deltaY=windH/imgH;
	deltaX=windW/imgW;
	x=deltaX*x/sizeWidth*imgDiv;
	y=deltaY*y/sizeHeight*imgDiv;
	
	width = deltaX*((double)data.getImageWidth())*imgDiv+1;
	height = deltaY*((double)data.getImageHeight())*imgDiv+1;
	y+=1;
	Selector selector = new Selector();
	selector.setAttack(data.getAttack());
	selector.setSolid(data.isSolid());
	selector.setDefence(data.getDefence());
	selector.setMovieEditor(this);
	selector.setSdeltaX(this.x);
	selector.setSdeltaY(this.y);
	selector.setDivHeight(sizeHeight);
	selector.setDivWidth(sizeWidth);
	selector.setDeltaX(this.x);
	selector.setDeltaY(this.y);
	selector.setRect(new Rectangle((int)x ,(int)y,(int)width,(int)height));
	selector.setSaved(true);
//	selector.setRect(frame);
//	selector.tranzlateFromFrame(frame,windW, windH, imgW, imgH);
	selector.setLocked(true);
	selector.setFrame(new Rectangle(data.getImageX(),data.getImageY(),data.getImageWidth(),data.getImageHeight()));
	selector.setFrameDeltaBounds(new Rectangle(data.getDeltaX(),data.getDeltaY()));
	selectors.add(selector);
	for(ImagesGroup group: data.getGroup()){
		ArrayList<Selector> bonesGroup = new ArrayList<Selector>();
		
		for(ImageData temp : group.getImages()){
			
			width =temp.getImageWidth();
			height=  temp.getImageHeight();
			x= temp.getImageX();
			y= temp.getImageY();
			deltaY=windH/imgH;
			deltaX=windW/imgW;
			x=deltaX*x/sizeWidth*imgDiv;
			y=deltaY*y/sizeHeight*imgDiv;

			width = deltaX*((double)temp.getImageWidth())*imgDiv+1;
			height = deltaY*((double)temp.getImageHeight())*imgDiv+1;
			y+=1;
			
			
			Selector bone = new Selector();
			bone.setAttack(temp.getAttack());
			bone.setDefence(temp.getDefence());
			bone.setSolid(temp.isSolid());
			bone.setMovieEditor(this);
			bone.setSdeltaX(this.x);
			bone.setSdeltaY(this.y);
			bone.setDivHeight(sizeHeight);
			bone.setDivWidth(sizeWidth);
			bone.setDeltaX(this.x);
			bone.setDeltaY(this.y);
			bone.setRect(new Rectangle((int)x,(int)y,(int)width,(int)height));
			bone.setSaved(true);
			bone.setLocked(true);
			bone.setFrame(new Rectangle(temp.getImageX(),temp.getImageY(),temp.getImageWidth(),temp.getImageHeight()));
			bone.setFrameDeltaBounds(new Rectangle(temp.getDeltaX(),temp.getDeltaY()));
			bone.setBone(true);
			bone.setArmature(selector);
			selector.addBone(bone);
			bonesGroup.add(bone);
			selectors.add(bone);
		}
		
		selector.asignGroup(bonesGroup);
		BonesGroup g = selector.getGroups().get(selector.getGroups().size()-1);	
		g.setAttackNoBones(group.getAttack());
		g.setDefenceNoBones(group.getDefence());
		g.setSolid(group.isSolid());

	}
	
	}

	this.setWindowLocationBounds(zoom, start);

	String t= tc.getFilmByCharacterName(characterName, name).getKey();
		System.out.println(this.getClass().toString() +" open key is " +t);
	if(t!=null&&t.length()>0){
	openKey=	t.toCharArray()[0];
	settings.getOpenKeyButton().setString(openKey+"");
	}
	String specialKey = tc.getFilmByCharacterName(characterName, name).getSpecialKey();
	if(specialKey!=null&&specialKey!=""){
		settings.getKeyMode().setMode(specialKey);
		this.specialKey=specialKey;;
	}
	
	}
	
	
	public Selector loadSelectorFromPicture(Rectangle rect){
		Point start = new Point(x,y);
		double zoom = 1.0/this.sizeHeight;
		setBoundsToNoraml();	

	
		ArrayList<ImageData> imagesData = new ArrayList<ImageData>();
		imagesData.add(new ImageData(rect));
	ImageData data = new ImageData(rect);
			double x,y,width,height,windW,windH,imgW,imgH,deltaX,deltaY;
		windH=Game.HEIGHT;
		windW=Game.WIDTH;
		imgH=this.img.getHeight();
		imgW=this.img.getWidth();
		width =data.getImageWidth();
		height=  data.getImageHeight();
		x= data.getImageX();
		y= data.getImageY();
		deltaY=windH/imgH;
		deltaX=windW/imgW;
		x=deltaX*x/sizeWidth*imgDiv;
		y=deltaY*y/sizeHeight*imgDiv;

		width = deltaX*((double)data.getImageWidth())*imgDiv+1;
		height = deltaY*((double)data.getImageHeight())*imgDiv+1;
		y+=1;
		Selector selector = new Selector();
		selector.setMovieEditor(this);
		selector.setSdeltaX(this.x);
		selector.setSdeltaY(this.y);
		selector.setDivHeight(sizeWidth);
		selector.setDivWidth(sizeHeight);
		selector.setDeltaX(this.x);
		selector.setDeltaY(this.y);
		selector.setRect(new Rectangle((int)x ,(int)y,(int)width,(int)height));
		selector.setSaved(true);
//		selector.setRect(frame);
//		selector.tranzlateFromFrame(frame,windW, windH, imgW, imgH);
		selector.setLocked(true);
		selector.setFrame(rect);
		selector.setFrameDeltaBounds(new Rectangle(data.getDeltaX(),data.getDeltaY()));
		selectors.add(selector);
		this.setWindowLocationBounds(zoom, start);
		return selector;

	}
	
	public double getImgDiv(){
		return this.imgDiv;
	}
	
	
	public ArrayList<Selector> getSelectedBones(){
	ArrayList<Selector> bones = new ArrayList<Selector>();
	int i = 0;
	while(i<selectors.size()){
		Selector bone = selectors.get(i);
		if(bone.isBone()&&bone.isChosen()){
			bones.add(bone);
		}
		i++;
	}
	return bones;
	}
	
	
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public  Settings getSettings(){
		return this.settings;
	}

	public Selector getSelectedSelector() {
	int i = 0 ;
	while(i<selectors.size()){
		if(normalBounds!=null&&normalBounds.isChosen()){
			return normalBounds;
		}
		Selector selector = selectors.get(i);
		if(selector!=null){
			if(selector.isChosen()){
				return selector;
			}
		}
	
		i++;
	
	}
	
	return null;
}

	public SpriteSheet getSpriteSheet() {
		// TODO Auto-generated method stub
		return ss;
	}

	public ArrayList<Selector> getSelectors() {
		// TODO Auto-generated method stub
		return selectors;
	}
	
	public void setNormalBounds(Selector selector){
		this.normalBounds=new Selector(selector);
		selector.setMovieEditor(this);
		this.normalBounds.setFrame(selector.getFrame());
	}

	public void setSelectors(ArrayList<Selector> selectors) {
	this.selectors=selectors;
		
	}
	
	public void setName(String name){
		this.name=name;
	}

	public Selector getNormlSelectorBounds() {
		// TODO Auto-generated method stub
		return normalBounds;
	}
	
	public void setFps(int fps){
		this.fps= fps;
	}

	public String getCharacter() {
		// TODO Auto-generated method stub
		return this.characterName;
	}

	public void setCharacter(String string) {
	this.characterName=string;
	}

	public void removeSelector(Selector bone) {
		this.selectors.remove(bone);
		
	}

	public void addSelecotr(Selector temp) {
		this.selectors.add(temp);
		
	}
	public boolean isSaveRam() {
		// TODO Auto-generated method stub
		return saveRam;
	}
	
	public void setSaveRam(boolean b){
		int i = 0;
		while(i<selectors.size()){
		Selector selector = selectors.get(i);
		if(b==true){
		if(!selector.isBone()&&!selector.isChosen()){
			selector.hideBones(b);
		}
		}else{
			if(!selector.isBone()){
				selector.hideBones(false);
			}
		}
			i++;
		}
		this.saveRam=b;
	}
	
	
	public void setWindowSizeReltiveToImage(){
		double gh,gw,ph,pw;
		gh = Game.HEIGHT;
		ph=img.getHeight();
		pw=img.getWidth();
		gw=gh*pw/ph;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if(gw>screenSize.getWidth()-5){
			gw =screenSize.getWidth()-5;
		}
		
		Game.changeSize((int)gw,(int) gh);
		
	}
	
	public void setWindowSizeToNormal(){
		Game.changeSize(swWidth, swHeight);
	}

	public void setSensitivity(int value) {
	this.sensetivity=value;
		
	}

	public void removeSelector(int value) {
		selectors.remove(value);
		
	}
	
	public void setChest(ImageData data){
		chest = new ImageData(data);
	}
	
	public void setLeg(ImageData data){
		leg = new ImageData(data);
	}
	
	public void setArm(ImageData data){
		arm = new ImageData(data);
	}
	
	public void setHead(ImageData data){
		head = new ImageData(data);
	}
	
	public ImageData getArm(){
		return this.arm;
	}
	
	public ImageData getLeg(){
		return this.leg;
	}
	
	public ImageData getHead(){
		return this.head;
	}
	
	public ImageData getChest(){
		return this.chest;
	}


	public void setPath(String string) {
		this.path=string;
		
	}
	public void loadImage(){
		ss = new SpriteSheet(new ImageLoader().loadImage(path));
		backGround=ss.grabImage(1,1,1,1).getRGB(0,0);
		this.img=ss.getImage();
	}

	public void setOpenKey(char openKey) {
	this.openKey=openKey;
	}
		

}
