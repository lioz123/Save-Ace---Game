package Editors;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseListener extends MouseAdapter {
	private Slot data = null;
	private ArrayList<ArrayList<Slot>> senders,recievers;
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private int sendersIndex= 0,recieversIndex=0;
	private EditComponetsHandler handler;
	public MouseListener(EditComponetsHandler handler){
		this.handler=handler;
		
	}
	
	public void mousePressed(MouseEvent e){
		
		
		for(Slot slot:handler.getSenders()){
			if(e.getX()>slot.getX()&&e.getX()<slot.getX()+slot.getWidth()){
				if(e.getY()>slot.getY()&&e.getY()<slot.getY()+slot.getHeight()){
					data=slot;
					return;
				}
			}
		}
		for(Slot slot:handler.getSpecialSlot()){
			if(e.getX()>slot.getX()&&e.getX()<slot.getX()+slot.getWidth()){
				if(e.getY()>slot.getY()&&e.getY()<slot.getY()+slot.getHeight()){
					if(slot.getType()==Type.earaser){
						data=slot;
					}else if(slot.getType()==Type.fillCol||slot.getType()==Type.fillRow){
						if(data.type==Type.send){
							slot.setBufferedImage(data.getImage());
							slot.setObjectName(data.getObjectName());
							System.out.println("slot objectName is " + data.getObjectName());
							slot.setObjectImageX(data.getObjectImageX());
							slot.setObjectImageY(data.getObjectImageY());
							data=slot;
						}
					}
					
					return;
				}
			}
		}
		for(Button button:handler.getButtons()){
		if(button.onClick(e.getX(),e.getY())){
			if(button instanceof CounterButton){
				System.out.println("counter button value is -" + ((CounterButton)button).getValue());
			}
			return;
		}
		}
		for(Slot slot:handler.getRecivers()){
			if(e.getX()>slot.getX()&&e.getX()<slot.getX()+slot.getWidth()){
				if(e.getY()>slot.getY()&&e.getY()<slot.getY()+slot.getHeight()){
					if(data!=null){
						
						if(data.getType()==Type.send){
							slot.setBufferedImage(data.getImage());
							slot.setObjectName(data.getObjectName());
							System.out.println("slot objectName is " + data.getObjectName());
							slot.setObjectImageX(data.getObjectImageX());
							slot.setObjectImageY(data.getObjectImageY());
							/*slot.getSlotData().setObjectName(data.getSlotData().getObjectName());
							slot.getSlotData().setObjectCol(data.getSlotData().getObectCol());
							slot.getSlotData().setObjectRow(data.getSlotData().getObjectRow());
						*/
						}
						else if(data.getType()==Type.earaser){
						if(!slot.objectName.equals("null")){
							slot.setBufferedImage(data.getImage());
							slot.setObjectName(data.getObjectName());
							System.out.println("slot objectName is " + data.getObjectName());
							slot.setObjectImageX(data.getObjectImageX());
							slot.setObjectImageY(data.getObjectImageY());
						}else{
							slot.setFloorImage(null);
							slot.setFloorImageX(-1);
							slot.setFloorImageY(-1);
						}
						}else if(data.getType()==Type.fillCol){
							for(Slot tempSlot:handler.getRecivers()){
								if(tempSlot.getRow()==slot.getRow()){ // I dont know why this is the oposite but it works... 
								
									tempSlot.setBufferedImage(data.getImage());
									tempSlot.setObjectName(data.getObjectName());
									System.out.println("slot objectName is " + data.getObjectName());
									tempSlot.setObjectImageX(data.getObjectImageX());
									tempSlot.setObjectImageY(data.getObjectImageY());
								}
							}
						}else if(data.getType()==Type.fillRow){
							for(Slot tempSlot:handler.getRecivers()){
								if(tempSlot.getCol()==slot.getCol()){
									tempSlot.setBufferedImage(data.getImage());
									tempSlot.setObjectName(data.getObjectName());
									System.out.println("slot objectName is " + data.getObjectName());
									tempSlot.setObjectImageX(data.getObjectImageX());
									tempSlot.setObjectImageY(data.getObjectImageY());
								}
							}
							
						}	else{
							slot.setFloorImage(data.getFloorImage());
							slot.setFloorImageX(data.getFloorImageX());
							slot.setFloorImageY(data.getFloorImageY());
							/*
							slot.getSlotData().setFloorCol(data.getSlotData().getFloorCol());
							slot.getSlotData().setFloorRow(data.getSlotData().getFloorRow());
						*/
							}
						

					
						
					}
					return;
				}
			}
		}
		for(Slot slot:handler.getcurrentEmptySenders()){
			if(e.getX()>slot.getX()&&e.getX()<slot.getX()+slot.getWidth()){
				if(e.getY()>slot.getY()&&e.getY()<slot.getY()+slot.getHeight()){
					data=slot;
					return;
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}

	

}
