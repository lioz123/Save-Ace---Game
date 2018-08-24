package Editors;

import java.awt.image.BufferedImage;

public class CounterButton extends Button {
	protected int value,delta,maxValue,minValue;
	public CounterButton(BufferedImage img, int x, int y, int width, int height,Type type,int value,int delta,int maxValue,int minValue) {
		super(img, x, y, width, height, type);
		this.value=value;
		this.delta=delta;
		this.maxValue=maxValue;
		this.minValue=minValue;
	}
	
	public int getMaxValue(){
		return this.maxValue;
	}
	
	public void setMaxValue(int value){
		this.maxValue=value;
	}
	
	
	
	

	@Override
	public boolean onClick(int x,int y) {
		if(isClicked(x, y)){
			if(type==Type.counterUpDown&&y>this.y+height/2||type==Type.counterRightLeft&&x<this.x+width/2){
				 if(delta<0&&value<maxValue||delta>0&&value>minValue){
					 this.value-=delta;
				 }
		}else if(delta>0&&value<maxValue||delta<0&&value>minValue){
			this.value+=delta;	
			
		}
	 if(value>maxValue){
		value=maxValue;
	}else if(value<minValue){
		value = minValue;
	 }
	return true;
		
	}
	return false;
	}

	public int getValue(){
		return this.value;
	}
	
	public int getDelta(){
		return this.delta;
	}

	public void setValue(int val) {
		this.value=val;
		
	}
	
	public void setDelta(int x){
		this.delta=x;
	}
	
	public void setDeltaNoChargeChange(int x){
		if(this.delta>0&&x>0||delta<0&&x<0){
			this.delta = x;
		}else{
			this.delta=-x;
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	

}
