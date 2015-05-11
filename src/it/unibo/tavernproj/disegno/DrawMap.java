package it.unibo.tavernproj.disegno;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DrawMap implements Map<Integer, Pair<Integer, Integer>>,java.io.Serializable {
  
  private static final long serialVersionUID = 1L;

  //SINGLETON
  private static DrawMap SINGLETON = new DrawMap();
  
  private Map<Integer,Pair<Integer,Integer>> disegno = new HashMap<>();
 
    
  private DrawMap(){  }
  
  public static Map<Integer,Pair<Integer,Integer>> getMap(){
    return SINGLETON;
  }
  
  @Override
  public String toString() {
    return "DrawMap [disegno=" + disegno +"]";
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return disegno.size();
  }


  @Override
  public boolean isEmpty() {
    // TODO Auto-generated method stub
    return disegno.isEmpty();
  }


  @Override
  public boolean containsKey(Object key) {
    // TODO Auto-generated method stub
    return disegno.containsKey(key);
  }


  @Override
  public boolean containsValue(Object value) {
    // TODO Auto-generated method stub
    return disegno.containsValue(value);
  }


  @Override
  public Pair<Integer, Integer> get(Object key) {
    // TODO Auto-generated method stub
    return disegno.get(key);
  }


  @Override
  public Pair<Integer, Integer> put(Integer key, Pair<Integer, Integer> value) {
    // TODO Auto-generated method stub
    return disegno.put(key, value);
  }


  @Override
  public Pair<Integer, Integer> remove(Object key) {
    // TODO Auto-generated method stub
    return disegno.remove(key);
  }


  @Override
  public void putAll(Map<? extends Integer, ? extends Pair<Integer, Integer>> m) {
    // TODO Auto-generated method stub
    disegno.putAll(m);
  }


  @Override
  public void clear() {
    // TODO Auto-generated method stub
    disegno.clear();
  }


  @Override
  public Set<Integer> keySet() {
    // TODO Auto-generated method stub
    return disegno.keySet();
  }


  @Override
  public Collection<Pair<Integer, Integer>> values() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public Set<java.util.Map.Entry<Integer, Pair<Integer, Integer>>> entrySet() {
    // TODO Auto-generated method stub
    return null;
  }
  
  
}
