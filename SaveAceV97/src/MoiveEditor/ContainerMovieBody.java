

package MoiveEditor;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.peer.FileDialogPeer;
import java.util.ArrayList;

import javax.swing.filechooser.FileView;

import Editors.Type;
import GameObjects.Side;
import Editors.NormalButton;
import General.Film;
import General.ImageData;
import MapTrazlation.SpriteSheetList;

public class ContainerMovieBody extends ContainerBody{
	private Film film = null;
	private ImageData img;
	private boolean start =false;
	private NormalButton startFilm,flipVertical;
	private double nimageheight,nimagewidth; // n = normal
	private CounterButtonDisplayValue fpsCounter;
	private Rectangle normalBounds;
	private Side side= Side.right;
	public ContainerMovieBody(int x, int y, int width, int height,MovieEditor editor) {
		super(x, y, width, height);
		selectors=editor.getSelectors();
		film = new Film();
		normalBounds = new Rectangle(10,10,10,10);
		super.editor=editor;
		startFilm = new NormalButton(SpriteSheetList.runIcon.getImage(),x,y+height-60,50,50,Type.noraml);
		buttons.add(startFilm);
		nimageheight = 50;
		nimagewidth = 50;
		fpsCounter = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width-80, y+height-45,40,40, Type.counterUpDown, 6, 1, 60, 1);
		fpsCounter.setRect(new Rectangle(fpsCounter.getX()+fpsCounter.getWidth()-10,fpsCounter.getY(),40,40));
		fpsCounter.setName("fps");
		fpsCounter.setDeltaNameX(35);
		fpsCounter.setDeltaNameY(-5);
		fpsCounter.setFont(new  Font("Tahoma",Font.PLAIN,15));
		flipVertical = new NormalButton(null,x+140,y+height-25, 80,20,Type.noraml);
		flipVertical.setName("flip vertical");
		flipVertical.setFont(new Font("Tahoma",Font.PLAIN,15));
		flipVertical.setDeltaNameX(5);
		flipVertical.setDeltaNameY(14);
		buttons.add(flipVertical);
	
		buttons.add(fpsCounter);
		
	}
	
	public void tick(){
		super.tick();
		int i = 0;
		ArrayList<Selector> tempSelectors = new ArrayList<Selector>();
		while(i<editor.getSelectors().size()){
			if(!editor.getSelectors().get(i).isBone()){
			tempSelectors.add(editor.getSelectors().get(i));
			}
			i++;
		}
		this.selectors=tempSelectors;
		Film tempFilm = new Film();
		i=0;
		while(i<selectors.size()){
			
			Selector selector = selectors.get(i);
			if(selector.isSaved()){
				ImageData data = new ImageData(selector.getFrame());
				data.setDeltaX((int)selector.getFrameDeltaBounds().getWidth());
				data.setDeltaY((int)selector.getFrameDeltaBounds().getHeight());
				
				tempFilm.addImage(data);
				
				
			}
			i++;
		}
			
			if(start){
				film.setFrames(tempFilm.getFrames());
				img =film.run();
			}else{
				film.stop();
			}
			
		
	}
	
	public void render(Graphics2D g2d){
		
		setNormalBounds();
		if(normalBounds==null){
			return;
		}
		super.render(g2d);
		if(close){
			return;
		}
	
		if(img!=null){
			
			int x = img.getImageX();
			int y=  img.getImageY();
		
			int maxY = this.x+150;
			double rectHeight =150,rectWidth;
			double dx=img.getDeltaX(), dy=img.getDeltaY();
			double normalHeight = normalBounds.getHeight();
			double normalWidth = normalBounds.getWidth();
			double deltaHeight;
			double width = img.getImageWidth();
			double height = img.getImageHeight();
			deltaHeight=height- normalHeight;
			rectWidth = rectHeight*normalBounds.getWidth()/normalBounds.getHeight();
			Rectangle rect =new Rectangle(this.x,this.y+50,(int)rectWidth,(int)rectHeight);
			double divHeight = normalHeight/height;
			
			double divWidth = normalWidth/width;
			double ch = rect.getHeight()/divHeight;
			double cw =rect.getWidth()/divWidth;
			
			int vheight=(int)ch;
			int vwidth=(int)cw;
			int cy = -vheight +(int)rect.getHeight()+(int)rect.getY();
			double cx;
		
			double dw = dx;
			
			double divdx = dw/ (double)img.getImageWidth();
			dx=0;
			dy=0;
			dy/=divHeight;
			if(side ==Side.left){
				cx = this.width+this.x;
				vwidth = - vwidth;
				
			}else{
				cx= this.x+100;
				dw = divdx *vwidth;
				cx-=dw;
				
			
			}
			
			if(vheight>this.height){
				vheight=this.height;
			}
		
			g2d.drawImage(editor.getSpriteSheet().grabImage((int)x, (int)y, (int)width, (int)height),(int)cx-(int)dx,(int)cy-(int)dy,(int)vwidth,(int)vheight,null);
			g2d.drawRect((int)cx,cy,vwidth,vheight);
		}

	}
	
	public boolean onClick(int x, int y){
		if(!opening&&show){
		boolean isClicked =super.onClick(x, y);
		if(startFilm.isClicked(x, y)){
			start=!start;
		}
		if(fpsCounter.isClicked(x, y)){
			double fpsOposite = fpsCounter.getValue();
		
			film.setFps((int)fpsOposite);
			editor.setFps(fpsCounter.getValue());
		}
		if(flipVertical.isClicked(x, y)){
			if(side ==Side.right){
				side = Side.left;
			}else{
				side =Side.right;
			}
		}
		
		return isClicked;
		}
		return false;
		}
	
	public void setNormalBounds(){
		if(editor == null){
			return ;
		}
		
		if(editor.getNormlSelectorBounds()!=null){
			this.normalBounds=editor.getNormlSelectorBounds().getFrame();
			return;
		}
		
		if(editor.getSelectors()!=null&&editor.getSelectors().size()>0){
			this.normalBounds=editor.getSelectors().get(0).getFrame();
		}
		
		
	}

}
