package GameObjects;
import AI.FieldOfView;

import AI.Movment;
import AI.PathFinder;
import AI.RandomMovment;
import AI.RectangleNode;
import AI.StraitPath;
import General.FilmMannager;
import General.SpriteSheet;
import Views.Bar;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.SynchronousQueue;

import javax.print.attribute.standard.Sides;
import javax.swing.text.Utilities;
import javax.swing.text.StyledEditorKit.ForegroundAction;
public abstract class Mob extends GameObject {

	public int hp,formerX,formerY,speedX=0,speedY=0,moveSpeed =1;
	public Weapon weapon;
	public ObjectHandler objectHandler;

	protected ArrayList<Point> foremLineOfSight;
	protected int speed;
	protected int tickCounter =0;
	protected boolean sawEnemy = false,isMoving =false;
	protected Side side = Side.right;
	protected GameObject enemy;
	protected Movment movment;
	protected boolean isOwnPath =false;
	protected ArrayList<Point> enemyTrail=new ArrayList<Point>();
	protected int enemyNotOnSightCounter =0;
	protected boolean isAttacking =false;
	protected boolean isUnderAttack=false;
	protected boolean enemyOnView = false;
	protected int attackRange =15;
	protected FilmMannager walking;
	protected Bar healthBar;
	protected int health;
	protected int shield;
	protected int deltaBar=10;
	private ObjectHandler room=null;
	protected MovementData dying;
	ArrayList<ID> enemiesId = new ArrayList<ID>();
	ArrayList<GameObject> enemies = new ArrayList<GameObject>();
	ArrayList<GameObject> enemiesOnSight = new ArrayList<GameObject>();
	protected ArrayList<RectangleNode> pathNode=new ArrayList<RectangleNode>();
	

	public Mob(int x, int y, int width, int height,ID id,  ObjectHandler objectHandler ,SpriteSheet ss) {
		super(x, y, width, height, id);
		
		this.objectHandler=objectHandler;
	}
	public void addEnemy(ID enemy){
		this.enemiesId.add(enemy);
	}
	public abstract void attack();
	public abstract void getHit(int demage);
	public void serch(){
	EnemiesInRoom();
	
	}
	
	public abstract void buildFilms();
	public abstract void buildAttacksData();
	public void updateBar(){
		healthBar.setCapasity(health);
		healthBar.setX(x);
		healthBar.setY(y-deltaBar);
	}
	public void EnemiesInRoom(){
		ArrayList<GameObject> objects = (ArrayList<GameObject>) objectHandler.getObjects();
		for(GameObject object:objects){
			for(ID id: enemiesId){
				if(object.id==id){
					boolean isEnemy=false;
					for(GameObject enemy:enemies){
						if(enemy.equals(object)){
							isEnemy=true;
						}
					}
					if(!isEnemy)
					enemies.add(object);
				}
			}
		}
	}
	
	public void setRoom(ObjectHandler room){
		this.room=room;
		this.objectHandler=room;
	}
	
	public void creatEnemyTrail(){
		ArrayList<Point> tempTrail = new ArrayList<Point>();
		ArrayList<Point> deletePoint = new ArrayList<Point>();
		int counter =0;
		
		for(GameObject object:enemiesOnSight){
			if(enemy==object){
				enemyTrail.add(new Point(enemy.getX(),enemy.getY()));
			}
		}
			
			for(Point point:enemyTrail){
				boolean pointExist =false;
				for(Point tempPoint:tempTrail){
					if(tempPoint.distance(point)<5){
						pointExist =true;
					}
				}
				if(!pointExist){
					tempTrail.add(point);
				}
				
			}
			
			
	for(Point point:deletePoint){
		tempTrail.remove(point);
	}
			
			enemyTrail=tempTrail;
			while(enemyTrail.size()>7){
				enemyTrail.remove(0);
			}
			
			while(counter<enemyTrail.size()){
				if(enemyTrail.get(counter).getX()==x&&enemyTrail.get(counter).getY()==y){
					enemyTrail.remove(counter);
				}
				counter++;
			}	
	}
	
