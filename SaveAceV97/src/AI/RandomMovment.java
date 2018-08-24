package AI;

import java.util.ArrayList;
import java.util.Random;

import GameObjects.Side;

public class RandomMovment {
	
	public Movment movemnt;
	Side side;
	public RandomMovment(){
		Random rnd = new Random();
		int time;
		switch (rnd.nextInt(5)) {
		case 0:
				side=Side.up;
		break;
		case 1: side=Side.left;
		break;
		case 2:side=Side.right;
		break;
		case 3:side=Side.down;
		break;
		case 4:side=Side.none;
		}	
		time = rnd.nextInt(200)+10;
		this.movemnt=new Movment(side, time);
	}
	public RandomMovment(ArrayList<Side> openSides){
		Random rnd = new Random();
		int time;
		int sideIndex =rnd.nextInt(openSides.size());
		side = openSides.get(sideIndex);
	
		time = rnd.nextInt(200)+10;
		this.movemnt=new Movment(side, time);
	}
	
	public Movment getMovement(){
		return this.movemnt;
	}

}
