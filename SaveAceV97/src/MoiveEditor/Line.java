package MoiveEditor;

import java.awt.image.BufferedImage;

public class Line {
private PointInt start,end;
public Line(PointInt start, PointInt end){
	int sx,sy,ex,ey;
	if(start.getX()<end.getX()){
		sx= start.getX();
		ex= end.getX();
	}else{
		ex=start.getX();
		sx = end.getX();
	}
	
	if(start.getY()<end.getY()){
		sy=start.getY();
		ey = end.getY();
	}else{
		sy = end.getY();
		ey = start.getY();
	}
	this.start=new PointInt(sx,sy);
	this.end= new PointInt(ex,ey);
}

public boolean isTouchingImgPx(BufferedImage img){
	for(int i = start.getY();i<end.getY();i++){
		for(int j = start.getX();j<end.getY();j++){
			if(img.getRGB(j,i)!=0){
				return true;
			}
		
		}
	}
	
	return false;
}
}
