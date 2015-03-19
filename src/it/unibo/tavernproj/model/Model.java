package it.unibo.tavernproj.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * @author Giulia Lucchi
 */

public class Model implements IModel {
	
	private Map<String, Reservation> map = new HashMap<>();

	@Override
	public void add(final Reservation pren) {
		map.put(pren.getDate(), pren);
	}

	@Override
	public void remove(final Reservation pren) {
		map.remove(pren);
	}

	@Override
	public Map<String,Reservation> getMap() {
		return map;
	}

	
}
