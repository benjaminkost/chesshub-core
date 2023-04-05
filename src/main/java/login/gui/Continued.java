package login.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 27.03.2023
 * @author 
 */

public class Continued extends JFrame {
  // Anfang Attribute
  private JLabel lLoggedinas = new JLabel();
  private JButton bLogout = new JButton();
  // Ende Attribute
  
  public Continued(String username) {
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300; 
    int frameHeight = 153;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("GUIs.Continued");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    bLogout.setBounds(8, 56, 264, 24);
    bLogout.setText("Logout");
    bLogout.setMargin(new Insets(2, 2, 2, 2));
    bLogout.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bLogout_ActionPerformed(evt);
      }
    });
    bLogout.setBackground(Color.WHITE);
    cp.add(bLogout);
    lLoggedinas.setBounds(8, 24, 264, 24);
    lLoggedinas.setText("Logged in as: " + username);
    lLoggedinas.setBackground(Color.WHITE);
    lLoggedinas.setOpaque(true);
    cp.add(lLoggedinas);
    
    cp.setBackground(Color.WHITE);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public GUIs.Continued
  
  // Anfang Methoden
  
  public void bLogout_ActionPerformed(ActionEvent evt) {
    new GUI();
    dispose();
    
  } // end of bLogout_ActionPerformed

  // Ende Methoden
} // end of class GUIs.Continued
