package Main;
import java.awt.Canvas;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JTextArea;

import CharactersMenu.CharactersMenu;
import Editors.EditMap;
import GameObjects.ID;
import GameObjects.ObjectHandler;
import GameObjects.Player;
import MainMenu.EscapeInput;
import MainMenu.HoldMenu;
import MainMenu.MainMenu;
import MapTrazlation.Characters;
import MapTrazlation.ColorList;
import MapTrazlation.EditTranzlation;
import MapTrazlation.FilmList;
import MapTrazlation.SpriteSheetList;
import MapTrazlation.TranzlateCharacters;
import MapTrazlation.TranzlateMovie;
import MoiveEditor.MovieEditor;
import QestionMenu.QeustionAble;
import QestionMenu.QeustionMenu;
import Views.Inventory;
import GameObjects.Map;
public class Game extends Canvas implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private boolean running = false;
    private Thread thread;
    public int x=0,y=0;
    public ObjectHandler objectHandler;
    private Inventory inventory;
    public static int WIDTH = Map.BLOCKSIZE*16 , HEIGHT = Map.BLOCKSIZE*15;
    public static int WINDOWHEIGHT =Map.BLOCKSIZE*15+25;
    public static int PLAYBOUNDRY = WINDOWHEIGHT - HEIGHT;
    public MainMenu mainMenu;
    public GameState state = GameState.mainMenu;
    public EditMap mapEditor;
    public Map map;
    public MovieEditor movieEditor;
    private boolean loading = true;
    private Loading loadingGif;
    private Image loadingBackGround;
    private static Window window;
    private HoldMenu holdmenu;
    private boolean showHoldMenu=false;
    private CharactersMenu characterMenu;
    private QeustionMenu question=null;

    public Game(){
     	loadingGif = new Loading(this);
	  window = new Window(WIDTH,WINDOWHEIGHT,"Save Ace!",this,loadingGif);	 
		URL url = this.getClass().getResource("/loading2.gif");
		holdmenu = new HoldMenu(this);
		loadingBackGround= new ImageIcon(url).getImage();
		holdmenu = new HoldMenu(this);
		EscapeInput escapeInput = new EscapeInput(this);
		this.addKeyListener(escapeInput);
		
		
	switch (state) {
	case playing:
		String path = "Levels/movies.txt";
		
		TranzlateCharacters ct= new TranzlateCharacters();
		ct.GenerateCharactersMovment(path);
		Characters.setCharacters(ct.getCharacterMovments());
//		TranzlateMovie luffyStands = new TranzlateMovie("Levels/movies.txt","luffyStands");
//		TranzlateMovie luffyRun = new TranzlateMovie("Levels/movies.txt","luffyRun");
//		TranzlateMovie luffyBazuka = new TranzlateMovie("Levels/movies.txt", "luffyBazuka");
//	  	luffyRun.TranzlateObject();
//	  	luffyStands.TranzlateObject();
//	  	luffyBazuka.TranzlateObject();
//	  	FilmList.luffyStands = luffyStands.getFilm();
//	  	FilmList.luffyRun=luffyRun.getFilm();
//	  	FilmList.luffyBazuka = luffyBazuka.getFilm();
		 Player player = new Player(500,500,50,50,ID.player,objectHandler);
		 inventory = new Inventory(player);
		map = new Map(player,inventory);
		objectHandler=map.getRoom();
		 player.setRoom(objectHandler);	
		 this.addKeyListener(new KeyInput(player,objectHandler,this));
		break;
	case mapEditor:
		 mapEditor=new EditMap();
		 this.addMouseListener(mapEditor.getMouseListener());
		 
		break;
	case movieEditor:
		String tpath = "Levels/movies.txt";
		TranzlateCharacters tct= new TranzlateCharacters();
		tct.GenerateCharactersMovment(tpath);
		Characters.setCharacters(tct.getCharacterMovments());
		ColorList colorsList = new ColorList();
		movieEditor = new MovieEditor("luffy", "/BackGroundSprite.png");
		this.addMouseListener(movieEditor);
		this.addMouseMotionListener(movieEditor);
		this.addKeyListener(movieEditor.getKeyInput());
		this.addMouseWheelListener(movieEditor.getWeelInput());
		
		
		
		break;
	case mainMenu:
	//	mainMenu = new MainMenu();
		mainMenu= new MainMenu(this);
		this.addMouseListener(mainMenu);
		this.addMouseMotionListener(mainMenu);
		break;
	
	}
	

	 }
	
	public static void changeSize(int width,int height){
		WIDTH=width;
		HEIGHT=height;
		window.resize(width,height);
		
	}
	

	@Override
	public void run() {
	
		long lastTime = System.nanoTime();
		double fps = 60.0;
		double ns =1000000000/fps;
		double delta =1;
		int tickCounter  = 0;
		while(running){
			
			long now = System.nanoTime();
			delta +=(now -lastTime)/ns;
			lastTime = now;
			
			while(delta>=1)
			{
				tick();
				delta--;
				if(tickCounter<100){
					
					tickCounter++;
					
				}else{
					loading = false;
				}
		
				
			}
			if(running){
				render();
				
			}else{
				loading();
			}
			
		
		
			
		}
		
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try {
			running = false;
			thread.join();
	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick(){
		switch(state){
		case playing:
				if(map!=null){
					map.tick();
			break;
		}
		case movieEditor:
			if(movieEditor!=null){
				movieEditor.tick();
			}
			break;
		case mapEditor:

			break;
		case mainMenu:
			if(mainMenu!=null){
				mainMenu.tick();
			}
		case charactersMenu:
			if(characterMenu!=null){
				characterMenu.tick();
			}
			break;
		}
		
		
    
	}
	
	
	
	public void loading(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		   g2d.dispose();
        bs.show();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0,WIDTH , HEIGHT);
		
		
	
			
	
//		g2d.setColor(new Color(230, 230, 230));
	//	g2d.fillRect(0, 0, WIDTH, WINDOWHEIGHT-HEIGHT);
	
		
		
		switch (state) {
		case playing:
			
			if(map!=null){
				map.render(g2d);
			}
			break;
		case mapEditor:
			g2d.setColor(new Color(144, 164, 174));
			g2d.fillRect(0,0, this.WIDTH, this.HEIGHT);
			if(mapEditor!=null)
			mapEditor.render(g2d);
			break;
		case movieEditor:
			if(movieEditor!=null)
			movieEditor.render(g2d);
			break;
		case mainMenu:
			if(mainMenu!=null){
				mainMenu.render(g2d);
			}
		break;
		case charactersMenu:
			if(characterMenu!=null){
				characterMenu.render(g2d);
			}
			break;

		}
		if(showHoldMenu){
			holdmenu.render(g2d);
		}
		if(question!=null){
			question.render(g2d);
		}
		

	//	g2d.setColor(new Color(68, 89, 92));
		//g2d.drawRect(0, 0, WIDTH,WINDOWHEIGHT- HEIGHT);
       g2d.dispose();
        bs.show();
	}
	
	public void clear(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0,Game.WIDTH , Game.HEIGHT);

		   g2d.dispose();
        bs.show();
	}
	
	public void LoadImage(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.clearRect(0, 0,Game.WIDTH , Game.HEIGHT);
		g2d.drawImage(loadingBackGround,0,0,Game.WIDTH,Game.HEIGHT,null);
		   g2d.dispose();
        bs.show();
	}
	
	
	public void setGameState(GameState state){
		
		this.state=GameState.loading;
	
	
		stop();
		
		 if(mapEditor!=null){
			 this.removeMouseListener(mapEditor.getMouseListener());
			 mapEditor=null;
		 }
		 if(movieEditor!=null){
			 this.removeMouseListener(movieEditor);
			 this.removeMouseMotionListener(movieEditor);
			 this.removeKeyListener(movieEditor.getKeyInput());
			 movieEditor=null;
		 }
		 
		 if(mainMenu!=null){
			 this.removeMouseListener(mainMenu);
			 this.removeMouseMotionListener(mainMenu);
			 this.mainMenu=null;
		 }
		 
		 if(characterMenu!=null){
			 this.removeMouseListener(characterMenu);
			 this.removeMouseMotionListener(characterMenu);
			 this.removeKeyListener(characterMenu.getKeyInputer());
			 this.characterMenu=null;
		 }
		 
		
		
		
		window.startLoading();
		switch (state) {
		case playing:
	
			System.out.println("changed state to playing");
			String path = "Levels/movies.txt";
			
			TranzlateCharacters ct= new TranzlateCharacters();
			ct.GenerateCharactersMovment(path);
			Characters.setCharacters(ct.getCharacterMovments());
//			TranzlateMovie luffyStands = new TranzlateMovie("Levels/movies.txt","luffyStands");
//			TranzlateMovie luffyRun = new TranzlateMovie("Levels/movies.txt","luffyRun");
//			TranzlateMovie luffyBazuka = new TranzlateMovie("Levels/movies.txt", "luffyBazuka");
//		  	luffyRun.TranzlateObject();
//		  	luffyStands.TranzlateObject();
//		  	luffyBazuka.TranzlateObject();
//		  	FilmList.luffyStands = luffyStands.getFilm();
//		  	FilmList.luffyRun=luffyRun.getFilm();
//		  	FilmList.luffyBazuka = luffyBazuka.getFilm();
			 Player player = new Player(500,500,50,50,ID.player,objectHandler);
			 inventory = new Inventory(player);
			map = new Map(player,inventory);
			objectHandler=map.getRoom();
			 player.setRoom(objectHandler);	
			 this.addKeyListener(new KeyInput(player,objectHandler,this));
			
		
					
		
			 break;
		
		case mapEditor:
			 mapEditor=new EditMap();
			 this.addMouseListener(mapEditor.getMouseListener());
			 
			break;
		case movieEditor:
			String tpath = "Levels/movies.txt";
			TranzlateCharacters tct= new TranzlateCharacters();
			tct.GenerateCharactersMovment(tpath);
			Characters.setCharacters(tct.getCharacterMovments());
			ColorList colorsList = new ColorList();
			movieEditor = new MovieEditor("luffy", "/luffyCutPropFinish3.png");
			this.addMouseListener(movieEditor);
			this.addMouseMotionListener(movieEditor);
			this.addKeyListener(movieEditor.getKeyInput());
			this.addMouseWheelListener(movieEditor.getWeelInput());
			

			
			break;
		case mainMenu:
			mainMenu= new MainMenu(this);
			showHoldMenu=false;
			this.removeMouseListener(holdmenu);
			this.removeMouseMotionListener(holdmenu);
			this.addMouseListener(mainMenu);
			this.addMouseMotionListener(mainMenu);
			break;
		case charactersMenu:
			String path2 = "Levels/movies.txt";
			
			TranzlateCharacters tc= new TranzlateCharacters();
			tc.GenerateCharactersMovment(path2);
			Characters.setCharacters(tc.getCharacterMovments());
			characterMenu = new CharactersMenu(this);
			this.addMouseListener(characterMenu);
			this.addMouseMotionListener(characterMenu);
			this.addKeyListener(characterMenu.getKeyInputer());
			break;
			
			default:
				break;
		
		
		
		
		}
		this.state= state;
		loadingGif.stop();
	 this.start();
		
	}
	
	
	public static Window getWindow(){
		return window;
	}

	public void exit() {
		window.getFrame().dispose();
		stop();
		
	}
	
	public void handleHoldMenu(){
		if(state!=GameState.mainMenu){
			showHoldMenu = !showHoldMenu;
			if(showHoldMenu){
				
				 if(mapEditor!=null){
					 this.removeMouseListener(mapEditor.getMouseListener());
			
				 }
				 if(movieEditor!=null){
					 this.removeMouseListener(movieEditor);
					 this.removeMouseMotionListener(movieEditor);
					 this.removeKeyListener(movieEditor.getKeyInput());
				
				 }
				 
				 if(mainMenu!=null){
					 this.removeMouseListener(mainMenu);
					 this.removeMouseMotionListener(mainMenu);
				
				 }
				 
				 if(characterMenu!=null){
					 this.removeMouseListener(characterMenu);
					 this.removeMouseMotionListener(characterMenu);
					 this.removeKeyListener(characterMenu.getKeyInputer());
					
				 }
				
				this.addMouseListener(holdmenu);
				this.addMouseMotionListener(holdmenu);
			}else{
				
				 if(mapEditor!=null){
					 this.addMouseListener(mapEditor.getMouseListener());
			
				 }
				 if(movieEditor!=null){
					 this.addMouseListener(movieEditor);
					 this.addMouseMotionListener(movieEditor);
					 this.addKeyListener(movieEditor.getKeyInput());
				 }
				 
				 if(mainMenu!=null){
					 this.addMouseListener(mainMenu);
					 this.addMouseMotionListener(mainMenu);
				
				 }
				 
				 if(characterMenu!=null){
					 this.addMouseMotionListener(characterMenu);
					 this.addMouseListener(characterMenu);
					 this.addKeyListener(characterMenu.getKeyInputer());
				 }
				
				this.removeMouseListener(holdmenu);
				this.removeMouseMotionListener(holdmenu);
			
			}
		}
	}
	
	public void save(){
		switch(state){
		case  mapEditor:
			if(mapEditor!=null){
				mapEditor.save();
			}
			break;
		case movieEditor:
			movieEditor.save();
			break;
		case charactersMenu:
			characterMenu.save();
			break;
		default:
			break;
		}
	}
	
	public  Graphics2D getIntimadatedGraphics(){
		// after using this method needs to dispose the Graphics virable (the g2d)!!!!!!!!!!!!!!!!!!!!
		BufferStrategy bs = this.getBufferStrategy();

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		return g2d;
	}

	public void loadMovie(String character, String movie,String path) {
		this.state=GameState.movieEditor;
		
		
		stop();
		
		 if(mapEditor!=null){
			 this.removeMouseListener(mapEditor.getMouseListener());
			 mapEditor=null;
		 }
		 if(movieEditor!=null){
			 this.removeMouseListener(movieEditor);
			 this.removeMouseMotionListener(movieEditor);
			 this.removeKeyListener(movieEditor.getKeyInput());
			 movieEditor=null;
		 }
		 
		 if(mainMenu!=null){
			 this.removeMouseListener(mainMenu);
			 this.removeMouseMotionListener(mainMenu);
			 this.mainMenu=null;
		 }
		 
		 if(characterMenu!=null){
			 this.removeMouseListener(characterMenu);
			 this.removeMouseMotionListener(characterMenu);
			 this.removeKeyListener(characterMenu.getKeyInputer());
			 this.characterMenu=null;
		 }
		 
		
		
		
		window.startLoading();

		String tpath = "Levels/movies.txt";
		TranzlateCharacters tct= new TranzlateCharacters();
		tct.GenerateCharactersMovment(tpath);
		Characters.setCharacters(tct.getCharacterMovments());
		ColorList colorsList = new ColorList();
		System.out.println(this.getClass().toString() + " moveis properties " + character + " " + movie +" " + path );
		movieEditor = new MovieEditor(character,movie, path);
		this.addMouseListener(movieEditor);
		this.addMouseMotionListener(movieEditor);
		this.addKeyListener(movieEditor.getKeyInput());
		this.addMouseWheelListener(movieEditor.getWeelInput());
		
		this.state= state;
		loadingGif.stop();
	 this.start();
		
	}
	
	public void CloseQuestion(){
		this.removeMouseListener(this.question);
		this.removeMouseMotionListener(this.question);
		this.removeKeyListener(this.question.getKeyInputer());
		this.question = null;
		
		 if(mapEditor!=null){
			 this.addMouseListener(mapEditor.getMouseListener());
	
		 }
		 if(movieEditor!=null){
			 this.addMouseListener(movieEditor);
			 this.addMouseMotionListener(movieEditor);
			 this.addKeyListener(movieEditor.getKeyInput());
		 }
		 
		 if(mainMenu!=null){
			 this.addMouseListener(mainMenu);
			 this.addMouseMotionListener(mainMenu);
		
		 }
		 
		 if(characterMenu!=null){
			 this.addMouseMotionListener(characterMenu);
			 this.addMouseListener(characterMenu);
			 this.addKeyListener(characterMenu.getKeyInputer());
		 }
	}
	
	public void OpenQuestion(String question,QeustionAble object){
		this.question = new QeustionMenu(this, question,object);
		this.addMouseListener(this.question);
		this.addMouseMotionListener(this.question);
		this.addKeyListener(this.question.getKeyInputer());
		
		 if(mapEditor!=null){
			 this.removeMouseListener(mapEditor.getMouseListener());
	
		 }
		 if(movieEditor!=null){
			 this.removeMouseListener(movieEditor);
			 this.removeMouseMotionListener(movieEditor);
			 this.removeKeyListener(movieEditor.getKeyInput());
		
		 }
		 
		 if(mainMenu!=null){
			 this.removeMouseListener(mainMenu);
			 this.removeMouseMotionListener(mainMenu);
		
		 }
		 
		 if(characterMenu!=null){
			 this.removeMouseListener(characterMenu);
			 this.removeMouseMotionListener(characterMenu);
			 this.removeKeyListener(characterMenu.getKeyInputer());
			
		 }
	}
	
	
	
	
	
	
	
	
	

}
