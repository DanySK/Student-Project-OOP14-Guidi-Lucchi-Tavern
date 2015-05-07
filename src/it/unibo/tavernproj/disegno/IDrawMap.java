package it.unibo.tavernproj.disegno;

import java.util.HashMap;
import java.util.Map;

public interface IDrawMap {
   
  Map<Integer,Pair<Integer,Integer>> getMap();
  
  void setMap(int x, int y);
  
  int getSize();
  
  void removeAll();

}
