package it.unibo.tavernproj.view.disegno;

import it.unibo.tavernproj.model.disegno.DrawMap;
import it.unibo.tavernproj.model.disegno.Pair;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Map;

import javax.swing.JLabel;

/**
 *  @author Giulia Lucchi
 *
 */

public class DrawPosition implements Serializable,IDrawPosition {

  private static final long serialVersionUID = -2547031979468896800L;

  private final Map<Integer, Pair<Integer, Integer>> draw = DrawMap.getMap();
  private final JLabel label;
  
  
  public DrawPosition(JLabel label) {
    this.label = label;
  }

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

  /**
   * @return
   *      the number of tables on the map.
   */
  public int size() {
    return draw.keySet().size();
  }

}
