package MoiveEditor;

import java.awt.image.BufferedImage;

import Editors.Button;
import Editors.Type;

public class SettingsButton extends Button {
	private Settings settings;
	private boolean selected=false;
	public SettingsButton(BufferedImage img, int x, int y, int width, int height, Type type,Settings settings) {
		super(img, x, y, width, height, type);
		this.settings=settings;
	}

	@Override
	public boolean onClick(int x, int y) {
		if(isClicked(x, y)){
			//selected=!selected;
			boolean tempSelected = !selected;
			if(tempSelected){
				if(settings.Show()){
					selected =tempSelected;
				}
			}else{
				if(settings.Hide()){
					selected =tempSelected;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
