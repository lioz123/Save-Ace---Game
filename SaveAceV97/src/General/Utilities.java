package General;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.util.ArrayList;

import GameObjects.Side;
import MoiveEditor.PointInt;
public class Utilities {
	


	public static int clamp(int x, int max, int min){
		if(x>max) return max;
		else if(x<min) return min;
		else return x;
	}
	
	public static Side getSideX(int x1,int x2){
		if(x1>x2){
			return Side.right;
		}
		return Side.left;
	}
	
	public static Side getSideY(int y1,int y2){
		if(y1>y2){
			return Side.down;
		}
		return Side.up;
	}
	
	public static Side getCollsionAxis(Rectangle r1, Rectangle r2){
		if(Math.sqrt(Math.pow(r1.getX()-r2.getHeight(),2))<=r1.getWidth()){
			return Side.x;
		}
		else if(Math.sqrt(Math.pow(r1.getY()-r2.getY(),2))<=r1.getHeight()){
			return Side.y;
		}
		return Side.none;
		
	}
	
	public static Point getMiddle(Rectangle rect){
		double x = rect.getWidth()/2 + rect.getX();
		double y = rect.getHeight()/2+rect.getY();
		return new Point((int)x,(int)y);
	}
	
	public static boolean isContained(Rectangle container, Rectangle contained){
		if(container.getX()<contained.getX()&&container.getY()<contained.getY()){
			return true;
		}
	
		return false;
	}
	
	public static boolean isClicked(int x, int y, Rectangle rect){
		if(x>rect.getX()&&x<rect.getX()+rect.getWidth()&&y>rect.getY()&&y<rect.getY()+rect.getHeight()){
			return true;
		}
		
		return false;
	}
	
	public static Rectangle getRectangle(PointInt start, PointInt end){
		int x,y,width,height;
		if(start.getX()>end.getX()){
			x=end.getX();
		}else{
			x=start.getX();
		}
		if(start.getY()>end.getY()){
			y=end.getY();
		}else{
			y=start.getY();
		}
		width = Math.abs(start.getX()-end.getX());
		height = Math.abs(start.getY()-end.getY());
		
		return new Rectangle(x,y,width,height);
	}
	
	
	public static boolean isNumeber(String number){
		char[] numbers = number.toCharArray();
		for(char c:numbers){
			boolean isNumber = false;

			for(char i = '0';i<='9';i++){
				if(c==i){
					isNumber=true;
				}
			}
			if(!isNumber){
				return false;
			}
		}
		
		return true;
	}
	
	public static Rectangle getMiddleBounds(Rectangle containing,Rectangle contained){
		double x,y,width,height;
		x= + containing.getX()+(containing.getWidth()-contained.getWidth())/2;
		y = containing.getY()+ (containing.getHeight()-contained.getHeight())/2;
		return new Rectangle((int) x, (int)y,(int)contained.getWidth(),(int)contained.getY());
	
	}
	


	
	
	public static Rectangle getBoundsFromImageToWindow(Rectangle window,Rectangle imageBounds, Rectangle panelBounds){
		double x,y,divX,divY,width,height,subX,subY;
		width = window.getWidth()*imageBounds.getWidth()/panelBounds.getWidth();
		height  = window.getHeight()*imageBounds.getHeight()/panelBounds.getHeight();
		subX=imageBounds.getX()-panelBounds.getX();
		subY=imageBounds.getY()-panelBounds.getY();
		x=subX*window.getWidth()/panelBounds.getWidth()+window.getX();
		y=subY*window.getHeight()/panelBounds.getHeight()+window.getY();
		
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
		
	}
	
	public static Rectangle getStringBounds(Graphics2D g2d, String str, int x, int y){
		   FontRenderContext frc = g2d.getFontRenderContext();
	        GlyphVector gv = g2d.getFont().createGlyphVector(frc, str);
	        return gv.getPixelBounds(null, x, y);
	}
	
	
	
	public static boolean PointInArrayList(ArrayList<Point> points, Point point){
		for(Point temp : points){
			if(temp.getX()==point.getX()&&temp.getY()==point.getY()){
				return true;
			}
		}
		
		return false;
	}
	
	
	public static ArrayList<Point> getPointsOnDistance(Point point ,ArrayList<Point> points, int distance){
	 ArrayList<Point> pointsOnDistance = new ArrayList<Point>();
	 for(Point temp:points){
		 if(Distance(temp,point)==distance){
			 pointsOnDistance.add(temp);
		 }
	 }
	 
	 return pointsOnDistance;
	}
	
	public static ArrayList<Point> getPointsOnDistance(Point point ,ArrayList<Point> points, int distance ,char axs){
		 ArrayList<Point> pointsOnDistance = new ArrayList<Point>();
		 for(Point temp:points){
			 if(axs=='x'){
				 if(temp.getX()-point.getX()==distance||temp.getX()-point.getX()==-distance){
					 if(temp.getY()-point.getY()==0){
						 pointsOnDistance.add(temp);
					 }
				 }
			 }else{
				 if(temp.getY()-point.getY()==distance||temp.getY()-point.getY()==-distance){
					 if(temp.getX()-point.getX()==0){
						 pointsOnDistance.add(temp);
					 }
				 }
			 }
		 }
		 
		 return pointsOnDistance;
		}
		
	
	
	public  static double Distance(Point p1 , Point p2){
		double d =  Math.sqrt(Math.pow(2, p1.getX()-p2.getX()) + Math.pow(2, p1.getY()-p2.getY()));
			return d;
	}

	
	
	
	
	
	
	

}
