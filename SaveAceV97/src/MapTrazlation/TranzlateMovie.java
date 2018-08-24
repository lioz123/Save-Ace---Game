package MapTrazlation;

import java.awt.Rectangle;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.util.ArrayList;

import FilesUtils.ReadFile;
import General.Film;
import General.ImageData;
import General.ImagesGroup;
import MoiveEditor.StringReader;

public class TranzlateMovie {
	private ArrayList<Rectangle> frames;	
	private String name , path;
	private Rectangle normalBounds,deltaBounds;
	private int fps;
	private String content = " ";
	private ArrayList<String> words,descripton,lines;
	private ArrayList<ImageData> imagesData;
	private ImageData head, leg, chest,arm;
	private String key;
	private String moviePath = "";
	private String specialKey="NONE";
	public TranzlateMovie(String path,String name){
		this.path=path;
		this.name=name;
		lines = new ArrayList<String>();
		this.descripton=new ArrayList<String>();
		this.words= new ArrayList<String>();
		frames = new ArrayList<Rectangle>();
		this.descripton.add(name);
		imagesData = new ArrayList<ImageData>();
		head = new ImageData(0,0,0,0);
		leg = new ImageData(0,0,0,0);
		chest = new ImageData(0,0,0,0);
		arm = new ImageData(0,0,0,0);
	}
	
	public void setlines(ArrayList<String> lines){
		this.lines=lines;
	}
	
