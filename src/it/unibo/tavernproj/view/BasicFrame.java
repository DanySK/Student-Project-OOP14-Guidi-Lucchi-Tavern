package it.unibo.tavernproj.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BasicFrame extends JFrame{
  
  private static final long serialVersionUID = 1L;
  private final IGUIutilities util = new GUIutilities();
  
  /**
   * Builds a new basic form with width and height half of the main view.
   */
  public BasicFrame() {
    super();
    setLocationByPlatform(true);
    this.setSize(util.getDefaultWidth() / 2, util.getDefaultHeight() / 2);
    this.setResizable(true);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    buildLayout();
    this.setVisible(true);
  }

  private void buildLayout() {
    this.getContentPane().setLayout(new BorderLayout());
  }
}
