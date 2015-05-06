package it.unibo.tavernproj.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Giulia Lucchi
 * 
 */

public class Model implements IModel {
	
	private Map<String, Map <Integer, IReservation>> map; // = new HashMap<>();
	
	public Model(){
	  this.map = new HashMap<>();
	}
	
	public boolean isEmpty(){
	  return this.map.size() == 0;
	}
	
	/*usato nel test junit*/
	@Override
	public void setModel(Map<String, Map <Integer, IReservation>> map){
	  this.map = map;
	}

	@Override
	public void add(final String date, final IReservation pren) {
		
		Map<Integer, IReservation> temp = new HashMap<>();
		
		if (map.containsKey(date)){
			temp = map.get(date); 
		}
		temp.put(pren.getTable(), pren);
		map.put(date, temp);		
	}
		/*
		 * Fare un file per giorno
		 * salvare:
		 * - numero tavoli prenotati
		 * - per ogni tavolo salvare la IReservation
		 * 
		 * 
		 * 
		 * Vedere come fare per raggruppare i file a fine mese
		 * 
		 * */
	
	@Override
	public void remove(final String date, final IReservation pren) {
	//ho dovuto utilizzare l'iterator altrimenti mi dava il CurrentModificationException
	//http://stackoverflow.com/questions/602636/concurrentmodificationexception-and-a-hashmap
		if (map.containsKey(date)) {
			Map<Integer,IReservation> temp = map.get(date);
			Iterator<Entry<Integer, IReservation>> it = temp.entrySet().iterator();
			   while (it.hasNext()){
			      Entry item = it.next();
			      if( item.getValue().equals(pren)){
			    	  it.remove();
			    	  if(temp.size()==0){
			    		  map.remove(date);
			    	  }
			      }
			      
			   }
			}
			
	}
	
	@Override
	public Set<IReservation> getRes(final String date) {
		Set<IReservation> res = new HashSet<>();
		if (map.containsKey(date)) {
			Map<Integer,IReservation> temp = map.get(date);
			for(Integer i : temp.keySet()){
				res.add(temp.get(i));
			}
		}		
		return res;
	}
	
	@Override
	public Map<Integer,IReservation> getTableRes(String date){
//	  if (!map.containsKey(date)){
//	    throw new NullPointerException();
//	  }
		return map.get(date);
	}
	
	@Override
	public Map<String, Map <Integer, IReservation>> getMap(){
		return this.map;
	}
	
	public Model getModel(){
	  return this;
	}
	
	@Override
	public int getSize(){
		return map.size();
	}
	
	
	//eleonora
  @Override
  public void remove(String date, Integer table) {
    if (map.containsKey(date)) {
      Map<Integer,IReservation> temp = map.get(date);
      if (temp.containsKey(table)) {
        map.get(date).remove(table);
      }
      else {
        throw new IllegalArgumentException("Non esiste quel tavolo");
      }
     }
    else{
      throw new IllegalArgumentException("Non esiste quella data");
    }
  }
	
	@Override
	public Set<IReservation> getNameRes( final String name){
	  
	  Set<IReservation> res = new HashSet<>();
	  for(String date : map.keySet()){
	    Map<Integer,IReservation> mapRes = this.getTableRes(date);
	    for(Integer i : mapRes.keySet()){
	      IReservation temp = mapRes.get(i);
	      if(temp.getName().equals(name)){
	        res.add(temp);
	     }
	    }  
	   }
    return res;
	}


	
}