	public void TranzlateToObjectNoLineTranzlate(){
		boolean head = false;
		boolean body = false;
		// body values are x y width height deltaWidth deltaHeight isSolid(true or false) attack defence
		for(String line:lines){
			StringReader reader = new StringReader(line);
			while(reader.hasNext()){
				String str =reader.getNext();
				if(str.equals("</head>")){
					head =false;
				} 
				if(str.equals("</body>")){
					body =false;
				}
				if(head){			
					switch (str) {
					case "path:":
						reader.getNext();
						this.moviePath=reader.getNext();
						break;
					case "fps:":
						fps = Integer.parseInt(reader.getNext());
					break;
					case "normalBounds:":
						int width = Integer.parseInt(reader.getNext());
						int height = Integer.parseInt(reader.getNext());
						this.normalBounds = new Rectangle(width,height);
					break;
					
					case "leg:":
						boolean solid;
						int attack,defence;
						if(reader.getNext().equals("true")){
							solid=true;
						}else{
							solid =false;
						}
						attack = Integer.parseInt(reader.getNext());
						defence = Integer.parseInt(reader.getNext());
						leg.setAttack(attack);
						leg.setDefence(defence);
						leg.setSolid(solid);
						break;
						
					
					
					case "arm:":
						boolean solid2;
						int attack2,defence2;
						if(reader.getNext().equals("true")){
							solid2=true;
						}else{
							solid2 =false;
						}
						attack2 = Integer.parseInt(reader.getNext());
						defence2 = Integer.parseInt(reader.getNext());
						arm.setAttack(attack2);
						arm.setDefence(defence2);
						arm.setSolid(solid2);
						break;
						
						
					case "chest:":
						boolean solid3;
						int attack3,defence3;
						if(reader.getNext().equals("true")){
							solid3=true;
						}else{
							solid3 =false;
						}
						attack3 = Integer.parseInt(reader.getNext());
						defence3 = Integer.parseInt(reader.getNext());
						chest.setAttack(attack3);
						chest.setDefence(defence3);
						chest.setSolid(solid3);
						break;
					
					
					case "head-armature:":
						boolean solid4;
						int attack4,defence4;
						if(reader.getNext().equals("true")){
							solid4=true;
						}else{
							solid4 =false;
						}
						attack4 = Integer.parseInt(reader.getNext());
						defence4 = Integer.parseInt(reader.getNext());
						this.head.setAttack(attack4);
						this.head.setDefence(defence4);
						this.head.setSolid(solid4);
						break;
						
					case "Key:" :
					key = reader.getNext();
						break;
						
					case "SpecialKey:":
						specialKey = reader.getNext();
						break;
					default:
						break;
					}
					descripton.add(str);
					
				}
				
				if(body){
					if(!str.equals("end")){
						int x = Integer.parseInt(str);
						int y= 	Integer.parseInt(reader.getNext());
						int width = Integer.parseInt(reader.getNext());
						int height = Integer.parseInt(reader.getNext());
						int deltaWidth = Integer.parseInt(reader.getNext());
						int deltaHeight = Integer.parseInt(reader.getNext());
						String isSolidstr = reader.getNext();
						int attack = Integer.parseInt(reader.getNext());
						int defence = Integer.parseInt(reader.getNext());
						this.frames.add(new Rectangle(x,y,width,height));
						this.imagesData.add(new ImageData(x,y,width,height));
						this.imagesData.get(imagesData.size()-1).setDeltaX(deltaWidth);
						this.imagesData.get(imagesData.size()-1).setDeltaY(deltaHeight);
						if(isSolidstr.equals("true")){
							this.imagesData.get(imagesData.size()-1).setSolid(true);
						}else{
							this.imagesData.get(imagesData.size()-1).setSolid(false);
						}
						this.imagesData.get(imagesData.size()-1).setAttack(attack);
						this.imagesData.get(imagesData.size()-1).setDefence(defence);
						
						ImagesGroup group = new ImagesGroup();
						while(reader.hasNext()){
							String tempStr = reader.getNext();
							
							if(tempStr.equals("group-end")){
								if(group.getImages().size()>0){
									this.imagesData.get(imagesData.size()-1).getGroup().add(group);
								}
							}
							
							
							if(tempStr.equals("group:")){
								group= new ImagesGroup();
								if(reader.getNext().equals("true")){
									group.setSolid(true);
								}else{
									group.setSolid(false);
								}
								int tAttack=  Integer.parseInt(reader.getNext());
								int tDefence = Integer.parseInt(reader.getNext());
								group.setAttack(tAttack);
								group.setDefence(tDefence);
							}
							
							
							
							if(tempStr.equals("bone:")){
								int tx = Integer.parseInt(reader.getNext());
								int ty= 	Integer.parseInt(reader.getNext());
								int twidth = Integer.parseInt(reader.getNext());
								int theight = Integer.parseInt(reader.getNext());
								int tdeltaWidth = Integer.parseInt(reader.getNext());
								int tdeltaHeight = Integer.parseInt(reader.getNext());
								String tisSolidstr = reader.getNext();
								int tattack = Integer.parseInt(reader.getNext());
								int tdefence = Integer.parseInt(reader.getNext());
								ImageData bone= new ImageData(tx,ty,twidth,theight);
								bone.setDeltaX(tdeltaWidth);
								bone.setDeltaY(tdeltaHeight);
								if(isSolidstr.equals("true")){
									bone.setSolid(true);
								}else{
									bone.setSolid(false);
								}
								bone.setAttack(tattack);
								bone.setDefence(tdefence);
								group.add(bone);
							}
							
						
							
						}
						
						
					}
				
				}
				
				
				if(str.equals("<head>")){
					head =true;
				}
				
				if(str.equals("<body>")){
					
				body =true;
				}
			}
		}
	}
	
	public void tranzlateIntoStrings(){
		ReadFile file= new ReadFile();
		file.openFile(path);
		lines = file.GetFromLines(name,"end");
		file.close();
	}
	
