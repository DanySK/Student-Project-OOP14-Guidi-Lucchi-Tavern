package it.unibo.tavernproj.view;

import it.unibo.tavernproj.model.IUtilities;

import java.awt.LayoutManager;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 *
 */
public interface IBasicGUIutilities extends IUtilities {
  
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
   * Builds a new panel with the layout passed and the two components
   * @param lm
   *      the layout to use.
   * @param c1
   *      the first component.
   * @param c2
   *      the second component.
   * @return
   *      the panel.
   */
  JPanel getDefaultPanel(final LayoutManager lm, final JComponent c1, final JComponent c2);
  
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
  JLabel getDefaultMap(String srt);
  
  /**
   * It creates a new JButton with the string passed as text,
   * size as the integer passed, white background and black border.
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

  int getMapWidth();

  int getMapHeight();
}
