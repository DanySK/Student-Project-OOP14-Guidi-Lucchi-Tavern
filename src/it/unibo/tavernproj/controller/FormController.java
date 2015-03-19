package it.unibo.tavernproj.controller;

import java.util.HashSet;
import java.util.Set;

import it.unibo.tavernproj.view.Form;
import it.unibo.tavernproj.view.IForm;

public class FormController implements IFormController {
	
	IController c = new Controller();
	private final Set<IForm> form = new HashSet<>();

	@Override
	public void tableAdd() {
		System.out.print("b");			
		c.tableAdd();	
	}

	@Override
	public void addView(Form f) {
		f.attachViewObserver(this);
		form.add(f);
	}

	@Override
	public void removeView(Form f) {
		form.remove(f);		
	}

	
	
	
}
