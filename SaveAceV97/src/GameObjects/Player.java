package GameObjects;

import java.awt.AlphaComposite;
import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector.Characteristics;

import General.Film;
import General.FilmMannager;
import General.ImageData;
import General.ImageLoader;

import General.SpriteSheet;
import Main.Game;
import Main.KeyInput;
import MapTrazlation.CharacterMovments;
import MapTrazlation.Characters;
import MapTrazlation.FilmList;
import MapTrazlation.SpriteSheetList;
import MoiveEditor.MovieEditor;
import Views.ItemsInventory;
import Views.Screen;
import Views.Slot;

public class Player extends GameObject{

    public int hp=80;
	public int stamina =80;
	private SpriteSheet s;
	private BufferedImage skin;
	private int formerX,formerY;
	private ObjectHandler objectHandler;
	private Weapon jetPistol;
	private FilmMannager walking,jetPistolFilm,stands,bazuka;
	private Film walkingRight;
	private ImageData imageData;
	private FilmMannager currentFilm;
	private MovementData movment = null;
	private Rectangle bounds ;
	private ArrayList<MovementData> movments = new ArrayList<MovementData>();
	
	
	
	public Player(int x, int y, int width, int height, ID id,ObjectHandler objectHandler) {
		super(x, y, width, height, id);
		imageX=45;
		super.imageY=40;
		imageWidth=30;
		imageHeight=38;
		this.objectHandler=objectHandler;
		 s = SpriteSheetList.luffy;
	    super.img = s.getImage();
	    skin = s.grabImage(imageX, imageY, imageWidth, imageHeight);
	    formerX=x;
	    formerY=y;
	    items = new ArrayList<Item>();
	    defultImageHeight=imageHeight;
	    defultImageWidth=imageWidth;
	    defultRImageY=120;
	    attackImageLX=230;
	    attackImageRX=712;
	    defultWidth=width;
	    defultLImageY=40;
	    side=Side.left;
	    jetPistol=WeaponShop.getJetPistol(s);
	    jetPistol.setUser(this);
	    buildFilms();
	    buildAttacks();
	    buildInventory();
	    bounds = new Rectangle(x,y,width,height);
	}
	
	
	
	
	
