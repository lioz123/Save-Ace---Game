package MoiveEditor;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class WheelEvent implements MouseWheelListener {
	private MovieEditor editor;
	public WheelEvent(MovieEditor editor){
		this.editor=editor;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
	editor.mouseWheelMoved(arg0);
	}

}
