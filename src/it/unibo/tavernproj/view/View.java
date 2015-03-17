package it.unibo.tavernproj.view;
import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JCalendar;

/**
 * @author Eleonora Guidi
 *
 */

//per adattare l'immagine allo sfondo! http://www.hwupgrade.it/forum/archive/index.php/t-2060553.html

public class View extends JFrame implements IView{

	private static final long serialVersionUID = 1L;	
	
	private final IconBuilder build = new IconBuilder();	
	private final JButton bNew = new JButton("Nuova Prenotazione"); 
	private final LinkedList<JButton> table = new LinkedList<>();
	private IController controller;
	
	public View(){
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		
		/* Set window dimension to the screen */
		/* Se si salva sempre sullo stesso pc funziona anche usando le dimensioni dello schermo,
		 * se invece si apre il programma su un altro computer e le informazioni di salvataggio non sono lì
		 * non va, serve settare le dimensioni fisse o fare apposite funzioni per ricalcolare le posizioni.
		 */ 
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final int sw = (int) screen.getWidth() * 3/4;
		final int sh = (int) screen.getHeight() * 3/4;
		this.setSize(sw, sh);
		this.setResizable(true);
			
		buildLayout();	
		
		/*Fare metodi a parte per cambiare lo stile dei bottoni 
		 *e anche degli altri componenti che si rovano in icon builder
		 *(tipo il pannello si può rendere bianco di là)*/
		bNew.setFont(new Font("Arial", Font.BOLD, 18));
		bNew.setBackground(Color.white);
		
		setHandlers();
		
		this.setVisible(true);
	}	
	
JPanel tablesButtons = build.buildPanel(new FlowLayout());	

	private void buildLayout() {
		
		final JLabel map = build.buildLabel("res/map.png");	
		final JPanel dx = build.buildPanel(new BorderLayout());		
		final JPanel pNew = build.buildPanel(new GridBagLayout());			
		final JLabel logo = build.buildLabel("res/logo.jpg");
		
		for (JButton b: table){
			tablesButtons.add(b);
		}			
		
		
		GridBagConstraints gap = new GridBagConstraints();		
		gap.gridy = 0;
		gap.insets = new Insets(10, 10, 20, 10);
		gap.fill = GridBagConstraints.HORIZONTAL;
		bNew.setSize(pNew.getWidth(), pNew.getHeight()*1/10);
		pNew.add(bNew, gap);
		gap.gridy++;
		//pNew.add(bWarehouse, gap);
		
		dx.add(pNew, BorderLayout.CENTER);
		dx.add(logo, BorderLayout.NORTH);
		
		final JPanel center = build.buildPanel(new BorderLayout());
		center.add(map, BorderLayout.CENTER);
		center.add(tablesButtons, BorderLayout.SOUTH);
		
		final JPanel main = build.buildPanel(new BorderLayout(5, 5));		
		main.add(center, BorderLayout.CENTER);
		main.add(dx, BorderLayout.EAST);
		
		this.getContentPane().add(main);
		
	}
		
	/**
	 * @author Giulia Lucchi
	 *
	 */
	private void setHandlers() {
		
		bNew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frame = new JFrame("JCalendar");

				JCalendar jcalendar = new JCalendar();
				frame.getContentPane().add(jcalendar);
				frame.pack();
				frame.setVisible(true);
	
			}			
		});
		
	}
	
	@Override
	public void attachViewObserver(IController listener) {
		this.controller = listener;
	}

	@Override
	public void addTable() {
		/* System.getProperty("user.home")+System.getProperty("file.separator")+
				*/ 
		
		//NON VAAAAAAAAAAAAAAAA! 
		//non mostra i bottoni, non so più come fare!!
		
		int i = 0;
		
		JButton b = build.buildButton("res" + System.getProperty("file.separator") + i + "s.png");
		
		i++;

		table.addLast(b);	
		this.validate();

	}
	
	
	public static void main(String[] argv){
		final Controller c = new Controller();
		final View v = new View();		
		c.addView(v);
	}
}