	public void buildInventory(){
	    BufferedImage inventoryBackGround = new ImageLoader().loadImage("/grayBackGroundRoundBorder.png");
	    SpriteSheet simbols=new SpriteSheet(new ImageLoader().loadImage("/simbolsSpriteSheet2.png"));
	    Screen screen= new Screen(new Color(217, 217, 217),30,30,30,30);
	    screen.setTransperent(0.5);
	    Slot slot = new Slot(screen);
	    slot.getScreen().setBorder(Color.black, 4);
	    Screen backGround=new Screen(new Color(56, 56, 41),x,y,width,height);
	    Screen luffy=new Screen(s.grabImage(imageX, imageY, imageWidth, imageHeight),40,40,150,200);
	   
	    luffy.setBorder(new Color(140, 140, 140), 4);
	    luffy.getBorder().setBorder(Color.BLACK, 4);
	    Screen headScreen =new Screen(simbols.grabImage(5,5,500,500),40,40,40,40);
	    Screen chestScreen = new Screen(simbols.grabImage(505,505,500,500),40,40,40,40);
	    Screen legsScreen = new Screen(simbols.grabImage(505,5,500,500),40,40,40,40); 
	    Screen shoesScreen = new Screen(simbols.grabImage(5,1015,500,500),40,40,40,40);
	    Screen fistScreen =new Screen(simbols.grabImage(5,505,500,500),40,40,40,40);
	    
	    headScreen.setBorder(Color.GRAY, 2);
	    headScreen.getBorder().setBorder(Color.black, 4);
	    chestScreen.setBorder(Color.GRAY, 2);
	    chestScreen.getBorder().setBorder(Color.black, 4);
	    legsScreen.setBorder(Color.GRAY, 2);
	    legsScreen.getBorder().setBorder(Color.black, 4);
	    shoesScreen.setBorder(Color.GRAY, 2);
	    shoesScreen.getBorder().setBorder(Color.black, 4);
	    fistScreen.setBorder(Color.GRAY, 2);
	    fistScreen.getBorder().setBorder(Color.black, 4);
	    
	    Slot headSlot=new Slot(headScreen);
	    Slot chestSlot=new Slot(chestScreen);
	    Slot legsSlot = new Slot(legsScreen);
	    Slot shoesSlot=new Slot(shoesScreen);
	    Slot fistSlot=new Slot(fistScreen);
	    
	    ArrayList<Slot> simbolsArray=new ArrayList<Slot>();
	    simbolsArray.add(headSlot);
	    simbolsArray.add(chestSlot);
	    simbolsArray.add(legsSlot);
	    simbolsArray.add(shoesSlot);
	    inventory = new ItemsInventory(new Color(191, 191, 191), Game.WIDTH/2+-300, Game.HEIGHT/2-200, 600, 400);
	    
	    Screen line =new Screen(Color.BLACK,1,1,2,inventory.getScreen().getHeight()-100);
	    line.setTransperent(0.5);
	   inventory.setBackGround(Color.black, 4);
	   inventory.getScreen().setTransperent(0.5);
	   inventory.getScreen().setBorderTransperent(0.5);
	   inventory.slotsAreaWithMarginPrecent(slot, 4, 1, 3, 5, 10, 10);
	   inventory.slotsAreaWithMarginPrecent(simbolsArray, 52 ,15, 10,Side.vertical);
	   inventory.addScreenWithMargin(line,inventory.getScreen().getWidth()/2,(inventory.getHeight()-line.getHeight())/2);
	   inventory.addScreenWithMarginPrecent(luffy, 61, 15);
	   inventory.slotsAreaWithMarginPrecent(fistSlot,88,55, 1, 1, 1, 1);
	   
	/*   inventory.slotsAreaWithMargin(slot,10,10,3,5,10,10);
	   inventory.slotsAreaWithMargin(headSlot,inventory.getWidth()/2+10,100,4, 1,10,10);
	   inventory.addScreenWithMargin(line,inventory.getScreen().getWidth()/2,50);
	   inventory.addScreenWithMargin(luffy, 370, 100);
	   */
	}

	

	@Override
	public void render(Graphics2D g2d) {
		if(imageData!=null){
			
		
	//	data = walking.getFrame();
		this.imageX=imageData.getImageX();
		this.imageY= imageData.getImageY();
		this.imageWidth=imageData.getImageWidth();

		skin = s.grabImage(imageData.getImageX(),imageData.getImageY(),imageData.getImageWidth(),imageData.getImageHeight());
		g2d.setColor(Color.black);
		
		double vx,vy,vw,vh,dh,dw,fx,fy,fw,fh,nw=47,nh=47,cw;
		vx = x;
		vy= y;
		vh= height;
		fh = imageHeight;
		fw = imageWidth;
		
		dw = nw/fw;
		vw = 60.0/dw;
		double divx = getDiv(imageData.getImageWidth(),imageData.getDeltaX());
	
	

		//	g2d.fillRect(x,y,width,height);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.1));
		g2d.fillOval(x-10,y+height-30,(int)vw,50);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		
		
		if(side == Side.left){
			vx = x+width;
			vw = - vw;
	
		}else{
			vx =x;	
		}
		double dx = vw*divx;
		vx-=dx;
		if(startAttackCounter){
			
		    g2d.drawImage(skin,(int) vx, y,(int)vw,height,null);
		     
		}else{
		//	System.out.println("drowing skin");
		
	//		System.out.println("x,y,width,height : " + vx +" " + y +" " + vw + " " + height );
		     g2d.drawImage(skin,(int)vx, y,(int)vw,height,null);
		 	bounds = new Rectangle((int)vx,y,(int)vw,height);
		//     g2d.drawRect(x,y,(int)vw,(int)vh);

		}
     if(attack!=AttackID.none){
   // 	getAttackDataByAttackID(attack).getWeapon().render(g2d);
    	
     }
		}
   
	}
	
