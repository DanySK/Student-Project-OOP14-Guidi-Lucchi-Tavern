package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IReservation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
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
	private final JButton deleteButton = new JButton("Elimina");
	public JLabel date;
	private final IReservation res;
	private boolean modified = false;
  private boolean deleted = false;
  
  private IUtilities utilities = new Utilities();
	
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
	  final JPanel east = utilities.buildGridPanel(modifyButton, deleteButton, 5);	  
	  
		final JPanel north = new JPanel(new BorderLayout());
		north.add(date, BorderLayout.WEST);
		
		north.add(east, BorderLayout.EAST);		
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
		
		this.deleteButton.addActionListener(new ActionListener(){
		  @Override
      public void actionPerformed(ActionEvent arg0) {
        
		    TableReservationForm.this.deleted = true;
        TableReservationForm.this.setVisible(false);
      }		  
		});
    
	}

	public boolean isBeenModified() {
		return this.modified;
	}
	
	public boolean isBeenDeleted() {
    return this.deleted ;
  }

  public IReservation getOld() {
    return this.res;
  }


}
