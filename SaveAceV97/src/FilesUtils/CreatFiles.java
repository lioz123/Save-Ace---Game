package FilesUtils;

import java.io.FileNotFoundException;
import java.util.Formatter;

public class CreatFiles {
	private Formatter file;
	
	public CreatFiles(String name){
		try {
			file = new Formatter(name);
		} catch (FileNotFoundException e) {
			System.out.println("could not add a file named- " + name);
			e.printStackTrace();
		}
	}
}
