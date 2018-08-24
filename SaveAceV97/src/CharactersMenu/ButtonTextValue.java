package CharactersMenu;

import MoiveEditor.ButtonText;

public class ButtonTextValue extends ButtonText {
	private int value;
	public ButtonTextValue(int x, int y, String content,int value) {
		super(x, y, content);
		this.value=value;
	}
	
	public void setValue(int value){
		this.value=value;
	}
	
	public int getValue(){
		return this.value;
	}

}
