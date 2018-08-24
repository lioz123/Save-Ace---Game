package FilesUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

public class FileEditor {
	private BufferedWriter writer;
	private Scanner scanner;
	private File file;
	private BufferedReader bufferedReader;
	private String path;
	private ArrayList<String> lines = new ArrayList<String>();
	public FileEditor() {
		File file = new File("test.txt");
	}

	public void Openfile(String path) {
		File x = new File(path);
		this.path = path;
		if (!x.exists()) {
			CreatFiles fileCreator = new CreatFiles(path);

			System.out.println("file does not exists");
		} else {
			System.out.println("file does exists");
		}
		try {
			writer = new BufferedWriter(new FileWriter(path));

		} catch (IOException e) {
			System.out.println("could not open the file with path " + path);
			e.printStackTrace();
		}

	}

	public void setPath(String path) {
		this.path = path;
	}

	public void close() {
		try {
			writer.close();

		} catch (IOException e) {
			System.out.println("could not close file");
			e.printStackTrace();
		}
	}
	


	public void openScanner() {
		try {
			scanner = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeScanner() {
		scanner.close();
	}

	public void editFile(String line) {
		try {
			if(line!=null){
				writer.write(line);
				writer.newLine();
			}
			
		} catch (IOException e) {
			System.out.println("could not write line ");
			e.printStackTrace();
		}
	}

	public void removeFileAllFileData() {
		File tempFile = new File("tempFile.txt");
		tempFile.renameTo(file);
	}

	public void EditFileFrom(String start, ArrayList<String> content, String end) {
		openScanner();
		System.out.println("called to eadit from " + path);
		boolean foundStart = false;
		ArrayList<String> lines = new ArrayList<String>();
		while (scanner.hasNextLine()) {

			lines.add(scanner.nextLine());
		}
	
		closeScanner();
		int i = 0;
		while (i < lines.size()) {
			String line = lines.get(i);
			if (line.equals(start)) {
				foundStart = true;

			}
			if (foundStart) {
				lines.remove(i);
				i--;
			}

			if (line.equals(end) && foundStart) {
				// lines.remove(line);

				break;
			}
			i++;
		}

		this.Openfile(path);
		lines.addAll(content);
	
		for (String line : lines) {
			editFile(line);
		}
		this.close();
	}

	public void editFromBlockName(String blockName, String BlockEnd, String name, ArrayList<String> content,String end) {
		openScanner();
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<String> tempLines = new ArrayList<String>();
		boolean foundCharacter = false;
		boolean foundMovie = false;
		boolean hasCharacter = false;

		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}
		closeScanner();

		int i = 0;
		while (i < lines.size()) {
			String line = lines.get(i);
			if (line.equals(blockName)) {
				hasCharacter = true;
				foundCharacter = true;
			}

			if (line.equals(BlockEnd)&&foundCharacter) {
				if (!foundMovie) {
					tempLines.addAll(content);
				}
				// tempLines.add(line);
				foundCharacter = false;
			}

			if (foundCharacter) {
				if (line.equals(name)) {
					foundMovie = true;
					while (!line.equals(end)) {
						i++;
						line = lines.get(i);

					}
					tempLines.addAll(content);
				} else {
					tempLines.add(line);
				}
			} else {
				tempLines.add(line);
			}
			i++;
		}

		if (!hasCharacter) {
			tempLines.add(blockName);
			tempLines.addAll(content);
			tempLines.add(BlockEnd);
		}

		Openfile(path);
		for (String line : tempLines) {
			editFile(line);
		}
		close();
	}
	
	public void creatLines(){
		openScanner();

		
		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}
		closeScanner();
	}
	
	public void editLineFromBlockName(String blockName, String BlockEnd, String name,String end,String linestart,String content) {
		boolean foundLine = false;

		boolean foundCharacter = false;
		boolean foundMovie = false;
		boolean hasCharacter = false;
		

	
		int i = 0;
		while (i < lines.size()) {
			String line = lines.get(i);
		
			if(line.equals(BlockEnd)&&foundCharacter||line.equals(end)&&foundMovie){
				break;
			}
			
			if(line.indexOf(linestart)==0&&foundMovie){
			

			foundLine=true;
			}
			
			if (line.equals(blockName)) {
				hasCharacter = true;
				foundCharacter = true;
			}

			

			if (foundCharacter) {
				System.out.println(this.getClass() + " " +line);
				if (line.equals(name)) {
					foundMovie = true;
				}
			}
			
			if(foundLine){
				lines.set(i, content);
			break;
			}
			i++;
		}


	}
	
	public void Update(){


		Openfile(path);
		for (String line : lines) {
	//		System.out.println(line);
			editFile(line);
		}
		close();
	}

}
