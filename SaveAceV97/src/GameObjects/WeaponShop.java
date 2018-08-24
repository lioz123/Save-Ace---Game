package GameObjects;

import General.SpriteSheet;

public class WeaponShop {

	public static Weapon getJetPistol(SpriteSheet ss){
		
		Weapon jetPistol=new Weapon(40,30,AttackID.jetPistol,20,ss);
		    jetPistol.setImageHeight(25);
		    jetPistol.setImageWidth(50);
		    jetPistol.setImageLX(430);
		    jetPistol.setImageLY(5);
		    jetPistol.setImageRX(490);
		    jetPistol.setImageRY(5);
		    jetPistol.setDeltaY(20);
		    jetPistol.setDeltaRX(5);
		    jetPistol.setAttackTime(5);
		    jetPistol.setAttackSpeed(3);
		    jetPistol.setDistance(24);
		    jetPistol.createImage();
		    jetPistol.setAttackTime(50);
		    return jetPistol;
	}
	
	public static Weapon getPuch(SpriteSheet ss){
		Weapon punch=new Weapon(40,30,AttackID.punch,20,ss);
	    punch.setImageHeight(25);
	    punch.setImageWidth(50);
	    punch.setImageLX(300);
	    punch.setImageLY(5);
	    punch.setImageRX(320);
	    punch.setImageRY(5);
	    punch.setDeltaY(20);
	    punch.setDeltaRX(5);
	    punch.setAttackTime(5);
	    punch.setAttackSpeed(3);
	    punch.setDistance(24);
	    punch.createImage();
	    return punch;
	}

}
