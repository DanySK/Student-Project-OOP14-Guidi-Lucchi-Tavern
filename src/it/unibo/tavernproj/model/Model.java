package it.unibo.tavernproj.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Giulia Lucchi
 * 
 * 	modified by @author Eleonora Guidi
 */

public class Model implements IModel {
	
	private Map<String, Map<String,IReservation>> map = new HashMap<>();

	@Override
	public void add(final String date, final IReservation pren) {
		//FARE ANCHE IL SALVATAGGIO SU FILE!
		//Mi servono il salvataggio e un metodo per ricavare la reservation dandogli la data
		Map<String, IReservation> temp=new HashMap<>();
		
		if(map.containsKey(date)){
			temp= map.get(date);
			temp.put(pren.getTable(),pren);
		}
		
		temp.put(pren.getTable(), pren);
		map.put(date,temp);
		
//		if (map.containsKey(date)){
//			for (IReservation r: map.get(date)){
//				if (r.getTable().equals(pren.getTable())){
//					map.get(date).remove(r);
//				}
//			}
//			map.get(date).add(pren);
//		}
//		else{
//			Set<IReservation> temp = new HashSet<>();
//			temp.add(pren);
//			map.put(date, temp);
//		}
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
		if (map.containsKey(date)) {
			Map<String,IReservation> temp=map.get(date);
			for(String s : temp.keySet()){
				if(temp.get(s).equals(pren)){
					map.remove(s);
				}
			}
		}
	}
	
	@Override
	public Set<IReservation> getRes(final String date) {
		Set<IReservation> res = new HashSet<>();
		if (map.containsKey(date)) {
			Map<String,IReservation> temp=map.get(date);
			for(String s : temp.keySet()){
				res.add(temp.get(s));
			}
		}
		
		return res;
	}


	
}