package it.unibo.tavernproj.disegno;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

public class DrawMap implements IDrawMap {
  private Map<Integer,Pair<Integer,Integer>> disegno = new HashMap<>();
  private int index=0;
    
  public DrawMap(){
  }
  
  
  @Override
  public void setMap(Map<Integer,Pair<Integer,Integer>> map){
    this.disegno=map;
  }
  
  @Override
  public Map<Integer,Pair<Integer,Integer>> getMap(){
    return disegno;
  }
  
  @Override
  public void setMap(int x, int y){
    index++;
    disegno.put(index, new Pair<>(x,y));
  }
  
  @Override
  public int getSize(){
    return this.disegno.size();
  }
  
  @Override
  public void removeAll(){
    this.disegno.clear();
  }


}
