package GameObjects;

import java.awt.event.KeyEvent;

import General.FilmMannager;
import General.ImageData;

public class MovementData {
	private Weapon weapon;
	private FilmMannager film;
	private int width,height,deltaWidth,deltaHeight,attackTick,tickCounter;
	private AttackID id;
	private String name ;
	
	public MovementData(AttackID id ,Weapon weapon,FilmMannager film,int width, int height){
		this.film=film;
		this.weapon=weapon;
		this.width=width;
		this.height=height;
		this.id=id;
		deltaWidth=0;
		deltaHeight=0;
		attackTick = film.getFilmLeft().size()*film.getFilmLeft().getFps();
		tickCounter = 0;
	}
	
	
	

	
	
	public MovementData(FilmMannager film) {
		this.film=film;
		this.name= film.getName();
	}






	public void useWeapon(){
		this.weapon.useWeapon();
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public Weapon getWeapon(){
		return this.weapon;
	}
	
	public ImageData runFilm(Side side){
		return film.run(side);
	}
	
	public void resetFilms(){
		film.stop(Side.left);
		film.stop(Side.right);
	}
	
	public boolean isFinished(){
		if(tickCounter>attackTick){
			tickCounter = 0 ;
			return true;
		}
		tickCounter++;
		return false;
	}
	
	public void resetOneFilm(Side side){ // reset the film on the other side
	if(side==Side.right){
		film.stop(Side.left);
	}else{
		film.stop(Side.right);
	}
		
	}

	public AttackID getAttackID() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	
	public String getName(){
		return this.name;
	}

	public int getAttackTicks() {
		
		return film.getFps()*film.size();
	}






	public FilmMannager getFilm() {
		// TODO Auto-generated method stub
		return this.film;
	}






	public boolean activate(KeyEvent e) {
		System.out.println(this.getClass() + " attack actiavet  + " +  this.name);
		if((this.getFilm().getFilmRight().getKey()).equals(e.getKeyChar()+"")){
			return true;
		}
		return false;
	}





	
	

}
