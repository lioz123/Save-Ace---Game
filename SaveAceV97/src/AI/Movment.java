package AI;

import GameObjects.Side;

public class Movment {
	public Side side;
	 public int time;
	 
	 public Movment(Side side,int time){
		 this.time=time;
		 this.side=side;
	 }
	 
	 public int getTime(){
		 return this.time;
	 }
	 
	 public Side getside(){
		 return this.side;
	 }
	 
	 public void setTime(int time){
		 this.time=time;
	 }
	

}
