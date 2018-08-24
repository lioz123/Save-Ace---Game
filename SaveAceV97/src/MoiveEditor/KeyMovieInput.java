package MoiveEditor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyMovieInput extends KeyAdapter {
	private MovieEditor movieEditor;
	
	public KeyMovieInput(MovieEditor movieEditor){
		this.movieEditor=movieEditor;
	}
	public void keyPressed(KeyEvent e){
	movieEditor.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e){
		movieEditor.keyReleased(e);
	}

}
