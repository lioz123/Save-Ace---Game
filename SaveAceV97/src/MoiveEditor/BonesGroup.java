package MoiveEditor;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.Delayed;

import General.SpriteSheet;



public class BonesGroup {
private ArrayList<Selector> bones;
	private MovieEditor editor;
	private Selector armature;
	private BonesGroup formerGroup;
	private ArrayList<BonesGroup> formersGroup;
	private Color color=Color.red;
	private int attack ,defence;
	private boolean solid =false;
	public BonesGroup(Selector armature,MovieEditor editor){
		this.editor=editor;
		this.armature=armature;
		bones = new ArrayList<Selector>();
		formersGroup= new ArrayList<BonesGroup>();
	}
	
	public BonesGroup(BonesGroup bonesGroup) {
		this.editor=bonesGroup.getEditor();
		this.armature=bonesGroup.getAramture();
		this.bones = new ArrayList<Selector>();
		this.formerGroup=bonesGroup.getFormerGroup();
		int i = 0;
		while(i<bonesGroup.size()){
			bones.add(new Selector(bonesGroup.getBone(i)));
			i++;
		}
	}
	
	public void setNextGroup(){
		
	}
	
	
	




	public void add(Selector selector){
		bones.add(selector);
		selector.setColor(color);
	}
	
	public Selector getBone(int i){
		if(i<bones.size()){
			return bones.get(i);
		}else{
			return null;
		}
		
	}
	
	public void Subdivide(){
		formersGroup.add(new BonesGroup(this));
		ArrayList<Selector>tempBones = new ArrayList<Selector>();
		int i=0;
		while(bones.size()>0){
			Selector bone = bones.get(i);
			double imgWidth,imgHeight,dimgX,dimgY;
			imgWidth = (double)bone.getImageData().getImageWidth()/2.0;
			imgHeight = (double)bone.getImageData().getImageHeight()/2.0;
			dimgX=bone.getImageData().getImageX();
			dimgY=bone.getImageData().getImageY();
			if(imgWidth>1&&imgHeight>1){
			for(int j = 0; j<2;j++){
			
			double x,y,width,height,imgX,imgY;
			width = bone.getRect().getWidth()/2;
			height= bone.getRect().getHeight()/2;

			imgY = dimgY+ imgHeight*j;
			y=bone.getRect().getY()+j*height/bone.getDivHeight();
			
			for(int k = 0 ; k<2;k++){
				 Selector temp = new Selector(bone);
				x= bone.getRect().getX()+width*k/bone.getDivWidth();
				int deltaX=1,deltaY=1;
				if(k==0&&j==1){
				//	deltaX=1;
				//	deltaY=1;
				}
				temp.setRect(new Rectangle((int)x,(int)y,(int)width+deltaX,(int)height+deltaY));
				imgX= dimgX+imgWidth*k;
				temp.setFrame(new Rectangle((int)imgX,(int)imgY,(int)imgWidth+deltaX,(int)imgHeight+deltaY));
				tempBones.add(temp);
				armature.addBoneFromGroup(temp);
				editor.addSelecotr(temp);
			}
			}
			armature.removeBone(bone);
			editor.removeSelector(bone);
			}else{
				break;
			}
		}
		this.bones=tempBones;
		
		
	}
	
	public int size(){
		return this.bones.size();
	}
	
	public void setToFormerGroup(){
		int i = 0;
		while(i<bones.size()){
			Selector bone = bones.get(i);
			editor.removeSelector(bone);
			armature.removeBone(bone);
			i++;
		}
		this.bones= formerGroup.getBones();
		this.editor=formerGroup.getEditor();
		this.armature=formerGroup.getAramture();
		this.formerGroup= formerGroup.getFormerGroup();
	}
	

	public MovieEditor getEditor(){
		return this.editor;
	}
	
	public Selector getAramture(){
		return this.armature;
	}
	public ArrayList<Selector> getBones(){
		return this.bones;
	}
	
	public BonesGroup getFormerGroup(){
		return this.formerGroup;
	}



	public void removeBone(Selector bone) {
		bones.remove(bone);
		
	}
	
	public void clean(){
		int i = 0;
		ArrayList<Selector> deleteBones = new ArrayList<Selector>();
		SpriteSheet ss = editor.getSpriteSheet();
		while(i<bones.size()){
		BufferedImage img =	ss.grabImage(bones.get(i).getFrame());
		boolean noRbj=true;
		for(int j = 0;j<img.getHeight();j++){
				for(int k=0;k<img.getWidth();k++){
					if(img.getRGB(k, j)!=0){
						noRbj=false;
						break;	
					}
				}
				if(!noRbj){
					break;
				}
			}
		
		if(noRbj){
		deleteBones.add(bones.get(i));	
		}
			i++;
		}
		
		i=0;
		while(deleteBones.size()>i){
			
			editor.removeSelector(deleteBones.get(i));
			armature.removeBone(deleteBones.get(i));
		i++;
		}
	
	}
	
	public int getFomrersGroupSize(){
	return this.formersGroup.size();
	}



	public void setColor(Color color) {
		this.color=color;
		for(Selector bone:bones){
			bone.setColor(color);
		}
		
	}





	public int getDefence() {
		return defence;
	}





	public void setDefence(int defence) {
		this.defence = defence;
		int i=  0;
		while(i<bones.size()){
			Selector selector = bones.get(i);
			selector.setDefence(defence);
			i++;
		}
	}





	public int getAttack() {
		return attack;
	}





	public void setAttack(int attack) {
		this.attack = attack;
		int i = 0 ;
		while(i<bones.size()){
			Selector selector = bones.get(i);
			selector.setAttack(attack);
			i++;
		}
	}
	
	public void setAttackNoBones(int attack){
		this.attack=attack;
	}
	
	public void setDefenceNoBones(int defence){
		this.defence=defence;
	}
	
	public void setSolidNoBones(boolean solid){
		this.solid=solid;
	}





	public boolean isSolid() {
		return solid;
	}





	public void setSolid(boolean solid) {
		this.solid = solid;
		 int i = 0;
		 while(i<bones.size()){
			Selector selector = bones.get(i);
			selector.setSolid(solid);
			 i++;
		 }
	}

	public void remove() {
	while(bones.size()>0){
		armature.removeBone(bones.get(0));
		bones.remove(bones.get(0));
		editor.removeSelector(bones.get(0));
	}
		
	}
	


}
