package it.unibo.tavernproj.calendar;

import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.view.IView;
import it.unibo.tavernproj.view.View;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

public class CalendarController implements ICalendarController{

	private final IModel model = new Model();
	private Calendar calendar;
	private Set<Calendar> view = new HashSet<>();
	
	public CalendarController(Calendar c){
		this.calendar = c;
	}
	
	public String getCurrentDate(){
		return this.calendar.setPickedDate();
	}
	
	public Set<Reservation> reservations(View v){
		
		Set<Reservation> set = new HashSet<>();
		
		for(String s : model.getMap().keySet()){
			if(s.equals(this.getCurrentDate())){
				Reservation r = model.getMap().get(s);
				set.add(r);	
				
			}
		}
		return set;		
	}
	
	@Override
	public void addView(final Calendar c) {
		c.attachViewObserver(this);
		view.add(c);
		System.out.print(view.size());
	}

	@Override
	public void removeView(final Calendar c) {
		view.remove(c);	
	}
	
	
}
