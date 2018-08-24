package MoiveEditor;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.ScrollPaneAdjustable;
import java.awt.font.ImageGraphicAttribute;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import javax.rmi.CORBA.Util;

import General.ImageData;
import General.Utilities;
import Main.Game;
import MapTrazlation.ColorList;
import MapTrazlation.SpriteSheetList;

public class Selector {
	


	private Rectangle rect,viewRect,frame,frameDeltaBounds;
	private boolean selected =false,editable =false,saved=false,locked =false,chosen=false,isbone=false,fullVisabilty= true;
	private double divWidth=1,divHeight=1,scaleHeight=1,scaleWidth=1;
	private double deltaX=0,deltaY=0,sdeltaX=0,sdeltaY=0;
	private int bonesAttack=0,bonesDefence=0;
	private boolean bonesSolid = true;
	private ArrayList<Selector> bones= new ArrayList<Selector>();;
	private Selector armature=null;
	private ImageData imgData = new ImageData(0,0,0,0);
	private ArrayList<BonesGroup> groups = new ArrayList<BonesGroup>();	
	private boolean hide = false;
	private MovieEditor editor;
	private boolean hideBones =false;
	private Color color = Color.red;


	public Selector(){

	rect = new Rectangle(0,0);
	frameDeltaBounds = new Rectangle(0,0,0,0);

	}
	

	
	public void setMovieEditor(MovieEditor editor){
		this.editor=editor;
	}
	
	public Selector getAramature(){
		return this.armature;
	}
	
	public void setArmature(Selector armature){
		if(this.armature!=null){
		this.armature.removeBone(this);
		}
		
		this.armature=armature;
	}
	

	
	
	public void removeBone(Selector bone){
		bones.remove(bone);
		int i = 0;
		while(i<groups.size()){
			groups.get(i).removeBone(bone);
			i++;
		}
	}
	
	
	public void asignGroup(ArrayList<Selector> bones){
		BonesGroup group = new BonesGroup(this,editor);
		
		for(Selector bone:bones){
			if(this.bones.indexOf(bone)!=-1){
				group.add(bone);
			}
		
		}
		
		for(BonesGroup temp : groups){
			for(Selector bone : group.getBones()){
				if(temp.getBones().indexOf(bone)!=-1){
					temp.removeBone(bone);
				}
			}
		}
		group.setColor(ColorList.getColor(groups.size()));
		groups.add(group);
		int i =0; 
		while(i<groups.size()){
			BonesGroup temp = groups.get(i);
			if(temp.size()==0){
				groups.remove(temp);
			}else{
				i++;
			}
			
		}
	}
	
	public void removeGroup(int value){
		BonesGroup group = groups.get(value);
		group.remove();
	}
	
	
	public void setFrameDeltaBounds(Rectangle delta){
		this.frameDeltaBounds = delta;
		
		this.imgData.setDeltaX((int)delta.getWidth());
		this.imgData.setDeltaY((int)delta.getHeight());
	
	}
	

	
	public Rectangle getFrameDeltaBounds(){
		return new Rectangle(imgData.getDeltaX(),imgData.getDeltaY());
	}
	
	public void addBone(Selector bone){
		int i = 0;
		while(i<bones.size()){
			if(bone == bones.get(i)){
				return;
			}
			i++;
		}
		
		this.bones.add(bone);
		BonesGroup group = new BonesGroup(this,editor);
		group.add(bone);
		groups.add(group);

	}
	
