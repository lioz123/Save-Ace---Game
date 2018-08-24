

package MoiveEditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.peer.FileDialogPeer;
import java.util.ArrayList;

import javax.swing.filechooser.FileView;

import Editors.Type;
import GameObjects.Side;
import Editors.Button;
import Editors.NormalButton;
import General.Film;
import General.FilmMannager;
import General.ImageData;
import General.SpriteSheet;
import MapTrazlation.CharacterMovments;
import MapTrazlation.Characters;
import MapTrazlation.SpriteSheetList;

public class ContainerArmatureBody extends ContainerBody{
	private SpriteSheet ss;
	private FilmMannager film;
	private Film filmRight;
	private Font px11 = new Font("Tahoma" , Font.PLAIN,11),px17=new Font("Tahoma",Font.BOLD,17);
	
	private CounterButtonDisplayValue legAttack,legSolid,legDefence,headAttack,headSolid,headDefence,armAttack,armSolid,armDefence,chestAttack,chestSolid,chestDefence,delta;
	public ContainerArmatureBody(int x, int y, int width, int height,MovieEditor editor) {
		super(x, y, width, height);
		super.editor=editor;
		film = new FilmMannager(Characters.getCharacter("Armature").getFilms().get(0));
		filmRight = new Film(film.getFilmRight());
		ss = SpriteSheetList.Simbols;
		headAttack = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+10, 20,20, Type.counterUpDown, 0, 1, 1000, 0);
		headAttack.setRect(new Rectangle( headAttack.getX()+20, headAttack.getY(), 30,15));
		headAttack.setName("attack");
		headAttack.setFont(px11);
		headAttack.setDeltaNameX(55);
		headAttack.setDeltaNameY(12);
		
		
		
