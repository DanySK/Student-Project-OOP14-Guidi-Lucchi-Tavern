package it.unibo.tavernproj.disegno;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;
import it.unibo.tavernproj.model.IModel;
import it.unibo.tavernproj.model.IUtilities;
import it.unibo.tavernproj.model.Model;
import it.unibo.tavernproj.model.Utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Map;

import javax.swing.JLabel;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawPosition implements MouseListener,Serializable,IDrawPosition {

  private static final long serialVersionUID = -2547031979468896800L;

  private final Map<Integer, Pair<Integer, Integer>> draw = DrawMap.getMap();
  private final JLabel label;
  private final IModel model = new Model();
  private final IUtilities util = new Utilities();
  private transient IController controller = Controller.getController();  
  private int x0 = 0;
  private int y0 = 0;

  public DrawPosition(JLabel label) {
    this.label = label;
  }

  @Override
  public void mouseClicked(MouseEvent e1) { }

  @Override
  public void mousePressed(MouseEvent e1) { }

  @Override
  public void mouseReleased(MouseEvent e1) {
    this.x0 = e1.getX();
    this.y0 = e1.getY();
    if (draw.size() <= model.getRes(util.getCurrentDate()).size()) {
      this.paint(label.getGraphics(),x0,y0);
    } else {
      controller.displayException("Non ci sono altri tavoli prenotati.");
    }
   
  }

  @Override
  public void mouseEntered(MouseEvent e1) { }

  @Override
  public void mouseExited(MouseEvent e1) { }

  @Override
  public void paint(Graphics gr, int x0, int y0) {
    gr.drawRect(x0, y0, 50, 50);
    gr.setColor(Color.black); 
    draw.put(draw.size(), new Pair<>(x0,y0));
  }
  
  @Override
  public void paintCancel(Graphics gr, int x0, int y0) {
    gr.setColor(label.getBackground());
    gr.drawRect(x0,y0, 50, 50);
  }
  
  @Override
  public void cancel(Graphics g1) {
    if (!draw.isEmpty()) {
      Pair<Integer, Integer> pt = draw.get(draw.size() - 1);
      this.paintCancel(label.getGraphics(), pt.getX(),pt.getY());
      draw.remove(draw.size() - 1);
    }
  }
  
  @Override
  public void cancelAll(Graphics g1) {
    for (int i : draw.keySet()) {
      Pair<Integer, Integer> pt = draw.get(i);
      this.paintCancel(label.getGraphics(), pt.getX(),pt.getY());
    }
    draw.clear();
  }

}