	public Selector(Selector selector){
		this.color=selector.getColor();
	//	this.frameDeltaBounds=selector.getFrameDeltaBounds();
		this.rect = new Rectangle(selector.getRect());
		this.divHeight=selector.getDivHeight();
		this.divWidth = selector.getDivWidth();
		this.deltaX=selector.getDeltaX();
		this.deltaY=selector.getDeltaY();
		this.sdeltaX=selector.getSdeltaX();
		this.sdeltaY=selector.getSdeltaY();
		this.scaleHeight = selector.scaleHeight;
		this.scaleWidth=selector.scaleWidth;
		this.viewRect=selector.getViewRect();
		this.selected=selector.isSelected();
		this.editable=selector.isEditable();
		this.saved=selector.isSaved();
		this.frame=selector.getFrame();
		this.isbone = selector.isBone();
		this.bones=selector.getBones();
		this.armature=selector.getAramature();
		this.imgData=new ImageData(selector.getImageData());
		this.groups=selector.getGroups();
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public ImageData getImageData(){
		return this.imgData;
	}
	
	
	public void setBone(boolean b){
		this.isbone=b;
	}
	
	public boolean isBone(){
		return this.isbone;
	}
	
	public Rectangle getViewRect(){
		return this.viewRect;
	}
	
	public double getDeltaX(){
		return this.deltaX;
	}
	
	public double getDeltaY(){
		return this.deltaY;
	}
	
	public double getSdeltaX(){
		return this.sdeltaX;
	}
	
	public double getSdeltaY(){
		return this.sdeltaY;
	}
	

	
	
	
	public Rectangle getRect(){
		return this.rect;
	}
	

	
	public void setDeltaX(double d){
		this.deltaX=d;
	}
	
	public void setDeltaY(double d){
		this.deltaY=d;
	}

	

public void reLocation(int deltaX,int deltaY){
		rect.setLocation((int) rect.getX()+deltaX,deltaY+(int)rect.getY());
	}
	
	public void setSize(int width,int height){

		rect.setBounds((int)rect.getX(),(int)rect.getY(), width, height);
	}
	public void resize(int deltaX,int deltaY){
		rect.setBounds((int)rect.getX(),(int)rect.getY(),(int)rect.getWidth()+deltaX,(int)rect.getHeight()+deltaY);
	}
	
	
	public void tick(PointInt start, PointInt end){
		
		double x,y,width,height;
		if(end.getX()<start.getX()){
			x=end.getX();
		}else{
			x=start.getX();
		}
		if(end.getY()<start.getY()){
			y=end.getY();
		}else{
			y=start.getY();
		}
		width = Math.sqrt(Math.pow(end.getX()-start.getX(), 2));
	    height= Math.sqrt(Math.pow(end.getY()-start.getY(), 2));	

		x=x/divWidth;
		y=y/divHeight;
		
		rect = new Rectangle((int)x,(int)y,(int)width,(int)height);
	
	
	}
	
	public boolean onClick(int x,int y){
		if(viewRect==null){
			return false;
		}
	
		double vx =rect.getX();
		double vy= rect.getY();
		double width = rect.getWidth()*scaleWidth;
		double height = rect.getHeight()*scaleHeight;
		vx= (x+deltaX-sdeltaX)*divWidth*scaleWidth;
		vy=(y+deltaY-sdeltaY)*divHeight*scaleWidth;
		
		if(x>viewRect.getX()&&x<viewRect.getX()+viewRect.getWidth()){	
			if(y>viewRect.getY()&&y<viewRect.getHeight()+viewRect.getY()){
			;
					if(editable){
				editable=false;
			}else{
				editable = true;
			}		if(editor!=null){
				if(editor.isSaveRam()){
					this.hideBones(false);
					this.hideBones=false;
					}
				}
					
					chosen =true;

				return true;
			}
		}
		if(editor!=null){
			if(editor.isSaveRam()){
				hideBones(true);
				this.hideBones=true;
			}
		}
		
		chosen =false;
	
		return false;
	}
	
	
	public void setLocation(int x,int y){
		rect.setLocation(x, y);
	}
	
	public Point getMiddle(){
		return Utilities.getMiddle(viewRect);
	}
	

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
		if(saved){
			
		}
}
	public void move(double dx, double dy){

		double x =(rect.getX()+dx);
		double y = (rect.getY()+dy);
		deltaX+=dx;
		deltaY+=dy;
	//	rect.setLocation((int)x,(int)y);
		
	}

	
	
	
	
