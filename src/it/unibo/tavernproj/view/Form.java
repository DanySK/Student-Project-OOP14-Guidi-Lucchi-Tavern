package it.unibo.tavernproj.view;

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

public class Form extends JFrame{

	private static final long serialVersionUID = 1L;
	//numero massimo di campi per al form
	public static final int MAX = 100;
	
	private final JButton okButton = new JButton("OK");
	
	private final static ProgressiveAcceptor<JPanel> panelAggregator = new ProgressiveAcceptorImpl<>();
	private final Map<String, JComponent> map = new HashMap<>();

	
	/*Usare l'esame 01b del 2015 per fare la form!*/
	public Form(){
		setLocationByPlatform(true);
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final int sw = (int) screen.getWidth() * 1/2;
		final int sh = (int) screen.getHeight() * 1/2;
		this.setSize(sw, sh);
		this.setResizable(true);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(BorderLayout.SOUTH, okButton);
		panelAggregator.setProgressiveFilter(new ProgressiveFilter<JPanel>(){
			@Override
			public boolean isNextOK(JPanel previous, JPanel next) {
				return true;
			}			
		});
		
		panelAggregator.setAggregator(new Aggregator<JPanel>(){
			@Override
			public JPanel aggregate(JPanel one, JPanel two) {
				JPanel temp = new JPanel(new GridBagLayout());
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
		
		acceptPanel(new JLabel("Nome"),FlowLayout.RIGHT, 0);
		acceptPanel(new JLabel("Data"),FlowLayout.RIGHT, 1);		
		acceptPanel(new JLabel("Orario"),FlowLayout.RIGHT, 2);
		acceptPanel(new JLabel("Numero Persone"),FlowLayout.RIGHT, 3);
		acceptPanel(new JLabel("Telefono"),FlowLayout.RIGHT, 4);
		acceptPanel(new JLabel("Menu' fisso"),FlowLayout.RIGHT, 5);
		acceptPanel(new JLabel(""),FlowLayout.RIGHT, 6);
		
		center.add(panelAggregator.aggregateAll());
		
		acceptPanel(addTextField("Nome"), FlowLayout.CENTER, 0);
		acceptPanel(addTextField("Data"), FlowLayout.CENTER, 1);
		acceptPanel(addTextField("Ora"), FlowLayout.CENTER, 2);
		acceptPanel(addTextField("Num"), FlowLayout.CENTER, 3);
		acceptPanel(addTextField("Tel"), FlowLayout.CENTER, 4);
		acceptPanel(addRadioButton("Menu"), FlowLayout.CENTER, 5);
		acceptPanel(addTextField("Menu fisso"), FlowLayout.CENTER, 6);
		map.get("Menu fisso").setVisible(false);
		
		center.add(panelAggregator.aggregateAll());		
		
		((JRadioButton) map.get("Menu")).addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent e) {					
				//PERCHèèèèè NON VAAAAAAA?!!!!!!
				
				System.out.print("a");
				map.get("Menu fisso").setVisible(true);
				
				//devo aggirnare la view per mostrare la textbox credo
				//controller.resend();
				
				System.out.print("b");
			}			
		});
		
		this.getContentPane().add(BorderLayout.CENTER,center);
		
		this.setVisible(true);		
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

}
