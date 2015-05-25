package it.unibo.tavernproj.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Giulia Lucchi
 *      modify by @author Eleonora Guidi
 */

public class Model implements IModel, Serializable {
  
  private static final long serialVersionUID = -4827500479091017750L;
  
  private Map<String, Map<Integer, IReservation>> map; 

  public Model() {
    this.map = new HashMap<>();
  }
  
  @Override
  public Map<String, Map<Integer, IReservation>> getModel() {
    return this.map;
  }
  
  public boolean isEmpty() {
    return this.map.size() == 0;
  }

  /*usato nel test junit*/
  @Override
  public void setModel(final Map<String, Map<Integer, IReservation>> map) {
    this.map = map;
  }

  @Override
  public void add(final String date, final IReservation res) 
                                            throws IllegalArgumentException {
    Map<Integer, IReservation> temp = new HashMap<>();
    if (map.containsKey(date)) {
      temp = map.get(date); 
    }
    if (temp.containsKey(res.getTable())) {
      throw new IllegalArgumentException();
    }
    temp.put(res.getTable(), res);
    map.put(date, temp);
  }

  @Override
  public void remove(final String date, final IReservation pren) {
    //ho dovuto utilizzare l'iterator altrimenti mi dava il CurrentModificationException
    //http://stackoverflow.com/questions/602636/concurrentmodificationexception-and-a-hashmap
    if (map.containsKey(date)) {
      final Map<Integer,IReservation> temp = map.get(date);
      final Iterator<Entry<Integer, IReservation>> it = temp.entrySet().iterator();
      while (it.hasNext()) {
        final Entry<Integer, IReservation> item = it.next();
        if (item.getValue().equals(pren)) {
          it.remove();
          if (temp.isEmpty()) {
            map.remove(date);
          }
        }
      }
    }
  }
  
  @Override
  public void remove(final String date, final Integer table) {
    if (map.containsKey(date)) {
      final Map<Integer,IReservation> temp = map.get(date);
      if (temp.containsKey(table)) {
        map.get(date).remove(table);
      } else {
        throw new IllegalArgumentException("Non esiste quel tavolo");
      }
    } else {
      throw new IllegalArgumentException("Non esiste quella data");
    }
  }

  @Override
  public Set<IReservation> getRes(final String date) {
    final Set<IReservation> res = new HashSet<>();
    if (map.containsKey(date)) {
      final Map<Integer,IReservation> temp = map.get(date);
      for (final Integer i : temp.keySet()) {
        res.add(temp.get(i));
      }
    }
    return res;
  }

  @Override
  public Map<Integer,IReservation> getTableRes(final String date) {
    return map.get(date);
  }

  @Override
  public Map<String, Map<Integer, IReservation>> getMap() {
    return this.map;
  }


  @Override
  public int getSize() {
    return map.size();
  }

  @Override
  public Set<IReservation> getNameRes(final String name) {
    final Set<IReservation> res = new HashSet<>();
    for (final String date : map.keySet()) {
      for (final Integer i : this.getTableRes(date).keySet()) {
        final IReservation temp = this.getTableRes(date).get(i);
        if (temp.getName().equals(name)) {
          res.add(temp);
        }
      }  
    }
    return res;
  }

  @Override
  public Set<String> getDates() {
    return map.keySet();
  }
}