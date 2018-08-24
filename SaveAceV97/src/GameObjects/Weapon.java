package GameObjects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import General.SpriteSheet;

public class Weapon {
	public AttackID id;
	public ID userId;
	public int damage,width,height;
	public Rectangle rec;
	protected SpriteSheet ss;
	protected boolean isAttacking;
	protected int attackTime;
	protected int attackTicks=0,x,y;
	protected GameObject user;
	protected int attackSpeed=1;
	protected int imageLX=0;
	protected int imageLY=0;
	protected int imageRX=0;
	protected int imageRY=0;
	protected int imageWidth=0;
	protected int imageHeight=0;
	protected BufferedImage imgR;
	protected BufferedImage imgL;
	protected int deltaY=0,deltaRX=0,deltaUser=0,deltaLX=0;
	protected int distance = 1000;

	
	
	public Weapon(GameObject user,int width, int height,AttackID  id,int damage,Rectangle rec,SpriteSheet ss){
		
		this.id=id;
		this.userId=user.id;
		this.damage=damage;
		this.rec=rec;
		this.ss=ss;
		isAttacking = false;
		this.user=user;
		this.width=width;
		this.height=height;
		this.y=user.getY()+20;
		this.x=user.getX();		
		
	}
	
	public Weapon(int width, int height, AttackID id, int damage, SpriteSheet ss) {
		this.id=id;
		this.damage=damage;
		this.rec=rec;
		this.ss=ss;
		isAttacking = false;
		this.width=width;
		this.height=height;
		
	}
	public void setUser(GameObject user){
		this.userId=user.id;
		this.user=user;
		this.y=user.getY()+20;
		this.x=user.getX();		

		
	}

	public void setDeltaLX(int x){
		this.deltaLX=x;
	}
	
	public void setDistance(int d){
		this.distance=d;
	}
	
	public void setImageLX(int x){
		this.imageLX=x;
	}
	
	public void setDeltaRX(int x){
		this.deltaRX=x;
	}
	
	public void setImageLY(int x){
		this.imageLY=x;
	}
	
	public void setImageRX(int x){
		this.imageRX=x;
	}
	
	public void setImageRY(int x){
		this.imageRY=x;
	}
	
	
	public void setImageWidth(int x){
		this.imageWidth=x;
	}
	
	public void setImageHeight(int x){
		this.imageHeight=x;
	}
	
	public void setDeltaY(int y){
		this.deltaY=y;
	}
	
	public void createImage(){
		imgL =ss.grabImage(imageLX,imageLY,imageWidth,imageHeight);
		imgR =ss.grabImage(imageRX,imageRY,imageWidth,imageHeight);
	}
	
	
	
	public void setAttackSpeed(int x){
		this.attackSpeed=x;
	}
	
	
	public void tick(){
		attackTicks++;
		if(!isAttacking){
		if(user.side==Side.left)
		x=user.getX()-deltaLX;
		else{
			x= user.getX()+deltaRX ;
		}
		}
		else{
			
			if(user.getSide()==Side.left){
				attackSpeed=(int) -Math.sqrt(Math.pow(attackSpeed, 2));
				 
			}
			else{
				attackSpeed=(int) Math.sqrt(Math.pow(attackSpeed, 2));
			
			}
			
			if(attackTicks<attackTime){
				x+=attackSpeed+user.speedX;
				
			}
			else{
				System.out.println("stop attack");
				isAttacking=false;
				attackTicks=0;
			}
		}
		y=user.getY()+deltaY;
	}
	
	public void render(Graphics2D g2d){
		if(isAttacking){
			if(user.side==Side.left){
				g2d.drawImage(imgL,x,y,width,height,null);
			}else{
				g2d.drawImage(imgR,x,y,width,height,null);
			}
		}
	}
	
	
	public void setAttackTime(int x){
		this.attackTime=x;
	}
	
	public int getAttackTime(){
		
		return this.attackTime;
	}
	
	public void useWeapon(){
		if(!isAttacking){
			isAttacking = true;
			x=user.getX();
			y=user.getY();
		}
	
	}
	public Rectangle getBounds(){
	return new Rectangle(x,y,width,height);
	}
	
	
}