	private void buildFilms(){
		CharacterMovments movments = Characters.getCharacter("luffy");
		
		for(FilmMannager film: movments.getFilms()){
	
			if(film.getName().equals("run")){
				this.walking=new FilmMannager(new FilmMannager(film));
				System.out.println(this.getClass() + " walking image size " +walking.size());
			
			}else if(film.getName().equals("stand")){
				this.stands= new FilmMannager(new FilmMannager(film));
				
			}
			else{
				//film.setFps(5 );
				MovementData data = new MovementData(new FilmMannager(film));
				this.movments.add(data);
			}
			
		}
		
		
		
		
		
		
/*		
		  walkingRight = new Film();
		 Film walkingLeft=new Film();
		 Film jetPistolRight=new Film();
		 Film jetPistolLeft=new Film();
		 Film standsRight = new Film(FilmList.luffyStands);
		 Film standsLeft = new Film(FilmList.luffyStands);
		 Film bazukaRight = new Film(FilmList.luffyBazuka);
		 Film bazukaLeft = new Film(FilmList.luffyBazuka);
		int imageX=100;
		int imageY=120;
		int imageWidth=30;
		int imageHeight=38;
		int jetPistolImageLX=230;
	    int jetPistolImageRX=630;
	    int jetPistolImageY=0;
	//	walkingRight.buildFilm(imageX, imageY, imageWidth, imageHeight, 4, 40, 0);
	//    walkingRight = FilmList.luffyRun;
		
	    walkingRight = new Film(FilmList.luffyRun);
	    if(walkingRight==null){
	    	System.out.println("walkingRight is null");
	    }else{
	    	System.out.println("walking right size = " + walkingRight.getFrames().size());
	    }
	    imageY=40;
	//	walkingLeft.buildFilm(imageX, imageY, imageWidth, imageHeight, 4, 40, 0);
		jetPistolFilm= new FilmMannager(jetPistolImageRX+3,jetPistolImageLX-3,imageY+3,imageWidth+3,imageHeight,3,40,0);
		jetPistolFilm.setFps(12);
		walkingLeft= new Film(walkingRight);
		walking=new FilmMannager(walkingRight,walkingLeft);
		stands = new FilmMannager(standsRight,standsLeft);
	//	this.bazuka = new FilmMannager(bazukaRight,bazukaLeft);
	//	System.out.println("stands size is " +standsRight.getFrames().size());
		*/
	}
	
	public double getDiv(double imageWidth, double imageDeltaWidth){
		double div = imageDeltaWidth/imageWidth;
		return div;
	}
	
	public void buildAttacks(){
//		MovementData jetPistolData =new MovementData(AttackID.jetPistol,jetPistol,jetPistolFilm,width+4,height);
	//	attacksData.add(jetPistolData);
	//	MovementData bazuka = new MovementData(AttackID.jetBazoka,jetPistol,this.bazuka,40,40);
	//	attacksData.add(bazuka);
	}
	
	
	
	


	@Override
	public void tick() {
	
	//	jetPistol.tick();
		movmentHandler();
		
	//	attackHandler();
		
		imageHandler();
		
		
	}
	
	public void attackHandler(){
/*
		if(isAttacking){
			if(getAttackDataByAttackID(attack).isFinished()){
				getAttackDataByAttackID(attack).getFilm().reset();
				this.setAttack(AttackID.none);
				this.isAttacking=false;
				this.attackTimCtn=0;
				this.startAttackCounter=false;
				
			}
	//		startAttackCounter=true;
		}
		
/*		if(startAttackCounter){
			this.attackTimCtn++;
		}
		if(this.attackTimCtn>50){
			this.setAttack(AttackID.none);
			this.isAttacking=false;
			this.attackTimCtn=0;
			this.startAttackCounter=false;
			
		}
	
		*/
		/*
		for(MovementData data:attacksData){
		
			if(data.getAttackID()!=attack){
				data.resetFilms();
			}
			else{
				data.resetOneFilm(side);
			}
		}
	*/
	}
	
