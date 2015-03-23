package it.unibo.tavernproj.controller;

import java.util.HashSet;
import java.util.Set;

import it.unibo.tavernproj.view.Form;
import it.unibo.tavernproj.view.FormFrame;

public class FormController implements IFormController {
	
	IController c = new Controller();
	private final Set<FormFrame> form = new HashSet<>();
	private String currentDate;

	@Override
	public void tableAdd() {
		System.out.print("b");			
		c.tableAdd();	
	}

	@Override
	public void addView(FormFrame f) {
		f.attachViewObserver(this);
		form.add(f);
	}

	@Override
	public void removeView(FormFrame f) {
		form.remove(f);		
	}

	@Override
	public String getDate() {
		return this.currentDate;
	}

	
	
	
}
