package MoiveEditor;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import javax.xml.transform.Templates;

import General.Utilities;

public class ImageScanner {
	
	private BufferedImage img;
	private int backGround = 0;
	public ImageScanner(BufferedImage img){
		this.img=img;
	}
	
	public void setBackGround(int backGround){
		this.backGround = backGround;
	}
	
	
	public ArrayList<Point> getShellPoints(){
		ArrayList<Point> shellPoints = new ArrayList<Point>();
		ArrayList<Point > coloredPoints = new ArrayList<Point>();
	
		ArrayList<Rectangle> shellRecs = new ArrayList<Rectangle>();
		ArrayList<Point> numberOfPoints = new ArrayList<Point>();
		int width =img.getWidth();
		int height = img.getHeight();
		for(int i =0;i<height;i++){
			for(int j = 0; j< width;j++){
	
				numberOfPoints.add(new Point(j,i));
				boolean isshellPoint =false;
				if(img.getRGB(j, i)!=backGround){
					coloredPoints.add(new Point(j,i));
					if(i>0&&j>0&&!isshellPoint){
					if(img.getRGB(j-1, i-1)==backGround){
						shellPoints.add(new Point(j,i));
						isshellPoint = true;
					}
				}
					if(i>0&&j<width-1&&!isshellPoint){
				
						if(img.getRGB(j+1, i-1)==backGround){
							
							shellPoints.add(new Point(j,i));
							isshellPoint = true;
						}
					}
					
					if(i<img.getHeight()-1&&j>0&&!isshellPoint){
						if(img.getRGB(j-1, i+1)==backGround){
							shellPoints.add(new Point(j,i));
							isshellPoint = true;
						}
					
					}
					
					if(i<img.getHeight()-1&&j<img.getWidth()-1&&!isshellPoint){
						if(img.getRGB(j+1, i+1)==backGround){
							shellPoints.add(new Point(j,i));
							isshellPoint = true;
						}
					}
					
					
					if(i<img.getHeight()-1&&!isshellPoint){
						if(img.getRGB(j,i+1)==backGround){
							shellPoints.add(new Point(j,i));
							isshellPoint = true;
						}
					}
					
					if(i>0&&!isshellPoint){
						if(img.getRGB(j, i-1)==backGround){
							shellPoints.add(new Point(j,i));
							isshellPoint = true;
						}
					}
					
					if(j>0&&!isshellPoint){
						if(img.getRGB(j-1, i)==backGround){
							shellPoints.add(new Point(j,i));
							isshellPoint = true;
						}
					}
					
					if(j<img.getWidth()-1&&!isshellPoint){
						if(img.getRGB(j+1, i)==backGround){
							shellPoints.add(new Point(j,i));
							isshellPoint = true;
						}
					}
				
			}
			}
		}
	
		return shellPoints;
	}
	
	public boolean isPointMin(Point point , ArrayList<Point> points){
			double minX,minY;
			if(points==null || points.size()==0){
				return false;
			}
			minX=points.get(0).getX();
			minY=points.get(0).getY();
			
		for(Point temp : points){
			if(temp.getX()<minX){
				minX=temp.getX();
			}
			if(temp.getY()<minY){
				minY=temp.getY();
			}
		}
		if(minY==point.getY()&&minX==point.getX()){
			return true;
		}
		return false;
	
	}
	
	
	public boolean isPointMax(Point point , ArrayList<Point> points){
		double maxX,maxY;
		if(points==null || points.size()==0){
			return false;
		}
		maxX=points.get(0).getX();
		maxY=points.get(0).getY();
		
	for(Point temp : points){
		if(temp.getX()>maxX){
			maxX=temp.getX();
		}
		if(temp.getY()>maxY){
			maxY=temp.getY();
		}
	}
	if(maxY==point.getY()&&maxX==point.getX()){
		return true;
	}
	return false;

}
	
	
	
	
	
	
	public ArrayList<Rectangle> getShellRectangles(){
		ArrayList<Point> shellPoints = getShellPoints();
		
		ArrayList<ArrayList<Point>> pArr= new ArrayList<ArrayList<Point>>();
		ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
		int i =0;
		while(i<shellPoints.size()){
			Point point = shellPoints.get(i);
			ArrayList<ArrayList<Point>> tempPointsArr = PointInArrayList(point, pArr);
			if(tempPointsArr.size()<2){
				if(tempPointsArr.size()>0){
				double x=	tempPointsArr.get(0).get(0).getX()-point.getX();
					if(x!=0){
					ArrayList<Point> points=	getSequencePointsOnRangeIntoArrayList(point, shellPoints,1,'y');
					if(points.size()>1){
						pArr.add(points);
					}
				
					}else{
						ArrayList<Point> points=	getSequencePointsOnRangeIntoArrayList(point, shellPoints,1,'x');
						if(points.size()>1){
							pArr.add(points);
						}
					}
					
				}else{
					ArrayList<Point> pointsX=getSequencePointsOnRangeIntoArrayList(point, shellPoints,1,'x');
					ArrayList<Point> pointsY=getSequencePointsOnRangeIntoArrayList(point, shellPoints,1,'y');
					if(pointsX.size()>1){
						pArr.add(pointsX);
					}
					
					if(pointsY.size()>1){
						pArr.add(pointsY);
					}
					
					if(pointsY.size()==1&&pointsX.size()==1){
						pArr.add(pointsX);
					}
					
					
				}
			 	
			}
			
			i++;
		}
		
		
	/*	i=0;
		while(i<pArr.sze()){
			int j = 0;
			ArrayList<Point> points = pArr.get(i);
			while(j<points.size()){
				Point point = points.get(j);
				ArrayList<ArrayList<Point>> tempPArr = PointInArrayList(point, pArr);
				if(tempPArr.size()==2){
				if(isPointMax(point, tempPArr.get(0))||isPointMin(point,tempPArr.get(0))){
					tempPArr.get(0).remove(point);
					j--;
				}else if(isPointMax(point, tempPArr.get(1))||isPointMin(point,tempPArr.get(1))){
					tempPArr.get(1).remove(point);
					j--;
				}
				
				}
				j++;
			}
			
			i++;
		}
		*/
		for(ArrayList<Point> points:pArr){
			rects.add(getPointsIntoRectangleLine(points));
		}
		
	
		return rects;	
	}
	
