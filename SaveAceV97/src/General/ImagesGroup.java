package General;

import java.util.ArrayList;

public class ImagesGroup {
	private ArrayList<ImageData> images = new ArrayList<ImageData>();
	private int attack,defence;
	private boolean isSolid;
	public ImagesGroup(){
		
	}
	
	public void setSolid(boolean solid){
		this.isSolid=solid;
	}
	
	public void setAttack(int attack){
		this.attack=attack;
	}
	
	public void setDefence(int defence){
		this.defence=defence;
	}
	
	public int getDefence(){
		return this.defence;
	}
	
	public int getAttack(){
		return this.attack;
	}
	
	public boolean isSolid(){
		return this.isSolid;
	}
	
	
	
	public void add(ImageData img){
		this.images.add(img);
	}
	
	public ImageData get(int i){
		return this.images.get(i);
	}
	
	public ArrayList<ImageData> getImages(){
		return images;
	}
	
	
	
	
	
	

}
