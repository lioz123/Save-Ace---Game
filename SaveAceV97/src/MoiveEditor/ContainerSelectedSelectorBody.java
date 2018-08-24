package MoiveEditor;

import java.awt.Color;
import Editors.Button;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.util.ArrayList;

import javax.print.attribute.TextSyntax;
import javax.xml.transform.Templates;

import Editors.CounterButton;
import Editors.NormalButton;
import Editors.SaveButton;
import General.ImageData;
import General.Utilities;
import MapTrazlation.SpriteSheetList;

public class ContainerSelectedSelectorBody extends ContainerBody{
	private ArrayList<Frame> frames;
	private Frame frame=null;
	private Selector selector;
	private CounterButtonDesign xCounter,yCounter,wCounter,hCounter,dh,dw,delta;
	private ButtonText shrink;
	
	private Font px12 = new Font("Tahoma" , Font.PLAIN,12);

	public ContainerSelectedSelectorBody(int x, int y, int width, int height,MovieEditor editor) {
		super(x, y, width, height);
		this.editor = editor;
	
		xCounter= new CounterButtonDesign(SpriteSheetList.rightLeftArraws.getImage(), x+5, y+height-45, 20, 20, Editors.Type.counterRightLeft, 0, -1,100000, 0);
		xCounter.setRectBounds(30, xCounter.getHeight());
		xCounter.setName("X");
		xCounter.setDeltaNameX(35);
		xCounter.setDeltaNameY(35);
		xCounter.setFont(px12);
		
		yCounter= new CounterButtonDesign(SpriteSheetList.upDownArras.getImage(), x+45, y+height-25, 20, 20, Editors.Type.counterUpDown, 0, 1,100000, 0);
		yCounter.setRectBounds(30, yCounter.getHeight());
		yCounter.setName("Y");
		yCounter.setDeltaNameX(55);
		yCounter.setDeltaNameY(14);
		yCounter.setFont(px12);
		
		wCounter= new CounterButtonDesign(SpriteSheetList.rightLeftArraws.getImage(), x+110, y+height-45, 20, 20, Editors.Type.counterRightLeft, 0, -1,100000, 0);
		wCounter.setRectBounds(30, wCounter.getHeight());
		wCounter.setName("W");
		wCounter.setDeltaNameX(35);
		wCounter.setDeltaNameY(35);
		wCounter.setFont(px12);
		
		hCounter= new CounterButtonDesign(SpriteSheetList.upDownArras.getImage(), x+160, y+height-25, 20, 20, Editors.Type.counterUpDown, 0, 1,100000, 0);
		hCounter.setRectBounds(30, hCounter.getHeight());
		hCounter.setName("Y");
		hCounter.setDeltaNameX(55);
		hCounter.setDeltaNameY(14);
		hCounter.setFont(px12);
		
		dw= new CounterButtonDesign(SpriteSheetList.rightLeftArraws.getImage(), x+195, y+height-160-100, 20, 20, Editors.Type.counterRightLeft, 0, 1,100000, 0);
		dw.setRectBounds(30, dw.getHeight());
		dw.setName("DW");
		dw.setDeltaNameX(35);
		dw.setDeltaNameY(35);
		dw.setFont(px12);
		
		
		dh= new CounterButtonDesign(SpriteSheetList.upDownArras.getImage(), x+175, y+height-180-100, 20, 20, Editors.Type.counterUpDown, 0, 1,100000, 0);
		dh.setRectBounds(30, hCounter.getHeight());
		dh.setName("DH");
		dh.setDeltaNameX(55);
		dh.setDeltaNameY(14);
		dh.setFont(px12);
		
		shrink = new ButtonText(x+195,y+height-210,"Shrink");
		
		delta= new CounterButtonDesign(SpriteSheetList.upDownArras.getImage(), x+175, y+height-175, 20, 20, Editors.Type.counterUpDown, 1, 1,100000, 1);
		delta.setRectBounds(30, hCounter.getHeight());
		delta.setName("Delta");
		delta.setDeltaNameX(20);
		delta.setDeltaNameY(-2);
		delta.setFont(px12);
		
		
		
		buttons.add(delta);
		buttons.add(shrink);
		buttons.add(dh);
		buttons.add(dw);
		buttons.add(hCounter);
		buttons.add(wCounter);
		buttons.add(yCounter);
		buttons.add(xCounter);
		
		for(Button button:buttons){
			if(button instanceof CounterButton){
				((CounterButton) button).setValue(0);
			}
		}
		frame = new Frame(x+200,y+40,175,190,new Rectangle(0,0,0,0),editor.getSpriteSheet());
	//	this.frames.add(frame);
	}
	


	
	public void tick(){
	Selector temp= editor.getSelectedSelector();
		if(temp!=null){
			if(temp!=selector){
				selector =temp;
				frame.setRect(selector.getFrame());
				ImageData data = selector.getImageData();
				xCounter.setValue(data.getImageX());
				yCounter.setValue(data.getImageY());
				hCounter.setValue(data.getImageHeight());
				wCounter.setValue(data.getImageWidth());
				dh.setValue(data.getDeltaY());
				dw.setValue(data.getDeltaX());
				
			}
		
		}else{
			selector = null;
			frame.setRect(new Rectangle(0,0,0,0));
			for(Button button:buttons){
				if(button instanceof CounterButton){
					if(button!=delta){
						((CounterButton) button).setValue(0);
					}
					
				}
			}
		}
		if(editor.getSpriteSheet()!=frame.getSs()){
			frame.setSs(editor.getSpriteSheet());
		}
		
		frame.setY(y);
		frame.setHeight(height);
		frame.setX(x);
		super.tick();
	
	}
	

	
	public void render(Graphics2D g2d){
		super.render(g2d);
		frame.setY(y);
		if(height-65>=0){
			frame.setHeight(height-65);
		}else{
			frame.setHeight(0);
		}
		
		frame.setX(x);
	//	upDownFrameDisplay.render(g2d);
		if(!close){
			if(frame!=null){
				
				frame.render(g2d);
				
				g2d.drawRect(frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());
			
			}
			

			
			
		}
		
	}
	
