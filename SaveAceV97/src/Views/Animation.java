package Views;

public class Animation {
	private int x,maxX,startX;

	private double delta,totalDelta,xkey=0.1;
	private boolean animate =false;
	private AnimationID id;
	private boolean positive = true;

	
	public Animation(int x, int maxX,double delta){
		this.setX(x);
		this.setMaxX(maxX);
		this.delta=delta;
		id = id.Asymptote;
		if(maxX<x){
			positive=false;
		}
		this.startX=x;
	}
	
	public void animate(){
	
		switch(id){
		case Asymptote:
			if(positive){
			if(x<maxX){
				
					double t  =  delta/xkey;
					totalDelta+=t;
					x+=t;
				
					if(xkey<0.9){
						xkey +=0.1;
					}
					
				}else{
					
					x=maxX;
					totalDelta = maxX;
					animate =false;
					xkey =0.1;
				}
				}else{
					if(x>maxX){
						
						double t  =  delta/xkey;
						totalDelta+=t;
						x+=t;
					
						if(xkey<0.9){
							xkey +=0.1;
						}
						
					}else{
					
						x=maxX;
						totalDelta = maxX;
						animate =false;
						xkey =0.1;
					}
					}
		
			break;
		case linear:
			if(this.positive){
				if(x<maxX){
					totalDelta+=delta;
					x+=totalDelta;
				}else{
					x=maxX;
					animate= false;
				}
			}else{
				if(x>maxX){
					totalDelta+=delta;
					x+=totalDelta;
				}else{
					x=maxX;
					animate= false;
				}
			}
		
			break;
		
		default :
			break;
		}

	}
	
	

	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getMaxX() {
		return maxX;
	}


	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}


	public double getTotalDelta() {
		return totalDelta;
	}


	public void setTotalDelta(double totalDelta) {
		this.totalDelta = totalDelta;
	}
	
	public void tick(){
		if(animate){	
			animate();
		}else{
			this.x=startX;
			this.totalDelta=0;
			xkey = 0.1;
		
		}
	}

	public void rest(){
		this.x=startX;
		this.totalDelta=0;
		xkey = 0.1;
	}



	public boolean isAnimate() {
		return animate;
	}




	public void setAnimate(boolean animate) {
		this.animate = animate;
	}
	
	public void start(){
		this.animate=true;
	}

}
