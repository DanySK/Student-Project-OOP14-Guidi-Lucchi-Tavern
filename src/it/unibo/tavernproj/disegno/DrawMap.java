package it.unibo.tavernproj.disegno;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DrawMap implements Map<Integer, Pair<Integer, Integer>>,java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private final Map<Integer,Pair<Integer,Integer>> disegno = new HashMap<>();
  
  private static DrawMap SINGLETON = new DrawMap();

  private DrawMap(){}

  public static Map<Integer, Pair<Integer,Integer>> getMap() {
    return SINGLETON;
  }

  @Override
  public String toString() {
    return "DrawMap [disegno=" + disegno + "]";
  }

  @Override
  public int size() {
    return disegno.size();
  }


  @Override
  public boolean isEmpty() {
    return disegno.isEmpty();
  }


  @Override
  public boolean containsKey(final Object key) {
    return disegno.containsKey(key);
  }


  @Override
  public boolean containsValue(final Object value) {
    return disegno.containsValue(value);
  }


  @Override
  public Pair<Integer, Integer> get(final Object key) {
    return disegno.get(key);
  }


  @Override
  public Pair<Integer, Integer> put(final Integer key, final Pair<Integer, Integer> value) {
    return disegno.put(key, value);
  }


  @Override
  public Pair<Integer, Integer> remove(final Object key) {
    return disegno.remove(key);
  }


  @Override
  public void putAll(final Map<? extends Integer, ? extends Pair<Integer, Integer>> map) {
    disegno.putAll(map);
  }


  @Override
  public void clear() {
    disegno.clear();
  }


  @Override
  public Set<Integer> keySet() {
    return disegno.keySet();
  }


  @Override
  public Collection<Pair<Integer, Integer>> values() {
    return null;
  }


  @Override
  public Set<java.util.Map.Entry<Integer, Pair<Integer, Integer>>> entrySet() {
    return null;
  }


}
