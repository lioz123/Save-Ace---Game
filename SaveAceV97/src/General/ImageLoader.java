package General;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	private BufferedImage img;
	public BufferedImage loadImage(String path){
	  try {
		img = ImageIO.read(this.getClass().getResource(path));
		return img;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
	 
	}
	

}
