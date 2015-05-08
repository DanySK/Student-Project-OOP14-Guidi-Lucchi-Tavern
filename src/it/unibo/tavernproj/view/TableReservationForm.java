package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IFormController;
import it.unibo.tavernproj.model.IReservation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TableReservationForm extends ReservationForm{
	
	private static final long serialVersionUID = 1L;
	private final JButton modifyButton = new JButton("Modifica");
	public JLabel date;
	private final IReservation res;
	private boolean modified = false;
	
	/*Usare l'esame 01b del 2015 per fare la form!*/
	public TableReservationForm(String date, IReservation res){
		super();
		this.date = new JLabel(date);
		this.res = res;
		
		buildLayout();		
		setHandlers();
		
		writeForm();
		
		this.setVisible(true);		
	}

	private void writeForm() {
		super.setTable(res.getTable());		
		super.setName(res.getName());
		super.setH(res.getHours());
		super.setTel(res.getTel());
		super.setNum(Integer.parseInt(res.getNumPers()));
		/*if (res.getMenu().isPresent()){
		  super.setMenu(res.getMenu().get());
		}*/
		try{
		  super.setMenu(res.getMenu());
		} catch(Exception e){
		  //
		}
	}

	private void buildLayout() {
		final JPanel north = new JPanel(new BorderLayout());
		north.add(date, BorderLayout.WEST);
		north.add(modifyButton, BorderLayout.EAST);		
		super.getContentPane().add(north, BorderLayout.NORTH);			
		this.disableAll();
	}
	
	private void setHandlers() {
		this.modifyButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				TableReservationForm.this.enableAll();				
				TableReservationForm.this.modified = true;
			}			
		});	
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

	public String getMenu() {
		return super.getMenu();
	}

	public boolean isMenuSelected() {
		return super.isMenuSelected();
	}

	@Override
	public void disableAll(){		
		super.disableAll();	
	}

	@Override
	public void enableAll() {
		super.enableAll();		
	}

	public boolean isBeenModified() {
		return this.modified;
	}

  public IReservation getOld() {
    return this.res;
  }


}
