package Editors;

import java.awt.image.BufferedImage;

import FilesUtils.FileEditor;

public class SaveButton extends Button {
	
	private String path;
	private String data=null;
	private EditComponetsHandler handler;
	public SaveButton(BufferedImage img, int x, int y, int width, int height, Type type,String path,EditComponetsHandler handler) {
		super(img, x, y, width, height, type);
		this.path=path;
		this.handler=handler;
		}

	@Override
	public boolean onClick(int x, int y) {
	if(isClicked(x, y)){
		handler.SaveData(path);
		
		return true;
	}
		return false;
	}
	public void setdata(String data){
		this.data=data;
	}
	public void adddata(String data){
		this.data+=data;
	}
	
	public void setPath(String path){
		this.path=path;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}
