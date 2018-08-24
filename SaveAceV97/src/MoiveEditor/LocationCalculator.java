package MoiveEditor;

public class LocationCalculator {
	
	public static int getPxByCordinate(double x, double width,double imgWidth){
		double px = x*width/imgWidth+1; // an = a1 + d(n-1) --> d = width/imgWidth  an = px  a1 =0
		return (int)px;
	}
	
	public static int getCordinateByPx(double x, double width , double imgWidth){
		double cr = width/imgWidth*(x-1); // same algoritam as getPxByCordinate 
		return (int)cr;
	}
	
	
	
	public static int getPxByCordinate(double x, double width,double imgWidth, double a1){
	
		double d = imgWidth/width;
		double px = (x-a1)/d+1; // an = a1 + d(n-1) -->   an = n
		return (int)px;
	}
	
	public static int getCordinateByPx(double x, double width , double imgWidth,double a1){
	
		double d = imgWidth/width;
		double cr = a1+d*(x-1); //an = a1 + d(n-1) --> cr==an
		return (int)cr;
	}
	
	

}
