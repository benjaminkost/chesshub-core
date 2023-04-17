package login.deprecated.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 27.03.2023
 * @author  unknown
 */

public class GUI extends JFrame {
  // Anfang Attribute
  private JButton bLogin = new JButton();
  private JButton bSignUp = new JButton();
  // Ende Attribute
  
  public GUI() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 300;
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("GUIs.GUI");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten 
    bLogin.setBounds(48, 80, 192, 32);
    bLogin.setText("Login");
    bLogin.setMargin(new Insets(2, 2, 2, 2));
    bLogin.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bLogin_ActionPerformed(evt);
      }
    });
    bLogin.setBackground(Color.WHITE);

    cp.add(bLogin);
    
    cp.setBackground(Color.WHITE);
    bSignUp.setBounds(48, 128, 192, 32);
    bSignUp.setText("Sign Up");
    bSignUp.setMargin(new Insets(2, 2, 2, 2));
    bSignUp.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        bSignUp_ActionPerformed(evt);
      }
    });
    bSignUp.setBackground(Color.WHITE);
    cp.add(bSignUp);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public GUIs.GUI
  
  // Anfang Methoden
  
  public void bLogin_ActionPerformed(ActionEvent evt) {
    new LoginGUI();
    dispose();

  } // end of bLogin_ActionPerformed

  public void bSignUp_ActionPerformed(ActionEvent evt) {
    new SignUpGUI();
    dispose();

  } // end of bSignUp_ActionPerformed

  // Ende Methoden
} // end of class GUIs.GUI
