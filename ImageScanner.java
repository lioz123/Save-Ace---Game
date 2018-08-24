import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageScanner {
	private SpriteSheet ss;
	private int background;
	private ArrayList<CharModule> chars;
	
	public ImageScanner(SpriteSheet ss){
		this.ss=ss;
		chars = new ArrayList<CharModule>();
	}
	
	public void scanImage(){
		BufferedImage img = ss.getImage();
		background = img.getRGB(0, 0);
	}
	
	
	
	
	
	
}