	public ArrayList<ArrayList<Point>> PointInArrayList(Point point,ArrayList<ArrayList<Point>> pArr){
		ArrayList<ArrayList<Point>> arrays = new ArrayList<ArrayList<Point>>();
		for(ArrayList<Point> tempArr:pArr){
			for(Point temp : tempArr){
				if(temp.getX()==point.getX()&temp.getY()==point.getY()){
				arrays.add(tempArr);
				break;
				}
			}
		}
		return arrays;
	}
	
	
	public ArrayList<Point>  getSequencePointsOnRangeIntoArrayList(Point point,ArrayList<Point> points, int range,char axs){
		boolean finished =false;
		ArrayList<Point> openList = Utilities.getPointsOnDistance(point, points,range,axs);
		ArrayList<Point> closedList = new ArrayList<Point>();
		ArrayList<Point> tempOpenList = new ArrayList<Point>();
		closedList.add(point);
	
		while(openList.size()>0){
			int i = 0;
			while(i<openList.size()){
				Point open = openList.get(i);
					ArrayList<Point> tempPoints = new ArrayList<Point>();
					tempPoints = Utilities.getPointsOnDistance(open, points, range, axs);
					for(Point temp :  tempPoints){
						if(!Utilities.PointInArrayList(closedList,temp)){
							tempOpenList.add(temp);
						}
					}
					closedList.add(open);
				
				i++;
			}
			openList= tempOpenList;
			tempOpenList= new ArrayList<Point>();
			
		}

		return closedList;
	}
	
	
	public Rectangle getPointsIntoRectangleLine(ArrayList<Point> points){
		if(points.size()==0){
			return null;
		}
		Point firstPoint=  points.get(0);
		Point lastPoint  = points.get(0);
		double maxx=points.get(0).getX(),minx=points.get(0).getX(),maxy=points.get(0).getY(),miny=points.get(0).getY();
		for(Point point:points){
			if(point.getX()<minx){
				minx=point.getX();
			}
			if(point.getX()>maxx){
				maxx=point.getX();
			}
			
			if(point.getY()>maxy){
				maxy=point.getY();
			}
			if(point.getY()<miny){
				miny=point.getY();
			}
		}
		
		double width ,height;
		width = maxx-minx;
		height = maxy-miny;
		return new Rectangle((int)minx,(int)miny,(int)width+1,(int)height+1);
	}
	
	
	public ArrayList<Rectangle> PointsIntoRectangle(ArrayList<Point> points){
		ArrayList<Rectangle> rects = new ArrayList<Rectangle>();
		for(Point point : points){
			rects.add(new Rectangle((int)point.getX(),(int)point.getY(),1,1));
			
		}
		return rects;
	}
	
	
	
	
	
	
	
	public Rectangle getCharacterBoundsFromImage(){// return rectngle that pressent the character or an object bounds inside an image 
		int maxX=0, maxY=0, minX=0,minY=0;
		boolean foundPx = false;
		for(int i = 0 ; i<img.getHeight();i++){
			for(int j= 0; j < img.getWidth();j++){
				if(!foundPx){
					if(img.getRGB(j, i)!=backGround){
						maxX=j;
						minX=j;
						maxY=i;
						minY=i;
						foundPx=true;
					}
				}
				
				if(foundPx){
					if(img.getRGB(j, i)!=backGround){
					if(j>maxX){
						maxX=j;
					}else if(j<minX){
						minX=j;
					}
					
					if(i>maxY){
						maxY=i;
					}else if(i<minY){
						minY=i;
					}
					}
				}
				
			}
		}
		int width  = maxX- minX+1;
		int height = maxY-minY+1;
		
		return new Rectangle(minX,minY,width,height);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
