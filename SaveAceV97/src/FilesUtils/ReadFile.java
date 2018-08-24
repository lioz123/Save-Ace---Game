package FilesUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
	
	private Scanner scanner;
	public void openFile(String path){
		try {
			scanner = new Scanner(new File(path));
			
		} catch (FileNotFoundException e) {
			System.out.println("could not read file + " + path);
			e.printStackTrace();
		}
	}
	public void close(){
		scanner.close();
	}
	
	public String getFileText(){
		String text= "";
		while(scanner.hasNext()){
			 text += " " + scanner.next();
		}
		this.close();
		return text;
	}
	public String getNext(){
	if(scanner.hasNext()){
		return scanner.next();
	}
	return null;
	}
	public boolean hasNext() {
		return scanner.hasNext();
	}
	
	public String GetFrom(String start,String end){

	String string="";
	boolean foundStart =false;
	while(scanner.hasNext()){
		String temp = scanner.next();
		if(temp.indexOf(start)>-1){
			foundStart=true;
		}
		if(foundStart){
			string +=" " + temp;
		}
		if(foundStart&&temp.indexOf(end)>-1){
			break;
		}
	
	}
	return string;
	}
	
	public ArrayList<String> GetFromLines(String start,String end){
	ArrayList<String> lines = new ArrayList<String>();
	boolean foundStart =false;
	while(scanner.hasNextLine()){
		String temp = scanner.nextLine();
		if(temp.equals(start)){
			foundStart=true;
		}
		if(foundStart){
		lines.add(temp);
		}
		if(foundStart&&temp.equals(end)){
			break;
		}
	
	}
	return  lines;
	}
	
	public ArrayList<String> getLines(){

		ArrayList<String> lines = new ArrayList<String>();
		while(scanner.hasNextLine()){
			lines.add(scanner.nextLine());
		}
	
		return lines;
	}
	

	
}