	public void MovmentMannager(){
		if(sawEnemy){
			if(enemiesOnSight.size()>0)
			enemy = closestObject(enemiesOnSight);
			chase(enemy);
			
		}else{
			this.randomMovment();
			this.Move();
			ArrayList<Side> openSides = new ArrayList<Side>();
			openSides.add(Side.up);
			openSides.add(Side.down);
			openSides.add(Side.left);
			openSides.add(Side.right);
			openSides.add(Side.none);
			while(WillTouchSolid(x+speedX,y+speedY)&&openSides.size()>1){
				openSides.remove(movment.getside());
				newRandomMovment(openSides);
				this.Move();
			}
			
		}
	}
	
	
	public void serchEnemy(){
		if(enemies.size()==0)
			return;
		FieldOfView view = new FieldOfView(new Point(x,y),objectHandler,side);
		enemiesOnSight=view.getTargetsOnSight(enemies);
	
		if(enemiesOnSight.size()>0){
			sawEnemy=true;
			enemyOnView = true;
		}
		else{
			enemyOnView=false;
		}
		
	}
	
	public void bestEnemy(){
		this.enemy = closestObject(enemiesOnSight);
		
	}
	
	public void setSide(Side side){
		this.side=side;
	}
	
	public void setMove(boolean ismove){
		this.isMoving=ismove;
	}
	
	
	
	public void chase(GameObject object){
	  if(true){
	  		PathFinder paths=new PathFinder(this.room.getMap());	
	  		Block start =objectHandler.getBlockByObject(this);
	  		Block end= objectHandler.getBlockByObject(object);
	  		RectangleNode nextMove = paths.getNextMove(start,end );
	  		if(nextMove!=null){
	  			Point target = new Point(nextMove.getX(),nextMove.getY());			
				if(x>target.getX()) {speedX=-moveSpeed;}
				else if(x<target.getX()) speedX=moveSpeed;
				else speedX =0;
				if(y<target.getY()) speedY=moveSpeed;
				else if(y>target.getY()) speedY=-moveSpeed;
				else speedY =0;
	  		}else{
	  			sawEnemy=false;
	  		}
			
		}
	}
	
	public GameObject closestObject(ArrayList<GameObject> objects){
		if(objects.size()==0)
			return null;
		GameObject bestObject = objects.get(0);
		for(GameObject object: objects){
			if(bestObject.distance(x, y)<object.distance(x, y)){
				bestObject=object;
			}
		}
		return bestObject;
	}
	
	
	public void touchSolid(){
		ArrayList<GameObject> objects = (ArrayList<GameObject>) objectHandler.getObjects();
		for(GameObject object:objects){
			if(object.id==ID.solid||object.id==ID.chest){
				if(this.getBounds().intersects(object.getBounds())){
					int maxX=x+width;
					int maxY = y+height;
					boolean isXIntersects = false;
					boolean isYIntersects =false;
					Rectangle left = new Rectangle(x, y+1, 1, height-3);
					Rectangle up = new Rectangle(x+1, y, width-3, 1);
					Rectangle dawn = new Rectangle(x+1,maxY, width-3, 1);
					Rectangle right = new Rectangle(maxX,y+1,1, height-3);
					
					if(left.intersects(object.getBounds())||right.intersects(object.getBounds())){
						this.x=formerX;
						isXIntersects=true;
					}
					 if(up.intersects(object.getBounds())||dawn.intersects(object.getBounds())){
						this.y=formerY;
						isYIntersects=true;
					}
					 if(!isXIntersects){
						 formerX=x;
					 }
					 if(!isYIntersects){
						 formerY=y;
					 }
					
			
				}
			}
		}
	}
	
	public boolean isTouchingSolid(){
		ArrayList<GameObject> objects = (ArrayList<GameObject>) objectHandler.getObjects();
		for(GameObject object:objects){
			if(object.id==ID.solid||object.id==ID.chest){
				if(this.getBounds().intersects(object.getBounds())){
					return true;
				}
			}
		}
		return false;
	}
	public boolean WillTouchSolid(int x, int y){
		ArrayList<GameObject> objects = (ArrayList<GameObject>) objectHandler.getObjects();
		for(GameObject object:objects){
			if(object.id==ID.solid||object.id==ID.chest){
				Rectangle rec = new Rectangle(x,y,width,height);
				if(rec.intersects(object.getBounds())){
					
					return true;
				}
			}
		}
		return false;
	}
	
