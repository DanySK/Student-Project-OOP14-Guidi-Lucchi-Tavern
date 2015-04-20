package it.unibo.tavernproj.controller;
import java.util.HashSet;
import java.util.Set;

import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.ISaveReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.model.SaveReservation;
import it.unibo.tavernproj.view.NewReservationForm;
import it.unibo.tavernproj.view.IView;

/**
 * @author Eleonora Guidi
 * 
 * modify by @author Giulia Lucchi
 *
 */

//HO USATO IL PATTERN SINGLETON

public class Controller implements IController{
  
	private static final Controller SINGLETON = new Controller();  
	
	private final Set<IView> view = new HashSet<>();
	private final IModel model = new Model();
	private final ISaveReservation res = new SaveReservation();

	private Controller(){};
	
	public static Controller getController(){
	  return SINGLETON;
	}

	@Override
	public void tablesLoad() {
		res.load();
		
	}

	@Override
	public void tablesSave() {
		res.save();
	}

	@Override
	public void addTable(String table, String date) {
		//this.reservationAdd();
		for (final IView v: view){	
			v.addTable(table, date);
		}
		
	}

	@Override
	public void removeTable(final int number) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public IReservation getReservation(int table, String date) {
		for (IReservation r: model.getRes(date)){
			if (r.getTable() == table){
				return r;
			}
		}
		throw new NumberFormatException();
	}

	@Override
	public void addReservation() {
		// TODO Auto-generated method stub
		
		//parte di modello: salvare la prenotazione sul file system
		//poi vedremo se cavare tutti e metterli direttamente in tableAdd!!
	}

	@Override
	public void removeReservation() {
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
	}

	@Override
	public void removeView(final IView v) {
		view.remove(v);	
	}




}