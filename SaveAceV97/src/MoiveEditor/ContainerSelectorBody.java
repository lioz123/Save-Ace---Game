package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import Editors.Button;
import Editors.CounterButton;
import Editors.NormalButton;
import General.Utilities;
import MapTrazlation.SpriteSheetList;

public class ContainerSelectorBody extends ContainerBody{
	private Selector selector=null;
	private Frame frame = null;
	private TextArea xText,yText,widthText,heightText;
	private NormalButton saveBounds,setToNormalButton;
	private ButtonText shrinkToNormalSize;
	private CounterButton xc,yc,wc,hc,pageCounter;
	private CounterButtonDisplayValue dw,dh,delta,damage,deltaXDamage;
	private Font px12 = new Font("Tahoma",Font.PLAIN,12);
	public ContainerSelectorBody(int x, int y, int width, int height,MovieEditor editor) {
		super(x, y, width, height);
		saveBounds = new NormalButton(SpriteSheetList.SaveTexture.getImage(),x+width-50,y+5,40,20,Editors.Type.noraml);
		setToNormalButton= new NormalButton(SpriteSheetList.runIcon.getImage(),x+width-50,y+45,40,40,Editors.Type.noraml);
		setToNormalButton.setName("set default selector");
		Font font = new Font("Tahoma", Font.PLAIN, 10);
		setToNormalButton.setFont(font);
		setToNormalButton.setDeltaNameX(-35);
			buttons.add(setToNormalButton);
		 xText = new TextArea(x+10,y+height-25,35,20,"x");
		 widthText = new TextArea(x+60,y+height-25,35,20,"w");
		 yText = new TextArea(x+110,y+height-25,35,20,"y");
		 heightText = new TextArea(x+160,y+height-25,35,20,"h");
		shrinkToNormalSize = new ButtonText( x+10 , y+height-70, "Shrink");
		
		buttons.add(shrinkToNormalSize);
		 
		
		
		 delta = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-60 , y+100,20,25,Editors.Type.counterUpDown,1,1,1000,1);
		 delta.setRect( new  Rectangle( x+width-40, y+100,34,20));
		 delta.setFont(px12);
		delta.setName("delta");
		delta.setDeltaNameY(-1);
		delta.setDeltaNameX(20);
		
		 
		 dw = new CounterButtonDisplayValue(SpriteSheetList.rightLeftArraws.getImage(), x+width-40, y+157, 35,20, Editors.Type.counterRightLeft, 0, 1, 1000, 0);
		 dw.setRect( new  Rectangle( x+width-40, y+172,35,20));
		 dw.setName("dw");
		 dw.setDeltaNameY(30);
		 dw.setDeltaNameX(-18);
		 dw.setFont(new Font("Tahoma" , Font.PLAIN,12));
		 dh = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-60 , y+135,20,25,Editors.Type.counterUpDown,0,1,1000,0);
		 dh.setRect(new Rectangle(x+width-40,y+140,35,20)); 
		 dh.setName("dh");
		 dh.setDeltaNameX(20);
		 dh.setFont(new Font("Tahoma" , Font.PLAIN,12));
		 damage= new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-60 , y+205,20,25,Editors.Type.counterUpDown,0,1,1000,0);
		 damage.setRect(new Rectangle(x+width-40,y+206,35,20)); 
		 damage.setName("damage");
		 damage.setDeltaNameX(18);
		 damage.setFont(new Font("Tahoma" , Font.PLAIN,12));
		 deltaXDamage= new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(),x+width-60 , y+236,20,25,Editors.Type.counterUpDown,0,1,1000,0);
		 deltaXDamage.setRect(new Rectangle(x+width-40,y+238,35,20)); 
		 deltaXDamage.setName("damage dw");
		 deltaXDamage.setDeltaNameX(-10);
		 deltaXDamage.setFont(new Font("Tahoma" , Font.PLAIN,12));
	//	 buttons.add(deltaXDamage);
	//	 buttons.add(damage);
		 buttons.add(delta);
		 buttons.add(dh);
		 buttons.add(dw);
		
		
		this.texts.add(xText);
		this.texts.add(yText);
		this.texts.add(widthText);
		this.texts.add(heightText);
		Font textfont = new Font("Tahoma", Font.PLAIN, 12);
		for(TextArea text:texts){
			text.setString(" ");
			text.setMaxWidth(35);
			text.setMaxChars(5);
			text.setIsNumber(true);
			text.setFont(textfont);
			text.setNameDeltaWidth(-5);
		
		}
		
		this.editor=editor;
		
		
			int counterWidth=20,counterHeight=20,dcx=5,dcy=20;
			
		 xc= new CounterButton(SpriteSheetList.rightLeftArraws.getImage(), xText.getX()-dcx, xText.getY()-dcy,counterWidth,counterHeight, Editors.Type.counterRightLeft, 0,-1, 999999, 0);
		 yc= new CounterButton(SpriteSheetList.upDownArras.getImage(), yText.getX()-dcx, yText.getY()-dcy,counterWidth,counterHeight, Editors.Type.counterUpDown, 0,1, 999999, 0);
		 wc= new CounterButton(SpriteSheetList.rightLeftArraws.getImage(), widthText.getX()-dcx, widthText.getY()-dcy,counterWidth,counterHeight, Editors.Type.counterRightLeft, 0,-1, 999999, 0);
		 hc= new CounterButton(SpriteSheetList.upDownArras.getImage(), heightText.getX()-dcx, heightText.getY()-dcy,counterWidth,counterHeight, Editors.Type.counterUpDown, 0,1, 999999, 0);
		buttons.add(xc);
		buttons.add(yc);
		buttons.add(wc);
		buttons.add(hc);
	
		this.buttons.add(saveBounds);
		
	}
	
	public void setFrame(Frame frame){
	this.frame =frame;
	
	if(frame!=null&&frame.getRect()!=null){
		
		xText.setString(""+(int)frame.getRect().getX());
		yText.setString(""+(int)frame.getRect().getY());
		widthText.setString(""+(int)frame.getRect().getWidth());
		heightText.setString(""+(int)frame.getRect().getHeight());
		
	}
		
	
	}
	
	public Frame getFrame(){
		return this.frame;
	}
	
	
	public void tick(){
		super.tick();
	}
	
	public void setSelector(Selector selector){
	
		this.selector=selector;
		if(selector!=null){
			Rectangle rect = selector.getFrame();
			if(rect!=null){
				xc.setValue((int)rect.getX());
				yc.setValue((int)rect.getY());
				wc.setValue((int)rect.getWidth());
				hc.setValue((int)rect.getHeight());
				dw.setValue((int)selector.getFrameDeltaBounds().getWidth());
				dh.setValue((int)selector.getFrameDeltaBounds().getHeight());
			}
			
		}else{
			for(TextArea text:texts){
				text.setString("0");
				for(Button button : buttons){
					if(button instanceof CounterButton){
						if(button!=delta){
							((CounterButton) button).setValue(0);
						}
					}
				}
			}
		}
	}
	
	
	public void render(Graphics2D g2d){
		super.render(g2d);
		if(!close){
			if(frame!=null){
				
					frame.setHeight(height);
				
				g2d.setColor(Color.BLACK);
				g2d.drawRect(x,y,frame.getWidth(),frame.getHeight());
				if(frame!=null){
					frame.render(g2d);
				}
				
				
			
			}
		}
		
	}
	
	public boolean onClick(int x, int y){
		if(!opening&&show){
		boolean clicked = super.onClick(x, y);
		int tx,ty,tw,th;
		if(saveBounds.onClick(x, y)){
			
			tx = xText.getNubmer();
			ty = yText.getNubmer();
			tw= widthText.getNubmer();
			th = heightText.getNubmer();
			
			if(selector!=null){
				selector.setLocked(true);
				selector.setFrame(new Rectangle(tx,ty,tw,th));
				this.frame.setRect(selector.getFrame());
				xc.setValue(tx);
				yc.setValue(ty);
				wc.setValue(tw);
				hc.setValue(th);
			}
	
		}else{
			tx = xText.getNubmer();
			ty=  yText.getNubmer();
			tw = widthText.getNubmer();
			th  = heightText.getNubmer();
			int formerdw = dw.getValue();
			int formerdh = dh.getValue();
			if(selector!=null){
				if(xc.isClicked(x, y)){
					tx=xc.getValue();
					xText.setString(tx+"");
				}
				if(yc.isClicked(x, y)){
					ty=yc.getValue();
					yText.setString(ty+"");
				}
				if(wc.isClicked(x, y)){
					tw=wc.getValue();
					widthText.setString(tw+"");
				}
				if(hc.isClicked(x, y)){
					th=hc.getValue();
					heightText.setString(th+"");
				}
				if(dh.isClicked(x, y)){
					th = hc.getValue();
					ty = yc.getValue();
					ty+=dh.getValue();
					th-=dh.getValue();
					heightText.setString("" + th);
					yText.setString(""+ty);
				}
				if(dw.isClicked(x, y)){
					tw = wc.getValue();
					tx = xc.getValue();
					tx-=dw.getValue();
					tw +=dw.getValue();
					widthText.setString(tw + "");
					xText.setString(""+tx);
				}
				if(delta.isClicked(x, y)){
					for(Button button : buttons){
						
						if(delta!=button&&button instanceof CounterButton){
							((CounterButton)button).setDeltaNoChargeChange(delta.getValue());
						}
					}
				}
				
				if(shrinkToNormalSize.isClicked(x, y)){
					
					ImageScanner scanner =new ImageScanner(editor.getSpriteSheet().grabImage(selector.getFrame()));
					scanner.setBackGround(editor.getBackGround());
					Rectangle bounds =scanner.getCharacterBoundsFromImage();
					tx= (int)(bounds.getX()+selector.getFrame().getX());
					ty= (int)(bounds.getY()+selector.getFrame().getY());
					tw =(int)(bounds.getWidth());
					th=(int)(bounds.getHeight());
					xText.setString(tx+"");
					yText.setString(ty+"");
					widthText.setString(tw+"");
					heightText.setString(th+"");
					wc.setValue(tw);
					xc.setValue(tx);
					yc.setValue(ty);
					hc.setValue(th);
					
				}
				
				
				
				
				
				selector.setLocked(true);
				selector.setFrame(new Rectangle(xText.getNubmer(),yText.getNubmer(),widthText.getNubmer(),heightText.getNubmer()));
				selector.setFrameDeltaBounds(new Rectangle(dw.getValue(),dh.getValue()));
				this.frame.setRect(selector.getFrame());
				
			
				
			
			}
		
		}
		
		if(setToNormalButton.onClick(x,y)){
			if(selector !=null){
				editor.setNormalBounds(selector);
			}
		
		}
		
		return clicked;
		}
	return false;
	}

}
