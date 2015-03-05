package Progetto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View extends JFrame implements IView{

	private static final long serialVersionUID = 1L;	
	
	private final IconBuilder build = new IconBuilder();
	
	private final JButton bNew = build.buildButton("res/np24.png"); 

	
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
		setHandlers();
		
		this.setVisible(true);
	}	

	private void buildLayout() {
		
		JLabel map = build.buildLabel("res/map.png");
		
		JPanel dx = build.buildPanel(new BorderLayout());
		
		JPanel pNew = build.buildPanel(new GridBagLayout());		
		
		JPanel tablesButtons = build.buildPanel(new FlowLayout());
		/* vedere se mettere i bottoni in un vettore (se sono un numero finito)
		 * o linked list di bottoni aggiungo in fondo e ricavo gli indici
		 * 
		 * System.getProperty("user.home")+System.getProperty("file.separator")+
		 * */
		JButton one = build.buildButton("res/1s.png", "icon");
		JButton two = build.buildButton("res/2s.png", "icon");
		tablesButtons.add(one);
		tablesButtons.add(two);	
		
		JLabel logo = build.buildLabel("res/logo.jpg");
		
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
		
		JPanel center = build.buildPanel(new BorderLayout());
		center.add(map, BorderLayout.CENTER);
		center.add(tablesButtons, BorderLayout.SOUTH);
		
		JPanel main = build.buildPanel(new BorderLayout(5, 5));		
		main.add(center, BorderLayout.CENTER);
		main.add(dx, BorderLayout.EAST);
		
		this.getContentPane().add(main);
		
	}
	
	private Form form;
	
	private void setHandlers() {
		
		bNew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				form = new Form();
				form.setVisible(true);			
				
			}			
		});
		
	}
	
	
	public static void main(String[] argv){
		new View();
	}

	@Override
	public void attachViewObserver(IController listener) {
		this.controller = listener;
	}

}
