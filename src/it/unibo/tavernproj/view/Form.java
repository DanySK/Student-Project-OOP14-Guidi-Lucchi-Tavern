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

public class Form extends JFrame implements IForm{

	private static final long serialVersionUID = 1L;
	//numero massimo di campi per il form
	public static final int MAX = 100;
	
	private final JButton okButton = new JButton("OK");
	public JLabel date;
	private final JLabel reserv = new JLabel();
	
	private final ProgressiveAcceptor<JPanel> panelAggregator = new ProgressiveAcceptorImpl<>();
	private final Map<String, JComponent> map = new HashMap<>();

	private IFormController ctrl;
	
	/*Usare l'esame 01b del 2015 per fare la form!*/
	public Form(String date){
		super();
		setLocationByPlatform(true);
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final int sw = (int) screen.getWidth() * 1/2;
		final int sh = (int) screen.getHeight() * 1/2;
		this.setSize(sw, sh);
		this.setResizable(true);	
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.date = new JLabel(date);
		//ctrl.setDate(date);
		
		loadReservation();
		
		buildLayout();
		setHandlers();	
		
		this.setVisible(true);		
	}
	
	private void loadReservation() {
		//scaricare da un file le prentazioni in base alla data
		
		reserv.setText(""); //stampare il set con le prenotazioni nel file di sopra
		this.validate();
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
		acceptPanel(new JLabel("Tavolo"), FlowLayout.RIGHT, 0);
		acceptPanel(new JLabel("Nome"),FlowLayout.RIGHT, 1);
		acceptPanel(new JLabel("Orario"),FlowLayout.RIGHT, 2);
		acceptPanel(new JLabel("Numero Persone"),FlowLayout.RIGHT, 3);
		acceptPanel(new JLabel("Telefono"),FlowLayout.RIGHT, 4);
		acceptPanel(new JLabel("Menu' fisso"),FlowLayout.RIGHT, 5);
		acceptPanel(new JLabel(""),FlowLayout.RIGHT, 6);
		
		center.add(panelAggregator.aggregateAll());
		
		acceptPanel(addTextField("Tav"), FlowLayout.CENTER, 0);
		acceptPanel(addTextField("Nome"), FlowLayout.CENTER, 1);
		acceptPanel(addTextField("Ora"), FlowLayout.CENTER, 2);
		acceptPanel(addTextField("Num"), FlowLayout.CENTER, 3);
		acceptPanel(addTextField("Tel"), FlowLayout.CENTER, 4);
		acceptPanel(addRadioButton("Menu"), FlowLayout.CENTER, 5);
		acceptPanel(addTextField("Menu fisso"), FlowLayout.CENTER, 6);
		map.get("Menu fisso").setVisible(false);
		
		center.add(panelAggregator.aggregateAll());
		
		//Set<IReservation> res = ctrl.getRes();
		//reserv.setText(res.toString());
		
		final JPanel north = new JPanel(new BorderLayout());
		north.add(date, BorderLayout.NORTH);
		north.add(reserv, BorderLayout.CENTER);
		this.getContentPane().add(north, BorderLayout.NORTH);
		
		this.getContentPane().add(center, BorderLayout.CENTER);
		
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

	public void attachViewObserver(IFormController formController) {
		this.ctrl = formController;		
	}	
	
	public String getTable() {
		if (((JTextField) map.get("Tav")).getText().equals("")){
			throw new NumberFormatException();
		}
		return ((JTextField) map.get("Tav")).getText();
	}

	public String getName() {
		if (((JTextField)map.get("Nome")).getText().equals("")){
			throw new NullPointerException();
		}
		return ((JTextField)map.get("Nome")).getText();
	}

	public String getH() {
		if (((JTextField) map.get("Ora")).getText().equals("")){
			throw new NullPointerException();
		}
		return ((JTextField) map.get("Ora")).getText();
	}

	public String getTel() {
		if (((JTextField) map.get("Tel")).getText().equals("")){
			throw new NullPointerException();
		}
		return ((JTextField)map.get("Tel")).getText();
	}

	public String getNum() {
		if (((JTextField) map.get("Num")).getText().equals("")){
			throw new NumberFormatException();
		}
		return ((JTextField) map.get("Num")).getText();
	}

	public Optional<String> getMenu() {
		return Optional.ofNullable(((JTextField)map.get("Menu fisso")).getText());
	}

	public boolean isMenuSelected() {
		return ((JRadioButton) map.get("Menu")).isSelected();
	}
	
	
/*
	@Override
	public void disableAll() {
		for (JComponent c: map.values()){
			c.setEnabled(false);
		}		
	}

	@Override
	public void enableAll() {
		for (JComponent c: map.values()){
			c.setEnabled(true);
		}		
	}
	
	boolean modified = false;
	
	@Override
	public void setModified(){
		this.modified = true;
	}

	@Override
	public boolean isBeenModified() {
		return this.modified;
	}

	@Override
	public void setTable(String srt) {
		((JTextField) map.get("Tav")).setText(srt);		
	}
	
	@Override
	public void setName(String srt) {
		((JTextField) map.get("Nome")).setText(srt);		
	}

	@Override
	public void setH(String srt) {
		((JTextField) map.get("Ora")).setText(srt);		
	}

	@Override
	public void setTel(String srt) {
		((JTextField) map.get("Tel")).setText(srt);
	}

	@Override
	public void setNum(String srt) {
		((JTextField) map.get("Num")).setText(srt);
	}

	@Override
	public void setMenu(String srt) {
		((JTextField) map.get("Menu fisso")).setText(srt);
	}	*/
	
	
	
}
