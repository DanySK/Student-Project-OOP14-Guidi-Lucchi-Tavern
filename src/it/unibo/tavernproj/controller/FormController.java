package it.unibo.tavernproj.controller;

import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.view.IReservationForm;
import it.unibo.tavernproj.view.NewReservationForm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


// PATTERN SINGLETON

public class FormController implements IFormController {
  
  private static final FormController SINGLETON = new FormController();
	
	private final Set<IReservationForm> form = new HashSet<>();
	/*da creare*/
	private IModel model = new Model();
	private String date;
	private IReservation res = null;	
	private ObjectOutput out;
	private ObjectInput  in;
	
	 private FormController(){};
	  
	  public static FormController getController(){
	    return SINGLETON;
	  }

	@Override
	public void addView(IReservationForm f) {
		f.attachViewObserver(this);
		form.add(f);
	}

	@Override
	public void removeView(NewReservationForm f) {
		form.remove(f);		
	}

	@Override
	public String getDate() {
		return this.date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}	

	@Override
	public Set<IReservation> getRes(String date) {
		return model.getRes(date);
	}

	@Override
	public void save(String table, String name, String h, String tel,
			String numPers, Optional<String> menu) {
		Reservation res = new Reservation(table, name, this.date, h, tel, Integer.parseInt(numPers), menu);
		/*try{
      in = new ObjectInputStream(new FileInputStream("map.txt"));
      model.setModel((Map<String, Map <Integer, IReservation>>) in.readObject());
      in.close();
    }catch(Exception e){
      
    }*/
		model.add(this.date, res);
		
		/*try{
	        out = new ObjectOutputStream(new FileOutputStream("map.txt"));
	        out.writeObject(this.model.getMap());
	        out.close();
	      }catch (Exception e){
	        
	  }*/
	}

  @Override
  public void setModel(IModel model) {
    this.model = model;
  }

  @Override
  public void delete(Integer table, String date) {
    model.remove(date, table);
    
  }
	
}
