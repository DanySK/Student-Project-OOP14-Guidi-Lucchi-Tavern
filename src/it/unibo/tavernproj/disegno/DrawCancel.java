package it.unibo.tavernproj.disegno;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class DrawCancel implements MouseListener {

	private final JLabel label;
	private DrawTable table;
	private int x0=0;
	private int y0=0;
	
	public DrawCancel(JLabel label){
		this.label=label;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.x0=e.getX();
		this.y0=e.getY(); 
		table= new DrawTable(x0, y0);
		table.calcel(label.getGraphics(), e.getX(), e.getY());
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
	

}
