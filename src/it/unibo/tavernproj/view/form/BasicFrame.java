package it.unibo.tavernproj.view.form;

import it.unibo.tavernproj.view.utilities.GUIutilities;
import it.unibo.tavernproj.view.utilities.IGUIutilities;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * @author Eleonora Guidi
 *
 */
public class BasicFrame extends JFrame{
  
  private static final long serialVersionUID = 1L;
  private final transient IGUIutilities util = new GUIutilities();
  
  /**
   * Builds a new basic form with width and height half of the main view.
   */
  public BasicFrame() {
    super();
    setLocationByPlatform(true);
    this.setSize(util.getDefaultWidth() * 5 / 8, util.getDefaultHeight() * 3 / 4);
    this.setResizable(true);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setLayout(new BorderLayout());
    this.setVisible(true);
  }
}
