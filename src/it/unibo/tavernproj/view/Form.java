package it.unibo.tavernproj.view;

import it.unibo.tavernproj.calendar.Calendar;
import it.unibo.tavernproj.calendar.CalendarController;
import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.FormController;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.controller.IFormController;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.Model;

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
 */

//vedere se fare un'interfaccia per la form

public class Form extends JFrame{

	private static final long serialVersionUID = 1L;
	//numero massimo di campi per il form
	public static final int MAX = 100;
	
	private final JButton okButton = new JButton("OK");
	public final JLabel date = new JLabel();
	private final JLabel reserv = new JLabel();
	
	private final ProgressiveAcceptor<JPanel> panelAggregator = new ProgressiveAcceptorImpl<>();
	private final Map<String, JComponent> map = new HashMap<>();
	//private final IModel model = new Model();
	//private final CalendarController contrCal = new CalendarController();
	
	//private boolean okState = false;
	private IFormController ctrl;
	
	private String currentDate;
	
	/*Usare l'esame 01b del 2015 per fare la form!*/
	public Form(String date){
		super();
		setLocationByPlatform(true);
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final int sw = (int) screen.getWidth() * 1/2;
		final int sh = (int) screen.getHeight() * 1/2;
		this.setSize(sw, sh);
		this.setResizable(true);	
		
		buildLayout();
		setHandlers();	
		
		this.currentDate = date;
		
		this.setVisible(true);		
	}
	
	private void setHandlers() {
		((JRadioButton) map.get("Menu")).addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (((JRadioButton) map.get("Menu")).isSelected()){
					map.get("Menu fisso").setVisible(true);
					Form.this.validate();
				}
				else{
					map.get("Menu fisso").setVisible(false);
					Form.this.validate();
				}				
			}			
		});		
		
		this.okButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(final ActionEvent arg0) {
				Form.this.setVisible(false);
				//okState = true;
				System.out.print("a");
				ctrl.tableAdd();
			}			
		});
		
	}

	private void buildLayout() {
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(BorderLayout.SOUTH, okButton);
		panelAggregator.setProgressiveFilter(new ProgressiveFilter<JPanel>(){
			@Override
			public boolean isNextOK(final JPanel previous, final JPanel next) {
				return true;
			}			
		});
		
		panelAggregator.setAggregator(new Aggregator<JPanel>(){
			@Override
			public JPanel aggregate(final JPanel one, final JPanel two) {
				final JPanel temp = new JPanel(new GridBagLayout());
				GridBagConstraints gap = new GridBagConstraints();
				gap.gridy = 0;
				temp.add(one, gap);
				gap.gridy++;
				temp.add(two, gap);
				return temp;
			}
			
		});
		
		panelAggregator.setSize(MAX);
		
		// Pannello centrale, ossia una griglia a due colonne
		final JPanel center = new JPanel(new GridLayout(0,2));
		
		/*fare un metodo per mettere insieme sti accept!!*/
		acceptPanel(new JLabel("Nome"),FlowLayout.RIGHT, 0);
		acceptPanel(new JLabel("Orario"),FlowLayout.RIGHT, 1);
		acceptPanel(new JLabel("Numero Persone"),FlowLayout.RIGHT, 2);
		acceptPanel(new JLabel("Telefono"),FlowLayout.RIGHT, 3);
		acceptPanel(new JLabel("Menu' fisso"),FlowLayout.RIGHT, 4);
		acceptPanel(new JLabel(""),FlowLayout.RIGHT, 5);
		
		center.add(panelAggregator.aggregateAll());
		
		acceptPanel(addTextField("Nome"), FlowLayout.CENTER, 0);
		acceptPanel(addTextField("Ora"), FlowLayout.CENTER, 1);
		acceptPanel(addTextField("Num"), FlowLayout.CENTER, 2);
		acceptPanel(addTextField("Tel"), FlowLayout.CENTER, 3);
		acceptPanel(addRadioButton("Menu"), FlowLayout.CENTER, 4);
		acceptPanel(addTextField("Menu fisso"), FlowLayout.CENTER, 5);
		map.get("Menu fisso").setVisible(false);
		
		center.add(panelAggregator.aggregateAll());
		
		this.add(date, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(this.reserv, BorderLayout.NORTH);
		panel.add(center, BorderLayout.CENTER);
		
		this.date.setText(this.currentDate);
		
		this.reserv.setText(this.currentDate);
		
		this.getContentPane().add(BorderLayout.CENTER,panel);
		
	}

	private JTextField addTextField(final String string) {
		final JTextField t = new JTextField(20);		
		t.setName(string);	
		map.put(string, t);
		return t;
	}
	
	private JRadioButton addRadioButton(final String string) {
		final JRadioButton b = new JRadioButton();	
		b.setSelected(false);
		b.setName(string);	
		map.put(string, b);
		return b;
	}	

	private void acceptPanel(final JComponent component, final int orientation, final int pos) {
		final JPanel panel = new JPanel(new FlowLayout(orientation));
		panel.add(component);	
		panelAggregator.accept(pos, panel);	
	}
	
	/**
	 * @return se il bottone è stato premuto.
	 * 
	 * @return true if "OK" has been selected
	 */
	//public boolean isOkState(){
	//	return this.okState;
	//}
}
