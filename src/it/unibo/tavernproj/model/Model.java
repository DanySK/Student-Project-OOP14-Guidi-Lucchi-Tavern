package it.unibo.tavernproj.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Giulia Lucchi
 * 
 * 	modified by @author Eleonora Guidi
 */

public class Model implements IModel {
	
	private Map<String, Set<IReservation>> map = new HashMap<>();

	@Override
	public void add(final String date, final IReservation pren) {
		//FARE ANCHE IL SALVATAGGIO SU FILE!
		if (map.containsKey(date)){
			for (IReservation r: map.get(date)){
				if (r.getTable().equals(pren.getTable())){
					map.get(date).remove(r);
				}
			}
			map.get(date).add(pren);
		}
		else{
			Set<IReservation> temp = new HashSet<>();
			temp.add(pren);
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
	}

	@Override
	public void remove(final String date, final IReservation pren) {
		if (map.containsKey(date)) {
			map.get(date).remove(pren);
		}
	}
	
	@Override
	public Set<IReservation> getRes(String date) {
		if (map.containsKey(date)) {
			return map.get(date);
		}
		return new HashSet<>();
	}

	@Override
	public Map<String, Set<IReservation>> getMap() {
		return map;
	}
	
}