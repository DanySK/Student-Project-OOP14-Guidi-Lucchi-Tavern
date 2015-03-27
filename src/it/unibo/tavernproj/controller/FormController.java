package it.unibo.tavernproj.controller;

import java.util.HashSet;
import java.util.Set;

import it.unibo.tavernproj.view.Form;

public class FormController implements IFormController {
	
	private final Set<Form> form = new HashSet<>();
	
	@Override
	public void addView(Form f) {
		f.attachViewObserver(this);
		form.add(f);
	}

	@Override
	public void removeView(Form f) {
		form.remove(f);		
	}
/*
	@Override
	public String getDate() {
		return this.currentDate;
	}

	@Override
	public void setDate(String date) {
		this.currentDate = date;
	}
*/
	
	
	
}
