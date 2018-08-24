package MapTrazlation;

import java.awt.Color;
import java.util.ArrayList;

public class ColorList {
	private static ArrayList<Color> colors = new ArrayList<Color>();
	public ColorList(){
		colors.add(Color.green);
		colors.add(Color.YELLOW);
		colors.add(Color.blue);
		colors.add(Color.CYAN);
		colors.add(Color.pink);
		colors.add(Color.magenta);
		colors.add(Color.orange);
	}
	
	public static Color getColor(int x){
		while(x>colors.size()-1){
			x=x-colors.size()+1;
		}
		return colors.get(x);
	}
	
}
