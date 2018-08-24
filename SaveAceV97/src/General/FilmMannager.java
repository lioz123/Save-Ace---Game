package General;

import java.nio.file.FileVisitOption;

import GameObjects.ID;
import GameObjects.Side;

public class FilmMannager {
private Film filmRight;
private Film filmLeft;
private Side side;
private ImageData lastFrame;
private String name;
private String openKey = null ;
private String specialKey = "None";
private int frameCounter = 0;
	public FilmMannager(Film right,Film left){
		this.filmLeft=left;
		this.filmRight=right;
		this.openKey=right.getKey();
		this.specialKey=right.getSpecialKey();
	}
	
	public boolean isFinished(){
		return filmRight.isFinished()||filmLeft.isFinished();
	}
	
	
	
	public void setOpenKey(String key){
		this.openKey= key;
	}
	
	public String getKey(){
		return this.openKey;
	}
	
	public FilmMannager(FilmMannager film){
		this.filmRight=new Film(film.getFilmRight());
		this.filmLeft=new Film(film.getFilmLeft());
		this.name=film.getName();
		this.openKey=film.getKey();
	}
	
	public FilmMannager(int RX,int LX,int y,int width,int height,int frames, int deltaX,int deltaY){
		//buildFilm(jetPistolImageRX,imageY+2,imageWidth,imageHeight,3,40,0);
		filmRight=new Film();
		filmLeft=new Film();
		filmRight.buildFilm(RX, y, width, height, frames, deltaX, deltaY);
		filmLeft.buildFilm(LX, y, width, height, frames, deltaX, deltaY);
		
	}
	
	
	public FilmMannager(int RX,int LX,int RY,int LY, int width,int height,int frames, int deltaX,int deltaY){
		//buildFilm(jetPistolImageRX,imageY+2,imageWidth,imageHeight,3,40,0);
		filmRight=new Film();
		filmLeft=new Film();
		filmRight.buildFilm(RX, RY, width, height, frames, deltaX, deltaY);
		filmLeft.buildFilm(LX, LY, width, height, frames, deltaX, deltaY);
	}
	
	public FilmMannager(int x,int RY,int LY, int width,int height,int frames, int deltaX,int deltaY,ID id){
		//buildFilm(jetPistolImageRX,imageY+2,imageWidth,imageHeight,3,40,0);
		filmRight=new Film();
		filmLeft=new Film();
		filmRight.buildFilm(x, RY, width, height, frames, deltaX, deltaY);
		filmLeft.buildFilm(x, LY, width, height, frames, deltaX, deltaY);
	}
	
	
	
	
	
	
	public ImageData run(Side side){
		this.side =side;
		if(side==Side.left){
			ImageData temp = filmLeft.run();
			if(!temp.equals(lastFrame)){
				frameCounter++;
			}
			lastFrame = temp;
			
			return lastFrame;
		}
		else{
			ImageData temp = filmLeft.run();

			if(!temp.equals(lastFrame)){
				frameCounter++;
			}
			lastFrame = temp;
			return lastFrame;
		}
	}
	
	public int getFrameCounter(){
		return frameCounter;
	}
	
	public Film getFilm(Side side){
		if(side == Side.left){
			return filmLeft;
		}
		return filmRight;
	}
	
	public Film getFilmLeft(){
		return this.filmLeft;
	}
	
	public Film getFilmRight(){
		return this.filmRight;
	}
	
	public ImageData stop(Side side){
		if(side==Side.right){
			return filmRight.getFrame(0);
		}else{
	
			return filmLeft.getFrame(0);
			
		}
	}
	
	public void reset(){
		if(filmLeft!=null)
		filmLeft.setFrameCounter(0);;
		if(filmRight!=null)
		filmRight.setFrameCounter(0);
		frameCounter=0;
	
	}
	
	public void resetOneFilm(Side side){
		if(side==Side.left){
			filmRight.stop();
		}else{
			filmLeft.stop();
		}
	}
	public void setFps(int fps){
		filmRight.setFps(fps);
		filmLeft.setFps(fps);
	}

	public int size() {
		
		return filmLeft.size();
	}

	public int getFps() {
		
		return filmLeft.getFps();
	}

	public ImageData getFrame() {
		if(lastFrame!=null){
			return lastFrame;
		}
		
		
		return filmRight.getFrame(0);
	
	
	}

	public void setName(String string) {
		this.name=string;
		
	}
	
	public String getName(){
		return this.name;
	}





	public String getSpecialKey() {
		// TODO Auto-generated method stub
		return specialKey;
	}





	public void setSpecialKey(String specialKey) {
		this.specialKey=specialKey;
		
	}

	public void setFinished(boolean b) {
		filmRight.setFinished(b);
		filmLeft.setFinished(b);;
		
	}
}
