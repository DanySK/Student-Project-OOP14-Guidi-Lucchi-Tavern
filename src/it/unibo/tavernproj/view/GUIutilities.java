package it.unibo.tavernproj.view;

import it.unibo.tavernproj.controller.Controller;
import it.unibo.tavernproj.controller.IController;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Eleonora Guidi
 *
 */
public class GUIutilities extends BasicGUIutilities implements IGUIutilities {  

  private IController controller = Controller.getController();
  private JPanel res = super.getDefaultPanel(new FlowLayout());

  @Override
  public JLabel getDateLabel() {
    final JLabel date = new JLabel(super.getCurrentDate());
    date.setFont(new Font("Arial", Font.BOLD, 18));
    return date;
  }
  
/* Mi sono basata su questo http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
 */
  @Override
  public JButton getPicButton(final String srt) throws IOException {
    final JButton picButton = new JButton(this.getButtonIcon(srt));
    picButton.setBackground(Color.WHITE);
    picButton.setBorderPainted(false);
    return picButton;
  }

  private ImageIcon getButtonIcon(final String srt) throws IOException {
    final ImageIcon img = this.getImage(srt);
    final Image temp = img.getImage().getScaledInstance(super.getDefaultHeight() * 1 / 8,
        super.getDefaultHeight() * 1 / 8, Image.SCALE_SMOOTH);
    img.setImage(temp);
    return img;
  }
  
  @Override
  public JPanel buildGridPanel(final List<JComponent> list, final int ins) {
    final JPanel panel = this.getDefaultPanel(new GridBagLayout());
    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridy = 0;
    gap.insets = new Insets(ins, ins, ins, ins);
    gap.fill = GridBagConstraints.HORIZONTAL;
    for (final JComponent c: list) {
      panel.add(c, gap);
      gap.gridy++;
    }    
    return panel;
  }

  @Override
  public JPanel buildOrizzontalGridPanel(List<JComponent> list, int ins) {
    final JPanel panel = this.getDefaultPanel(new GridBagLayout());
    final GridBagConstraints gap = new GridBagConstraints();
    gap.gridx = 0;
    gap.insets = new Insets(ins, ins, ins, ins);
    gap.fill = GridBagConstraints.VERTICAL;
    for (final JComponent c: list) {
      panel.add(c, gap);
      gap.gridx++;
    }    
    return panel;
  }
  
  private void loadReserv(final String date) {
    for (final Integer i: controller.getReservation(date).keySet()) {
      this.add(new JLabel(controller.getReservation(date).get(i).toString()));       
    }
  }
  
  @Override
  public JPanel loadReservation(final String date) {
    this.loadReserv(date);
    res.add(this.buildGridPanel(this.getList(), 10));
    return res;
  }

  @Override
  public JPanel loadReservations() {
    for (final String s: controller.getDates()) {
      DateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
      try {
        if (s.equals(getCurrentDate()) 
            || sdf.parse(s).after(sdf.parse(getCurrentDate()))) {
          this.loadReserv(s);
        }
      } catch (ParseException e) {
        
      }      
    }
    res.add(this.buildGridPanel(this.getList(), 10));
    return res;
  }
}
