package MoiveEditor;

import java.util.ArrayList;

public class StringReader {
	private String str;
	private ArrayList<String> strings;
	private int countWord = 0;
	public StringReader(String str){
	this.str =str;
	strings = new ArrayList<String>();
	strings = stringToWords(str);
	}
	
	public String getNext(){
		if(countWord<strings.size()){
			String string = strings.get(countWord);
			countWord++;
			return string;
		}
		return null;
		
	}
	
	public void setCountWord(int count){
		countWord =count;
	}
	
	public String setCountWord(String word){
		String str = getNext();
		while(!word.equals(str)){
			if(str==null){
				return null;
			}
			str = getNext();
		}
	return str;	
	}
	
	public ArrayList<String> stringToWords(String str){
		ArrayList<String> strings = new ArrayList<String>();
		String temp = "";
		char[] chars = str.toCharArray();
		
		for(int i = 0; i<chars.length;i++){
			if(chars[i]!=' '){
				
				temp+=chars[i];
			}else{
				strings.add(temp);
				temp="";
			}
			
		}
		if(temp!=""){
			strings.add(temp);
		}

		return strings;
	}
	
	public boolean hasNext(){
		if(countWord<strings.size()){
			return true;
		}
		return false;
	}

	public String getFullText() {
		
		return str;
	}


}
