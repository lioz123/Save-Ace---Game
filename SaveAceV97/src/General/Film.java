package General;

import java.util.ArrayList;

public class Film {
	ArrayList<ImageData> images;
	private int fps,counter,frameCounter,fpsOposite;
	private String key,path,specialKey;
	private boolean isFinished=false;
	public Film(){
		this.fps=10;
		frameCounter=0;
		this.counter=0;
		this.fpsOposite=60/fps;
		images=new ArrayList<ImageData>();
	}
	
	public void setFpsOposite(int fps){
		this.fpsOposite=fps;
	}
	
	public int getFpsOposite(){
		return this.fpsOposite;
	}
	
	public void setPath(String path){
		this.path=path;
	}
	public String getPath(){
		return this.path;
	}
	
	public Film(Film film){
		this.fps=film.getFps();
		this.images=film.getFrames();
		this.counter=0;
		this.frameCounter = 0;
		this.key=film.getKey();
		this.path=film.getPath();
		this.specialKey=film.getSpecialKey();
		this.fpsOposite=film.getFpsOposite();
		System.out.println(this.getClass() +" special key is " + specialKey);
	}
	
	public void setFps(int fps){
		double fpsOposite = 60.0/(double)fps;
		this.fps=(int)fpsOposite;
	}
	

	
	
	public void setFrames(ArrayList<ImageData> frames){
		this.images=frames;
	}
	
	public void addImage(ImageData image){
		images.add(image);
	}
	
	public ImageData run(){
		if(images.size()==0){
			return null;
		}
		counter++;
		if(counter>=fps){
			counter=0;
			frameCounter++;
		}
		if(frameCounter>images.size()-1){
			frameCounter=0;
			
		}
		if(frameCounter==images.size()-1){
			this.isFinished=true;
		}
		if(this.key!=null&&this.key.equals("h")){
			System.out.println(this.getClass() + " frame counter is  " + frameCounter + " images length " + images.size()) ;
			

		}
		
		
		
		return images.get(frameCounter);
	}
	
	public void setFrameCounter(int x){
		this.frameCounter=x;
	}
	
	public int getframeCounter(){
		return this.frameCounter;
	}
	
	public void buildFilm(int x,int y, int width,int height,int frames,int deltaX,int deltaY){
		for(int i=0; i<frames; i++){
		images.add(new ImageData(x,y,width,height));
		x+=deltaX;
		y+=deltaY;
		}	
	}

	public int getframesCount() {
		
		return frameCounter;
	}
	
	public int size(){
		return this.images.size();
	}

	public ImageData getFrame(int i) {
	
		if(i<images.size()){
		
			return images.get(i);
		}else{
			return null;
		}
		
	}
	
	public void stop(){
	
		this.frameCounter=0;
	}

	public int getFps() {
		// TODO Auto-generated method stub
		return fps;
	}

	public ArrayList<ImageData> getFrames() {
		// TODO Auto-generated method stub
		return images;
	}

	public ArrayList<ImageData> getImagesData() {
		// TODO Auto-generated method stub
		return this.images;
	}

	public void setKey(String key) {
	this.key=key;
		
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	public void setSpecialKey(String specialKey) {
		this.specialKey=specialKey;
	}
	
	public String getSpecialKey(){
		return specialKey;
	}
	
	public boolean isFinished(){
		return isFinished;
	}

	public void setFinished(boolean b) {
		this.isFinished=b;
		
	}
	
	
	

}