	public void KeyInput(KeyEvent e){

		System.out.println("key pressed");
		this.key=e;
		int key = e.getKeyCode();
		if(key==KeyEvent.VK_A||key==KeyEvent.VK_W||key==KeyEvent.VK_D||key==KeyEvent.VK_S){
			return;
		}
		if(isAttacking){
			return;
		}
		for(MovementData movment:movments){
			if(movment.activate(e)){
				this.movment=movment;
				isAttacking=true;
				System.out.println(this.getClass() + "Activate attack");
			}
		}
		
	}
	
	
	
	public void movmentHandler(){
		this.x += speedX;
		this.y+=speedY;
		if(collision()){
			x=formerX;
			y=formerY;
		}
		else{
			formerX=x;
			formerY=y;
		}
		

	

		
	}

	
	
	
	public void imageHandler(){
if(isAttacking){
	System.out.println(this.getClass() + " attacking");
	if(!movment.getFilm().isFinished()){
		
		System.out.println(this.getClass() +  " attack is not finsihed");
		
	}else{
		this.movment.getFilm().reset();
		this.movment.getFilm().setFinished(false);
//		this.movment=null;
		this.isAttacking=false;
	

	}
	
}
		if(!isAttacking){
			if(speedX!=0||speedY!=0){
				this.imageData= walking.run(side);
				System.out.println("image data properties are + " + imageData.getFrame());
				//System.out.println("walking size is " + walking.size());
				//System.out.println("walking frame is " + walking.getFrame().getDeltaX() + " "+ walking.getFrame().getDeltaY()+" " +walking.getFrame().getImageWidth()+" " + walking.getFrame().getImageHeight());
				this.stands.reset();
			}else{
		walking.reset();
		this.imageData=stands.run(side);
			}
		}else{
			
		
		this.imageData = movment.getFilm().run(side);
		//	System.out.println("attacking " + data.getAttackID());
			this.stands.reset();
			this.walking.reset();
			
		}
		
	//	System.out.println("attacking = " + isAttacking);
	
	
		/*F
		MovementData data = getAttackDataByAttackID(attack);	
		if(data==null){
			return;
		}
			boolean moving = false;
			if(speedX!=0||speedY!=0)moving=true;
			if(startAttackCounter){
				setImage(data.runFilm(side));
			}
			else if(moving){
			walking.resetOneFilm(side);
			setImage(walking.run(side));
			}
			else {
			walking.reset(); 
			setImage(walking.stop(side));
			}
			resetAttacksFilms();
		*/
		}
	
	
	
	
	public void setRoom(ObjectHandler objectHandler){
		this.objectHandler=objectHandler;
	}
	
	public boolean collision(){
        List<GameObject> objects = (List<GameObject>) objectHandler.getObjects();  
			for(GameObject object:objects){
				if(object.id ==ID.solid||object.id==ID.chest){
					if(object.getBounds().intersects(this.getBounds())){
						
						return true;
					}
				}
			}
			return false;
	}
	
	public GameObject getCollisionObject(){
        List<GameObject> objects = (List<GameObject>) objectHandler.getObjects();  
			for(GameObject object:objects){
				if(object.id ==ID.solid||object.id==ID.chest){
					if(object.getBounds().intersects(this.getBounds())){
						
						return object;
					}
				}
			}
			return null;
	}
	
	public void updateHp(int x){
		hp+=x;
	}
	
	public void updateStamina(int x){
		stamina+=x;
	}



	
	

}