	public void randomMovment(){
		if(this.movment==null||this.movment.getTime()<=0){
			RandomMovment rndMovment = new RandomMovment();
			this.movment=rndMovment.getMovement();
		}
	}
	
	public void newRandomMovment(){
			RandomMovment rndMovment = new RandomMovment();
			this.movment=rndMovment.getMovement();
	}
	public void newRandomMovment(ArrayList<Side> openSides){
		RandomMovment rndMovment = new RandomMovment(openSides);
		this.movment=rndMovment.getMovement();
}
	
	
	public void Move(){
		if(movment==null)
			return;
		this.movment.setTime(this.movment.getTime()-1);
		switch (movment.getside()) {
		case up:
			speedX=0;
			speedY=-speed;
			break;
		case down:
			speedX=0;
			speedY=speed;
			break;
		case left:
			speedY=0;
			speedX=-speed;
			break;
		case right:
			speedY=0;
			speedX=speed;
			break;
		default:
			speedY=0;
			speedX=0;
			break;	
		}
		
		
	}
	
	public void OwnPath(boolean randomMovment){
	this.isOwnPath=randomMovment;
	}
	
	public void updateSerching(){
	if(enemiesOnSight.size()==0){
		enemyNotOnSightCounter++;
		if(enemyNotOnSightCounter>200){
			enemyNotOnSightCounter=200;
		}
	}
	else{
		enemyNotOnSightCounter=0;
	}
	
/*	if(enemiesOnSight.size()==0&&enemyTrail.size()==0||enemyNotOnSightCounter>=200){
		sawEnemy=false;
	}
	*/
	}

	public void updateLocation(){
		if(!isAttackLock()){
			x+=speedX;
			y+=speedY;
		}
	
	}
	
	public void updateSide(){
		
		if(isAttacking){
			speedX=0;
		}
	
		if(speedX==-speed){
		if(!startAttackCounter)
			imageY=17+39;
			else{
				
			//	imageY=getAttackDataByAttackID(attack).getImageLY();
			//	imageX=getAttackDataByAttackID(attack).getStartLX();
				
			}
			side=Side.left;
		}
		else if(speedX==speed){
			
			if(!startAttackCounter)
			imageY=17+39*3;
			else{
				
			//	imageY=getAttackDataByAttackID(attack).getImageRY();
				//imageX=getAttackDataByAttackID(attack).getStartRX();
				
			}
			side=Side.right;
			
		}
		
	}
	
	public void attackHandler(){
		
		
		if(enemy!=null&&distance(enemy)<attackRange&&!startAttackCounter){
			setAttack(AttackID.punch);
			startAttackCounter=true;

		}
		
		if(attack==AttackID.none)
			return;
		
		
		if(startAttackCounter){
			attackTimCtn++;
		}
		if(attackTimCtn>=50){
			setAttack(attack.none);
			setAttackLock(false);
			attackTimCtn=0;
			startAttackCounter=false;
		}
		
		
		
		
	}
	
	public void fixCollisionSolid(){
		if(!isTouchingSolid()){
			formerY=y;
			formerX=x;
		}
		else{
			touchSolid();	
		}
	}
	
	public void checkMoving(){
	
		if(speedX==0&&speedY==0){
			isMoving=false;
		}else{
			isMoving=true;
		}
	}
	
	public void updateImage(){
		if(startAttackCounter){
			MovementData data= getAttackDataByAttackID(attack);
			setImage(data.runFilm(side));
		}
		else if(isMoving){
			setImage(walking.run(side));
		
		
		}else{
			setImage(walking.stop(side));
		}
		img=ss.grabImage(imageX, imageY, imageWidth, imageHeight);
			
	}
	public GameObject getSolidObject(){
		for(GameObject object:((ArrayList<GameObject>)objectHandler.getObjects())){
			if(object.getBounds().intersects(getBounds())&&object.id==ID.chest||object.id==ID.solid)
				return object;
		}
		return null;
	}
}
