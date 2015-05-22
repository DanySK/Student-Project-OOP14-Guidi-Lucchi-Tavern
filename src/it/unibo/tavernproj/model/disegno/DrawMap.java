package it.unibo.tavernproj.model.disegno;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class DrawMap implements Map<Integer, IPair<Integer, Integer>>,java.io.Serializable {

  private static final long serialVersionUID = 1L;
  private final Map<Integer, IPair<Integer,Integer>> disegno = new HashMap<>();
  
  private static DrawMap SINGLETON = new DrawMap();

  private DrawMap(){}

  public static Map<Integer, IPair<Integer,Integer>> getMap() {
    return SINGLETON;
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
  public IPair<Integer, Integer> get(final Object key) {
    return disegno.get(key);
  }


  @Override
  public IPair<Integer, Integer> put(final Integer key, final IPair<Integer, Integer> value) {
    return disegno.put(key, value);
  }


  @Override
  public IPair<Integer, Integer> remove(final Object key) {
    return disegno.remove(key);
  }


  @Override
  public void putAll(final Map<? extends Integer, ? extends IPair<Integer, Integer>> map) {
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
  public Collection<IPair<Integer, Integer>> values() {
    return null;
  }


  @Override
  public Set<java.util.Map.Entry<Integer, IPair<Integer, Integer>>> entrySet() {
    return null;
  }


}
