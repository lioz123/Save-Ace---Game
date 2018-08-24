package QestionMenu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import CharactersMenu.ButtonTextAdvanced;
import CharactersMenu.KeyInputer;
import CharactersMenu.KeyInterface;
import CharactersMenu.StringDisplay;
import Editors.Button;
import Main.Game;
import MoiveEditor.StringReader;

public class QeustionMenu extends MouseAdapter implements MouseMotionListener{
	private Game game;
	private KeyInputer keyInputer;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private int x, y,width=300 ,height=200;
	private ButtonTextAdvanced yes,no;
	private QeustionAble object;
	private ArrayList<StringDisplay> lines = new ArrayList<StringDisplay>();
	public QeustionMenu(Game game,String qestion,QeustionAble object){
		this.object=object;
		this.game=game;
	
		this.x= (Game.WIDTH-width)/2;
		this.y=(Game.HEIGHT-height)/2-100;
		yes = new ButtonTextAdvanced("Yes", x+70, y+height-30, game);
		yes.setFont(new Font("Tahoma",Font.PLAIN,20));
		yes.SetBoundsToStringBounds();
		yes.setX(x+(width-yes.getWidth())/2-yes.getWidth()/2);
		yes.setStringToCenter();
		
		
		no = new ButtonTextAdvanced("No", x+150, y+height-30, game);
		no.setFont(new Font("Tahoma",Font.PLAIN,20));
		no.setHeight(yes.getHeight());
		no.setWidth(yes.getWidth());
		no.setX(x+(width-no.getWidth())/2+no.getWidth()/2+5);
		no.setStringToCenter();
		buttons.add(no);
		buttons.add(yes);
		
		StringReader sentence = new StringReader(qestion);
		if(sentence.hasNext()){
			lines.add(new StringDisplay(sentence.getNext(), x+25, y+50, game));
			lines.get(0).setFont(new Font("Tahoma", Font.PLAIN, 18));
			lines.get(0).CalculateBounds();
		}
		int i = 0;
		while(sentence.hasNext()){
			String word = sentence.getNext();
			StringDisplay current = lines.get(i);
			
			StringDisplay temp = new StringDisplay(current.getString()+" " + word,current.getX(), current.getY(), game);
			temp.setFont(current.getFont());
			temp.CalculateBounds();
			if(temp.getX()+temp.getWidth()>width+x-25){
				i++;
				lines.add(new StringDisplay(word, current.getX(), current.getY()+i*30, game));
				lines.get(i).setFont(current.getFont());
			}else{
				lines.set(i, temp);
			}
		}
	}
	
	
	
	public void render(Graphics2D g2d){
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)0.6));
		g2d.setColor(Color.gray);
		g2d.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)1));
		g2d.fillRect(x,y,width,height);
		g2d.setColor(Color.black);
		g2d.drawRect(x, y, width, height);
	  for(Button button:buttons){
		  button.render(g2d);
	  }
	  for(StringDisplay string:lines){
		  string.render(g2d);
	  }
	}
	

	public void mousePressed(MouseEvent e){
		if(yes.isClicked(e)){
			object.reciveQeustion(true);
			game.CloseQuestion();
			
		}
		if(no.isClicked(e)){
			object.reciveQeustion(false);
			game.CloseQuestion();
		}
	}



	public KeyInputer getKeyInputer() {
		// TODO Auto-generated method stub
		return  this.keyInputer;
	}

}
