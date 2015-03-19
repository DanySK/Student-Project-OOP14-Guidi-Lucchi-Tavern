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
		//this.reservationAdd();
		System.out.print(view.size());
		for (final IView v: view){
			//magicamente la lunghezza di view diventa 0 -.-'''''''''
			//quindi non entra qui!
			System.out.print("d");
			
			v.addTable();
		}
		
	}

	@Override
	public void tableRemove(final int number) {
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
	public void addView(final IView v) {
		v.attachViewObserver(this);
		view.add(v);
		System.out.print(view.size());
	}

	@Override
	public void removeView(final IView v) {
		view.remove(v);	
	}


}
