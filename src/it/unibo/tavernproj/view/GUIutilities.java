package it.unibo.tavernproj.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
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
}
