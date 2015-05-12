package it.unibo.tavernproj.view;

import java.awt.LayoutManager;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 */

public interface IGUIutilities {
  
  /**
   * Creates a JPanel with a white background.
   * 
   * @param lm
   *      the layout to use.
   *
   * @return
   *      the panel.
   */
  JPanel getDefaultPanel(LayoutManager lm);
  
  /**
   * It creates a new JLabel with the image passed as background.
   *
   * @param srt
   *      the image path.
   *      
   * @return
   *      the JLabel created.
   *      
   * @throws IOException
   *      if you passed a wrong path.
   */
  JLabel getDefaultLogo(String srt) throws IOException;
  
  /**
   * It creates a new JLabel with the image passed as background.
   *
   * @param srt
   *      the image path.
   *      
   * @return
   *      the JLabel created.
   *      
   * @throws IOException
   *      if you passed a wrong path.
   */
  JLabel getDefaultMap(String srt) throws IOException;
  
  /**
   * It creates a new JButton with the image passed as background, 
   * white background base and no border.
   *
   * @param srt
   *      the image path.
   *      
   * @return
   *      the button.
   *
   * @throws IOException
   *      if you passed a wrong path.
   */
  JButton getPicButton(String srt) throws IOException;
  
  /**
   * It creates a JLabel containing the current date.
   * 
   * @return
   *      the label.
   */
  JLabel getDateLabel();

  /**
   * It creates a new JButton with the string passed as text,
   * size as the int passed, white background and black border.
   * 
   * @param string
   *      the button text.
   * 
   * @param size
   *      the font size desired.
   *      
   * @return
   *      the button.
   */
  JButton getDefaultButton(String string, int size);
  
  /**
   * It creates a new JButton with the string passed as text,
   * white background and black border.
   * 
   * @param string
   *      the button text.
   *      
   * @return
   *      the button.
   */
  JButton getDefaultButton(String string);

  /**
   * @return
   *      the default screen width for the program.
   */
  int getDefaultWidth();

  /**
   * @return
   *      the default screen height for the program.
   */
  int getDefaultHeight();

  /**
   * It creates a new GridBagLayouted panel with all the components in the list,
   * one above the other.
   * 
   * @param list
   *      the JComponent list.
   * @param ins
   *      the insets between the components.
   * @return
   *      the panel.
   */
  JPanel buildGridPanel(final List<JComponent> list, final int ins);
  

}
