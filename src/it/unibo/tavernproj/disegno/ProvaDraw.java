package it.unibo.tavernproj.disegno;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RectangularShape;

import javax.swing.JLabel;

public class ProvaDraw extends JLabel implements MouseMotionListener {


	//private Rectangle[] rect;
	private int x;
	private int y;
	private int table=0;
	private JLabel label;
	DrawTable t = new DrawTable(x, y, label);
	
	public ProvaDraw(JLabel label){
		this.label=label;
	//	this.rect= new Rectangle[20];
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		Rectangle rett;
		this.x = e.getX();
		this.y = e.getY();
		rett=t.paintComponent((Graphics2D) label.getGraphics(),x,y);
		//System.out.println(rett);
		repaint();
		//label.computeVisibleRect(rett);
		

	}
	
	
	
	
}
