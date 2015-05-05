package it.unibo.tavernproj.disegno;

import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.MouseInputListener;

import it.unibo.tavernproj.view.Utilities;

public class ProvaDraw1 implements MouseInputListener {
	
	Utilities icon = new Utilities();
	private int x;
	private int y;
	private JLabel label;
	//private Icon map= label.getIcon();;
	
	
	public ProvaDraw1(JLabel label){
		
		this.label=label;
		
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		this.x=e.getX();
//		this.y=e.getY();
//		System.out.println("entra");
//		JButton b = icon.buildButton("10s.png");
//		label.add(b,x,y);
//		b.setVisible(true);
//		System.out.println(b.getLocation());
		System.out.println("fatto");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
