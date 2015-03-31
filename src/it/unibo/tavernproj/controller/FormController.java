package it.unibo.tavernproj.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IReservation;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Reservation;
import it.unibo.tavernproj.view.Form;

public class FormController implements IFormController {
	
	private final Set<Form> form = new HashSet<>();
	/*da creare*/
	private final IModel model = new Model();
	private String date;
	private IReservation res = null;
	
	
	@Override
	public void addView(Form f) {
		f.attachViewObserver(this);
		form.add(f);
	}

	@Override
	public void removeView(Form f) {
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
	public void save(String table, String name, String h, String tel, String numPers, Optional<String> menu) {
		res = new Reservation(table, name, this.date, h, tel, Integer.parseInt(numPers), menu);
		model.add(this.date, res);
	}

	@Override
	public Set<IReservation> getRes(String date) {
		return model.getRes(date);
	}


	
}
