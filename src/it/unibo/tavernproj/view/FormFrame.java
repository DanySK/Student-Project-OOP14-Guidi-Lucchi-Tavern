package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IFormController;

import javax.swing.JFrame;

public class FormFrame extends JFrame{
	
	IFormController ctrl;
	private Form form;
	
	public FormFrame(Form f){
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.form = f;
		
		this.add(form);
	}

	public void attachViewObserver(IFormController formController) {
		this.ctrl = formController;		
	}

}
