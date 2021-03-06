package it.unibo.tavernproj.view.utilities;

import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 * 
 */
public interface IGUIutilities extends IBasicGUIutilities {
  
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
  
  /**
   * It builds a new GridBagLayouted panel with all the components in the list,
   * one next the other.
   * 
   * @param list
   *      the JComponent list.
   * @param ins
   *      the insets between the components.
   * @return
   *      the panel.
   */
  JPanel buildOrizzontalGridPanel(final List<JComponent> list, final int ins);
  
  /**
   * @return
   *      a panel containing all the reservations added.
   */
  JPanel loadReservations();
  
  /**
   * Loads all the reservation added for the date choosed.
   * 
   * @param date
   *      the date choosed.
   * @return
   *      the pael containig all the reservation on that date.
   */
  JPanel loadReservation(final String date);
}
