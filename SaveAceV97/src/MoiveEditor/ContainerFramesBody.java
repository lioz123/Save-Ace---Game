package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.util.ArrayList;

import javax.xml.transform.Templates;

import Editors.CounterButton;
import Editors.NormalButton;
import General.Utilities;
import MapTrazlation.SpriteSheetList;

public class ContainerFramesBody extends ContainerBody{
	private ArrayList<Frame> frames;
	private int frameWidth=40 , frameHeight =40,maxFrames;
	private CounterButton upDownFrameDisplay,moveFrame;
	private NormalButton delete,duplicate;
	public ContainerFramesBody(int x, int y, int width, int height,MovieEditor editor) {
		super(x, y, width, height);
		this.editor = editor;
		frames = new ArrayList<Frame>();
			delete = new NormalButton(null,x+width-55,y+height-30,50,25,Editors.Type.noraml);
			delete.setName("delete");
			delete.setNameColor(Color.black);
			delete.setFont(new Font("Tahoma" , Font.PLAIN,14));
			delete.setDeltaNameY(delete.getHeight()/2+5);
			delete.setDeltaNameX(5);
			
			duplicate= new NormalButton(null,x+width-55-60,y+height-30,55,25,Editors.Type.noraml);
			duplicate.setName("duplicate");
			duplicate.setNameColor(Color.black);
			duplicate.setFont(new Font("Tahoma" , Font.PLAIN,12));
			duplicate.setDeltaNameY(duplicate.getHeight()/2+5);
			duplicate.setDeltaNameX(4);
			buttons.add(duplicate);
			buttons.add(delete);
			double mfx = (double)width/(double)(frameWidth+10);
		
			double mfy= (double)height/(double)(frameHeight+10);
			maxFrames=(int)((mfx-1)*mfy);
			upDownFrameDisplay = new CounterButton(SpriteSheetList.upDownArras.getImage(),210,height+25,40,40,Editors.Type.counterUpDown,0,-maxFrames,9999,0);
			moveFrame = new CounterButton(SpriteSheetList.rightLeftArraws.getImage(),x+5,y+height-30,40,40,Editors.Type.counterRightLeft,0,1,0,0);
			buttons.add(moveFrame);
			buttons.add(upDownFrameDisplay);
	}
	


	
	public void tick(){
		ArrayList<Frame> frames = new ArrayList<Frame>();
		
		int i=0,fx = x+5,fy=y+5,counter=0;
		ArrayList<Selector> tempSelectors = new ArrayList<Selector>();
		while(i<editor.getSelectors().size()){
			Selector selector =editor.getSelectors().get(i);
			if(!selector.isBone()){
				tempSelectors.add(selector);
			}
			i++;
		}
		selectors = tempSelectors;
		i=0;
		while(i<selectors.size()){
			if(counter==maxFrames){
				fx = x+5;
				fy=y+5;
				counter=0;
			}
			Selector selector = selectors.get(i);
			if(selector!=null&&selector.getFrame()!=null){
				
			
			if(selector.isSaved()&&selector.getFrame().getWidth()>0&&selector.getFrame().getHeight()>0){
				Frame frame = new Frame(fx,fy,frameWidth,frameHeight,selector.getFrame(),editor.getSpriteSheet());
				frames.add(frame);
				if(fx+frame.getWidth()+5<=width){
					fx+=frame.getWidth()+5;
				}else{
					fx = x+5;
					fy+=frame.getHeight()+5;
				}
			}
			}
				i++;
				counter++;
			
		}
		this.frames=frames;
		super.tick();
	
	}
	

	
	public void render(Graphics2D g2d){
		super.render(g2d);
	//	upDownFrameDisplay.render(g2d);
		if(!close){
			int i = 0;
			i = upDownFrameDisplay.getValue();
			while(i<frames.size()){
			
				if(i<upDownFrameDisplay.getValue() +maxFrames){
		
				
					Frame frame = frames.get(i);
					frame.setY(frame.getMaxY()+height-maxHeight);
						if(frame.getY()+frame.getHeight()<=y+height&&frame.getY()>y){
							frame.render(g2d);
							g2d.setColor(Color.BLACK);
							if(moveFrame.getValue()==i){
								g2d.setColor(Color.YELLOW);
							}
							g2d.drawRect(frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());
						}
				
				}
		
				i++;
			}
			
			
		}
		
	}
	
	public boolean onClick(int x,int y){
		if(!opening&&show){
			
	
		moveFrame.setMaxValue(frames.size()-1);
		int frameIndex = moveFrame.getValue();
		boolean isClicked =super.onClick(x, y);
		int i = upDownFrameDisplay.getValue(); 
		if(i>=0&&i<frames.size()){
			while(i<frames.size()){
				if(i<upDownFrameDisplay.getValue()+maxFrames){
					Frame frame = frames.get(i);
					if(frame.onClicked(x,y)){
						
						moveFrame.setValue(i);
					}
				}
			
				i++;
			}
		}
		
		
		if(moveFrame.isClicked(x, y)){
			if(frameIndex<frames.size()){
				if(frameIndex>=0&&frameIndex<frames.size()){
							Selector temp = selectors.get(frameIndex);
							selectors.set(frameIndex, selectors.get(moveFrame.getValue()));
							selectors.set(moveFrame.getValue(), temp);
							
						}
			}
			
		}
		
		if(delete.isClicked(x, y)){
			if(moveFrame.getValue()>=0&&moveFrame.getValue()<selectors.size())
			selectors.remove(moveFrame.getValue());
			editor.removeSelector(moveFrame.getValue());
		}
		if(duplicate.isClicked(x, y)){
			
			if(moveFrame.getValue()>=0&&moveFrame.getValue()<selectors.size()){
				ArrayList<Selector> selectors = new ArrayList<Selector>();
				 i =0;
				while(i<=moveFrame.getValue()){
				selectors.add(this.selectors.get(i));
				i++;
				}
				Selector selector = new Selector(this.selectors.get(moveFrame.getValue()));
				selector.setFrame(this.selectors.get(moveFrame.getValue()).getFrame());
				selectors.add(selector);
				while(i<this.selectors.size()){
					selectors.add(this.selectors.get(i));
				i++;
				}
				
				this.selectors=selectors;
				editor.setSelectors(selectors);
			}
			
		}
		return isClicked;
		}
		return false;
		
		
	}
	

}