	public boolean onClick(int x,int y){
		int dhVal = dh.getValue();
		int dwVal = dw.getValue();
		super.onClick(x, y);
		if(!opening&&show){
			if(delta.isClicked(x, y)){
				for(Button button:buttons){
					if(button instanceof CounterButton ){
						if(button!=delta){
						if(((CounterButton) button).getDelta()>=0){
							((CounterButton)button).setDelta(delta.getValue());
						}else{
							((CounterButton)button).setDelta(-delta.getValue());
						}
						
						}
					}
				}
			}
			if(selector!=null){
				if(xCounter.isClicked(x, y)||yCounter.isClicked(x, y)||hCounter.isClicked(x, y)||wCounter.isClicked(x, y)||dh.isClicked(x, y)||dw.isClicked(x, y)){
					if(dh.isClicked(x, y)){
						int delta = dh.getValue()-dhVal;
						hCounter.setValue(hCounter.getValue()-delta);
					}
					if(dw.isClicked(x, y)){
						int delta = dw.getValue()-dwVal;
						xCounter.setValue(xCounter.getValue()-delta);
						wCounter.setValue(wCounter.getValue()+delta);
					}
					selector.setFrame(new Rectangle(xCounter.getValue(),yCounter.getValue(),wCounter.getValue(),hCounter.getValue()));
					selector.setFrameDeltaBounds(new Rectangle(dw.getValue(),dh.getValue()));
					this.frame.setRect(selector.getFrame());
				}
				
				if(shrink.isClicked(x, y)){
				ImageScanner scanner = new ImageScanner(editor.getSpriteSheet().grabImage(selector.getFrame()));
				scanner.setBackGround(editor.getBackGround());
				Rectangle bounds = scanner.getCharacterBoundsFromImage();
			
				xCounter.setValue((int)bounds.getX()+(int)selector.getFrame().getX());
				yCounter.setValue((int)bounds.getY()+(int)selector.getFrame().getY());
				hCounter.setValue((int)bounds.getHeight());
				wCounter.setValue((int)bounds.getWidth());
				
				selector.setFrame(new Rectangle(xCounter.getValue(),yCounter.getValue(),wCounter.getValue(),hCounter.getValue()));
				frame.setRect(selector.getFrame());
				}
			}
			
			
			
			
			
			}
		
		
	
	
		return false;
		
		
	}
	

}
