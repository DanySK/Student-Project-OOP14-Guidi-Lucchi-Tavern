package it.unibo.tavernproj.controller;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.view.NewReservationForm;
import it.unibo.tavernproj.view.IView;

/**
 * @author Eleonora Guidi
 * 
 * modify by @author Giulia Lucchi
 *
 */

public class Controller implements IController{
	
	
	private final Set<IView> view = new HashSet<>();
	private final IModel model = new Model();
	private String[] files;
	private ObjectOutput out;
	private ObjectInput  in;
	

	@Override
	public void tablesLoad( ) {
		this.load(null);//PER ORA HO MESSO NULL CAMBIARE!!!!
		
	}

	@Override
	public void tablesSave() {
		this.save();
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


	/*
	 * Save and Load
	 * @author Giulia Lucchi
	 * 
	 */
	
	@Override
	public void save(){
		int i = 0;
		for(String date : model.getMap().keySet()){
			files[i]=date;
			try{
				out = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home")+System.getProperty("file.separator")+date));
				//out.writeUTF(date); c'è già il nome del file con la data non gli scriverei anche la data
				Map<Integer, IReservation> temp = model.getMap().get(date);
				for(Integer table : temp.keySet()){
					out.writeObject(temp.get(table));
				}
				out.close();
				
			}catch (Exception e){
				
			}
			i++;
		}
		
		
	}
	
	//CARICO UN GIORNO ALLA VOLTA (quello della data presa come parametro)
	@Override
	public Map<Integer,IReservation> load(final String date){
		
			try{
				in = new ObjectInputStream(new FileInputStream(date));
				model.add(date,(IReservation) in.readObject());
				in.close();
			}catch(Exception e){
				
			}
		return model.getTableRes(date);
	}
	
}

