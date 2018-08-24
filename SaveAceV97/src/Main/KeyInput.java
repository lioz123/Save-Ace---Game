package Main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import GameObjects.GameObject;
import GameObjects.ID;
import GameObjects.ObjectHandler;
import GameObjects.Side;
import GameObjects.AttackID;
import GameObjects.Chest;

public class KeyInput extends KeyAdapter{
public GameObject object;
public int speed=2, stop =0;
private boolean lock = false;
private ObjectHandler objectHandler;
public static boolean[]keyPressed;
private Game game;
public List<GameObject> objects;
	public KeyInput(GameObject object,ObjectHandler objectHandler,Game game){
	keyPressed = new boolean[4];
	this.objectHandler = objectHandler;
	
     for(int i=0; i<4; i++){
    	 keyPressed[i]=false;
     }
     this.game=game;
	 this.object = object;
	 objects = (List<GameObject>) objectHandler.getObjects();
 }
 
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_W: object.setSpeedY(-speed);keyPressed[0]=true;
		break;
		case KeyEvent.VK_S:  object.setSpeedY(speed);keyPressed[1]=true;
		break;
		case KeyEvent.VK_D:object.setSpeedX(speed);keyPressed[2]=true; 
		if(!object.isAttack())
		object.setSide(Side.right);
		break;
		case KeyEvent.VK_A:object.setSpeedX(-speed);keyPressed[3]=true;
		if(!object.isAttack()) 
			object.setSide(Side.left);
		
		break;
		case KeyEvent.VK_SHIFT: if(!lock) lock=true; else lock=false; break;
		case KeyEvent.VK_E:for(GameObject object:objects){
			
			if(object.id==ID.chest&& this.object.distance(object.x,object.y)<55){
			 ((Chest) object).Activation();
			 object.ItemTransfer(this.object);
			 
			}
		}  break;
		case KeyEvent.VK_I:object.useItem(ID.medkit); break;
		case KeyEvent.VK_J:object.useItem(ID.syring);break;
	//	case KeyEvent.VK_SPACE:if(!object.isAttack())object.setAttack(AttackID.jetPistol);break;
		case KeyEvent.VK_SPACE:
			object.KeyInput(e);
			
			break;
		case KeyEvent.VK_O
		:   if(object.getInventory()!=null)
			if(object.getInventory().isShow()){
				object.getInventory().show(false);;
		}
		else{
			 object.getInventory().show(true);
			
		}
		
		
		break;
		default:
			
			object.KeyInput(e);
			break;
		
	
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
    switch(key){
	case KeyEvent.VK_W: keyPressed[0]=false;
	break;
	case KeyEvent.VK_S: keyPressed[1]=false;
	break;
	case KeyEvent.VK_D:keyPressed[2]=false;
	break;
	case KeyEvent.VK_A:keyPressed[3]=false;
	break;
		}
    if(!keyPressed[0]&&!keyPressed[1]){
    	object.setSpeedY(stop);
    }
    
    if(!keyPressed[2]&&!keyPressed[3]){
    	object.setSpeedX(stop);
    }
    	
	}

}
