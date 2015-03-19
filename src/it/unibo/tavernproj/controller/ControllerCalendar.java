package it.unibo.tavernproj.controller;

import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.view.View;

import java.util.HashSet;
import java.util.Set;

public class ControllerCalendar {

	private final IModel model = new Model();
	
	public String dateCurrent(View v){
		return v.getCalendar().setPickedDate();
	}
	
	public Set<Reservation> reservations(View v){
		
		Set<Reservation> set = new HashSet<>();
		
		for(String s : model.getMap().keySet()){
			if(s.equals(this.dateCurrent(v))){
				Reservation r = model.getMap().get(s);
				set.add(r);	
				
			}
		}
		return set;
		
	}
}
