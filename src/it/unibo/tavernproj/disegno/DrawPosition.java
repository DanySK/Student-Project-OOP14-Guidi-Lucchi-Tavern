package it.unibo.tavernproj.disegno;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawPosition implements MouseListener {
	
	private final JLabel label;
	private DrawTable table;
	private int x0=0;
	private int y0=0;
	
	public DrawPosition(JLabel label){
		this.label=label;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.x0=e.getX();
		this.y0=e.getY(); 
		table= new DrawTable(x0, y0);
		table.paintComponent(label.getGraphics());
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
