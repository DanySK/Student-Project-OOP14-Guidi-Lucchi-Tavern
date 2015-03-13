package src;

import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author
 * 		Eleonora Guidi
 * 
 */

public interface IIconBuilder {
	
	/**
	 * Crea un pannello con sfondo bianco.
	 * 
	 * Creates a JPanel with a white background.
	 * 
	 * @param lm
	 * 		il layout che si vuole usare nel pannello.
	 * 		the layout you want to use in the JPanel.
	 * 
	 * @return
	 * 		il pannello creato.
	 * 		the JPanel created.
	 */
	JPanel buildPanel(LayoutManager lm);
	
	/**
	 * Crea una JLabel usando come sfondo l'immagine al percorso passato.
	 * It creates a new JLabel with the image passed as background.
	 *
	 * @param str
	 * 		il path dell'immagine.
	 * 		the image path.
	 * 
	 * @throws IllegalArgumentException
	 * 		se il percorso immesso e' errato.
	 * 		if you passed a wrong path.
	 * 
	 * @return
	 * 		la JLabel creata.
	 * 		the JLabel created.
	 */
	JLabel buildLabel(String str);
	
	/**
	 * Crea un bottne usando come sfondo l'immagine al percorso passato,
	 * sfondo bianco e senza borso.
	 * It creates a new JButton with the image passed as background, 
	 * white background base and no border.
	 *
	 * @param str
	 * 		il path dell'immagine.
	 * 		the image path.
	 * 
	 * @throws IllegalArgumentException
	 * 		se il percorso immesso e' errato.
	 * 		if you passed a wrong path.
	 * 
	 * @return
	 * 		il JButton creato.
	 * 		the JButton created.
	 */
	JButton buildButton(String str);	

}
