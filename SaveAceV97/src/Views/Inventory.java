package Views;
import java.awt.AlphaComposite;
import GameObjects.Syringe;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import GameObjects.MedKit;
import GameObjects.ID;
import GameObjects.Item;
import GameObjects.Player;
import GameObjects.Syringe;
public class Inventory {
	List<Bar> bars = new ArrayList();
	List<Item> items = new ArrayList();
	private Player player;
	public Inventory(Player player){
		this.player = player;
	}
	public void addItem(Item item){
		items.add(item);
	}
	public void addbar(Bar bar){
		bars.add(bar);
	}
	
	
	public void render(Graphics2D g2d){
	 for(Bar bar:bars){
		 bar.render(g2d);
	 }
	 for(Item item: items){
		 g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1));
		item.render(g2d);
		if(item.id==ID.medkit){
			if(player.medKitCounter>0){
			 ((MedKit) item).hasMedcits=true;
			}
			else{
				((MedKit) item).hasMedcits=false;
			}
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
			g2d.drawString(player.medKitCounter+"", item.x+ item.width+1, item.y+15);
		}
		else if(item.id==ID.syring){
			if(player.syringeCounter>0){
				 ((Syringe) item).hasSyringe=true;
				}
				else{
					((Syringe) item).hasSyringe=false;
				}
				g2d.setColor(Color.BLACK);
				g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
				g2d.drawString(player.syringeCounter+"", item.x+ item.width+1, item.y+15);
			
		}
	 }
	 
	}
	
	public void tick(){
		for(Bar bar: bars){
		switch (bar.id){
		case hp:bar.tick(player.hp);break;
		case stamina:bar.tick(player.stamina); break;
		}
	
		}
	}

}