		headDefence = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+35-2, 20,20, Type.counterUpDown, 0, 1, 1000, 0);
		headDefence.setRect(new Rectangle( headDefence.getX()+20, headDefence.getY(), 30,15));
		headDefence.setFont(px11);
		headDefence.setName("defence");
		headDefence.setDeltaNameX(55);
		headDefence.setDeltaNameY(12);
		
		
		
		
		headSolid = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+60-2, 20,20, Type.counterUpDown, 0, 1, 1, 0);
		headSolid.setRect(new Rectangle( headSolid.getX()+20, headSolid.getY(), 30,15));
		headSolid.setFont(px11);
		headSolid.setName("solid");
		headSolid.setDeltaNameX(55);
		headSolid.setDeltaNameY(12);
		
		
		
		
		
		
		
		
		chestAttack = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+90, 20,20, Type.counterUpDown, 0, 1, 1000, 0);
		chestAttack.setRect(new Rectangle( chestAttack.getX()+20, chestAttack.getY(), 30,15));
		chestAttack.setName("attack");
		chestAttack.setFont(px11);
		chestAttack.setDeltaNameX(55);
		chestAttack.setDeltaNameY(12);
		
		
		
		chestDefence = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+90+23, 20,20, Type.counterUpDown, 0, 1, 1000, 0);
		chestDefence.setRect(new Rectangle(chestDefence.getX()+20, chestDefence.getY(), 30,15));
		chestDefence.setFont(px11);
		chestDefence.setName("defence");
		chestDefence.setDeltaNameX(55);
		chestDefence.setDeltaNameY(12);
		
		
		
		
		chestSolid = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+90+23*2, 20,20, Type.counterUpDown, 0, 1, 1, 0);
		chestSolid .setRect(new Rectangle( chestSolid.getX()+20, chestSolid.getY(), 30,15));
		chestSolid.setFont(px11);
		chestSolid .setName("solid");
		chestSolid .setDeltaNameX(55);
		chestSolid .setDeltaNameY(12);
		
		
		
		
		armAttack = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+166, 20,20, Type.counterUpDown, 0, 1, 1000, 0);
		armAttack.setRect(new Rectangle( armAttack.getX()+20,armAttack.getY(), 30,15));
		armAttack.setName("attack");
		armAttack.setFont(px11);
		armAttack.setDeltaNameX(55);
		armAttack.setDeltaNameY(12);
		
		
		
		armDefence = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+166+23, 20,20, Type.counterUpDown, 0, 1, 1000, 0);
		armDefence.setRect(new Rectangle(armDefence.getX()+20, armDefence.getY(), 30,15));
		armDefence.setFont(px11);
		armDefence.setName("defence");
		armDefence.setDeltaNameX(55);
		armDefence.setDeltaNameY(12);
		
		
		
		
		armSolid = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+166+23*2, 20,20, Type.counterUpDown, 0, 1, 1, 0);
		armSolid.setRect(new Rectangle( armSolid.getX()+20, armSolid.getY(), 30,15));
		armSolid.setFont(px11);
		armSolid.setName("solid");
		armSolid.setDeltaNameX(55);
		armSolid.setDeltaNameY(12);
		
		
		
		
		
		
		legAttack = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+242, 20,20, Type.counterUpDown, 0, 1, 1000, 0);
		legAttack.setRect(new Rectangle( legAttack.getX()+20,legAttack.getY(), 30,15));
		legAttack.setName("attack");
		legAttack.setFont(px11);
		legAttack.setDeltaNameX(55);
		legAttack.setDeltaNameY(12);
		
		
		
		legDefence = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+242+23, 20,20, Type.counterUpDown, 0, 1, 1000, 0);
		legDefence.setRect(new Rectangle(legDefence.getX()+20, legDefence.getY(), 30,15));
		legDefence.setFont(px11);
		legDefence.setName("defence");
		legDefence.setDeltaNameX(55);
		legDefence.setDeltaNameY(12);
		
		
		
		
		legSolid = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+width/2-25+50, y+242+23*2, 20,20, Type.counterUpDown, 0, 1, 1, 0);
		legSolid.setRect(new Rectangle( legSolid.getX()+20, legSolid.getY(), 30,15));
		legSolid.setFont(px11);
		legSolid.setName("solid");
		legSolid.setDeltaNameX(55);
		legSolid.setDeltaNameY(12);
		
		
		
		delta = new CounterButtonDisplayValue(SpriteSheetList.upDownArras.getImage(), x+1, y+height-25, 20,20, Type.counterUpDown, 1, 1, 50, 1);
		delta.setRect(new Rectangle(delta.getX()+20, delta.getY(), 30,15));
		delta.setFont(px11);
		delta.setName("Delta");
		delta.setDeltaNameX(20);
		delta.setDeltaNameY(-1);
		
		
		
		buttons.add(delta);
		buttons.add(legSolid);
		buttons.add(legDefence);
		buttons.add(legAttack);
		buttons.add(armAttack);
		buttons.add(armDefence);
		buttons.add(armSolid);
		buttons.add(chestSolid);
		buttons.add(chestDefence);
		buttons.add(chestAttack);
		buttons.add(headAttack);
		buttons.add(headSolid);
		buttons.add(headDefence);
	
	}
	
	public void tick(){
		super.tick();
		
	}
	
	public void render(Graphics2D g2d){
	super.render(g2d);
	if(open){
		ImageData data = filmRight.getFrame(0);
		Rectangle bounds = new Rectangle(data.getImageX(),data.getImageY(),data.getImageWidth(),data.getImageHeight());
	
		
		g2d.drawImage(ss.grabImage(bounds),x+width/2-25,y+10,50,50,null);
		
		data = filmRight.getFrame(4);
		 bounds = new Rectangle(data.getImageX(),data.getImageY(),data.getImageWidth(),data.getImageHeight());
		g2d.drawImage(ss.grabImage(bounds),x+width/2-25,y+105-10,50,50,null);
		data = filmRight.getFrame(3);
		 bounds = new Rectangle(data.getImageX(),data.getImageY(),data.getImageWidth(),data.getImageHeight());
		g2d.drawImage(ss.grabImage(bounds),x+width/2-25,y+250,50,50,null);		
		 data = filmRight.getFrame(1);
		 bounds = new Rectangle(data.getImageX(),data.getImageY(),data.getImageWidth(),data.getImageHeight());
		g2d.drawImage(ss.grabImage(bounds),x+width/2-25,y+185-10,50,50,null);
		g2d.setFont(px17);
		g2d.setColor(Color.black);
		g2d.drawString("Head",x+40,y+40 );
		g2d.drawString("Chest",x+40,y+130 );
		g2d.drawString("Arm",x+40,y+210 );
		g2d.drawString("Leg",x+40,y+285 );
		
	}
}
	
	public boolean onClick(int x, int y){
		if(super.onClick(x, y)){
			if(delta.isClicked(x, y)){
				for(Button button : buttons){
					if(button instanceof CounterButtonDisplayValue){
						if(button!=delta){
							((CounterButtonDisplayValue)(button)).setDelta(delta.getValue());
						}
					
						
					}
				}
			}
			
			if(legAttack.isClicked(x, y)){
				ImageData data = editor.getLeg();
				data.setAttack(legAttack.getValue());
			}
			
			if(legSolid.isClicked(x, y)){
		
				if(legSolid.getValue()>0){
					editor.getLeg().setSolid(true);
				}else{
					editor.getLeg().setSolid(false);
				}
				
			}
			
			if(legDefence.isClicked(x, y)){
				ImageData data = editor.getLeg();
				data.setDefence(legDefence.getValue());
			}
			
			
			if(headAttack.isClicked(x, y)){
				editor.getHead().setAttack(headAttack.getValue());
			}
			
			
			if(headSolid.isClicked(x, y)){
			if(headSolid.getValue()>0){
				editor.getHead().setSolid(true);
			}else{
				editor.getHead().setSolid(false);
			}
			}
			
			
			if(headDefence.isClicked(x, y)){
				editor.getHead().setDefence(headDefence.getValue());
			}
			
			
			if(chestAttack.isClicked(x, y)){
				editor.getChest().setAttack(chestAttack.getValue());
			}
			
			
			if(chestDefence.isClicked(x, y)){
			editor.getChest().setDefence(chestDefence.getValue());
			}
			
			if(chestSolid.isClicked(x, y)){
				if(chestSolid.getValue()>0){
					editor.getChest().setSolid(true);
				}else{
					editor.getChest().setSolid(false);
				}
			}
			
			
			
			if(armAttack.isClicked(x, y)){
				editor.getArm().setAttack(armAttack.getValue());
			}
			
			if(armDefence.isClicked(x, y)){
				editor.getArm().setDefence(armDefence.getValue());
			}
			
			if(armSolid.isClicked(x, y)){
				if(armSolid.getValue()>0){
					editor.getArm().setSolid(true);
				}else{
					editor.getArm().setSolid(false);
				}
				
			}
			
		
		}
		
		
		return false;
		}
	


}
