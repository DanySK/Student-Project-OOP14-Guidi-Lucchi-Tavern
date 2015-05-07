package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.FormController;
import it.unibo.tavernproj.controller.IFormController;
import it.unibo.tavernproj.model.IReservation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * @author Eleonora Guidi
 * 
 * modified by @author Giulia Lucchi
 *
 */


//vedere se fare un'interfaccia per la form

public class NewReservationForm extends ReservationForm{

	private static final long serialVersionUID = 1L;
	public JLabel date;
	private final JLabel reserv = new JLabel();
	
	/*Usare l'esame 01b del 2015 per fare la form!*/
	public NewReservationForm(String date){
		super();
		this.date = new JLabel(date);
		
		loadReservation();
		
		buildLayout();

		
		this.setVisible(true);		
	}
	
	private void loadReservation() {
		//scaricare da un file le prentazioni in base alla data
		
		reserv.setText(""); //stampare il set con le prenotazioni nel file di sopra
		this.validate();
	}

	private void buildLayout() {
		final JPanel north = new JPanel(new BorderLayout());
		north.add(date, BorderLayout.NORTH);
		north.add(reserv, BorderLayout.CENTER);
		super.getContentPane().add(north, BorderLayout.NORTH);		
	}

	public void attachViewObserver(IFormController formController) {
		super.attachViewObserver(formController);
	}	
	
	public Integer getTable() {
		return super.getTable();
	}

	public String getName() {
		return super.getName();
	}

	public String getH() {
		return super.getH();
	}

	public String getTel() {
		return super.getTel();
	}

	public String getNum() {
		return super.getNum();
	}

	public Optional<String> getMenu() {
		return super.getMenu();
	}

	public boolean isMenuSelected() {
		return super.isMenuSelected();
	}	
	
	
}
