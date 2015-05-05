package it.unibo.tavernproj.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Chooser extends JFrame{
  
  private JTextField name = new JTextField(20);
  private JButton ok = new JButton("OK");
  
  public Chooser(){
    this.setSize(500, 400);
    JButton bDate = new JButton("Scegli per data");
    bDate.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent arg0) {
        JFrame frame = new JFrame("Calendar");
        Calendar calendar = new Calendar(frame);
        while (!calendar.getPickedDate().equals("Error") && !calendar.isRight()) {
          //FARE una finestra al posto del messaggio stampato
          System.out.println("Selezionare una data utile");
          calendar = new Calendar(frame);
        }
        if (!calendar.getPickedDate().equals("Error")) {
          
          //FARE LA FORM CON L'ELENCO DELLE PRENOTAZIONI E LA POSSIBILITà DI CANCELLARE
          
        }
        
      }
      
    });
    
    
    JButton bPerson = new JButton("Scegli per nome");
    bPerson.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        name.setVisible(true);
        ok.setVisible(true);
        
      }
      
    }); 
    
    
    JPanel top = new JPanel(new FlowLayout());
    top.add(bDate);
    top.add(bPerson);   
    
    name.setVisible(false);
    
    ok.setVisible(false);
    ok.addActionListener(new ActionListener(){

      @Override
      public void actionPerformed(ActionEvent e) {
        
        // APRIRE UNA FORM CON L'ELENCO DEI CLIENTI FUTURI CON QUEL NOME E IL PULSANTE PER LA CANCELLAZIONE
        
      }
      
    });
    
    JPanel south = new JPanel(new FlowLayout());
    south.add(name);
    south.add(ok);
    
    JPanel main = new JPanel(new BorderLayout());
    main.add(top, BorderLayout.CENTER);
    main.add(south, BorderLayout.SOUTH);
    
    this.getContentPane().add(main);
    this.setVisible(true);
  }

}
