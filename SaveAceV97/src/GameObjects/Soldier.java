package GameObjects;

import java.awt.AlphaComposite;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.omg.Messaging.SyncScopeHelper;
import org.omg.PortableInterceptor.DISCARDING;


import AI.FieldOfView;

import AI.RectangleNode;
import General.Film;
import General.FilmMannager;
import General.ImageData;
import General.SpriteSheet;
import Views.Bar;

public class Soldier extends Mob {
	
	private FilmMannager punchFilm;
	public Soldier(int x, int y, int width, int height, ID id, ObjectHandler objectHandler, SpriteSheet ss) {
		super(x, y, width, height, id, objectHandler, ss);
		imageWidth=30;
		imageHeight=39;
		imageX=8;
		imageY=17+39;
		 this.img=ss.grabImage(imageX,imageY,imageWidth,imageHeight);
		 this.ss=ss;
		 speed = 1; 
		 this.attackImageLX=320;
		 this.attackImageLY=35;
		 defultImageHeight=imageHeight;
		 defultImageWidth=imageWidth;
		 defultLImageX=imageX;
		 defultLImageY=17+39;
		 defultRImageX=imageX;
		 defultRImageY=17+39*3;
		 weapon = WeaponShop.getPuch(ss);
		 this.addEnemy(ID.player);

		 this.attackRange=70;
		 buildFilms();
		 buildAttacksData();
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void buildFilms() {
		Film walkingRight =new Film();
		Film walkingLeft =new Film();
		Film attackingRight = new Film();
		Film attackingLeft = new Film();
		int imageX=8;
		int imageY=17+39*3;
		int imageWidth=30;
		int imageHeight=39;
		walkingRight.buildFilm(imageX,imageY,imageWidth,imageHeight,5,40,0);
		imageY=17+39;
		walkingLeft.buildFilm(imageX,imageY,imageWidth,imageHeight,5,40,0);
		walking= new FilmMannager(walkingRight,walkingLeft);
		imageHeight=38;
		imageY=44;
		imageX=596;
	    attackingRight.addImage(new ImageData(imageX+49+30,imageY, imageWidth+9, imageHeight));
		attackingRight.buildFilm( imageX,imageY, imageWidth+9, imageHeight, 3, -49, 0);
		imageX=286;
		attackingLeft.addImage(new ImageData(imageX-80,imageY,imageWidth+9,imageHeight));
		attackingLeft.buildFilm( imageX,imageY, imageWidth+9, imageHeight, 3, 49, 0);
		punchFilm= new FilmMannager(attackingRight,attackingLeft);
		punchFilm.setFps(15);
		healthBar = new Bar(x,y-deltaBar,width,7,100);
		deltaBar=7;
		health=50;
		attackingRight.setFps(5);
		attackingLeft.setFps(5);
	}
	
	@Override
	public void buildAttacksData() {
		MovementData punch = new MovementData(AttackID.punch,weapon,punchFilm,width+14,height);
		attacksData.add(punch);
	}

	
	

	@Override
	public void render(Graphics2D g2d) {
		
		if(startAttackCounter){
			g2d.drawImage(img,x,y,this.getAttackDataByAttackID(attack).getWidth(),height,null);
		}
		else{
			g2d.drawImage(img,x,y,width,height,null);
		}
		if(pathNode!=null)
		for(RectangleNode rec:pathNode){
			g2d.setColor(Color.red);
			g2d.drawRect(rec.getX(), rec.getY(),Map.BLOCKSIZE,Map.BLOCKSIZE);
		}
		
		 healthBar.render(g2d);
		 
	//	ArrayList<Rectangle> paths = path.getFieldOfPath();
		//FieldOfView view = new FieldOfView(new Point(x,y),objectHandler,side);
//		view.closestObject(objectHandler, enemies);
	//	ArrayList<Rectangle> recs = view.getFieldOfView();
		g2d.setColor(Color.green);
		 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.1));
	/*	for(Rectangle rec:recs){
			g2d.fill(rec);
		}
		*/
		
		 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		 
		 
	}

	@Override
	public void tick(){
	EnemiesInRoom();
	serchEnemy();
	creatEnemyTrail();
	MovmentMannager();		
	updateSerching();
	updateLocation();
	fixCollisionSolid();
	updateSide();
	attackHandler();
	checkMoving();
	updateImage();		
	updateBar();
//	System.out.println("imageX at the end "  + imageX  );
//	System.out.println("speedX " + speedX) ;
	}

	@Override
	public void getHit(int demage) {
	demage = demage - shield;
	if(demage>0){
		health-=demage;
	}
	
	}
	
	


	

}
