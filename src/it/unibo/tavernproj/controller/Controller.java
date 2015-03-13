package it.unibo.tavernproj.controller;
import java.util.HashSet;
import java.util.Set;

import it.unibo.tavernproj.view.IView;

/**
 * @author Eleonora Guidi
 *
 */

public class Controller implements IController{
	
	private final Set<IView> view = new HashSet<>();

	@Override
	public void tablesLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tablesSave() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tableAdd() {		
		this.reservationAdd();
		for (IView v: view){
			v.addTable();
		}
		
	}

	@Override
	public void tableRemove(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reservationAdd() {
		// TODO Auto-generated method stub
		
		//parte di modello: salvare la prenotazione sul file system
		//poi vedremo se cavare tutti e metterli direttamente in tableAdd!!
	}

	@Override
	public void reservationRemove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resend() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addView(IView v) {
		v.attachViewObserver(this);
		view.add(v);		
	}

	@Override
	public void removeView(IView v) {
		view.remove(v);		
	}


}