	public void render(Graphics2D g2d){
		
	/*	double divScaleWidth =1;
		double divScaleHeight=1;
		
			divScaleWidth=  divWidth/scaleWidth;
			divScaleHeight=  divHeight/scaleHeight;
		
*/		
	
			double x =rect.getX();
			double y= rect.getY();
			double width = rect.getWidth()*scaleWidth;
			double height = rect.getHeight()*scaleHeight;
			x= (x+deltaX-sdeltaX)*divWidth*scaleWidth;
			y=(y+deltaY-sdeltaY)*divHeight*scaleWidth;
			viewRect = new Rectangle((int)x,(int)y,(int)width,(int)height);
		//	g2d.scale(scaleWidth,scaleHeight);
			
			if(!hide){
			if(fullVisabilty){
		
			if(locked){
				g2d.drawImage(SpriteSheetList.lockSprite.getImage(),(int)x,(int)y,(int)width,(int)height,null);
			}
			
			}
			if(isbone&&armature!=null){
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.2));
				g2d.setColor(Color.blue);
			//	Point start =new Point((int) viewRect.getX(),(int)viewRect.getY());
			//	Point end =  new Point((int)armature.getViewRect().getX(),(int)armature.getViewRect().getY());
				Point start = Utilities.getMiddle(viewRect);
				Point end = Utilities.getMiddle(armature.getViewRect());
				
				g2d.drawLine((int)start.getX(),(int)start.getY(),(int)end.getX(),(int)end.getY());
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			}
			if(fullVisabilty){
				
			
			g2d.setColor(Color.gray);
			
			if(!chosen){
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.4));
			}else{
				g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.5));
			}
		
			
			if(isbone){
			g2d.setColor(color);
			
			}
			g2d.fill(viewRect);
			}
			g2d.setColor(Color.DARK_GRAY);
		g2d.draw(viewRect);
		
		
		
	
