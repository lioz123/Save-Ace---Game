package MapTrazlation;

public class CharacterKeysInfo {
@SuppressWarnings("unused")
private String name,key,specialKey, movieName;
	public CharacterKeysInfo(String name ,String movieName, String key, String specialKey){
		this.name=name;
		this.setKey(key);
		this.movieName=movieName;
		this.setSpecialKey(specialKey);
	}
	public String getSpecialKey() {
		return specialKey;
	}
	
	public String getMovieName(){
		return this.movieName;
	}
	public void setSpecialKey(String specialKey) {
		this.specialKey = specialKey;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	
	}
	
	public String getName(){
		return this.name;
	}
	
}
