package it.unibo.tavernproj.disegno;

import java.awt.Color;
import java.awt.Graphics;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawTable implements IDrawTable{
	
	private int x;
	private int y;
	
	
	public DrawTable(int x, int y){
		this.x=x;
		this.y=y;
	}
	public void paintComponent(Graphics g) {
		
		g.fillRect(this.x, this.y, 50,50);
		g.setColor(Color.black); 
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
}