	public void  TranzlateObject(){
		tranzlateIntoStrings();
		boolean head = false;
		boolean body = false;
		for(String line:lines){
			StringReader reader = new StringReader(line);
		
			while(reader.hasNext()){
			
				String str =reader.getNext();
			
				if(str.equals("</head>")){
					head =false;
				} 
				if(str.equals("</body>")){
					body =false;
				}
				
				
				if(head){			
					switch (str) {
					case "path:":
						reader.getNext();
						this.moviePath=reader.getNext();
						break;
					case "fps:":
						fps = Integer.parseInt(reader.getNext());
					break;
					case "normalBounds:":
						int width = Integer.parseInt(reader.getNext());
						int height = Integer.parseInt(reader.getNext());
						this.normalBounds = new Rectangle(width,height);
				
					case "leg:":
						boolean solid;
						int attack,defence;
						if(reader.getNext().equals("true")){
							solid=true;
						}else{
							solid =false;
						}
						attack = Integer.parseInt(reader.getNext());
						defence = Integer.parseInt(reader.getNext());
						leg.setAttack(attack);
						leg.setDefence(defence);
						leg.setSolid(solid);
						break;
						
					case "key:":
						this.key= reader.getNext();
						break;
					
					case "arm:":
						boolean solid2;
						int attack2,defence2;
						if(reader.getNext().equals("true")){
							solid2=true;
						}else{
							solid2 =false;
						}
						attack2 = Integer.parseInt(reader.getNext());
						defence2 = Integer.parseInt(reader.getNext());
						arm.setAttack(attack2);
						arm.setDefence(defence2);
						arm.setSolid(solid2);
						break;
						
						
					case "chest:":
						boolean solid3;
						int attack3,defence3;
						if(reader.getNext().equals("true")){
							solid3=true;
						}else{
							solid3 =false;
						}
						attack3 = Integer.parseInt(reader.getNext());
						defence3 = Integer.parseInt(reader.getNext());
						chest.setAttack(attack3);
						chest.setDefence(defence3);
						chest.setSolid(solid3);
						break;
					
					
					case "head-armature:":
						boolean solid4;
						int attack4,defence4;
						if(reader.getNext().equals("true")){
							solid4=true;
						}else{
							solid4 =false;
						}
						attack4 = Integer.parseInt(reader.getNext());
						defence4 = Integer.parseInt(reader.getNext());
						this.head.setAttack(attack4);
						this.head.setDefence(defence4);
						this.head.setSolid(solid4);
						break;
					
					
					default:
						break;
					}
					descripton.add(str);
					
				}
				
				if(body){
					if(!str.equals("end")){
						int x = Integer.parseInt(str);
						int y= 	Integer.parseInt(reader.getNext());
						int width = Integer.parseInt(reader.getNext());
						int height = Integer.parseInt(reader.getNext());
						int deltaWidth = Integer.parseInt(reader.getNext());
						int deltaHeight = Integer.parseInt(reader.getNext());
						this.frames.add(new Rectangle(x,y,width,height));
						this.imagesData.add(new ImageData(x,y,width,height));
						this.imagesData.get(imagesData.size()-1).setDeltaX(deltaWidth);
						this.imagesData.get(imagesData.size()-1).setDeltaY(deltaHeight);
						
					}
				
				}
				
				
				if(str.equals("<head>")){
					head =true;
				}
				
				if(str.equals("<body>")){
					
				body =true;
				}
			}
		}
	}
	
	public ArrayList<String> getMoviesList(){
		boolean foundName = false;
		ArrayList<String> moviesList = new ArrayList<String>();
		ReadFile reader = new ReadFile();
		reader.openFile(path);
		StringReader stringReader = new StringReader( reader.getFileText()); 
		reader.close();
		while(stringReader.hasNext()){
			String string = stringReader.getNext();
			
			if(foundName){
				moviesList.add(string);
				foundName =false;
			}
			
			if(string.equals("end")){
				foundName = true;
			}
			
		}
		return moviesList;
	}
	
	public ArrayList<Rectangle> getFrames(){
		return this.frames;
	}
	
	public ArrayList<ImageData>	getImagesData(){
		return this.imagesData;
	}
	
	public ArrayList<String> getHead(){
		return this.descripton;
	}
	
	public String getPath(){
		return this.path;
	}
	
	public Rectangle getNormalBounds(){
		return this.normalBounds;
	}
	
	public int getFps(){
		return this.fps;
	}
	
	public ImageData getArmData(){
		return this.arm;
	}
	
	public ImageData getHeadData(){
		return this.head;
	}
	
	public ImageData getLegData(){
		return this.leg;
	}
	
	public ImageData getChestData(){
		return this.chest;
	}
	
	
	public Film getFilm(){
		Film film = new Film();
		film.setFps(fps);
		film.setKey(key);
		film.setSpecialKey(specialKey);
		film.setPath(moviePath);
		film.setFpsOposite(fps);
		//	film.addImage(new ImageData((int)frame.getX(),(int)frame.getY(),(int)frame.getWidth(),(int)frame.getHeight()));
			film.setFrames(imagesData);
		return film;
	}
	
}
