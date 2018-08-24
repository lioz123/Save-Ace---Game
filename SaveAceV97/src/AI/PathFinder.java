package AI;

import java.util.ArrayList;

import org.omg.Messaging.SyncScopeHelper;

import GameObjects.Block;
import GameObjects.Map;

public class PathFinder {
private ArrayList<RectangleNode> map=new ArrayList<RectangleNode>();
private ArrayList<RectangleNode> openList=new ArrayList<RectangleNode>();
private boolean foundPath=false;
private RectangleNode end;
private double normalCost=1;
private double upperCost=Math.sqrt(2);
	
public PathFinder(ArrayList<Block> blocks){
	for(Block block:blocks){
		RectangleNode temp=new RectangleNode(block);
		
			map.add(temp);
	
		
	}
}

public ArrayList<RectangleNode> findPath(Block startBlock,Block endBlock){
	if(startBlock==null||endBlock==null){
		return null;
	}
	
	RectangleNode start = getNode(startBlock.getCol(),startBlock.getRow());
	RectangleNode finalNode=null;
	end = getNode(endBlock.getCol(),endBlock.getRow());
	start.SetDistance(end);
	start.setG(0);
	start.calculateF();
	openList.add(start);
	
	while(!foundPath){
		if(openList==null){
			break;
		}
		else{
		}
		for(RectangleNode temp:openList){
			if(temp.getRow()==end.getRow()&&temp.getCol()==end.getCol()){
				
				finalNode=temp;
				foundPath=true;
				break;
			}
		}
		if(!foundPath)
		openList=generateOpenList();		
	}
	return generatePath(finalNode);
	
}

public ArrayList<RectangleNode> findPathNoUpperCost(Block startBlock,Block endBlock){
	
	RectangleNode start = getNode(startBlock.getCol(),startBlock.getRow());
	RectangleNode finalNode=null;
	end = getNode(endBlock.getCol(),endBlock.getRow());
	start.SetDistance(end);
	start.setG(0);
	start.calculateF();
	openList.add(start);
	
	while(!foundPath){
		if(openList==null){
			break;
		}
		else{
		}
		for(RectangleNode temp:openList){
			if(temp.getRow()==end.getRow()&&temp.getCol()==end.getCol()){
				
				finalNode=temp;
				foundPath=true;
				break;
			}
		}
		if(!foundPath)
		openList=generateOpenListNoUppderCost();		
	}
	return generatePath(finalNode);
	
}

public RectangleNode getNextMove(Block startBlock, Block endBlock){
	ArrayList<RectangleNode> path = findPath(startBlock, endBlock);
	if(path!=null&&path.size()>0)
	return path.get(path.size()-1);
	return null;
}

public RectangleNode getNextMoveNoUpperCost(Block startBlock, Block endBlock){
	ArrayList<RectangleNode> path = findPathNoUpperCost(startBlock, endBlock);
	if(path!=null&&path.size()>0)
	return path.get(path.size()-1);
	return null;
}

public ArrayList<RectangleNode> generatePath(RectangleNode node){
	ArrayList<RectangleNode> path= new ArrayList<RectangleNode>();
	if(node==null){
		return null;
	}
	while(node.getPrevRec()!=null){
		path.add(node);
		node=node.getPrevRec();
	}
	
	return path;
}
 
public ArrayList<RectangleNode> generateOpenList(){
	ArrayList<RectangleNode> tempList = new ArrayList<RectangleNode>();
	for(RectangleNode node:openList){
		for(int row=node.getRow()-1;row<=node.getRow()+1;row++){
			for(int col=node.getCol()-1;col<=node.getCol()+1;col++){
				boolean doNotDo=false;
				if(row==node.getRow()&&col==node.getCol()){
					doNotDo=true;
				}
				
				RectangleNode temp = getNode(col,row);
				if(temp!=null&&!temp.IsBlocked()&&!doNotDo){
					if(!temp.IsClosed()){
						temp.SetDistance(end);
						if(temp.getRow()==node.getRow()||temp.getCol()==node.getCol()){
							temp.setG(node.getG()+normalCost);
							temp.setClose(true);
							temp.calculateF();
								tempList.add(temp);
								temp.setPrevRec(node);	
						}
						else{
							ArrayList<RectangleNode> middleNode=new ArrayList<RectangleNode>();
							middleNode.add(getNode(node.getCol(),node.getRow()-1));
							middleNode.add(getNode(node.getCol(),node.getRow()+1));
							middleNode.add(getNode(node.getCol()+1,node.getRow()));
							middleNode.add(getNode(node.getCol()-1,node.getRow()));
							boolean doNotAdd = false;
							for(int i=0;i<middleNode.size();i++){
								if(middleNode.get(i)!=null){
									if(middleNode.get(i).IsBlocked()){
										if(middleNode.get(i).getRow()-row==1||middleNode.get(i).getRow()-row==-1||middleNode.get(i).getCol()-col==-1||middleNode.get(i).getCol()-col==1){
											doNotAdd=true;
										}
									}
								}
							
							}
							if(!doNotAdd){
								temp.setG(upperCost+node.getG());
								temp.setClose(true);
								temp.calculateF();
									tempList.add(temp);
									temp.setPrevRec(node);	
							}
						
						}
						
					
						
						
						
					}else{
						double nowG=temp.getG();
						double tempG=node.getG();
						if(row==node.getRow()||node.getCol()==col){
							tempG+=normalCost;
						}
						else{
							tempG+=upperCost;
						}
						
						if(nowG>tempG){
							temp.setG(tempG);
							temp.setPrevRec(node);
							temp.calculateF();
						
						}
						
					}
					
				}
			}
		}
		node.setClose(true);
	}

	return orgnizeListByFValue(tempList);
}

public ArrayList<RectangleNode> generateOpenListNoUppderCost(){
	ArrayList<RectangleNode> tempList = new ArrayList<RectangleNode>();
	for(RectangleNode node:openList){
		for(int row=node.getRow()-1;row<=node.getRow()+1;row++){
			for(int col=node.getCol()-1;col<=node.getCol()+1;col++){
				boolean doNotDo=false;
				if(row==node.getRow()&&col==node.getCol()){
					doNotDo=true;
				}
				
				RectangleNode temp = getNode(col,row);
				if(temp!=null&&!temp.IsBlocked()&&!doNotDo){
					if(!temp.IsClosed()){
						temp.SetDistance(end);
						if(temp.getRow()==node.getRow()||temp.getCol()==node.getCol()){
							temp.setG(node.getG()+normalCost);
							temp.setClose(true);
							temp.calculateF();
								tempList.add(temp);
								temp.setPrevRec(node);	
						}
					
					
					/*	temp.setClose(true);
						temp.calculateF();
							tempList.add(temp);
							temp.setPrevRec(node);	
						*/
						
					}else{
						double nowG=temp.getG();
						double tempG=node.getG();
						if(row==node.getRow()||node.getCol()==col){
							tempG+=normalCost;
						}
						if(nowG>tempG){
							temp.setG(tempG);
							temp.setPrevRec(node);
							temp.calculateF();
						
						}
						
					}
					
				}
			}
		}
		node.setClose(true);
	}

	return orgnizeListByFValue(tempList);
}

public ArrayList<RectangleNode> orgnizeListByFValue(ArrayList<RectangleNode> nodes){
	//ArrayList<RectangleNode> purList= new ArrayList<RectangleNode>();
	ArrayList<RectangleNode> tempList=new ArrayList<RectangleNode>();
	
	while(nodes.size()>0){
		RectangleNode bestNode=nodes.get(0);
		for(RectangleNode temp :nodes){
			if(temp.getF()<bestNode.getF()){
				bestNode=temp;
			}
		}
		tempList.add(bestNode);
		nodes.remove(bestNode);
		}
	if(tempList.size()==0)
		return null;
	return tempList;
}

public RectangleNode getNode(int col, int row){
	for(RectangleNode node:map){
		if(node.getRow()==row&&node.getCol()==col){
			return node;
		}
	}
	return null;
}
}