//	g2d.scale(1/scaleWidth, 1/scaleHeight);
	
			/*if(this.selected){
				 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.7));
				g2d.setColor(Color.DARK_GRAY);
			g2d.draw(rect.getBounds2D());
			}
		*/
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)1));
			}
	
	}
	
	public void hideBones(boolean b){
		int i = 0 ;
		while(i<bones.size()){
			bones.get(i).setHide(b);
			i++;
		}
	}

	public int getWidth() {
		// TODO Auto-generated method stub
		return (int)rect.getWidth();
		
	}
	
	public void setColor(Color color){
		this.color=color;
	}
	
	public int getHeight(){
		return (int) rect.getHeight();
	}

	public int getX() {
		// TODO Auto-generated method stub
		return(int) rect.getX();
	
	}
	
	public int getY(){
		return (int) rect.getY();
	}

	public double getDivWidth() {
		return divWidth;
	}

	public void setDivWidth(double divWidth) {
		this.divWidth = divWidth;
	}

	public double getDivHeight() {
		return divHeight;
	}

	public void setDivHeight(double divHeight) {
		this.divHeight = divHeight;
	}

	public double getScaleHeight() {
		return scaleHeight;
	}

	public void setScaleHeight(double scaleHeight) {
		this.scaleHeight = scaleHeight;
	}

	public double getScaleWidth() {
		return scaleWidth;
	}

	public void setScaleWidth(double scaleWidth) {
		this.scaleWidth = scaleWidth;
	}



	public void setSdeltaX(int sdeltaX) {
		this.sdeltaX = sdeltaX;
	}



	public void setSdeltaY(int sdeltaY) {
		this.sdeltaY = sdeltaY;
	}
	
	public String toString(double windW,double windH,double imgW, double  imgH){
		if(isLocked()){
		
			return (int)frame.getX()+" " +(int)frame.getHeight()+" " +(int)frame.getWidth()+" " + (int)frame.getHeight();
		}
	

		double x =rect.getX();
		double y= rect.getY();
		double width = rect.getWidth();
		double height = rect.getHeight();
		x= (x-sdeltaX)*divWidth;
		y=(y-sdeltaY)*divHeight;
		viewRect = new Rectangle((int)x,(int)y,(int)width,(int)height);
		
		
		double dx = windW/imgW*divWidth;
		double dy = windH/imgH*divWidth;
	
		
		int vx ,vy,vw,vh;
		width/=dx;
		height/=dy;
		x/=dx;
		y/=dy;
	
		vx = (int) x;
		vy= (int) y;
		if(x-(double)vx>0){
		//	vx++;
		}
		if(y-(double)vy>0){
		//	vy++;
		}
		
	
		vw = (int) width;
		vh = (int) height;
	
		if(width-(double)vw>0){
			vw++;
		}
		if(height-(double)vh>0){
			vh++;
	
		}
		
		String str = vx+" " + vy +" " + vw + " " + vh; 
	
		
		/*
		
		double windw =Game.WIDTH;
		double windh = Game.HEIGHT;
		
	double dx = windw/3720*10;
		double dy = windh/5506*10;
		y/=dy;
		x/=dx;
	*/
		
	

		
		return str;
	}
	
	public void generateFrame(double windW,double windH,double imgW, double  imgH){
		if(this.locked){
			return ;
		}
		double x =rect.getX();
		double y= rect.getY();
		double width = rect.getWidth();
		double height = rect.getHeight();
		x= (x-sdeltaX)*divWidth;
		y=(y-sdeltaY)*divHeight;
		viewRect = new Rectangle((int)x,(int)y,(int)width,(int)height);
		
		
		double dx = windW/imgW*divWidth;
		double dy = windH/imgH*divWidth;
	
		
		int vx ,vy,vw,vh;
		width/=dx;
		height/=dy;
		x/=dx;
		y/=dy;
	
		vx = (int) x;
		vy= (int) y;
		if(x-(double)vx>0){
		//	vx++;
		}
		if(y-(double)vy>0){
		//	vy++;
		}
		
	
		vw = (int) width;
		vh = (int) height;
	
		if(width-(double)vw>0){
			vw++;
		}
		if(height-(double)vh>0){
			vh++;
	
		}
		
		this.frame = new Rectangle(vx,vy,vw,vh);
		this.imgData= new ImageData(vx,vy,vw,vh);
	}
	
	
	

	
	


	


	
	public Rectangle BoundsFromImg(BufferedImage img){
		double x =rect.getX();
		double y= rect.getY();
		double width = rect.getWidth();
		double height = rect.getHeight();
		double imgW,imgH,windW,windH,divW,divH;
		imgW=img.getWidth();
		imgH=img.getHeight();
		windW=Game.WIDTH;
		windH=Game.HEIGHT;
		divW=windW/imgW;
		divH=windH/imgH;
		x= (x+-sdeltaX)*divWidth;
		y=(y+-sdeltaY)*divHeight;
		x = LocationCalculator.getPxByCordinate(x,imgW,windW*10);
		y= LocationCalculator.getPxByCordinate(y,windH, windH*10);
	
		Rectangle temp= new Rectangle((int)x,(int)y,(int)width,(int)height);
		
		return temp;	
	}

	public void setRect(Rectangle rect) {
		this.rect=rect;
	}

	public void setViewRectangle(Rectangle rectangle) {
		this.viewRect=rectangle;
		double x,y,w,h;
	w= rectangle.getWidth()/scaleWidth;
	h =  rectangle.getHeight()/scaleHeight;
		x= (viewRect.getX()+deltaX-sdeltaX)/divWidth/scaleWidth;
		y=(viewRect.getHeight()+deltaY-sdeltaY)/divHeight/scaleWidth;
		this.rect=new Rectangle((int)x,(int)y,(int)w,(int)h);
	}

	public Rectangle getViewRect2() {
		double x =rect.getX();
		double y= rect.getY();
		double width = rect.getWidth()*scaleWidth;
		double height = rect.getHeight()*scaleHeight;
		x= (x+deltaX-sdeltaX)*divWidth*scaleWidth;
		y=(y+deltaY-sdeltaY)*divHeight*scaleWidth;
		viewRect = new Rectangle((int)x,(int)y,(int)width,(int)height);
	
	return viewRect;
	}
	
	public Rectangle getFrame() {
		return frame;
	}
	
	public void setStartDeltaX(int x){
		this.sdeltaX=x;
	}
	
	public void setStartDeltaY(int y){
		this.sdeltaY=y;
	}

	
	public void setFrame(Rectangle frame) {
		this.frame = frame;
		imgData.setBounds(frame);
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}

	public boolean isClicked(int x, int y) {
		if(viewRect==null){
			return false;
		}
		return Utilities.isClicked(x, y, viewRect);
	}

	public void delete() {
		
		while(bones.size()>0){
			bones.get(0).setArmature(null);
		// no need for i++ couse the setArmature deletes the bone from the armature (:
	
		}
		if(armature!=null){
			armature.removeBone(this);
		}
		
	}
	
	
	
	public ArrayList<Selector> getBones(){
		return this.bones;
	}
	
	public String getContent(){
		String content = "";
		if(imgData==null){
			return content;
		}
		content = (int)imgData.getImageX() +" " + (int)imgData.getImageY() + " " + imgData.getImageWidth()+ " " + (int)imgData.getImageHeight() +" "+ imgData.getDeltaX()+" "+ imgData.getDeltaY() +" "+ imgData.isSolid() +" " + imgData.getAttack() +" " + imgData.getDefence();
		int i = 0;
		while(i<groups.size()){
			BonesGroup group = groups.get(i);
			content += " group: "; 
			content+=  group.isSolid()+ " "+group.getAttack() +" " + group.getDefence() ;
			
			int j = 0;
			while(j<group.size()){
				Selector bone = group.getBone(j);
				content+= " bone: ";
				content += bone.getContent();
				content+=" bone-end ";
				j++;
			}
			content+=" group-end ";
			
			i++;
		}
		
		/*		int i = 0;
		while(i<bones.size()){
			Selector bone = bones.get(i);
			content+= " bone: ";
			content += bone.getContent();
			content+=" bone-end ";
			i++;
		}
	*/
		return content;
	}

	public void setFullVizability(boolean b) {
		this.fullVisabilty=b;
	}
	
	public void setAttack(int attack){
		imgData.setAttack(attack);
	}
	
	public void setDefence(int defence){
		imgData.setDefence(defence);
	}
	
	public void setSolid(boolean b){
		imgData.setSolid(b);
	}
	
	public void setBonesSolid(boolean b){
		int i = 0;
		this.bonesSolid=b;
		while(i<bones.size()){
			Selector bone = bones.get(i);
			bone.setSolid(b);
			i++;
		}
	}
	
	public void setBonesAttack(int attack){
		bonesAttack=attack;
		int i = 0;
		while(i<bones.size()){
			Selector bone = bones.get(i);
			bone.setAttack(attack);;
			i++;
		}
	}
	
	
	public void setBonesDefence(int defence){
		bonesDefence=defence;
		int i = 0;
		while(i<bones.size()){
			Selector bone = bones.get(i);
			bone.setDefence(defence);
			i++;
		}
	}
	
	public int getBonesDefence(){
		return this.bonesDefence;
	}
	
	public int getBonesAttack(){
		return this.bonesAttack;
	}
	
	public boolean isBonesSolid(){
		return this.bonesSolid;
	}
	

	
	public void Subdivide(int i){
	groups.get(i).Subdivide();
	}

	public ArrayList<BonesGroup> getGroups() {
		// TODO Auto-generated method stub
		return this.groups;
	}

	public void addBoneFromGroup(Selector temp) {
		this.bones.add(temp);
		
	}
	
	
	public ArrayList<Rectangle> creatBoneShell(){
	
			if(editor!=null&&imgData!=null&&editor.getSpriteSheet()!=null){
				BufferedImage img = editor.getSpriteSheet().grabImage(imgData.getImageX(), imgData.getImageY(),imgData.getImageWidth(),imgData.getImageHeight());
		ImageScanner scanner = new ImageScanner(img);
		scanner.setBackGround(editor.getBackGround());
		ArrayList<Rectangle> rects = scanner.getShellRectangles();
		BonesGroup group = new BonesGroup(this,editor);
		for(Rectangle rect:rects){
			rect.setLocation((int)(rect.getX()+imgData.getImageX()), (int)(rect.getY()+imgData.getImageY()));
			Selector selector = editor.loadSelectorFromPicture(rect);
			selector.setBone(true);
			selector.setArmature(this);
			group.add(selector);
			bones.add(selector);
		}
		groups.add(group);

			}
			return null;
		
	}
	
	public boolean PointInArrayList(ArrayList<Point> points, Point point){
		for(Point temp : points){
			if(temp.getX()==point.getX()&&temp.getY()==point.getY()){
				return true;
			}
		}
		
		return false;
	}



	public boolean isHide() {
		return hide;
	}



	public void setHide(boolean hide) {
		this.hide = hide;
	}



	public int getAttack() {
		// TODO Auto-generated method stub
		return imgData.getAttack();
	}
	
	



	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
